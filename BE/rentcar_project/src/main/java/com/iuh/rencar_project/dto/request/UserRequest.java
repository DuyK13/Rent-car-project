package com.iuh.rencar_project.dto.request;

import java.util.List;

/**
 * @author trant
 * @version 1.0
 * @date Apr 21, 2021
 */
public class UserRequest {

    private String username;

    private String email;

    private String password;

    private List<String> roles;

    public UserRequest(String username, String email, String password, List<String> roles) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserRequest() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
