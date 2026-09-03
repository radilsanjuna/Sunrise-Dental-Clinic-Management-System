/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BillDAO;
import java.math.BigDecimal;
import model.Bill;

import java.util.List;

public class BillController {

    private BillDAO billDAO;

    public BillController() {
        billDAO = new BillDAO();
    }

    // Add a new bill
    public boolean addBill(Bill bill) {
        return billDAO.addBill(bill);
    }

    // Search bill by bill number
    public Bill searchBill(String billNumber) {
        return billDAO.searchBill(billNumber);
    }

    // Get bill using appointment ID
    public Bill getBillByAppointmentId(int appointmentId) {
        return billDAO.getBillByAppointmentId(appointmentId);
    }

    // Get all bills
    public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }

    // Generate next bill number
    public String generateBillNumber() {
        return billDAO.generateBillNumber();
    }
    public BigDecimal calculateTotal(BigDecimal treatmentCost, BigDecimal consultationFee) {
    if (treatmentCost == null || consultationFee == null) {
        return BigDecimal.ZERO;
    }

    return treatmentCost.add(consultationFee);
}
}