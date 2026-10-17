package model;

public class Reminder {

    private int reminderId;
    private String petName;
    private String reminderType;
    private String reminderDate;
    private String reminderTime;
    private String description;
    private String status;

    public Reminder() {
    }

    public Reminder(int reminderId,
                    String petName,
                    String reminderType,
                    String reminderDate,
                    String reminderTime,
                    String description,
                    String status) {

        this.reminderId = reminderId;
        this.petName = petName;
        this.reminderType = reminderType;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        this.description = description;
        this.status = status;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getReminderType() {
        return reminderType;
    }

    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}