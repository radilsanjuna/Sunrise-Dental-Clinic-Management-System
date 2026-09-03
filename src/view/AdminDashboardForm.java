/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JOptionPane;
import controller.DashboardController;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author Radil_Sanjuna
 */
public class AdminDashboardForm extends javax.swing.JFrame {

    private DashboardController dashboardController;
    private User loggedInUser;

    
    public AdminDashboardForm() {
    this(null);
}
    public AdminDashboardForm(User user) {
       initComponents();
    setSize(1160, 780);
    setResizable(false);
    setLocationRelativeTo(null);

    loggedInUser = user;

    dashboardController = new DashboardController();

    loadDashboardData();
    }

    private void loadDashboardData() {

        try {

            int totalPatients
                    = dashboardController.getTotalPatients();

            int totalDentists
                    = dashboardController.getTotalDentists();

            int todayAppointments
                    = dashboardController.getTodayAppointments();

            java.math.BigDecimal totalRevenue
                    = dashboardController.getTotalRevenue();

            lblTotalPatientss.setText(
                    String.valueOf(totalPatients)
            );

            lblTotalDentists.setText(
                    String.valueOf(totalDentists)
            );

            lblTodayAppointments.setText(
                    String.valueOf(todayAppointments)
            );
//
//        lblTotalRevenue.setText(
//                "Rs. " + totalRevenue.toString()
//        );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading dashboard: "
                    + e.getMessage(),
                    "Dashboard Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnManageAppoinmentsSchedule = new javax.swing.JButton();
        btnManagepatients = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnBilling = new javax.swing.JButton();
        btnViewreports = new javax.swing.JButton();
        btnManageUsers = new javax.swing.JButton();
        btnManagetreatments = new javax.swing.JButton();
        btnManageDentist = new javax.swing.JButton();
        btnManageAppoinments1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblTotalDentists = new javax.swing.JLabel();
        lblTotalPatientss = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblTotalPatients1 = new javax.swing.JLabel();
        lblTotalPatientss1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblTodayAppointments = new javax.swing.JLabel();
        lblTotalPatientss2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 780));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("AdminDashboardForm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(505, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(17, 17, 17))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1150, 70));

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));

        btnManageAppoinmentsSchedule.setText(" Manage Appoinment Schedule");
        btnManageAppoinmentsSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageAppoinmentsScheduleActionPerformed(evt);
            }
        });

        btnManagepatients.setText("Manage Patients");
        btnManagepatients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagepatientsActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout ");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnBilling.setText("Billing & Receipts");
        btnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillingActionPerformed(evt);
            }
        });

        btnViewreports.setText("View Reports");

        btnManageUsers.setText("Manage Users");
        btnManageUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageUsersActionPerformed(evt);
            }
        });

        btnManagetreatments.setText("Manage Treatments");
        btnManagetreatments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagetreatmentsActionPerformed(evt);
            }
        });

        btnManageDentist.setText("Manage Dentist");
        btnManageDentist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageDentistActionPerformed(evt);
            }
        });

        btnManageAppoinments1.setText(" Manage Appoinment");
        btnManageAppoinments1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageAppoinments1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnManageAppoinmentsSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnViewreports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBilling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManagepatients, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManageUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManagetreatments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManageDentist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManageAppoinments1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(btnManagepatients, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnManageAppoinments1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnManageAppoinmentsSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnManageDentist, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnManagetreatments, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnManageUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBilling, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnViewreports, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 230, 670));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        lblTotalDentists.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblTotalDentists.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalDentists.setText("25");

        lblTotalPatientss.setFont(new java.awt.Font("Segoe UI Light", 1, 20)); // NOI18N
        lblTotalPatientss.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalPatientss.setText("TOTAL Dentist");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(lblTotalDentists)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(lblTotalPatientss)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalPatientss, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalDentists)
                .addGap(17, 17, 17))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 200, 130));

        btnRefresh.setText("jButton1");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 470, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        lblTotalPatients1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblTotalPatients1.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalPatients1.setText("25");

        lblTotalPatientss1.setFont(new java.awt.Font("Segoe UI Light", 1, 20)); // NOI18N
        lblTotalPatientss1.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalPatientss1.setText("TOTAL PATIENTS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(lblTotalPatients1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(lblTotalPatientss1)
                .addGap(17, 17, 17))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalPatientss1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalPatients1)
                .addGap(17, 17, 17))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 200, 130));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        lblTodayAppointments.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblTodayAppointments.setForeground(new java.awt.Color(0, 0, 0));
        lblTodayAppointments.setText("25");

        lblTotalPatientss2.setFont(new java.awt.Font("Segoe UI Light", 1, 20)); // NOI18N
        lblTotalPatientss2.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalPatientss2.setText("Appoinment");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(lblTodayAppointments)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(lblTotalPatientss2)
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalPatientss2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTodayAppointments)
                .addGap(17, 17, 17))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 200, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            new LoginForm().setVisible(true);
            this.dispose();

}    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnManageUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageUsersActionPerformed
        UserManagementForm userForm = new UserManagementForm();

        // 2. Make the new form visible on the screen
        userForm.setVisible(true);

        // 3. Close the current Admin Dashboard window so you don't have too many windows open
    this.dispose();     }//GEN-LAST:event_btnManageUsersActionPerformed

    private void btnManagepatientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagepatientsActionPerformed
           PatientManagmentForm patientForm = new PatientManagmentForm(loggedInUser);
    patientForm.setVisible(true);

    this.dispose();    }//GEN-LAST:event_btnManagepatientsActionPerformed

    private void btnManageAppoinments1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageAppoinments1ActionPerformed
        AppoinmentManagementForm appoinment = new AppoinmentManagementForm();
        appoinment.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageAppoinments1ActionPerformed

    private void btnManageAppoinmentsScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageAppoinmentsScheduleActionPerformed
        ScheduleManagementForm schedule = new ScheduleManagementForm();

        schedule.setVisible(true);
        this.dispose();               }//GEN-LAST:event_btnManageAppoinmentsScheduleActionPerformed

    private void btnManageDentistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageDentistActionPerformed
        DentistManagementForm dentist = new DentistManagementForm();
        dentist.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageDentistActionPerformed

    private void btnManagetreatmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagetreatmentsActionPerformed
        TreatmentManagementForm treatment = new TreatmentManagementForm();
        treatment.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManagetreatmentsActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
 loadDashboardData();    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBillingActionPerformed
           BillingManagementForm billingForm =
            new BillingManagementForm(loggedInUser);
    billingForm.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnBillingActionPerformed

    /**
     * @param args the command line arguments
     */
public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new AdminDashboardForm(null).setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBilling;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageAppoinments1;
    private javax.swing.JButton btnManageAppoinmentsSchedule;
    private javax.swing.JButton btnManageDentist;
    private javax.swing.JButton btnManageUsers;
    private javax.swing.JButton btnManagepatients;
    private javax.swing.JButton btnManagetreatments;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnViewreports;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblTodayAppointments;
    private javax.swing.JLabel lblTotalDentists;
    private javax.swing.JLabel lblTotalPatients1;
    private javax.swing.JLabel lblTotalPatientss;
    private javax.swing.JLabel lblTotalPatientss1;
    private javax.swing.JLabel lblTotalPatientss2;
    // End of variables declaration//GEN-END:variables
}
