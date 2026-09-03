/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DentistAppointmentDAO;
import java.util.List;
import model.Appointment;

public class DentistAppointmentController {

    private DentistAppointmentDAO
            dentistAppointmentDAO;

    public DentistAppointmentController() {

        dentistAppointmentDAO =
                new DentistAppointmentDAO();
    }

    public List<Appointment>
            getAppointmentsByDentistId(
                    int dentistId) {

        return dentistAppointmentDAO
                .getAppointmentsByDentistId(
                        dentistId
                );
    }
}