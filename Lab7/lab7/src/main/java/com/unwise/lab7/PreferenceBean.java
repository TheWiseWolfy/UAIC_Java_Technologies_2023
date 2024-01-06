package com.unwise.lab7;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Named("preferenceBean")
@SessionScoped
public class PreferenceBean implements Serializable {

    @EJB
    private PreferenceDAO preferenceDAO; // Assume this DAO exists to handle Preference persistence

    private Preference preference;
    private String startHourStr;
    private String endHourStr;

    @PostConstruct
    public void init() {
        preference = new Preference();
    }

    public String submitPreference() {

        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");



        if(user == null) {
            // Handle scenario when there is no user logged in.
            // Perhaps redirect to login page.
            return "login?faces-redirect=true";

        } else {
            // Assign user to the preference
            preference.setUser(user);
        }

        String registrationNumber = UUID.randomUUID().toString();
        preference.setRegistrationNumber(registrationNumber);

        LocalDateTime currentTime = LocalDateTime.now();
        preference.setSubmissionTimestamp(currentTime);

        preferenceDAO.savePreference(preference);
        return "success";
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public Preference.Day[] getDays() {
        return Preference.Day.values();
    }

    public String getStartHourStr(){
        return startHourStr;
    }
    public String getEndHourStr(){
        return endHourStr;
    }

    public void setStartHourStr(String startHourStr){
        LocalTime startHour = LocalTime.parse(startHourStr, DateTimeFormatter.ISO_TIME);
        this.preference.setStartHour(startHour);
    }

    public void setEndHourStr(String endHourStr) {
        LocalTime endHour = LocalTime.parse(endHourStr, DateTimeFormatter.ISO_TIME);
        this.preference.setEndHour(endHour);
    }
}
