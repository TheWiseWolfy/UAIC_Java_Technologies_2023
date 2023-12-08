package com.unwise.lab3;

import java.util.logging.Logger;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

@Named("projectListBean")
@SessionScoped
public class ProjectListBean implements Serializable {
    private static final Logger logger = Logger.getLogger(String.valueOf(ProjectListBean.class));

    private Project newProject = new Project(); // Initialize a new project

    public Project getNewProject() {
        return newProject;
    }

    public void setNewProject(Project newProject) {
        this.newProject = newProject;
    }

    public ProjectListBean() {
        // Initialize the list with some data (you can add your own data here).
    }

    public List<Project> getProjects() {

        DatabaseManager db = new DatabaseManager();

        // Add more projects as needed.
        logger.info("THE PROJECTS HAVE BEEN LOADED");

        return db.getAllProjects();
    }

    public void addProject() {
        logger.info("Trying to add project.");
        DatabaseManager db = new DatabaseManager();
        String projectName = newProject.getProjectName();
        String projectDescription = newProject.getProjectDescription();
        String projectCategory = newProject.getProjectCategory();

        //Date conversion
        Date utilDate = newProject.getProjectDeadline();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        String message = db.insertProject(projectName, projectDescription, projectCategory, sqlDate);

        if (!message.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
        }

        newProject = new Project();
    }
}

