package model;

public class VaccinationRecord {

    private int vaccinationId;
    private String petName;
    private String vaccineName;
    private String vaccinationDate;
    private String dueDate;
    private String veterinarian;

    public VaccinationRecord() {
    }

    public VaccinationRecord(int vaccinationId,
                             String petName,
                             String vaccineName,
                             String vaccinationDate,
                             String dueDate,
                             String veterinarian) {

        this.vaccinationId = vaccinationId;
        this.petName = petName;
        this.vaccineName = vaccineName;
        this.vaccinationDate = vaccinationDate;
        this.dueDate = dueDate;
        this.veterinarian = veterinarian;
    }

    public int getVaccinationId() {
        return vaccinationId;
    }

    public void setVaccinationId(int vaccinationId) {
        this.vaccinationId = vaccinationId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }
}