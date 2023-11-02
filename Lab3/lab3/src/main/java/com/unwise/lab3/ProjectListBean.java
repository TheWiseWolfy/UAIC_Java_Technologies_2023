package com.unwise.lab3;

import java.util.logging.Logger;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

@Named("projectListBean")
@SessionScoped
public class ProjectListBean implements Serializable {
    private List<Project> projects;
    private static final Logger logger = Logger.getLogger(String.valueOf(ProjectListBean.class));

    public ProjectListBean() {
        // Initialize the list with some data (you can add your own data here).
    }

    public List<Project> getProjects() {

        DatabaseManager db = new DatabaseManager();

        // Add more projects as needed.
        logger.info("THE PROJECTS HAVE BEEN CREATED");

        return db.getAllProjects();
    }

}