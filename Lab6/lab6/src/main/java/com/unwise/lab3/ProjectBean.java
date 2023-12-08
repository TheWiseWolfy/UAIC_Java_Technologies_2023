package com.unwise.lab3;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named("ProjectBean")
@RequestScoped
public class ProjectBean {

    @EJB
    private ProjectRepository projectRepository;

    private List<Project> projects;

    @PostConstruct
    public void init() {
        // Load projects when the bean is constructed
        projects = projectRepository.getAllProjects();
    }

    public List<Project> getProjects() {
        return projects;
    }
}