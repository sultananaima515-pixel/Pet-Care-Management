package model;

public class FeedingSchedule {

    private int scheduleId;
    private String petName;
    private String foodType;
    private String feedingTime;
    private String frequency;

    public FeedingSchedule() {
    }

    public FeedingSchedule(int scheduleId,
                           String petName,
                           String foodType,
                           String feedingTime,
                           String frequency) {

        this.scheduleId = scheduleId;
        this.petName = petName;
        this.foodType = foodType;
        this.feedingTime = feedingTime;
        this.frequency = frequency;

    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(String feedingTime) {
        this.feedingTime = feedingTime;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

}
