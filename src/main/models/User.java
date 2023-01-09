package models;

import enums.Role;

public class User {
    protected Long id;
    protected String username;
    protected String password;
    protected Role role;

    public User() {

    }

    public User(Long id, String login, String pass) {
        this.id = id;
        this.username = login;
        this.password = pass;
        this.role = Role.USER;
    }

    public User(Long id, String login, String pass, Role role) {
        this.id = id;
        this.username = login;
        this.password = pass;
        this.role = role;
    }

    public User(entities.User user) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
