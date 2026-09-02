package model;

import java.math.BigDecimal;

public class Treatment {

    private int treatmentId;
    private String treatmentName;
    private String description;
    private BigDecimal cost;

    public Treatment() {
    }

    public Treatment(int treatmentId, String treatmentName,
                     String description, BigDecimal cost) {

        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.description = description;
        this.cost = cost;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}