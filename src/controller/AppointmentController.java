package controller;

import dao.AppointmentDAO;
import model.Appointment;

import java.util.List;

public class AppointmentController {

    private AppointmentDAO appointmentDAO;

    // Constructor
    public AppointmentController() {
        appointmentDAO = new AppointmentDAO();
    }

    // 1. Add Appointment
    public boolean addAppointment(Appointment appointment) {

        return appointmentDAO.addAppointment(appointment);
    }

//    // 2. Search Appointment
//    public Appointment searchAppointment(String appointmentNumber) {
//
//        return appointmentDAO.searchAppointment(appointmentNumber);
//    }

    // 3. Update Appointment
    public boolean updateAppointment(Appointment appointment) {

        return appointmentDAO.updateAppointment(appointment);
    }

    // 4. Delete Appointment
    public boolean deleteAppointment(int appointmentId) {

        return appointmentDAO.deleteAppointment(appointmentId);
    }

//    // 5. Get All Appointments
//    public List<Appointment> getAllAppointments() {
//
//        return appointmentDAO.getAllAppointments();
//    }
}