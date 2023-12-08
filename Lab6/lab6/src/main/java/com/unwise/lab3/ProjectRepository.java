package com.unwise.lab3;

import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ProjectRepository implements Serializable {
    private static final Logger logger = Logger.getLogger(String.valueOf(ProjectRepository.class));

    @PersistenceContext
    private EntityManager entityManager;

    public void updateAssignment(int projectId, boolean isAssigned) {
        Project project = entityManager.find(Project.class, projectId);
        if (project != null) {
            project.setAssigned(isAssigned);
            entityManager.merge(project);
        }
    }

    public boolean isProjectAvailable(int projectId) {
        Project project = entityManager.find(Project.class, projectId);

        // Assuming 'isAssigned' is a field indicating project availability
        return project != null && !project.isAssigned();
    }

    public Project findProjectById(int projectId) {
        return entityManager.find(Project.class, projectId);
    }

    public List<Project> getAllProjects() {
        Query query = entityManager.createNamedQuery("Project.findAll");
        return query.getResultList();
    }

    // Add other methods for CRUD operations

    public void createProject(Project project) {
        entityManager.persist(project);
    }

    public void updateProject(Project project) {
        entityManager.merge(project);
    }

    public void deleteProject(int projectId) {
        Project project = entityManager.find(Project.class, projectId);
        if (project != null) {
            entityManager.remove(project);
        }
    }


}

