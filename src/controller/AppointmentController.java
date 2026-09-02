package controller;

import dao.AppointmentDAO;
import model.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentController {

    private AppointmentDAO appointmentDAO;

    public AppointmentController() {
        appointmentDAO = new AppointmentDAO();
    }

    // Add a new appointment
    public boolean addAppointment(Appointment appointment) {
        return appointmentDAO.addAppointment(appointment);
    }

    // Search appointment by appointment number
    public Appointment searchAppointment(String appointmentNumber) {
        return appointmentDAO.searchAppointment(appointmentNumber);
    }

    // Update an existing appointment
    public boolean updateAppointment(Appointment appointment) {
        return appointmentDAO.updateAppointment(appointment);
    }

    // Delete an appointment
    public boolean deleteAppointment(int appointmentId) {
        return appointmentDAO.deleteAppointment(appointmentId);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    // Check whether a time slot is already booked
    public boolean isTimeSlotBooked(
            int dentistId,
            LocalDate appointmentDate,
            LocalTime appointmentTime) {

        return appointmentDAO.isTimeSlotBooked(
                dentistId,
                appointmentDate,
                appointmentTime
        );
    }

    // Get appointments for a dentist on a specific date
    public List<Appointment> getAppointmentsByDentistAndDate(
            int dentistId,
            LocalDate appointmentDate) {

        return appointmentDAO.getAppointmentsByDentistAndDate(
                dentistId,
                appointmentDate
        );
    }

    // Generate the next appointment number
    public String generateAppointmentNumber() {
        return appointmentDAO.generateAppointmentNumber();
    }
}