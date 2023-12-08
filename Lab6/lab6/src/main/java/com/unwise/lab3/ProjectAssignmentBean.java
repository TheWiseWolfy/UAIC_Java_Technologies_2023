package com.unwise.lab3;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.*;

import java.util.List;

@Named("ProjectAssignmentBean")
@RequestScoped
public class ProjectAssignmentBean {
    @EJB
    private ProjectRepository projectRepository;

   // @EJB
    //private UserTransaction userTransaction;
    @PersistenceContext
    private EntityManager entityManager;

    public void assignProjectsToStudent(int studentId) throws SystemException, HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException {
        List<Project> allProjects = projectRepository.getAllProjects();

        try {
            //userTransaction.begin();

            for (Project project : allProjects) {
                if (projectRepository.isProjectAvailable(project.getProjectId())) {
                    StudentProject studentProject = new StudentProject();

                    studentProject.setStudent(studentId);
                    studentProject.setProject(project.getProjectId());

                    entityManager.persist(studentProject);

                    projectRepository.updateAssignment(project.getProjectId(), true);
                }
            }

           // userTransaction.commit();
        } catch (Exception e) {
           // userTransaction.rollback();
            throw e;
            // Handle the original exception
        }
    }
}
