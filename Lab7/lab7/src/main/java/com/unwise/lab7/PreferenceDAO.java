package com.unwise.lab7;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PreferenceDAO {

    @PersistenceContext
    private EntityManager em;

    public void savePreference(Preference preference) {
        if (preference.getId() == null) {
            // Persist new Preference
            em.persist(preference);
        } else {
            // Update existing Preference
            em.merge(preference);
        }
    }
}