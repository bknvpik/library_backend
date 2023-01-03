package controllers;

import exceptions.UnauthorizedException;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import services.AuthService;
import services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public models.User login(@RequestBody String login) throws UnauthorizedException {

        System.out.println("AUTHCONTROLLER");

        models.User user;

        try {
            user = this.authService.getUserByUsername(login);
            System.out.println(user);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        return user;
    }
}
