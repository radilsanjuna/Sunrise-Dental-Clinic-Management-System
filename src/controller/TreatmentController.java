/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TreatmentDAO;
import model.Treatment;

import java.util.List;

public class TreatmentController {

    private TreatmentDAO treatmentDAO;

    public TreatmentController() {
        treatmentDAO = new TreatmentDAO();
    }

    public boolean addTreatment(Treatment treatment) {
        return treatmentDAO.addTreatment(treatment);
    }

    public Treatment searchTreatment(int treatmentId) {
        return treatmentDAO.searchTreatment(treatmentId);
    }

    public boolean updateTreatment(Treatment treatment) {
        return treatmentDAO.updateTreatment(treatment);
    }

    public boolean deleteTreatment(int treatmentId) {
        return treatmentDAO.deleteTreatment(treatmentId);
    }

    public List<Treatment> getAllTreatments() {
        return treatmentDAO.getAllTreatments();
    }
}