package model;

public class Activity {

    private int activityId;
    private String petName;
    private String activityType;
    private String activityDate;
    private String duration;
    private String notes;

    public Activity() {
    }

    public Activity(int activityId,
                    String petName,
                    String activityType,
                    String activityDate,
                    String duration,
                    String notes) {

        this.activityId = activityId;
        this.petName = petName;
        this.activityType = activityType;
        this.activityDate = activityDate;
        this.duration = duration;
        this.notes = notes;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}