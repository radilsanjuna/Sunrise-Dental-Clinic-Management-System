/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class DentistItem {

    private int dentistId;
    private String dentistName;

    public DentistItem(int dentistId, String dentistName) {
        this.dentistId = dentistId;
        this.dentistName = dentistName;
    }

    public int getDentistId() {
        return dentistId;
    }

    @Override
    public String toString() {
        return dentistId + " - " + dentistName;
    }
}