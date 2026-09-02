/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.TreatmentController;
import model.Treatment;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.List;

public class TreatmentManagementForm extends javax.swing.JFrame {

   private TreatmentController treatmentController;
    
    
    public TreatmentManagementForm() {
        initComponents();
         setSize(1160, 780);
        setResizable(false); 
        setLocationRelativeTo(null); 
        treatmentController = new TreatmentController();

    loadTreatmentsToTable();
    }

 
private void clearFields() {

    txtTreatmentId.setText("");
    txtTreatmentName.setText("");
    txtDescription.setText("");
    txtCost.setText("");
}
    
    private void loadTreatmentsToTable() {

    DefaultTableModel tableModel =
            (DefaultTableModel) tblTreatment.getModel();

    tableModel.setRowCount(0);

    try {

        List<Treatment> treatmentList =
                treatmentController.getAllTreatments();

        for (Treatment treatment : treatmentList) {

            Object[] rowData = {

                treatment.getTreatmentId(),
                treatment.getTreatmentName(),
                treatment.getDescription(),
                treatment.getCost()
            };

            tableModel.addRow(rowData);
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error loading treatments: "
                        + e.getMessage(),
                "Error",
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
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTreatmentId = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        txtTreatmentName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        txtCost = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTreatment = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 780));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("TREATMENT MANAGEMENT");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(515, Short.MAX_VALUE)
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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1160, 70));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 660));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Treatment Cost :");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Description :");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Treatment Name :");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Treatment ID :");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAdd.setText("Add Treatment");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        tblTreatment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Treatment", "Description", "Cost"
            }
        ));
        jScrollPane1.setViewportView(tblTreatment);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTreatmentId)
                            .addComponent(txtTreatmentName)
                            .addComponent(txtDescription)
                            .addComponent(txtCost, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                        .addGap(57, 57, 57)
                        .addComponent(btnSearch))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(253, 253, 253)
                                .addComponent(btnAdd)
                                .addGap(55, 55, 55)
                                .addComponent(btnUpdate)
                                .addGap(62, 62, 62)
                                .addComponent(btnDelete)
                                .addGap(58, 58, 58)
                                .addComponent(btnClear)))
                        .addGap(44, 44, 44)
                        .addComponent(btnBack)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(50, 50, 50))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtTreatmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearch))
                                .addGap(36, 36, 36)
                                .addComponent(jLabel5))
                            .addComponent(txtTreatmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete)
                            .addComponent(btnClear))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBack)
                        .addGap(52, 52, 52)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
  String treatmentName =
            txtTreatmentName.getText().trim();

    String description =
            txtDescription.getText().trim();

    String costText =
            txtCost.getText().trim();

    if (treatmentName.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the treatment name."
        );

        return;
    }

    if (!treatmentName.matches("[a-zA-Z ]+")) {

        JOptionPane.showMessageDialog(
                this,
                "Treatment name can contain letters and spaces only."
        );

        return;
    }

    if (costText.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the treatment cost."
        );

        return;
    }

    try {

        BigDecimal cost =
                new BigDecimal(costText);

        if (cost.compareTo(BigDecimal.ZERO) <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment cost must be greater than zero."
            );

            return;
        }

        Treatment treatment =
                new Treatment(
                        0,
                        treatmentName,
                        description,
                        cost
                );

        boolean added =
                treatmentController.addTreatment(
                        treatment
                );

        if (added) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment added successfully!"
            );

            clearFields();
            loadTreatmentsToTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add treatment. "
                    + "The treatment name may already exist."
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter a valid treatment cost."
        );
    }

    }//GEN-LAST:event_btnAddActionPerformed

    
    
    
    
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
         String idText =
            txtTreatmentId.getText().trim();

    if (idText.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter a Treatment ID."
        );

        return;
    }

    try {

        int treatmentId =
                Integer.parseInt(idText);

        Treatment treatment =
                treatmentController.searchTreatment(
                        treatmentId
                );

        if (treatment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment not found."
            );

            return;
        }

        txtTreatmentName.setText(
                treatment.getTreatmentName()
        );

        txtDescription.setText(
                treatment.getDescription()
        );

        txtCost.setText(
                treatment.getCost().toString()
        );

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Treatment ID must be a valid number."
        );
    }
    }//GEN-LAST:event_btnSearchActionPerformed

    
    
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
         String idText =
            txtTreatmentId.getText().trim();

    String treatmentName =
            txtTreatmentName.getText().trim();

    String description =
            txtDescription.getText().trim();

    String costText =
            txtCost.getText().trim();

    if (idText.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter a Treatment ID."
        );

        return;
    }

    if (treatmentName.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the treatment name."
        );

        return;
    }

    if (!treatmentName.matches("[a-zA-Z ]+")) {

        JOptionPane.showMessageDialog(
                this,
                "Treatment name can contain letters and spaces only."
        );

        return;
    }

    if (costText.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the treatment cost."
        );

        return;
    }

    try {

        int treatmentId =
                Integer.parseInt(idText);

        BigDecimal cost =
                new BigDecimal(costText);

        if (cost.compareTo(BigDecimal.ZERO) <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment cost must be greater than zero."
            );

            return;
        }

        Treatment treatment =
                treatmentController.searchTreatment(
                        treatmentId
                );

        if (treatment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment not found."
            );

            return;
        }

        treatment.setTreatmentName(
                treatmentName
        );

        treatment.setDescription(
                description
        );

        treatment.setCost(cost);

        boolean updated =
                treatmentController.updateTreatment(
                        treatment
                );

        if (updated) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment updated successfully!"
            );

            clearFields();
            loadTreatmentsToTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update treatment."
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter valid numeric values."
        );
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    
    
    
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       String idText =
            txtTreatmentId.getText().trim();

    if (idText.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter a Treatment ID."
        );

        return;
    }

    try {

        int treatmentId =
                Integer.parseInt(idText);

        Treatment treatment =
                treatmentController.searchTreatment(
                        treatmentId
                );

        if (treatment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment not found."
            );

            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this treatment?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirm == JOptionPane.YES_OPTION) {

            boolean deleted =
                    treatmentController.deleteTreatment(
                            treatmentId
                    );

            if (deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Treatment deleted successfully!"
                );

                clearFields();
                loadTreatmentsToTable();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to delete treatment."
                );
            }
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Treatment ID must be a valid number."
        );
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
  clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        AdminDashboardForm dashboard = new AdminDashboardForm();
    dashboard.setVisible(true);
    this.dispose();  
    }//GEN-LAST:event_btnBackActionPerformed

    
    

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TreatmentManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TreatmentManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TreatmentManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TreatmentManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             new TreatmentManagementForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTreatment;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtTreatmentId;
    private javax.swing.JTextField txtTreatmentName;
    // End of variables declaration//GEN-END:variables
}
