package service;

import model.Activity;

import java.util.ArrayList;

public class ActivityService {

    private ArrayList<Activity> activityList =
            new ArrayList<>();

    private final String FILE_NAME =
            "activities.txt";

    public ActivityService() {
        loadFromFile();
    }

    // Add
    public void addActivity(Activity activity) {

        activityList.add(activity);

        saveToFile();
    }

    // Get
    public ArrayList<Activity> getActivityList() {

        return activityList;
    }

    // Delete
    public void removeActivity(int index) {

        if (index >= 0 &&
                index < activityList.size()) {

            activityList.remove(index);

            saveToFile();
        }
    }

    // Update
    public void saveChanges() {

        saveToFile();
    }

    // Save
    private void saveToFile() {

        StringBuilder data =
                new StringBuilder();

        for (Activity activity :
                activityList) {

            data.append(
                    activity.getActivityId()
            ).append("|");

            data.append(
                    activity.getPetName()
            ).append("|");

            data.append(
                    activity.getActivityType()
            ).append("|");

            data.append(
                    activity.getActivityDate()
            ).append("|");

            data.append(
                    activity.getDuration()
            ).append("|");

            data.append(
                    activity.getNotes()
            ).append("\n");
        }

        FileManager.save(
                FILE_NAME,
                data.toString()
        );
    }

    // Load
    private void loadFromFile() {

        String data =
                FileManager.read(FILE_NAME);

        if (data.isEmpty()) {
            return;
        }

        String[] lines =
                data.split("\n");

        for (String line : lines) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts =
                    line.split("\\|");

            if (parts.length == 6) {

                try {

                    Activity activity =
                            new Activity(

                                    Integer.parseInt(parts[0]),
                                    parts[1],
                                    parts[2],
                                    parts[3],
                                    parts[4],
                                    parts[5]
                            );

                    activityList.add(activity);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid activity data."
                    );
                }
            }
        }
    }

}