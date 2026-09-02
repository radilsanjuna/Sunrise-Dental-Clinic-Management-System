package dao;

import db.DBConnection;
import model.Treatment;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO {

    public boolean addTreatment(Treatment treatment) {

        String sql = "INSERT INTO treatments "
                + "(treatment_name, description, cost) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, treatment.getTreatmentName());
            stmt.setString(2, treatment.getDescription());
            stmt.setBigDecimal(3, treatment.getCost());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding treatment: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public Treatment searchTreatment(int treatmentId) {

        String sql = "SELECT * FROM treatments "
                + "WHERE treatment_id = ?";

        Treatment treatment = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, treatmentId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    treatment = new Treatment();

                    treatment.setTreatmentId(
                            rs.getInt("treatment_id")
                    );

                    treatment.setTreatmentName(
                            rs.getString("treatment_name")
                    );

                    treatment.setDescription(
                            rs.getString("description")
                    );

                    treatment.setCost(
                            rs.getBigDecimal("cost")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error searching treatment: "
                    + e.getMessage()
            );
        }

        return treatment;
    }

    public boolean updateTreatment(Treatment treatment) {

        String sql = "UPDATE treatments SET "
                + "treatment_name = ?, "
                + "description = ?, "
                + "cost = ? "
                + "WHERE treatment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, treatment.getTreatmentName());
            stmt.setString(2, treatment.getDescription());
            stmt.setBigDecimal(3, treatment.getCost());
            stmt.setInt(4, treatment.getTreatmentId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating treatment: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public boolean deleteTreatment(int treatmentId) {

        String sql = "DELETE FROM treatments "
                + "WHERE treatment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, treatmentId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting treatment: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public List<Treatment> getAllTreatments() {

        List<Treatment> treatmentList = new ArrayList<>();

        String sql = "SELECT * FROM treatments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Treatment treatment = new Treatment();

                treatment.setTreatmentId(
                        rs.getInt("treatment_id")
                );

                treatment.setTreatmentName(
                        rs.getString("treatment_name")
                );

                treatment.setDescription(
                        rs.getString("description")
                );

                treatment.setCost(
                        rs.getBigDecimal("cost")
                );

                treatmentList.add(treatment);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading treatments: "
                    + e.getMessage()
            );
        }

        return treatmentList;
    }
}