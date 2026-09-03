package model;

//LocalDate allows Java to properly work with dates.
import java.time.LocalDate;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Radil_Sanjuna
 */
public class Patient {
    private int patientId;
private String fullName;
private String address;
private String phoneNumber;
private LocalDate dateOfBirth;
private String gender;

public Patient() {
}

public Patient(int patientId, String fullName, String address, String phoneNumber, LocalDate dateOfBirth, String gender) {
    this.patientId = patientId;
    this.fullName = fullName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
}

public Patient(String fullName, String address, String phoneNumber, LocalDate dateOfBirth, String gender) {
    this.fullName = fullName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
}

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }




}
