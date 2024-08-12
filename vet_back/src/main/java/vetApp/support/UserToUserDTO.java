package vetApp.support;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vetApp.model.User;
import vetApp.web.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToUserDTO implements Converter<User, UserDTO>{


    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.seteMail(user.geteMail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUserName(user.getUserName());

        return userDTO;
    }

    public List<UserDTO> convert(List<User> users){
        List<UserDTO> userDTOS = new ArrayList<>();

        for(User u : users) {
            UserDTO dto = convert(u);
            userDTOS.add(dto);
        }

        return userDTOS;
    }
}
