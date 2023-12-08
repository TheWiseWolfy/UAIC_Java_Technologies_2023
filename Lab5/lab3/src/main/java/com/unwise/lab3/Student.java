package com.unwise.lab3;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
        @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.studentId = :studentId"),
        @NamedQuery(name = "Student.findByStudentName", query = "SELECT s FROM Student s WHERE s.studentName = :studentName")
})
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "student_name")
    private String studentName;

    // Constructor
    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public Student() {
        this.studentId = -1;
        this.studentName = "Arnold";
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    // Setters (if needed)
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}