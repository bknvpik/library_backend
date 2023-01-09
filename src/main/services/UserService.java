package services;

import entities.Book;
import entities.User;
import enums.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private ModelMapper mapper;
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public models.User getUserByUsername(String username) {

        return convertUserEntityToModel(userRepository.findUserByUsername(username));
    }

    public void addNewUser(User user) {
        Iterable<User> users = this.getAllUsers();
        AtomicBoolean exists = new AtomicBoolean(false);
        users.forEach(
                u -> {
                    if(Objects.equals(u.getUsername(), user.getUsername())) {
                        exists.set(true);
                    }
                }
        );
        if(!exists.get()) {
            userRepository.addNewUser(user.getUsername(), user.getPassword(), user.getRole());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid data provided");
        }
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteUserById(userId);
    }

    private models.User convertUserEntityToModel(entities.User user) {
        models.User userModel = new models.User();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        Role role = Role.valueOf(user.getRole());
        userModel.setRole(role);
        return userModel;
    }
}
