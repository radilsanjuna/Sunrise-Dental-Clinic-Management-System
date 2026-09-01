//package dao;
//
//import db.DBConnection;
//import model.Appointment;
//import model.Patient;
//import model.Dentist;
//import model.Treatment;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Date;
//import java.sql.Time;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AppointmentDAO {
//
//    // 1. Add Appointment
//    public boolean addAppointment(Appointment appointment) {
//
//        String sql = "INSERT INTO appointments "
//                + "(appointment_number, patient_id, dentist_id, treatment_id, "
//                + "appointment_date, appointment_time) "
//                + "VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, appointment.getAppointmentNumber());
//
//            stmt.setInt(2, appointment.getPatient().getPatientId());
//
//            stmt.setInt(3, appointment.getDentist().getDentistByUserId());
//
//            stmt.setInt(4, appointment.getTreatment().getTreatmentId());
//
//            stmt.setDate(
//                    5,
//                    Date.valueOf(appointment.getAppointmentDate())
//            );
//
//            stmt.setTime(
//                    6,
//                    Time.valueOf(appointment.getAppointmentTime())
//            );
//
//            return stmt.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//
//            System.out.println(
//                    "Error adding appointment: " + e.getMessage()
//            );
//
//            return false;
//        }
//    }
//
//    // 2. Search Appointment by Appointment Number
////    public Appointment searchAppointment(String appointmentNumber) {
////
////        String sql = "SELECT * FROM appointments "
////                + "WHERE appointment_number = ?";
////
////        Appointment appointment = null;
////
////        try (Connection conn = DBConnection.getConnection();
////             PreparedStatement stmt = conn.prepareStatement(sql)) {
////
////            stmt.setString(1, appointmentNumber);
////
////            try (ResultSet rs = stmt.executeQuery()) {
////
////                if (rs.next()) {
////
////                    appointment = new Appointment();
////
////                    appointment.setAppointmentId(
////                            rs.getInt("appointment_id")
////                    );
////
////                    appointment.setAppointmentNumber(
////                            rs.getString("appointment_number")
////                    );
////
////                    appointment.setAppointmentDate(
////                            rs.getDate("appointment_date").toLocalDate()
////                    );
////
////                    appointment.setAppointmentTime(
////                            rs.getTime("appointment_time").toLocalTime()
////                    );
////
////                    // Create Patient object
////                    Patient patient = new Patient();
////                    patient.setPatientId(
////                            rs.getInt("patient_id")
////                    );
////
////                    // Create Dentist object
////                    Dentist dentist = new Dentist();
////                    dentist.setDentistId(
////                            rs.getInt("dentist_id")
////                    );
////
////                    // Create Treatment object
////                    Treatment treatment = new Treatment();
////                    treatment.setTreatmentId(
////                            rs.getInt("treatment_id")
////                    );
////
////                    appointment.setPatient(patient);
////                    appointment.setDentist(dentist);
////                    appointment.setTreatment(treatment);
////                }
////            }
////
////        } catch (SQLException e) {
////
////            System.out.println(
////                    "Error searching appointment: " + e.getMessage()
////            );
////        }
////
////        return appointment;
////    }
//
//    // 3. Update Appointment
//    public boolean updateAppointment(Appointment appointment) {
//
//        String sql = "UPDATE appointments SET "
//                + "patient_id = ?, "
//                + "dentist_id = ?, "
//                + "treatment_id = ?, "
//                + "appointment_date = ?, "
//                + "appointment_time = ? "
//                + "WHERE appointment_id = ?";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(
//                    1,
//                    appointment.getPatient().getPatientId()
//            );
//
//            stmt.setInt(
//                    2,
//                    appointment.getDentist().getDentistId()
//            );
//
//            stmt.setInt(
//                    3,
//                    appointment.getTreatment().getTreatmentId()
//            );
//
//            stmt.setDate(
//                    4,
//                    Date.valueOf(appointment.getAppointmentDate())
//            );
//
//            stmt.setTime(
//                    5,
//                    Time.valueOf(appointment.getAppointmentTime())
//            );
//
//            stmt.setInt(
//                    6,
//                    appointment.getAppointmentId()
//            );
//
//            return stmt.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//
//            System.out.println(
//                    "Error updating appointment: " + e.getMessage()
//            );
//
//            return false;
//        }
//    }
//
//    // 4. Delete Appointment
//    public boolean deleteAppointment(int appointmentId) {
//
//        String sql = "DELETE FROM appointments "
//                + "WHERE appointment_id = ?";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, appointmentId);
//
//            return stmt.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//
//            System.out.println(
//                    "Error deleting appointment: " + e.getMessage()
//            );
//
//            return false;
//        }
//    }
//
////    // 5. Get All Appointments
////    public List<Appointment> getAllAppointments() {
////
////        List<Appointment> appointmentList = new ArrayList<>();
////
////        String sql = "SELECT * FROM appointments";
////
////        try (Connection conn = DBConnection.getConnection();
////             PreparedStatement stmt = conn.prepareStatement(sql);
////             ResultSet rs = stmt.executeQuery()) {
////
////            while (rs.next()) {
////
////                Appointment appointment = new Appointment();
////
////                appointment.setAppointmentId(
////                        rs.getInt("appointment_id")
////                );
////
////                appointment.setAppointmentNumber(
////                        rs.getString("appointment_number")
////                );
////
////                appointment.setAppointmentDate(
////                        rs.getDate("appointment_date").toLocalDate()
////                );
////
////                appointment.setAppointmentTime(
////                        rs.getTime("appointment_time").toLocalTime()
////                );
////
////                // Patient
////                Patient patient = new Patient();
////                patient.setPatientId(
////                        rs.getInt("patient_id")
////                );
////
////                // Dentist
////                Dentist dentist = new Dentist();
////                dentist.setDentistId(
////                        rs.getInt("dentist_id")
////                );
////
////                // Treatment
////                Treatment treatment = new Treatment();
////                treatment.setTreatmentId(
////                        rs.getInt("treatment_id")
////                );
////
////                appointment.setPatient(patient);
////                appointment.setDentist(dentist);
////                appointment.setTreatment(treatment);
////
////                appointmentList.add(appointment);
////            }
////
////        } catch (SQLException e) {
////
////            System.out.println(
////                    "Error loading appointments: " + e.getMessage()
////            );
////        }
////
////        return appointmentList;
////    }
//}