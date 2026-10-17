package model;

public class HealthRecord {

    private int recordId;
    private String petName;
    private double weight;
    private double temperature;
    private String disease;
    private String allergy;
    private String checkupDate;

    public HealthRecord() {
    }

    public HealthRecord(int recordId,
                        String petName,
                        double weight,
                        double temperature,
                        String disease,
                        String allergy,
                        String checkupDate) {

        this.recordId = recordId;
        this.petName = petName;
        this.weight = weight;
        this.temperature = temperature;
        this.disease = disease;
        this.allergy = allergy;
        this.checkupDate = checkupDate;
    }

    // Getter and Setter for Record ID
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    // Getter and Setter for Pet Name
    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    // Getter and Setter for Weight
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Getter and Setter for Temperature
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    // Getter and Setter for Disease
    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    // Getter and Setter for Allergy
    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    // Getter and Setter for Checkup Date
    public String getCheckupDate() {
        return checkupDate;
    }

    public void setCheckupDate(String checkupDate) {
        this.checkupDate = checkupDate;
    }

}