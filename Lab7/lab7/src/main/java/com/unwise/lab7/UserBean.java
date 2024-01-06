package com.unwise.lab7;


import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.logging.Logger;

@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private UserDAO userDAO;

    private User user;

    // Create a Logger
    private static final Logger LOGGER = Logger.getLogger(UserBean.class.getName());

    @PostConstruct
    public void init() {
        user = new User();
    }

    public String login() {

        LOGGER.info("Username: " + user.getUsername());
        LOGGER.info("Password: " + user.getPassword());

        User userFromDb = userDAO.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (userFromDb != null) {
            this.user = userFromDb; // update the current user with the complete user details from the database

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);

            return "dashboard?faces-redirect=true"; // Replace with your landing page after login
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Username or Password", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
    }

    //getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}