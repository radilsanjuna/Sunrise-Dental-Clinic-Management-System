package model;

public class Dentist {

    private int dentistId;
    private int userId;
    private String fullName;
    private String specialization;
    private String phoneNumber;

    public Dentist() {
    }

    public Dentist(int dentistId, int userId, String fullName,
                   String specialization, String phoneNumber) {

        this.dentistId = dentistId;
        this.userId = userId;
        this.fullName = fullName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
    }

    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}