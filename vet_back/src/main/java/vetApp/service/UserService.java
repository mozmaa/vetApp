package vetApp.service;

import org.springframework.data.domain.Page;

import vetApp.model.User;
import vetApp.web.dto.UserChangePassword;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findOne(Long id);

    List<User> findAll();

    Page<User> findAll(int pageNo);

    User save(User user);

    void delete(Long id);

    Optional<User> findbyUserName(String userName);

    boolean changePassword(Long id, UserChangePassword UserChangePasswordDTO);
}
