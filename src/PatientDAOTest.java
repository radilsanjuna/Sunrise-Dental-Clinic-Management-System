import dao.PatientDAO;
import model.Patient;

import java.util.List;

public class PatientDAOTest {

    public static void main(String[] args) {

        PatientDAO patientDAO = new PatientDAO();

        List<Patient> patients = patientDAO.getAllPatients();

        for (Patient patient : patients) {

            System.out.println(
                patient.getPatientId() + " | " +
                patient.getFullName() + " | " +
                patient.getPhoneNumber()
            );
        }
    }
}