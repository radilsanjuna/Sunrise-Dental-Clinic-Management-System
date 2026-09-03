package dao;

import db.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO {

    // Get total number of patients
    public int getTotalPatients() {

        String sql = "SELECT COUNT(*) FROM patients";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(
                    "Error getting total patients: "
                    + e.getMessage()
            );
        }

        return 0;
    }

    // Get total number of dentists
    public int getTotalDentists() {

        String sql = "SELECT COUNT(*) FROM dentists";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(
                    "Error getting total dentists: "
                    + e.getMessage()
            );
        }

        return 0;
    }

    // Get today's appointments
    public int getTodayAppointments() {

        String sql =
                "SELECT COUNT(*) FROM appointments "
                + "WHERE appointment_date = CURDATE()";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(
                    "Error getting today's appointments: "
                    + e.getMessage()
            );
        }

        return 0;
    }

    // Get total revenue from paid bills
    public BigDecimal getTotalRevenue() {

        String sql =
                "SELECT COALESCE(SUM(total_amount), 0) "
                + "FROM bills "
                + "WHERE payment_status = 'Paid'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal(1);
            }

        } catch (Exception e) {
            System.out.println(
                    "Error getting total revenue: "
                    + e.getMessage()
            );
        }

        return BigDecimal.ZERO;
    }
}