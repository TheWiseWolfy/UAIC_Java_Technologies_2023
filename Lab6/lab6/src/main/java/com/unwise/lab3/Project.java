package com.unwise.lab3;


import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "project") // Define the table name in the database
@NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_category")
    private String projectCategory;

    @Column(name = "project_deadline")
    private Date projectDeadline;

    @Column(name = "is_assigned")
    private boolean isAssigned;

    // Constructors
    public Project(){
        this.projectId = 0;
        this.projectName = "Name";
        this.projectDescription = "none";
        this.projectCategory = "yes";
        this.projectDeadline = new Date(0);
    }
    public Project(int projectId,String projectName, String projectDescription, String projectCategory, Date projectDeadline) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectCategory = projectCategory;
        this.projectDeadline = projectDeadline;
    }

    public Project(String projectName, String projectDescription, String projectCategory, Date projectDeadline) {
        this.projectId = -1;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectCategory = projectCategory;
        this.projectDeadline = projectDeadline;
    }

    public boolean isAssigned() {
        return isAssigned;
    }
    public void setAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    // Getters and setters
    public Integer getProjectId() {
        return projectId;
    }
    public void setProjectName(Integer projectId) {
        this.projectId = projectId;
    }

    public void setProjectId(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public Date getProjectDeadline() {
        return projectDeadline;
    }

    public void setProjectDeadline(Date projectDeadline) {
        this.projectDeadline = projectDeadline;
    }

    public void setProjectDeadlineUtilDate(Date projectDeadline) {
        this.projectDeadline = new java.sql.Date(projectDeadline.getTime());
    }

    public Date getProjectDeadlineUtilDate() {
        return new Date(projectDeadline.getTime());
    }



}