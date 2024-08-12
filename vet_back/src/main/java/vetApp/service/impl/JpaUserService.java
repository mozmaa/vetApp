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

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(int pageNo) {
        return userRepository.findAll(PageRequest.of(pageNo,10));
    }

    @Override
    public User save(User user) {
    	user.setRole(Roles.USER);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
    	userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findbyUserName(String korisnickoIme) {
        return userRepository.findFirstByUserName(korisnickoIme);
    }

    @Override
    public boolean changePassword(Long id, UserChangePassword userChangePasswordDTO) {
        Optional<User> res = userRepository.findById(id);

        if(!res.isPresent()) {
            throw new EntityNotFoundException();
        }

        User user = res.get();

        boolean passwordsMatch = BCrypt.checkpw(userChangePasswordDTO.getOldPassword(), user.getPassword());
        if(!user.getUserName().equals(userChangePasswordDTO.getUserName()) || !passwordsMatch){
            return false;
        }

        
        String password = userChangePasswordDTO.getNewPassword();
        if (!userChangePasswordDTO.getNewPassword().equals("")) {
            password = passwordEncoder.encode(userChangePasswordDTO.getNewPassword());
        }

        user.setPassword(password);

        userRepository.save(user);

        return true;
    }

}
