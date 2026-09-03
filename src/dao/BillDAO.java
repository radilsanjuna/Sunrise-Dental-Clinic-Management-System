/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    // Add a new bill
    public boolean addBill(Bill bill) {

        String sql = "INSERT INTO bills "
                + "(bill_number, appointment_id, consultation_fee, "
                + "total_amount, payment_status, bill_date) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bill.getBillNumber());
            stmt.setInt(2, bill.getAppointmentId());
            stmt.setBigDecimal(3, bill.getConsultationFee());
            stmt.setBigDecimal(4, bill.getTotalAmount());
            stmt.setString(5, bill.getPaymentStatus());
            stmt.setObject(6, bill.getBillDate());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error adding bill: " + e.getMessage()
            );

            return false;
        }
    }

    // Search bill by bill number
    public Bill searchBill(String billNumber) {

        String sql = "SELECT b.*, "
                + "a.appointment_number, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "p.full_name AS patient_name, "
                + "p.phone_number, "
                + "u.full_name AS dentist_name, "
                + "t.treatment_name, "
                + "t.cost AS treatment_cost "
                + "FROM bills b "
                + "JOIN appointments a "
                + "ON b.appointment_id = a.appointment_id "
                + "JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "JOIN users u "
                + "ON d.user_id = u.user_id "
                + "JOIN treatments t "
                + "ON a.treatment_id = t.treatment_id "
                + "WHERE b.bill_number = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, billNumber);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Bill bill = new Bill();

                    bill.setBillId(
                            rs.getInt("bill_id")
                    );

                    bill.setBillNumber(
                            rs.getString("bill_number")
                    );

                    bill.setAppointmentId(
                            rs.getInt("appointment_id")
                    );

                    bill.setConsultationFee(
                            rs.getBigDecimal("consultation_fee")
                    );

                    bill.setTotalAmount(
                            rs.getBigDecimal("total_amount")
                    );

                    bill.setPaymentStatus(
                            rs.getString("payment_status")
                    );

                    bill.setBillDate(
                            rs.getObject(
                                    "bill_date",
                                    LocalDate.class
                            )
                    );

                    bill.setAppointmentNumber(
                            rs.getString("appointment_number")
                    );

                    bill.setAppointmentDate(
                            rs.getObject(
                                    "appointment_date",
                                    LocalDate.class
                            )
                    );

                    bill.setAppointmentTime(
                            rs.getString("appointment_time")
                    );

                    bill.setPatientName(
                            rs.getString("patient_name")
                    );

                    bill.setPhoneNumber(
                            rs.getString("phone_number")
                    );

                    bill.setDentistName(
                            rs.getString("dentist_name")
                    );

                    bill.setTreatmentName(
                            rs.getString("treatment_name")
                    );

                    bill.setTreatmentCost(
                            rs.getBigDecimal("treatment_cost")
                    );

                    return bill;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error searching bill: " + e.getMessage()
            );
        }

        return null;
    }

    // Get bill using appointment ID
    public Bill getBillByAppointmentId(int appointmentId) {

        String sql = "SELECT b.*, "
                + "a.appointment_number, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "p.full_name AS patient_name, "
                + "p.phone_number, "
                + "u.full_name AS dentist_name, "
                + "t.treatment_name, "
                + "t.cost AS treatment_cost "
                + "FROM bills b "
                + "JOIN appointments a "
                + "ON b.appointment_id = a.appointment_id "
                + "JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "JOIN users u "
                + "ON d.user_id = u.user_id "
                + "JOIN treatments t "
                + "ON a.treatment_id = t.treatment_id "
                + "WHERE b.appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return createBillFromResultSet(rs);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error finding bill: " + e.getMessage()
            );
        }

        return null;
    }

 // Get all bills
public List<Bill> getAllBills() {
    List<Bill> bills = new ArrayList<>();

    String sql = "SELECT b.*, "
            + "a.appointment_number, "
            + "a.appointment_date, "
            + "a.appointment_time, "
            + "p.full_name AS patient_name, "
            + "p.phone_number, "
            + "u.full_name AS dentist_name, "
            + "t.treatment_name, "
            + "t.cost AS treatment_cost "
            + "FROM bills b "
            + "JOIN appointments a "
            + "ON b.appointment_id = a.appointment_id "
            + "JOIN patients p "
            + "ON a.patient_id = p.patient_id "
            + "JOIN dentists d "
            + "ON a.dentist_id = d.dentist_id "
            + "JOIN users u "
            + "ON d.user_id = u.user_id "
            + "JOIN treatments t "
            + "ON a.treatment_id = t.treatment_id "
            + "ORDER BY b.bill_date DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            bills.add(createBillFromResultSet(rs));
        }

    } catch (Exception e) {
        System.out.println("Error loading bills: " + e.getMessage());
    }

    return bills;
}

    // Generate the next bill number
    public String generateBillNumber() {

        String sql = "SELECT MAX(bill_id) FROM bills";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int nextId = 1;

            if (rs.next()) {

                int maxId = rs.getInt(1);

                if (!rs.wasNull()) {
                    nextId = maxId + 1;
                }
            }

            return String.format(
                    "BILL-%03d",
                    nextId
            );

        } catch (Exception e) {

            System.out.println(
                    "Error generating bill number: "
                    + e.getMessage()
            );

            return null;
        }
    }

    // Create Bill object from ResultSet
    private Bill createBillFromResultSet(ResultSet rs)
            throws Exception {

        Bill bill = new Bill();

        bill.setBillId(
                rs.getInt("bill_id")
        );

        bill.setBillNumber(
                rs.getString("bill_number")
        );

        bill.setAppointmentId(
                rs.getInt("appointment_id")
        );

        bill.setConsultationFee(
                rs.getBigDecimal("consultation_fee")
        );

        bill.setTotalAmount(
                rs.getBigDecimal("total_amount")
        );

        bill.setPaymentStatus(
                rs.getString("payment_status")
        );

        bill.setBillDate(
                rs.getObject(
                        "bill_date",
                        LocalDate.class
                )
        );

        bill.setAppointmentNumber(
                rs.getString("appointment_number")
        );

        bill.setAppointmentDate(
                rs.getObject(
                        "appointment_date",
                        LocalDate.class
                )
        );

        bill.setAppointmentTime(
                rs.getString("appointment_time")
        );

        bill.setPatientName(
                rs.getString("patient_name")
        );

        bill.setPhoneNumber(
                rs.getString("phone_number")
        );

        bill.setDentistName(
                rs.getString("dentist_name")
        );

        bill.setTreatmentName(
                rs.getString("treatment_name")
        );

        bill.setTreatmentCost(
                rs.getBigDecimal("treatment_cost")
        );

        return bill;
    }
}
