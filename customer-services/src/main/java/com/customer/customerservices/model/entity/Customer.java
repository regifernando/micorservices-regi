package com.customer.customerservices.model.entity;

/*
IntelliJ IDEA 2023.2.3 (Ultimate Edition)
Build #IU-232.10072.27, built on October 11, 2023
@Author Admin a.k.a. Regi Fernando
Java Developer
Created on 12/3/2023 11:00 PM
@Last Modified 12/3/2023 11:00 PM
Version 1.0
*/


import com.customer.customerservices.Helper.Helper;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String firstName;
    @Column(length = 100, nullable = false)
    private String lastName;
    private Character gender;
    private Date birthDate;
    @Column(length = 200, nullable = false, unique = true)
    private String email;
    @Column(length = 200, nullable = false)
    private String ktpNumber;
    private String historyOfIlness;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKtpNumber() {
        return ktpNumber;
    }

    public void setKtpNumber(String ktpNumber) {
        this.ktpNumber = ktpNumber;
    }

    public String getHistoryOfIlness() {
        return historyOfIlness;
    }

    public void setHistoryOfIlness(String historyOfIlness) {
        this.historyOfIlness = historyOfIlness;
    }
}
