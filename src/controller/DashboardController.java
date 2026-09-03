/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DashboardDAO;
import java.math.BigDecimal;

public class DashboardController {

    private DashboardDAO dashboardDAO;

    public DashboardController() {
        dashboardDAO = new DashboardDAO();
    }

    public int getTotalPatients() {
        return dashboardDAO.getTotalPatients();
    }

    public int getTotalDentists() {
        return dashboardDAO.getTotalDentists();
    }

    public int getTodayAppointments() {
        return dashboardDAO.getTodayAppointments();
    }

    public BigDecimal getTotalRevenue() {
        return dashboardDAO.getTotalRevenue();
    }
}
