package com.unwise.lab3;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("studentListBean")
@SessionScoped
public class StudentListBean implements Serializable {
    private static final Logger logger = Logger.getLogger(String.valueOf(ProjectListBean.class));

    private Student newStudent;

    // Getters and setters
    public List<Student> getStudents() {

        DatabaseManager db = new DatabaseManager();

        // Add more projects as needed.
        logger.info("THE STUDENTS HAVE BEEN CREATED");

        return db.getAllStudents();
    }

    public void addStudent() {
        DatabaseManager db = new DatabaseManager();
        String message = db.insertStudent(newStudent.getStudentName());
        newStudent = new Student(1,"Wa"); // Clear the new student for the next addition

        if (!message.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
        }
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }
}
