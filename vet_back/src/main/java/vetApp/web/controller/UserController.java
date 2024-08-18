package vetApp.web.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vetApp.model.User;
import vetApp.security.TokenUtils;
import vetApp.service.UserService;
import vetApp.support.UserDTOToUser;
import vetApp.support.UserToUserDTO;
import vetApp.web.dto.AuthUserDTO;
import vetApp.web.dto.UserChangePassword;
import vetApp.web.dto.UserDTO;
import vetApp.web.dto.UserRegistrationDTO;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOToUser toUser;

    @Autowired
    private UserToUserDTO toUserDTO;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new User entity based on the provided UserRegistrationDTO.
     * 
     * @param dto the UserRegistrationDTO containing user registration details.
     * @return a ResponseEntity containing the created UserDTO.
     */
    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Validated UserRegistrationDTO dto){

        // Validate that the ID is null and passwords match
        if(dto.getId() != null || !dto.getNewPassword().equals(dto.getRepeatedPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Convert DTO to User entity and encode password
        User user = toUser.convert(dto);
        String encodedPassword = passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(encodedPassword);

        // Save user and return the created UserDTO
        return new ResponseEntity<>(toUserDTO.convert(userService.save(user)), HttpStatus.CREATED);
    }

    
    /**
     * Updates an existing User entity based on the provided UserDTO.
     * 
     * @param id the ID of the user to update.
     * @param userDTO the UserDTO containing updated user information.
     * @return a ResponseEntity containing the updated UserDTO.
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO){

    	// Check if the provided ID matches the ID in the DTO
        if(!id.equals(userDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Convert DTO to User entity 
        User user = toUser.convert(userDTO);

        // Save User entity and return updated UserDTO
        return new ResponseEntity<>(toUserDTO.convert(userService.save(user)),HttpStatus.OK);
    }

    /**
     * Retrieves a User entity by its ID.
     * 
     * @param id the ID of the user to retrieve.
     * @return a ResponseEntity containing the UserDTO if found, or NOT_FOUND if not.
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id){

    	Optional<User> user = userService.findOne(id);

    	 // Return the UserDTO if found, or NOT_FOUND if not
        if(user.isPresent()) {
            return new ResponseEntity<>(toUserDTO.convert(user.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves a paginated list of UserDTOs.
     * 
     * @param page the page number for pagination (default is 0).
     * @return a ResponseEntity containing a list of UserDTOs.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> get(@RequestParam(defaultValue="0") int page){
        Page<User> users = userService.findAll(page);
        return new ResponseEntity<>(toUserDTO.convert(users.getContent()), HttpStatus.OK);
    }

    
    /**
     * Changes the password of a User entity.
     * 
     * @param id the ID of the user whose password is to be changed.
     * @param dto the UserChangePassword DTO containing new password details.
     * @return a ResponseEntity indicating the result of the password change.
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value="/{id}", method = RequestMethod.PUT, params = "passwordChange")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody UserChangePassword dto){

    	// Validate that the new password matches the repeated password
        if(!dto.getNewPassword().equals(dto.getRepeatedPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Attempt to change the password and return appropriate response
        boolean res;
        try {
        	res = userService.changePassword(id, dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(res) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    /**
     * Authenticates a user and returns a JWT token if successful.
     * 
     * @param dto the AuthUserDTO containing authentication credentials.
     * @return a ResponseEntity containing the JWT token if authentication is successful, or NOT_FOUND if the user is not found.
     */
    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody AuthUserDTO dto) {
    	
    	// Create authentication token and authenticate
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Generate and return JWT token if user details are loaded successfully
        try {
            // Reload user details so we can generate token
	        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUserName());
	        return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
