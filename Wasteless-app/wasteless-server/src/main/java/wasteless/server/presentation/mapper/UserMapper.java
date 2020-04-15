package wasteless.server.presentation.mapper;

import org.springframework.stereotype.Component;
import wasteless.server.model.User;
import wasteless.server.presentation.dto.UserDTO;

@Component
public class UserMapper {

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmailAddress(user.getEmailAddress());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
