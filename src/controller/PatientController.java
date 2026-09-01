/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PatientDAO;
import model.Patient;
import java.util.List;

public class PatientController {

    // aii patientDAO methana haduwe private
    private PatientDAO patientDAO;

    public PatientController() {
        patientDAO = new PatientDAO();
    }

    // Register a new patient
    public boolean registerPatient(Patient patient) {
        return patientDAO.addPatient(patient);
    }

    // Search for a patient by ID
public Patient searchPatientByIdOrPhone(String searchText) {
    return patientDAO.getPatientByIdOrPhone(searchText);
}

    // Update patient details
    public boolean updatePatient(Patient patient) {
        return patientDAO.updatePatient(patient);
    }

    // Delete a patient
    public boolean deletePatient(int patientId) {
        return patientDAO.deletePatient(patientId);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }
}