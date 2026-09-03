/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Bill;

public class RevenueReportDAO {

    // Get bills between two dates
    public List<Bill> getRevenueReport(
            String fromDate,
            String toDate) {

        List<Bill> bills = new ArrayList<>();

        String sql =
                "SELECT b.bill_id, "
                + "b.bill_number, "
                + "b.appointment_id, "
                + "b.consultation_fee, "
                + "b.total_amount, "
                + "b.payment_status, "
                + "b.bill_date, "
                + "a.appointment_number, "
                + "p.patient_name, "
                + "p.phone_number, "
                + "u.full_name AS dentist_name, "
                + "t.treatment_name, "
                + "t.cost AS treatment_cost, "
                + "a.appointment_date, "
                + "a.appointment_time "
                + "FROM bills b "
                + "INNER JOIN appointments a "
                + "ON b.appointment_id = a.appointment_id "
                + "INNER JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "INNER JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "INNER JOIN users u "
                + "ON d.user_id = u.user_id "
                + "INNER JOIN treatments t "
                + "ON a.treatment_id = t.treatment_id "
                + "WHERE b.bill_date BETWEEN ? AND ? "
                + "ORDER BY b.bill_date DESC";

        try (Connection conn =
                     DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setString(1, fromDate);
            stmt.setString(2, toDate);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

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
                            rs.getBigDecimal(
                                    "consultation_fee"
                            )
                    );

                    bill.setTotalAmount(
                            rs.getBigDecimal(
                                    "total_amount"
                            )
                    );

                    bill.setPaymentStatus(
                            rs.getString(
                                    "payment_status"
                            )
                    );

                    bill.setBillDate(
                            rs.getDate("bill_date")
                                    .toLocalDate()
                    );

                    bill.setAppointmentNumber(
                            rs.getString(
                                    "appointment_number"
                            )
                    );

                    bill.setPatientName(
                            rs.getString(
                                    "patient_name"
                            )
                    );

                    bill.setPhoneNumber(
                            rs.getString(
                                    "phone_number"
                            )
                    );

                    bill.setDentistName(
                            rs.getString(
                                    "dentist_name"
                            )
                    );

                    bill.setTreatmentName(
                            rs.getString(
                                    "treatment_name"
                            )
                    );

                    bill.setTreatmentCost(
                            rs.getBigDecimal(
                                    "treatment_cost"
                            )
                    );

                    bill.setAppointmentDate(
                            rs.getDate(
                                    "appointment_date"
                            ).toLocalDate()
                    );

                    bill.setAppointmentTime(
                            rs.getTime(
                                    "appointment_time"
                            ).toLocalTime().toString()
                    );

                    bills.add(bill);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting revenue report: "
                    + e.getMessage()
            );
        }

        return bills;
    }

    // Calculate total paid revenue
    public BigDecimal getTotalPaidRevenue(
            String fromDate,
            String toDate) {

        String sql =
                "SELECT COALESCE(SUM(total_amount), 0) "
                + "FROM bills "
                + "WHERE payment_status = 'Paid' "
                + "AND bill_date BETWEEN ? AND ?";

        try (Connection conn =
                     DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setString(1, fromDate);
            stmt.setString(2, toDate);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getBigDecimal(1);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error calculating revenue: "
                    + e.getMessage()
            );
        }

        return BigDecimal.ZERO;
    }
}