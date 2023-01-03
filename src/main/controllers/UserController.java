package controllers;

import entities.Book;
import entities.User;
import exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<User> getUsers() throws UnauthorizedException {

        Iterable<User> users;

        try {
            users = this.userService.getAllUsers();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot get users");
        }
        return users;
    }

    @PostMapping(path = "/user/add", consumes = "application/json")
    public String addNewUser(@RequestBody() User newUser) {
        try {
            this.userService.addNewUser(newUser);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot add user");
        }
        return "User added!";
    }

    @DeleteMapping(path = "user/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        try {
            this.userService.deleteUserById(userId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot delete user");
        }
        return "User deleted!";
    }
}
