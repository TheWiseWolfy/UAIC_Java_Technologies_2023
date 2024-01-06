package com.unwise.lab7;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "public.preference")
public class Preference {

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false)
    private Day day;

    @Column(name = "start_hour", nullable = false)
    private LocalTime startHour;

    @Column(name = "end_hour", nullable = false)
    private LocalTime endHour;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "submission_timestamp", nullable = false)
    private LocalDateTime submissionTimestamp;

    public Preference() {}

    public Preference(Long id, User user, Day day, LocalTime startHour, LocalTime endHour, String registrationNumber, LocalDateTime submissionTimestamp) {
        this.id = id;
        this.user = user;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.registrationNumber = registrationNumber;
        this.submissionTimestamp = submissionTimestamp;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDateTime getSubmissionTimestamp() {
        return submissionTimestamp;
    }

    public void setSubmissionTimestamp(LocalDateTime submissionTimestamp) {
        this.submissionTimestamp = submissionTimestamp;
    }

}