package com.unwise.lab3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jdk.jfr.Name;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Name("navigationCoolBean")
@SessionScoped
public class NavigationBean implements Serializable {

    public NavigationBean(){

    }

    public String goToManagePage() {
        // Perform any necessary actions
        return "manage"; // Outcome to navigate to "manage.xhtml"
    }

    public String goToProjectsPage() {
        // Perform any necessary actions
        return "projects"; // Outcome to navigate to "projects.xhtml"
    }
}