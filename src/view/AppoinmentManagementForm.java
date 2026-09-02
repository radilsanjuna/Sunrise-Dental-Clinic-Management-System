
package view;

import controller.AppointmentController;
import controller.PatientController;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Patient;

import controller.DentistController;
import controller.TreatmentController;
import model.Dentist;
import java.util.List;
import model.Treatment;

import controller.DentistScheduleController;
import model.DentistSchedule;
import java.time.LocalTime;

import model.Appointment;

/**
 *
 * @author Radil_Sanjuna
 */
public class AppoinmentManagementForm extends javax.swing.JFrame {
    
   private PatientController patientController;
    private DentistController dentistController;
    private TreatmentController treatmentController;
    private DentistScheduleController scheduleController;
    private AppointmentController appointmentController;
    private List<Patient> patientList;
    private List<Dentist> dentistList;
    private List<Treatment> treatmentList;
    
public AppoinmentManagementForm() {
  initComponents();

    // Initialize controllers after GUI components are created
    patientController = new PatientController();
dentistController = new DentistController();
treatmentController = new TreatmentController();
scheduleController = new DentistScheduleController();
appointmentController = new AppointmentController();
    setupDateOfBirth();

    // Set form properties
    setSize(1160, 780);
    setResizable(false);
    setLocationRelativeTo(null);

    // Load initial patient data
    loadPatients();
      loadDentists();
      loadTreatments();
      loadAppointments();
    
}

    


private void loadAppointments() {

    List<Appointment> appointmentList =
            appointmentController.getAllAppointments();

    DefaultTableModel model =
            (DefaultTableModel) tblAppoinment.getModel();

    model.setRowCount(0);

    if (appointmentList == null) {
        return;
    }

    for (Appointment appointment : appointmentList) {

        model.addRow(new Object[]{
            appointment.getAppointmentId(),
            appointment.getAppointmentNumber(),
            appointment.getPatientName(),
            appointment.getDentistName(),
            appointment.getTreatmentName(),
            appointment.getAppointmentDate(),
            appointment.getAppointmentTime()
        });
    }
}



private boolean isTimeSlotBookedByAnotherAppointment(
        int dentistId,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        int appointmentId) {

    List<Appointment> appointments =
            appointmentController.getAppointmentsByDentistAndDate(
                    dentistId,
                    appointmentDate
            );

    if (appointments == null) {
        return false;
    }

    for (Appointment appointment : appointments) {

        if (appointment.getAppointmentId() != appointmentId
                && appointment.getAppointmentTime().equals(appointmentTime)) {

            return true;
        }
    }

    return false;
}


private void loadAvailableTimes() {

    cmbTime.removeAllItems();
    cmbTime.addItem("Select Time");

    int dentistId = getSelectedDentistId();

    if (dentistId == -1) {
        return;
    }

    // Check whether all date values are available
    if (cmbYear.getSelectedItem() == null
            || cmbMonth.getSelectedItem() == null
            || cmbDay.getSelectedItem() == null) {
        return;
    }

    if (cmbYear.getSelectedIndex() == 0
            || cmbMonth.getSelectedIndex() == 0
            || cmbDay.getSelectedIndex() == 0) {
        return;
    }

    try {

        int year = Integer.parseInt(
                cmbYear.getSelectedItem().toString()
        );

        int month = cmbMonth.getSelectedIndex();

        int day = Integer.parseInt(
                cmbDay.getSelectedItem().toString()
        );

        LocalDate appointmentDate =
                LocalDate.of(year, month, day);

        // Find the dentist's schedule for the selected date
        DentistSchedule schedule =
                scheduleController.getScheduleByDentistAndDate(
                        dentistId,
                        appointmentDate
                );

        if (schedule == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "The selected dentist is not available on this date."
            );

            return;
        }

        LocalTime startTime = schedule.getStartTime();
        LocalTime endTime = schedule.getEndTime();

        LocalTime currentTime = startTime;

        // Generate 30-minute appointment slots
        while (currentTime.plusMinutes(30).compareTo(endTime) <= 0) {

            boolean booked =
                    appointmentController.isTimeSlotBooked(
                            dentistId,
                            appointmentDate,
                            currentTime
                    );

            if (!booked) {

                cmbTime.addItem(
                        currentTime.toString()
                );
            }

            currentTime =
                    currentTime.plusMinutes(30);
        }

    } catch (Exception e) {

        // Ignore temporary combo box changes
        return;
    }
}





private void loadTreatments() {

    cmbTreatment.removeAllItems();

    cmbTreatment.addItem("Select Treatment");

    treatmentList = treatmentController.getAllTreatments();

    if (treatmentList == null) {
        return;
    }

    for (Treatment treatment : treatmentList) {

        cmbTreatment.addItem(
                treatment.getTreatmentName()
        );
    }
}



private int getSelectedTreatmentId() {

    int selectedIndex = cmbTreatment.getSelectedIndex();

    if (selectedIndex <= 0 || treatmentList == null) {
        return -1;
    }

    return treatmentList
            .get(selectedIndex - 1)
            .getTreatmentId();
}





  private void setupDateOfBirth() {

    // Fill the Year dropdown
    cmbYear.removeAllItems();
    cmbYear.addItem("Year");

    int currentYear =
            LocalDate.now().getYear();

    for (int i = currentYear; i <= currentYear + 2; i++) {

        cmbYear.addItem(
                String.valueOf(i)
        );
    }

    // Fill the Month dropdown
    cmbMonth.removeAllItems();
    cmbMonth.addItem("Month");

    String[] months = {
        "01 - Jan",
        "02 - Feb",
        "03 - Mar",
        "04 - Apr",
        "05 - May",
        "06 - Jun",
        "07 - Jul",
        "08 - Aug",
        "09 - Sep",
        "10 - Oct",
        "11 - Nov",
        "12 - Dec"
    };

    for (String month : months) {

        cmbMonth.addItem(month);
    }

    // Fill the Day dropdown
    cmbDay.removeAllItems();
    cmbDay.addItem("Day");

    for (int i = 1; i <= 31; i++) {

        cmbDay.addItem(
                String.format("%02d", i)
        );
    }
}
  
  
  
  private void loadPatients() {

    patientList =
            patientController.getAllPatients();

    if (patientList == null) {
        return;
    }
}









private void loadDentists() {

    cmbDentist.removeAllItems();

    cmbDentist.addItem("Select Dentist");

    dentistList = dentistController.getAllDentists();

    if (dentistList == null) {
        return;
    }

    for (Dentist dentist : dentistList) {

        cmbDentist.addItem(
                dentist.getFullName()
        );
    }
}





private int getSelectedDentistId() {

    int selectedIndex = cmbDentist.getSelectedIndex();

    if (selectedIndex <= 0 || dentistList == null) {
        return -1;
    }

    return dentistList
            .get(selectedIndex - 1)
            .getDentistId();
}

  


private void loadAppointmentToForm(Appointment appointment) {

    if (appointment == null) {
        return;
    }

    txtPatientId.setText(
            String.valueOf(appointment.getPatientId())
    );

    txtPatientName.setText(
            appointment.getPatientName()
    );

    // Load patient's phone number
 Patient patient =
        patientController.searchPatientByIdOrPhone(
                String.valueOf(appointment.getPatientId())
        );

if (patient != null) {
    txtPhone.setText(
            patient.getPhoneNumber()
    );
} else {
    txtPhone.setText("");
}

    // Select dentist
    for (int i = 0; i < dentistList.size(); i++) {

        if (dentistList.get(i).getDentistId()
                == appointment.getDentistId()) {

            cmbDentist.setSelectedIndex(i + 1);
            break;
        }
    }

    // Select treatment
    cmbTreatment.setSelectedItem(
            appointment.getTreatmentName()
    );

    // Set date
    LocalDate date =
            appointment.getAppointmentDate();

    cmbYear.setSelectedItem(
            String.valueOf(date.getYear())
    );

    cmbMonth.setSelectedIndex(
            date.getMonthValue()
    );

    cmbDay.setSelectedItem(
            String.format(
                    "%02d",
                    date.getDayOfMonth()
            )
    );

    // Load available appointment times
    loadAvailableTimes();

    // Select appointment time
    if (appointment.getAppointmentTime() != null) {

        String appointmentTime =
                appointment.getAppointmentTime().toString();

        boolean timeExists = false;

        for (int i = 0; i < cmbTime.getItemCount(); i++) {

            if (appointmentTime.equals(
                    cmbTime.getItemAt(i))) {

                timeExists = true;
                break;
            }
        }

        if (!timeExists) {
            cmbTime.addItem(appointmentTime);
        }

        cmbTime.setSelectedItem(appointmentTime);
    }

    // Set notes
    if (appointment.getNotes() != null) {
        txtNote.setText(
                appointment.getNotes()
        );
    } else {
        txtNote.setText("");
    }
}





 
private void clearFields() {

    // Clear appointment search
    txtAppointmentNumber.setText("");

    // Clear patient search
    txtSearchPatient.setText("");

    // Clear selected patient details
    txtPatientId.setText("");
    txtPatientName.setText("");
    txtPhone.setText("");

    // Reset date fields
    cmbYear.setSelectedIndex(0);
    cmbMonth.setSelectedIndex(0);
    cmbDay.setSelectedIndex(0);

    // Reset dentist, treatment and time
    cmbDentist.setSelectedIndex(0);
    cmbTreatment.setSelectedIndex(0);
    cmbTime.setSelectedIndex(0);

    // Clear notes
    txtNote.setText("");

    // Clear patient search results
    DefaultTableModel patientModel =
            (DefaultTableModel) tblPatient.getModel();

    patientModel.setRowCount(0);

    // Clear appointment table selection
    tblAppoinment.clearSelection();
}

    


  


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSearchPatient = new javax.swing.JTextField();
        btnSelectPatient = new javax.swing.JButton();
        btnSearchPatient = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPatient = new javax.swing.JTable();
        btnAddPatient = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbDentist = new javax.swing.JComboBox<>();
        cmbTreatment = new javax.swing.JComboBox<>();
        cmbYear = new javax.swing.JComboBox<>();
        cmbMonth = new javax.swing.JComboBox<>();
        cmbDay = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtPatientId = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtPatientName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        cmbTime = new javax.swing.JComboBox<>();
        txtNote = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAppoinment = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtAppointmentNumber = new javax.swing.JTextField();
        btnSearchAppoinment = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 780));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Appoinment Management ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(463, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(17, 17, 17))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1160, 70));

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("PATIENT DETAILS");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Search Patient :");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Search Results");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Select Patient :");

        txtSearchPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchPatientActionPerformed(evt);
            }
        });

        btnSelectPatient.setText("Select Patient");
        btnSelectPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPatientActionPerformed(evt);
            }
        });

        btnSearchPatient.setText("Search");
        btnSearchPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPatientActionPerformed(evt);
            }
        });

        tblPatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Patient Name", "Phone Number"
            }
        ));
        jScrollPane1.setViewportView(tblPatient);

        btnAddPatient.setText("Add New Patient");
        btnAddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPatientActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Available Time :");

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Date :");

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Treatment :");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Patient ID :");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("APPOINTMENT DETAILS");

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Notes :");

        cmbDentist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Dentist" }));
        cmbDentist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDentistActionPerformed(evt);
            }
        });

        cmbTreatment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Treatment" }));

        cmbYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYearActionPerformed(evt);
            }
        });

        cmbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMonthActionPerformed(evt);
            }
        });

        cmbDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDayActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Dentist :");

        txtPatientId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPatientIdActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Patient Name :");

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Phone :");

        cmbTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Time" }));

        btnAdd.setText("Add");
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

        tblAppoinment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Appoinment No", "Patient", "Dentist", "Treatment", "Date", "Time"
            }
        ));
        tblAppoinment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAppoinmentMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAppoinment);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Appoinment No:");

        btnSearchAppoinment.setText("Search");
        btnSearchAppoinment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAppoinmentActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbDentist, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(254, 254, 254)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(64, 64, 64)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnSearchPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(cmbTime, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cmbTreatment, javax.swing.GroupLayout.Alignment.LEADING, 0, 143, Short.MAX_VALUE))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64)
                                        .addComponent(txtNote))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSelectPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(btnAddPatient))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 33, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(341, 341, 341)
                .addComponent(btnAdd)
                .addGap(32, 32, 32)
                .addComponent(btnUpdate)
                .addGap(27, 27, 27)
                .addComponent(btnDelete)
                .addGap(28, 28, 28)
                .addComponent(btnClear)
                .addGap(30, 30, 30)
                .addComponent(btnBack)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(txtAppointmentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnSearchAppoinment, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(txtSearchPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtSearchPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchPatient))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtAppointmentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchAppoinment)))
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(btnSelectPatient)
                    .addComponent(btnAddPatient))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(cmbDentist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cmbTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cmbTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear)
                    .addComponent(btnBack))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 1080, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPatientActionPerformed
  String searchText = txtSearchPatient.getText().trim();

    if (searchText.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Please enter Patient ID or Phone Number.");
        return;
    }

    Patient patient =
            patientController.searchPatientByIdOrPhone(searchText);

    DefaultTableModel model =
            (DefaultTableModel) tblPatient.getModel();

    model.setRowCount(0);

    if (patient == null) {
        JOptionPane.showMessageDialog(this,
                "Patient not found.");
        return;
    }

    model.addRow(new Object[]{
        patient.getPatientId(),
        patient.getFullName(),
        patient.getPhoneNumber()
    });
      
    }//GEN-LAST:event_btnSearchPatientActionPerformed

    private void btnSelectPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPatientActionPerformed
     int selectedRow =
            tblPatient.getSelectedRow();

    if (selectedRow == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a patient from the table."
        );

        return;
    }

    int patientId =
            Integer.parseInt(
                    tblPatient.getValueAt(
                            selectedRow,
                            0
                    ).toString()
            );

   Patient 
       patient = patientController.searchPatientByIdOrPhone(
               String.valueOf(patientId)
       );

    if (patient == null) {

        JOptionPane.showMessageDialog(
                this,
                "Patient information could not be found."
        );

        return;
    }

    txtPatientId.setText(
            String.valueOf(
                    patient.getPatientId()
            )
    );

    txtPatientName.setText(
            patient.getFullName()
    );

    txtPhone.setText(
            patient.getPhoneNumber()
    );

    JOptionPane.showMessageDialog(
            this,
            "Patient selected successfully."
    );
    
    }//GEN-LAST:event_btnSelectPatientActionPerformed

    private void btnAddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddPatientActionPerformed

    private void cmbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMonthActionPerformed
        if (cmbYear.getSelectedItem() == null
            || cmbMonth.getSelectedItem() == null) {
        return;
    }

    if (cmbMonth.getSelectedIndex() == 0
            || cmbYear.getSelectedIndex() == 0) {
        return;
    }

    int year = Integer.parseInt(
            cmbYear.getSelectedItem().toString()
    );

    int month = cmbMonth.getSelectedIndex();

    int daysInMonth =
            java.time.YearMonth.of(year, month)
                    .lengthOfMonth();

    cmbDay.removeAllItems();
    cmbDay.addItem("Day");

    for (int day = 1; day <= daysInMonth; day++) {

        cmbDay.addItem(
                String.format("%02d", day)
        );
    }

    loadAvailableTimes();
    }//GEN-LAST:event_cmbMonthActionPerformed

    
    
    private void txtPatientIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPatientIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPatientIdActionPerformed

    private void cmbDentistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDentistActionPerformed
 loadAvailableTimes();
    }//GEN-LAST:event_cmbDentistActionPerformed

    private void btnSearchAppoinmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAppoinmentActionPerformed
       String appointmentNumber =
            txtAppointmentNumber.getText().trim();

    if (appointmentNumber.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter appointment number."
        );

        return;
    }

    try {

        Appointment appointment =
                appointmentController.searchAppointment(
                        appointmentNumber
                );

        if (appointment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment not found."
            );

            return;
        }

        // Display appointment information
        loadAppointmentToForm(appointment);

        // Select the appointment in the table
        for (int i = 0; i < tblAppoinment.getRowCount(); i++) {

            String tableAppointmentNumber =
                    tblAppoinment.getValueAt(i, 1).toString();

            if (tableAppointmentNumber.equals(
                    appointmentNumber)) {

                tblAppoinment.setRowSelectionInterval(i, i);
                break;
            }
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error searching appointment: "
                        + e.getMessage()
        );
    }
    }//GEN-LAST:event_btnSearchAppoinmentActionPerformed

    private void txtSearchPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchPatientActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
    clearFields();
// TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void cmbDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDayActionPerformed
 loadAvailableTimes();
    }//GEN-LAST:event_cmbDayActionPerformed

    private void cmbYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYearActionPerformed
         loadAvailableTimes();
    }//GEN-LAST:event_cmbYearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
         // Validate patient
    if (txtPatientId.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Please select a patient."
        );
        return;
    }

    // Validate dentist
    int dentistId = getSelectedDentistId();

    if (dentistId == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Please select a dentist."
        );
        return;
    }

    // Validate date
    if (cmbYear.getSelectedIndex() == 0
            || cmbMonth.getSelectedIndex() == 0
            || cmbDay.getSelectedIndex() == 0) {

        JOptionPane.showMessageDialog(
                this,
                "Please select an appointment date."
        );
        return;
    }

    // Validate time
    if (cmbTime.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(
                this,
                "Please select an available time."
        );
        return;
    }

    // Validate treatment
    if (cmbTreatment.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(
                this,
                "Please select a treatment."
        );
        return;
    }

    try {

        int patientId = Integer.parseInt(
                txtPatientId.getText().trim()
        );

        int year = Integer.parseInt(
                cmbYear.getSelectedItem().toString()
        );

        int month = cmbMonth.getSelectedIndex();

        int day = Integer.parseInt(
                cmbDay.getSelectedItem().toString()
        );

        LocalDate appointmentDate =
                LocalDate.of(year, month, day);

        LocalTime appointmentTime =
                LocalTime.parse(
                        cmbTime.getSelectedItem().toString()
                );

        int treatmentId =
                getSelectedTreatmentId();

        // Check again before saving
        // to prevent double booking
        if (appointmentController.isTimeSlotBooked(
                dentistId,
                appointmentDate,
                appointmentTime)) {

            JOptionPane.showMessageDialog(
                    this,
                    "This time slot is already booked."
            );

            loadAvailableTimes();
            return;
        }

        // Create appointment object
        Appointment appointment =
                new Appointment();

        appointment.setAppointmentNumber(
                appointmentController.generateAppointmentNumber()
        );

        appointment.setPatientId(patientId);
        appointment.setDentistId(dentistId);
        appointment.setTreatmentId(treatmentId);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setNotes(txtNote.getText().trim());

        // Save appointment
        boolean success =
                appointmentController.addAppointment(
                        appointment
                );

     if (success) {

    JOptionPane.showMessageDialog(
            this,
            "Appointment registered successfully.\n"
            + "Appointment Number: "
            + appointment.getAppointmentNumber()
    );

    loadAppointments();
    clearFields();
}else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to register appointment."
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid patient information."
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage()
        );
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblAppoinment.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Please select an appointment to update."
        );
        return;
    }

    try {

        // Get the selected appointment number from the table
        String appointmentNumber =
                tblAppoinment.getValueAt(selectedRow, 1).toString();

        // Find the existing appointment
        Appointment appointment =
                appointmentController.searchAppointment(
                        appointmentNumber
                );

        if (appointment == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Appointment could not be found."
            );
            return;
        }

        // Validate patient
        if (txtPatientId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a patient."
            );
            return;
        }

        int patientId =
                Integer.parseInt(txtPatientId.getText().trim());

        // Validate dentist
        int dentistId = getSelectedDentistId();

        if (dentistId == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a dentist."
            );
            return;
        }

        // Validate date
        if (cmbYear.getSelectedIndex() == 0
                || cmbMonth.getSelectedIndex() == 0
                || cmbDay.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select appointment date."
            );
            return;
        }

        int year =
                Integer.parseInt(
                        cmbYear.getSelectedItem().toString()
                );

        int month =
                cmbMonth.getSelectedIndex();

        int day =
                Integer.parseInt(
                        cmbDay.getSelectedItem().toString()
                );

        LocalDate appointmentDate =
                LocalDate.of(year, month, day);

        // Validate time
        if (cmbTime.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select appointment time."
            );
            return;
        }

        LocalTime appointmentTime =
                LocalTime.parse(
                        cmbTime.getSelectedItem().toString()
                );

        // Validate treatment
        if (cmbTreatment.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select treatment."
            );
            return;
        }

        int treatmentId = getSelectedTreatmentId();

        // Check whether another appointment already uses this slot
        if (isTimeSlotBookedByAnotherAppointment(
                dentistId,
                appointmentDate,
                appointmentTime,
                appointment.getAppointmentId())) {

            JOptionPane.showMessageDialog(
                    this,
                    "This time slot is already booked."
            );

            loadAvailableTimes();
            return;
        }

        // Update appointment details
        appointment.setPatientId(patientId);
        appointment.setDentistId(dentistId);
        appointment.setTreatmentId(treatmentId);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setNotes(txtNote.getText().trim());

        // Update database
        boolean success =
                appointmentController.updateAppointment(
                        appointment
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment updated successfully."
            );

            loadAppointments();
            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update appointment."
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid patient information."
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error updating appointment: "
                + e.getMessage()
        );
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblAppoinmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAppoinmentMouseClicked
        int selectedRow = tblAppoinment.getSelectedRow();

    if (selectedRow == -1) {
        return;
    }

    try {

        // Get appointment number from selected row
        String appointmentNumber =
                tblAppoinment.getValueAt(selectedRow, 1).toString();

        // Get complete appointment details
        Appointment appointment =
                appointmentController.searchAppointment(
                        appointmentNumber
                );

        if (appointment == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Appointment information could not be found."
            );
            return;
        }

        // Load patient information
        txtPatientId.setText(
                String.valueOf(appointment.getPatientId())
        );

        txtPatientName.setText(
                appointment.getPatientName()
        );

        Patient patient =
                patientController.searchPatientByIdOrPhone(
                        String.valueOf(appointment.getPatientId())
                );

        if (patient != null) {
            txtPhone.setText(
                    patient.getPhoneNumber()
            );
        }

        // Select dentist
        for (int i = 0; i < dentistList.size(); i++) {

            if (dentistList.get(i).getDentistId()
                    == appointment.getDentistId()) {

                cmbDentist.setSelectedIndex(i + 1);
                break;
            }
        }

        // Select treatment by treatment name
        cmbTreatment.setSelectedItem(
                appointment.getTreatmentName()
        );

        // Load appointment date
        LocalDate date =
                appointment.getAppointmentDate();

        cmbYear.setSelectedItem(
                String.valueOf(date.getYear())
        );

        cmbMonth.setSelectedIndex(
                date.getMonthValue()
        );

        cmbDay.setSelectedItem(
                String.format(
                        "%02d",
                        date.getDayOfMonth()
                )
        );

        // Load available times
        loadAvailableTimes();

        // Add the current appointment time
        // because it is already booked
        if (appointment.getAppointmentTime() != null) {

            String appointmentTime =
                    appointment.getAppointmentTime().toString();

            boolean timeExists = false;

            for (int i = 0; i < cmbTime.getItemCount(); i++) {

                if (appointmentTime.equals(
                        cmbTime.getItemAt(i))) {

                    timeExists = true;
                    break;
                }
            }

            if (!timeExists) {
                cmbTime.addItem(appointmentTime);
            }

            cmbTime.setSelectedItem(appointmentTime);
        }

        // Load notes
        if (appointment.getNotes() != null) {
            txtNote.setText(
                    appointment.getNotes()
            );
        } else {
            txtNote.setText("");
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error loading appointment: "
                + e.getMessage()
        );
    }
    }//GEN-LAST:event_tblAppoinmentMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
         int selectedRow = tblAppoinment.getSelectedRow();

    // Check whether an appointment is selected
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Please select an appointment to delete."
        );
        return;
    }

    try {

        // Get appointment ID from the selected table row
        int appointmentId = Integer.parseInt(
                tblAppoinment.getValueAt(selectedRow, 0).toString()
        );

        String appointmentNumber =
                tblAppoinment.getValueAt(selectedRow, 1).toString();

        // Ask for confirmation before deleting
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete appointment "
                        + appointmentNumber + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Delete the appointment
        boolean success =
                appointmentController.deleteAppointment(appointmentId);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment deleted successfully."
            );

            // Refresh the appointment table
            loadAppointments();

            // Clear the form
            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete appointment."
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid appointment information."
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error deleting appointment: "
                        + e.getMessage()
        );
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(AppoinmentManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppoinmentManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppoinmentManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppoinmentManagementForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppoinmentManagementForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddPatient;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearchAppoinment;
    private javax.swing.JButton btnSearchPatient;
    private javax.swing.JButton btnSelectPatient;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbDay;
    private javax.swing.JComboBox<String> cmbDentist;
    private javax.swing.JComboBox<String> cmbMonth;
    private javax.swing.JComboBox<String> cmbTime;
    private javax.swing.JComboBox<String> cmbTreatment;
    private javax.swing.JComboBox<String> cmbYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblAppoinment;
    private javax.swing.JTable tblPatient;
    private javax.swing.JTextField txtAppointmentNumber;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtPatientId;
    private javax.swing.JTextField txtPatientName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearchPatient;
    // End of variables declaration//GEN-END:variables
}
