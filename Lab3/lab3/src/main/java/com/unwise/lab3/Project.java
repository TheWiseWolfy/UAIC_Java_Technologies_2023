package com.unwise.lab3;


import java.io.Serializable;
import java.sql.Date;

public class Project {
    private int projectId;
    private String projectName;
    private String projectDescription;
    private String projectCategory;
    private Date projectDeadline;

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