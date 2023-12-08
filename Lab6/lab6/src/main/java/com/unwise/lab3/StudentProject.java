package com.unwise.lab3;

import jakarta.persistence.*;

@Entity
@Table(name = "student_projects")
@IdClass(StudentProjectPK.class)
public class StudentProject {

    @Id
    @JoinColumn(name = "student_id")
    private int studentId;

    @Id
    @JoinColumn(name = "project_id")
    private int projectId;

    public int getStudent() {
        return studentId;
    }

    public void setStudent(int student) {
        this.studentId = student;
    }

    public int getProject() {
        return projectId;
    }

    public void setProject(int project) {
        this.projectId = project;
    }

}
