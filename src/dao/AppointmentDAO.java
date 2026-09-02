package dao;

import db.DBConnection;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // Add a new appointment
    public boolean addAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments "
                + "(appointment_number, patient_id, dentist_id, "
                + "treatment_id, appointment_date, appointment_time, notes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, appointment.getAppointmentNumber());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setInt(3, appointment.getDentistId());
            stmt.setInt(4, appointment.getTreatmentId());
            stmt.setDate(
                    5,
                    java.sql.Date.valueOf(
                            appointment.getAppointmentDate()
                    )
            );
            stmt.setTime(
                    6,
                    java.sql.Time.valueOf(
                            appointment.getAppointmentTime()
                    )
            );
            stmt.setString(7, appointment.getNotes());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding appointment: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // Search appointment by appointment number
    public Appointment searchAppointment(String appointmentNumber) {

        String sql = "SELECT "
                + "a.appointment_id, "
                + "a.appointment_number, "
                + "a.patient_id, "
                + "a.dentist_id, "
                + "a.treatment_id, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "a.notes, "
                + "p.full_name AS patient_name, "
                + "u.full_name AS dentist_name, "
                + "t.treatment_name "
                + "FROM appointments a "
                + "INNER JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "INNER JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "INNER JOIN users u "
                + "ON d.user_id = u.user_id "
                + "INNER JOIN treatments t "
                + "ON a.treatment_id = t.treatment_id "
                + "WHERE a.appointment_number = ?";

        Appointment appointment = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, appointmentNumber);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    appointment = new Appointment();

                    appointment.setAppointmentId(
                            rs.getInt("appointment_id")
                    );

                    appointment.setAppointmentNumber(
                            rs.getString("appointment_number")
                    );

                    appointment.setPatientId(
                            rs.getInt("patient_id")
                    );

                    appointment.setDentistId(
                            rs.getInt("dentist_id")
                    );

                    appointment.setTreatmentId(
                            rs.getInt("treatment_id")
                    );

                    appointment.setAppointmentDate(
                            rs.getDate("appointment_date")
                                    .toLocalDate()
                    );

                    appointment.setAppointmentTime(
                            rs.getTime("appointment_time")
                                    .toLocalTime()
                    );

                    appointment.setNotes(
                            rs.getString("notes")
                    );

                    appointment.setPatientName(
                            rs.getString("patient_name")
                    );

                    appointment.setDentistName(
                            rs.getString("dentist_name")
                    );

                    appointment.setTreatmentName(
                            rs.getString("treatment_name")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error searching appointment: "
                    + e.getMessage()
            );
        }

        return appointment;
    }

    // Update an existing appointment
    public boolean updateAppointment(Appointment appointment) {

        String sql = "UPDATE appointments SET "
                + "patient_id = ?, "
                + "dentist_id = ?, "
                + "treatment_id = ?, "
                + "appointment_date = ?, "
                + "appointment_time = ?, "
                + "notes = ? "
                + "WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDentistId());
            stmt.setInt(3, appointment.getTreatmentId());

            stmt.setDate(
                    4,
                    java.sql.Date.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            stmt.setTime(
                    5,
                    java.sql.Time.valueOf(
                            appointment.getAppointmentTime()
                    )
            );

            stmt.setString(6, appointment.getNotes());

            stmt.setInt(
                    7,
                    appointment.getAppointmentId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating appointment: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // Delete an appointment
    public boolean deleteAppointment(int appointmentId) {

        String sql = "DELETE FROM appointments "
                + "WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting appointment: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // Get all appointments for the appointment table
    public List<Appointment> getAllAppointments() {

        List<Appointment> appointmentList =
                new ArrayList<>();

        String sql = "SELECT "
                + "a.appointment_id, "
                + "a.appointment_number, "
                + "a.patient_id, "
                + "a.dentist_id, "
                + "a.treatment_id, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "a.notes, "
                + "p.full_name AS patient_name, "
                + "u.full_name AS dentist_name, "
                + "t.treatment_name "
                + "FROM appointments a "
                + "INNER JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "INNER JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "INNER JOIN users u "
                + "ON d.user_id = u.user_id "
                + "INNER JOIN treatments t "
                + "ON a.treatment_id = t.treatment_id "
                + "ORDER BY a.appointment_date, "
                + "a.appointment_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Appointment appointment =
                        new Appointment();

                appointment.setAppointmentId(
                        rs.getInt("appointment_id")
                );

                appointment.setAppointmentNumber(
                        rs.getString("appointment_number")
                );

                appointment.setPatientId(
                        rs.getInt("patient_id")
                );

                appointment.setDentistId(
                        rs.getInt("dentist_id")
                );

                appointment.setTreatmentId(
                        rs.getInt("treatment_id")
                );

                appointment.setAppointmentDate(
                        rs.getDate("appointment_date")
                                .toLocalDate()
                );

                appointment.setAppointmentTime(
                        rs.getTime("appointment_time")
                                .toLocalTime()
                );

                appointment.setNotes(
                        rs.getString("notes")
                );

                appointment.setPatientName(
                        rs.getString("patient_name")
                );

                appointment.setDentistName(
                        rs.getString("dentist_name")
                );

                appointment.setTreatmentName(
                        rs.getString("treatment_name")
                );

                appointmentList.add(appointment);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading appointments: "
                    + e.getMessage()
            );
        }

        return appointmentList;
    }

    // Check whether a dentist already has an appointment
    // at the selected date and time
    public boolean isTimeSlotBooked(
            int dentistId,
            java.time.LocalDate appointmentDate,
            java.time.LocalTime appointmentTime) {

        String sql = "SELECT appointment_id "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date = ? "
                + "AND appointment_time = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dentistId);

            stmt.setDate(
                    2,
                    java.sql.Date.valueOf(
                            appointmentDate
                    )
            );

            stmt.setTime(
                    3,
                    java.sql.Time.valueOf(
                            appointmentTime
                    )
            );

            try (ResultSet rs = stmt.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error checking appointment slot: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // Get appointments for a dentist on a specific date
    public List<Appointment> getAppointmentsByDentistAndDate(
            int dentistId,
            java.time.LocalDate appointmentDate) {

        List<Appointment> appointmentList =
                new ArrayList<>();

        String sql = "SELECT "
                + "appointment_id, "
                + "appointment_number, "
                + "patient_id, "
                + "dentist_id, "
                + "treatment_id, "
                + "appointment_date, "
                + "appointment_time, "
                + "notes "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date = ? "
                + "ORDER BY appointment_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dentistId);

            stmt.setDate(
                    2,
                    java.sql.Date.valueOf(
                            appointmentDate
                    )
            );

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Appointment appointment =
                            new Appointment();

                    appointment.setAppointmentId(
                            rs.getInt("appointment_id")
                    );

                    appointment.setAppointmentNumber(
                            rs.getString("appointment_number")
                    );

                    appointment.setPatientId(
                            rs.getInt("patient_id")
                    );

                    appointment.setDentistId(
                            rs.getInt("dentist_id")
                    );

                    appointment.setTreatmentId(
                            rs.getInt("treatment_id")
                    );

                    appointment.setAppointmentDate(
                            rs.getDate("appointment_date")
                                    .toLocalDate()
                    );

                    appointment.setAppointmentTime(
                            rs.getTime("appointment_time")
                                    .toLocalTime()
                    );

                    appointment.setNotes(
                            rs.getString("notes")
                    );

                    appointmentList.add(appointment);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading dentist appointments: "
                    + e.getMessage()
            );
        }

        return appointmentList;
    }

    // Generate the next appointment number
    public String generateAppointmentNumber() {

        String sql = "SELECT MAX(appointment_id) "
                + "FROM appointments";

        int nextId = 1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                int lastId = rs.getInt(1);

                if (!rs.wasNull()) {
                    nextId = lastId + 1;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error generating appointment number: "
                    + e.getMessage()
            );
        }

        return String.format(
                "APP-%04d",
                nextId
        );
    }
}