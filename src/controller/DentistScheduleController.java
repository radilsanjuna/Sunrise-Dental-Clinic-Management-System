package controller;

import dao.DentistScheduleDAO;
import model.DentistSchedule;

import java.time.LocalDate;
import java.util.List;

public class DentistScheduleController {

    private DentistScheduleDAO scheduleDAO;

    public DentistScheduleController() {
        scheduleDAO = new DentistScheduleDAO();
    }

    public boolean addSchedule(DentistSchedule schedule) {
        return scheduleDAO.addSchedule(schedule);
    }

    public boolean updateSchedule(DentistSchedule schedule) {
        return scheduleDAO.updateSchedule(schedule);
    }

    public boolean deleteSchedule(int scheduleId) {
        return scheduleDAO.deleteSchedule(scheduleId);
    }

    public List<DentistSchedule> getAllSchedules() {
        return scheduleDAO.getAllSchedules();
    }

    public DentistSchedule searchSchedule(int scheduleId) {
        return scheduleDAO.searchSchedule(scheduleId);
    }

    public DentistSchedule getScheduleByDentistAndDate(
            int dentistId,
            LocalDate scheduleDate) {

        return scheduleDAO.getScheduleByDentistAndDate(
                dentistId,
                scheduleDate
        );
    }
}