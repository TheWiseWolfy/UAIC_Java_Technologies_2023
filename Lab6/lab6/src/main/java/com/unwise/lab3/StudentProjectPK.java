package com.unwise.lab3;

import java.io.Serializable;
import java.util.Objects;

public class StudentProjectPK implements Serializable {

    private int studentId;

    private int projectId;

    // Constructors, getters, setters...

    // Make sure to implement equals and hashCode methods
    // These methods are necessary for Hibernate to correctly handle the composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProjectPK that = (StudentProjectPK) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, projectId);
    }
}