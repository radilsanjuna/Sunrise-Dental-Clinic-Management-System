/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    
    public boolean addPatient(Patient patient) {

    String sql = "INSERT INTO patients " +
                 "(full_name, address, phone_number, dob, gender) " +
                 "VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, patient.getFullName());
        stmt.setString(2, patient.getAddress());
        stmt.setString(3, patient.getPhoneNumber());
        stmt.setDate(4, Date.valueOf(patient.getDateOfBirth()));
        stmt.setString(5, patient.getGender());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error adding patient: " + e.getMessage());
        return false;
    }
}
    
    
    
  public Patient getPatientByIdOrPhone(String searchText) {

    String sql = "SELECT * FROM patients "
            + "WHERE patient_id = ? "
            + "OR phone_number = ? "
            + "OR full_name LIKE ? "
            + "LIMIT 1";

    Patient patient = null;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, searchText);
        stmt.setString(2, searchText);
        stmt.setString(3, "%" + searchText + "%");

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                patient = new Patient();

                patient.setPatientId(rs.getInt("patient_id"));
                patient.setFullName(rs.getString("full_name"));
                patient.setAddress(rs.getString("address"));
                patient.setPhoneNumber(rs.getString("phone_number"));

                if (rs.getDate("dob") != null) {
                    patient.setDateOfBirth(rs.getDate("dob").toLocalDate());
                }

                patient.setGender(rs.getString("gender"));
            }
        }

    } catch (SQLException e) {
        System.out.println("Error searching patient: " + e.getMessage());
    }

    return patient;
}
   
    
    public boolean updatePatient(Patient patient) {

    String sql = "UPDATE patients SET " +
                 "full_name = ?, " +
                 "address = ?, " +
                 "phone_number = ?, " +
                 "dob = ?, " +
                 "gender = ? " +
                 "WHERE patient_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, patient.getFullName());
        stmt.setString(2, patient.getAddress());
        stmt.setString(3, patient.getPhoneNumber());
        stmt.setDate(4, Date.valueOf(patient.getDateOfBirth()));
        stmt.setString(5, patient.getGender());
        stmt.setInt(6, patient.getPatientId());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error updating patient: " + e.getMessage());
        return false;
    }
}
    
    
    public boolean deletePatient(int patientId) {

    String sql = "DELETE FROM patients WHERE patient_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, patientId);

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error deleting patient: " + e.getMessage());
        return false;
    }
}
    
// need to understand this how this work what is the pattern in here
    public List<Patient> getAllPatients() {

    List<Patient> patients = new ArrayList<>();

    String sql = "SELECT * FROM patients ORDER BY patient_id";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Patient patient = new Patient(
                rs.getInt("patient_id"),
                rs.getString("full_name"),
                rs.getString("address"),
                rs.getString("phone_number"),
                rs.getDate("dob").toLocalDate(),
                rs.getString("gender")
            );

            patients.add(patient);
        }

    } catch (SQLException e) {
        System.out.println("Error retrieving patients: " + e.getMessage());
    }

    return patients;
}
}
