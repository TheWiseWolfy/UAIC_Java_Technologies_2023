package com.unwise.lab3;

import java.util.logging.Logger;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Named("ProjectRepository")
@SessionScoped
public class ProjectRepository implements Serializable {
    private static final Logger logger = Logger.getLogger(String.valueOf(ProjectRepository.class));

    @PersistenceContext
    private EntityManager entityManager;

    public List<Project> getProjects() {

        try {
            TypedQuery<Project> query = entityManager.createNamedQuery("Project.findAll", Project.class);

            // Log or perform any additional actions as needed
            logger.info("THE PROJECTS HAVE BEEN LOADED");

            return query.getResultList();
        } catch (Exception e) {
            // Log the exception or handle it as needed

            logger.info("Error loading projects");
            throw e;
            // Return an empty list or handle the error appropriately

        }
    }
}

