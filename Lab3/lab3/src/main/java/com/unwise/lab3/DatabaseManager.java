package com.unwise.lab3;

import javax.naming.InitialContext;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseManager {

    private DataSource dataSource;

    public DatabaseManager() {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("postgres");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String insertProject( String projectName, String projectDescription, String projectCategory, Date projectDeadline) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO public.project ( project_name, project_description, project_category, project_deadline) " +
                    "VALUES ( ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, projectName);
                preparedStatement.setString(2, projectDescription);
                preparedStatement.setString(3, projectCategory);
                preparedStatement.setDate(4, projectDeadline);

                int rowCount = preparedStatement.executeUpdate();

                if (rowCount > 0) {
                    return "Project inserted successfully.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Project insertion failed.";
    }

    // Method to retrieve all projects from the database
    public ArrayList<Project> getAllProjects() {
        ArrayList<Project> projects = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT project_id,project_name, project_description, project_category, project_deadline FROM public.project";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Integer projectId = resultSet.getInt("project_id");
                    String projectName = resultSet.getString("project_name");
                    String projectDescription = resultSet.getString("project_description");
                    String projectCategory = resultSet.getString("project_category");
                    Date projectDeadline = resultSet.getDate("project_deadline");

                    Project project = new Project(projectId, projectName, projectDescription, projectCategory, projectDeadline);
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    //_______________________________________STUDENTS___________________________________
    public String insertStudent(String studentName) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO public.student (student_name) VALUES (?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, studentName);

                int rowCount = preparedStatement.executeUpdate();

                if (rowCount > 0) {
                    return "Student inserted successfully.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions or errors here
        }
        return "Student insertion failed.";
    }

    public String deleteStudent(int studentId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM public.student WHERE student_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, studentId);

                int rowCount = preparedStatement.executeUpdate();

                if (rowCount > 0) {
                    return "Student deleted successfully.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions or errors here
        }
        return "Student deletion failed.";
    }



    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT student_id, student_name FROM public.student";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int studentId = resultSet.getInt("student_id");
                    String studentName = resultSet.getString("student_name");

                    Student student = new Student(studentId, studentName);
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

}
