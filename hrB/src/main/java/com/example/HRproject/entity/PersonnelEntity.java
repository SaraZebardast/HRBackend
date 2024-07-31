package com.example.HRproject.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personnel")
public class PersonnelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = false)
    private String maritalStatus;

    @Column(nullable = false, length = 11, unique = true)
    private long tckn;

    @Column
    private String educationStatus;

    @Column
    private String department;

    @Column
    private String position;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date terminationDate;

    @Column
    private String terminationReason;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public long getTckn() {
        return tckn;
    }


    public String getDepartment() {
        return department;
    }

    public String getEducationStatus() {
        return educationStatus;
    }

    public String getPosition() {
        return position;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setTckn(long tckn) {
        this.tckn = tckn;
    }


    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getTerminationReason() {
        return terminationReason;
    }

    public void setTerminationReason(String terminationReason) {
        this.terminationReason = terminationReason;
    }
}