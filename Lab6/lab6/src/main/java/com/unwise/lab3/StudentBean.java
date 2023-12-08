package com.unwise.lab3;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named("StudentBean")
@RequestScoped
public class StudentBean {

    @EJB
    private StudentRepository studentRepository;

    private List<Student> students;

    // Initialization method
    public void init() {
        // Load students when the bean is constructed
        students = studentRepository.getAllStudents();
    }

    // Getter method for students
    public List<Student> getStudents() {
        return students;
    }

    // Add other methods or actions as needed
}