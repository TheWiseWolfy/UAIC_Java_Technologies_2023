package com.unwise.lab7;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "postgres")
    private EntityManager em;


    public User findUserByUsernameAndPassword(String username, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void registerUser(String username, String password, User.Role role) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // Better to hash password before storing
        newUser.setRole(role);
        em.persist(newUser);
    }

}