/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RevenueReportDAO;
import java.math.BigDecimal;
import java.util.List;
import model.Bill;

public class RevenueReportController {

    private RevenueReportDAO revenueReportDAO;

    public RevenueReportController() {
        revenueReportDAO = new RevenueReportDAO();
    }

    public List<Bill> getRevenueReport(
            String fromDate,
            String toDate) {

        return revenueReportDAO.getRevenueReport(
                fromDate,
                toDate
        );
    }

    public BigDecimal getTotalPaidRevenue(
            String fromDate,
            String toDate) {

        return revenueReportDAO.getTotalPaidRevenue(
                fromDate,
                toDate
        );
    }
}