package com.unwise.lab3;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class StudentRepository implements Serializable {
    private static final Logger logger = Logger.getLogger(String.valueOf(ProjectRepository.class));

    @PersistenceContext
    private EntityManager entityManager;

    // Getters and setters
    public List<Student> getAllStudents() {
        try {
            TypedQuery<Student> query = entityManager.createNamedQuery("Student.findAll", Student.class);

            // Log or perform any additional actions as needed
            logger.info("THE STUDENTS HAVE BEEN LOADED");

            return query.getResultList();
        } catch (Exception e) {
            // Log the exception or handle it as needed
            logger.info("Error loading students");

            // Return an empty list or handle the error appropriately
            return Collections.emptyList();
        }
    }

    public Student findStudentById(int studentId) {
        TypedQuery<Student> query = entityManager.createNamedQuery("Student.findById", Student.class);
        query.setParameter("studentId", studentId);
        List<Student> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Student findStudentByStudentName(String studentName) {
        TypedQuery<Student> query = entityManager.createNamedQuery("Student.findByStudentName", Student.class);
        query.setParameter("studentName", studentName);
        List<Student> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    public void deleteStudent(int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        if (student != null) {
            entityManager.remove(student);
        }
    }



}


