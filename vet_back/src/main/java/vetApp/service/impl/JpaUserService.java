package vetApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vetApp.enumeration.Roles;
import vetApp.model.User;
import vetApp.repository.UserRepository;
import vetApp.service.UserService;
import vetApp.web.dto.UserChangePassword;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Finds a user by their ID.
     * @param id the ID of the user to find
     * @return an Optional containing the user if found, otherwise an empty Optional
     */
    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieves all users.
     * @return a list of all users
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Retrieves users with pagination.
     * @param pageNo the page number to retrieve
     * @return a page of users
     */
    @Override
    public Page<User> findAll(int pageNo) {
        return userRepository.findAll(PageRequest.of(pageNo,10));
    }

    /**
     * Saves a user to the repository. If no role is set, defaults to 'USER'.
     * @param user the user to save
     * @return the saved user
     */
    @Override
    public User save(User user) {
    	// Set role to 'USER' if none is provided
    	if(user.getRole() == null) {
    		user.setRole(Roles.USER);
    	}
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     * @param id the ID of the user to delete
     */
    @Override
    public void delete(Long id) {
    	userRepository.deleteById(id);
    }

    /**
     * Finds a user by their username.
     * @param korisnickoIme the username of the user to find
     * @return an Optional containing the user if found, otherwise an empty Optional
     */
    @Override
    public Optional<User> findbyUserName(String korisnickoIme) {
        return userRepository.findFirstByUserName(korisnickoIme);
    }

    /**
     * Changes a user's password.
     * @param id the ID of the user whose password is to be changed
     * @param userChangePasswordDTO the data transfer object containing old and new passwords
     * @return true if the password was successfully changed, otherwise false
     */
    @Override
    public boolean changePassword(Long id, UserChangePassword userChangePasswordDTO) {
        Optional<User> res = userRepository.findById(id);

        if(!res.isPresent()) {
            throw new EntityNotFoundException(); // Throw exception if user not found
        }

        User user = res.get();
 
        // Verify old password
        boolean passwordsMatch = BCrypt.checkpw(userChangePasswordDTO.getOldPassword(), user.getPassword());
        if(!user.getUserName().equals(userChangePasswordDTO.getUserName()) || !passwordsMatch){
            return false;
        }

        // Encode new password if provided
        String password = userChangePasswordDTO.getNewPassword();
        if (!userChangePasswordDTO.getNewPassword().equals("")) {
            password = passwordEncoder.encode(userChangePasswordDTO.getNewPassword());
        }

        user.setPassword(password);

        userRepository.save(user);

        return true;  // Return true if password was successfully updated
    }

}
