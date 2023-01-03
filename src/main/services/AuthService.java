package services;

import models.User;
import enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }
}
