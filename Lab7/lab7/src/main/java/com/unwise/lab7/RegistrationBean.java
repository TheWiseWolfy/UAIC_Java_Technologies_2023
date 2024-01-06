package com.unwise.lab7;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {

    @EJB
    private UserDAO userDAO;

    private User user;

    @PostConstruct
    public void init() {
        user = new User();
    }

    public String registerUser() {
        user.setRole(User.Role.TEACHER); // set role as TEACHER for registration
        userDAO.registerUser(user.getUsername(), user.getPassword(), user.getRole());
        return "login?faces-redirect=true"; // Redirect to login page after successful registration
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // getter and setter methods for 'user'
}