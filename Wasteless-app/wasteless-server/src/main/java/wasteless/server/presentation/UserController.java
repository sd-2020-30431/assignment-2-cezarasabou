package wasteless.server.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wasteless.server.business.UserService;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.User;
import wasteless.server.presentation.dto.UserDTO;
import wasteless.server.presentation.mapper.UserMapper;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUSerById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(userMapper.convertToDTO(user));
    }

    @GetMapping("activeUser")
    public UserDTO getActiveUser() {
        User user = userService.getActiveUser();
        return userMapper.convertToDTO(user);
    }

    @PostMapping("user/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody User loginUser) {

        Optional<User> matchingUser = userService.loginUser(loginUser);

        return matchingUser.isPresent() ? ResponseEntity.ok(userMapper.convertToDTO(matchingUser.get())) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody User user) {
        return userMapper.convertToDTO(userService.createUser(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long userId,
                                                   @Valid @RequestBody User userDetails) throws ResourceNotFoundException{


        final User updatedUser = userService.updateUser(userId,userDetails);
        return ResponseEntity.ok(userMapper.convertToDTO(updatedUser));
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        userService.deleteUser(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
