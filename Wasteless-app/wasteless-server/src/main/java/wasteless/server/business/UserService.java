package wasteless.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.User;
import wasteless.server.persistance.UserRepository;
import wasteless.server.presentation.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) throws ResourceNotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return user;
    }
    public User getActiveUser() {
        return userRepository.findFirstByActiveTrue();
    }

    //loc pentru login

    public Optional<User> loginUser(User loginUser){
        List<User> allUsers = userRepository.findAll();

        Optional<User> matchingUser = allUsers
                .stream()
                .filter(user -> user.getEmailAddress().equals(loginUser.getEmailAddress()) &&
                        user.getPassword().equals(loginUser.getPassword()))
                .findFirst();
        if (matchingUser.isPresent()) {
            User user = matchingUser.get();
            user.setActive(true);
            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User userDetails) throws ResourceNotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setEmailAddress(userDetails.getEmailAddress());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        final User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    public void deleteUser(Long userId) throws ResourceNotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
    }
}
