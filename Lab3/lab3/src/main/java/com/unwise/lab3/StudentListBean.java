package com.unwise.lab3;


import jakarta.annotation.PostConstruct;
import jakarta.el.MethodExpression;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("studentListBean")
@SessionScoped
public class StudentListBean implements Serializable {
    private static final Logger logger = Logger.getLogger(String.valueOf(ProjectListBean.class));

    private Student newStudent;

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    public StudentListBean() {
        newStudent = new Student();
    }

    // Getters and setters
    public ArrayList<Student> getStudents() {

        DatabaseManager db = new DatabaseManager();

        // Add more projects as needed.
        logger.info("THE STUDENTS HAVE BEEN LOADED");

        return db.getAllStudents();
    }

    public void addStudent() {
        DatabaseManager db = new DatabaseManager();
        String message = db.insertStudent(newStudent.getStudentName());

        newStudent = new Student(); // Clear the new student for the next addition

        if (!message.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
        }
    }


    public void deleteStudent(int studentId) {
        // Delete the student based on the ID
        DatabaseManager db = new DatabaseManager();
        String message = db.deleteStudent(studentId);

        // Update the student list after deletion
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formName:objectId");

        if (!message.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
        }
    }
}
