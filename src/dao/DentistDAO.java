package dao;

import db.DBConnection;
import model.Dentist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DentistDAO {

    public boolean addDentist(Dentist dentist) {

        String sql = "INSERT INTO dentists "
                + "(user_id, specialization, phone_number) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dentist.getUserId());
            stmt.setString(2, dentist.getSpecialization());
            stmt.setString(3, dentist.getPhoneNumber());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error adding dentist: "
                    + e.getMessage());

            return false;
        }
    }

    public Dentist searchDentist(int dentistId) {

        String sql = "SELECT d.dentist_id, d.user_id, "
                + "u.full_name, d.specialization, d.phone_number "
                + "FROM dentists d "
                + "INNER JOIN users u ON d.user_id = u.user_id "
                + "WHERE d.dentist_id = ?";

        Dentist dentist = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dentistId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    dentist = new Dentist();

                    dentist.setDentistId(
                            rs.getInt("dentist_id")
                    );

                    dentist.setUserId(
                            rs.getInt("user_id")
                    );

                    dentist.setFullName(
                            rs.getString("full_name")
                    );

                    dentist.setSpecialization(
                            rs.getString("specialization")
                    );

                    dentist.setPhoneNumber(
                            rs.getString("phone_number")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println("Error searching dentist: "
                    + e.getMessage());
        }

        return dentist;
    }

    public boolean updateDentist(Dentist dentist) {

        String sql = "UPDATE dentists SET "
                + "specialization = ?, "
                + "phone_number = ? "
                + "WHERE dentist_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dentist.getSpecialization());
            stmt.setString(2, dentist.getPhoneNumber());
            stmt.setInt(3, dentist.getDentistId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error updating dentist: "
                    + e.getMessage());

            return false;
        }
    }

    public boolean deleteDentist(int dentistId) {

        String sql = "DELETE FROM dentists "
                + "WHERE dentist_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dentistId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error deleting dentist: "
                    + e.getMessage());

            return false;
        }
    }

    public List<Dentist> getAllDentists() {

        List<Dentist> dentistList = new ArrayList<>();

        String sql = "SELECT d.dentist_id, d.user_id, "
                + "u.full_name, d.specialization, d.phone_number "
                + "FROM dentists d "
                + "INNER JOIN users u ON d.user_id = u.user_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Dentist dentist = new Dentist();

                dentist.setDentistId(
                        rs.getInt("dentist_id")
                );

                dentist.setUserId(
                        rs.getInt("user_id")
                );

                dentist.setFullName(
                        rs.getString("full_name")
                );

                dentist.setSpecialization(
                        rs.getString("specialization")
                );

                dentist.setPhoneNumber(
                        rs.getString("phone_number")
                );

                dentistList.add(dentist);
            }

        } catch (SQLException e) {

            System.out.println("Error loading dentists: "
                    + e.getMessage());
        }

        return dentistList;
    }
}