package dao;

import db.DBConnection;
import model.DentistSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DentistScheduleDAO {

    public boolean addSchedule(DentistSchedule schedule) {

        String sql = "INSERT INTO dentist_schedules "
                + "(dentist_id, schedule_date, start_time, end_time) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, schedule.getDentistId());
            stmt.setObject(2, schedule.getScheduleDate());
            stmt.setObject(3, schedule.getStartTime());
            stmt.setObject(4, schedule.getEndTime());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding schedule: " + e.getMessage()
            );

            return false;
        }
    }

    public boolean updateSchedule(DentistSchedule schedule) {

        String sql = "UPDATE dentist_schedules SET "
                + "dentist_id = ?, "
                + "schedule_date = ?, "
                + "start_time = ?, "
                + "end_time = ? "
                + "WHERE schedule_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, schedule.getDentistId());
            stmt.setObject(2, schedule.getScheduleDate());
            stmt.setObject(3, schedule.getStartTime());
            stmt.setObject(4, schedule.getEndTime());
            stmt.setInt(5, schedule.getScheduleId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating schedule: " + e.getMessage()
            );

            return false;
        }
    }

    public boolean deleteSchedule(int scheduleId) {

        String sql = "DELETE FROM dentist_schedules "
                + "WHERE schedule_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting schedule: " + e.getMessage()
            );

            return false;
        }
    }

    public List<DentistSchedule> getAllSchedules() {

        List<DentistSchedule> schedules = new ArrayList<>();

        String sql = "SELECT ds.schedule_id, ds.dentist_id, "
                + "u.full_name AS dentist_name, "
                + "ds.schedule_date, ds.start_time, ds.end_time "
                + "FROM dentist_schedules ds "
                + "JOIN dentists d "
                + "ON ds.dentist_id = d.dentist_id "
                + "JOIN users u "
                + "ON d.user_id = u.user_id "
                + "ORDER BY ds.schedule_date, ds.start_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                DentistSchedule schedule =
                        new DentistSchedule();

                schedule.setScheduleId(
                        rs.getInt("schedule_id")
                );

                schedule.setDentistId(
                        rs.getInt("dentist_id")
                );

                schedule.setDentistName(
                        rs.getString("dentist_name")
                );

                schedule.setScheduleDate(
                        rs.getDate("schedule_date")
                                .toLocalDate()
                );

                schedule.setStartTime(
                        rs.getTime("start_time")
                                .toLocalTime()
                );

                schedule.setEndTime(
                        rs.getTime("end_time")
                                .toLocalTime()
                );

                schedules.add(schedule);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading schedules: "
                    + e.getMessage()
            );
        }

        return schedules;
    }

    public DentistSchedule searchSchedule(int scheduleId) {

        String sql = "SELECT ds.schedule_id, ds.dentist_id, "
                + "u.full_name AS dentist_name, "
                + "ds.schedule_date, ds.start_time, ds.end_time "
                + "FROM dentist_schedules ds "
                + "JOIN dentists d "
                + "ON ds.dentist_id = d.dentist_id "
                + "JOIN users u "
                + "ON d.user_id = u.user_id "
                + "WHERE ds.schedule_id = ?";

        DentistSchedule schedule = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    schedule =
                            new DentistSchedule();

                    schedule.setScheduleId(
                            rs.getInt("schedule_id")
                    );

                    schedule.setDentistId(
                            rs.getInt("dentist_id")
                    );

                    schedule.setDentistName(
                            rs.getString("dentist_name")
                    );

                    schedule.setScheduleDate(
                            rs.getDate("schedule_date")
                                    .toLocalDate()
                    );

                    schedule.setStartTime(
                            rs.getTime("start_time")
                                    .toLocalTime()
                    );

                    schedule.setEndTime(
                            rs.getTime("end_time")
                                    .toLocalTime()
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error searching schedule: "
                    + e.getMessage()
            );
        }

        return schedule;
    }

    public DentistSchedule getScheduleByDentistAndDate(
            int dentistId,
            java.time.LocalDate scheduleDate) {

        String sql = "SELECT ds.schedule_id, ds.dentist_id, "
                + "u.full_name AS dentist_name, "
                + "ds.schedule_date, ds.start_time, ds.end_time "
                + "FROM dentist_schedules ds "
                + "JOIN dentists d "
                + "ON ds.dentist_id = d.dentist_id "
                + "JOIN users u "
                + "ON d.user_id = u.user_id "
                + "WHERE ds.dentist_id = ? "
                + "AND ds.schedule_date = ?";

        DentistSchedule schedule = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dentistId);
            stmt.setObject(2, scheduleDate);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    schedule =
                            new DentistSchedule();

                    schedule.setScheduleId(
                            rs.getInt("schedule_id")
                    );

                    schedule.setDentistId(
                            rs.getInt("dentist_id")
                    );

                    schedule.setDentistName(
                            rs.getString("dentist_name")
                    );

                    schedule.setScheduleDate(
                            rs.getDate("schedule_date")
                                    .toLocalDate()
                    );

                    schedule.setStartTime(
                            rs.getTime("start_time")
                                    .toLocalTime()
                    );

                    schedule.setEndTime(
                            rs.getTime("end_time")
                                    .toLocalTime()
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error finding dentist schedule: "
                    + e.getMessage()
            );
        }

        return schedule;
    }
}