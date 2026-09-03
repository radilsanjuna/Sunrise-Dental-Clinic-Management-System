package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;

public class DentistAppointmentDAO {

    public List<Appointment> getAppointmentsByDentistId(
            int dentistId) {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT a.*, "
                + "p.full_name AS patient_name, "
                + "d.dentist_id, "
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
                + "WHERE a.dentist_id = ? "
                + "ORDER BY a.appointment_date ASC, "
                + "a.appointment_time ASC";

        try (Connection conn =
                     DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, dentistId);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                while (rs.next()) {

                    Appointment appointment =
                            new Appointment();

                    appointment.setAppointmentId(
                            rs.getInt("appointment_id")
                    );

                    appointment.setAppointmentNumber(
                            rs.getString(
                                    "appointment_number"
                            )
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
                            rs.getDate(
                                    "appointment_date"
                            ).toLocalDate()
                    );

                    appointment.setAppointmentTime(
                            rs.getTime(
                                    "appointment_time"
                            ).toLocalTime()
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

                    appointments.add(appointment);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading dentist appointments: "
                    + e.getMessage()
            );
        }

        return appointments;
    }
}