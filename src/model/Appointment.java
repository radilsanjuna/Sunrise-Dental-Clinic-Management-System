/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;


public class Appointment {
    
    private int appointmentId;
    private String appointmentNumber;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    
    
       // Relationships
    // check aii mewa damme kiyana eka
    private Patient patient;
    private Dentist dentist;
    private Treatment treatment;

       // Default constructor
    public Appointment() {
    }

    public Appointment(int appointmentId, String appointmentNumber, LocalDate appointmentDate, LocalTime appointmentTime, Patient patient, Dentist dentist, Treatment treatment) {
        this.appointmentId = appointmentId;
        this.appointmentNumber = appointmentNumber;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.dentist = dentist;
        this.treatment = treatment;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Treatment getTreatment() {
        return treatment;
   }

   public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
  }
    
    
    
}
