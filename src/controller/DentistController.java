package controller;

import dao.DentistDAO;
import model.Dentist;

import java.util.List;

public class DentistController {

    private DentistDAO dentistDAO;

    public DentistController() {
        dentistDAO = new DentistDAO();
    }

    public boolean addDentist(Dentist dentist) {
        return dentistDAO.addDentist(dentist);
    }

    public Dentist searchDentist(int dentistId) {
        return dentistDAO.searchDentist(dentistId);
    }

    public boolean updateDentist(Dentist dentist) {
        return dentistDAO.updateDentist(dentist);
    }

    public boolean deleteDentist(int dentistId) {
        return dentistDAO.deleteDentist(dentistId);
    }

    public List<Dentist> getAllDentists() {
        return dentistDAO.getAllDentists();
    }
}