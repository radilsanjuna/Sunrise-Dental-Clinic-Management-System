package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class DentistSchedule {

    private int scheduleId;
    private int dentistId;
    private String dentistName;
    private LocalDate scheduleDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public DentistSchedule() {
    }

    public DentistSchedule(
            int scheduleId,
            int dentistId,
            String dentistName,
            LocalDate scheduleDate,
            LocalTime startTime,
            LocalTime endTime) {

        this.scheduleId = scheduleId;
        this.dentistId = dentistId;
        this.dentistName = dentistName;
        this.scheduleDate = scheduleDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}