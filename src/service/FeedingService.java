package service;

import model.FeedingSchedule;

import java.util.ArrayList;

public class FeedingService {

    private ArrayList<FeedingSchedule> feedingList =
            new ArrayList<>();

    private final String FILE_NAME =
            "schedule.txt";

    // --------------------------------
    // Constructor
    // --------------------------------

    public FeedingService() {

        loadFromFile();
    }

    // --------------------------------
    // Add Feeding Schedule
    // --------------------------------

    public void addSchedule(FeedingSchedule schedule) {

        feedingList.add(schedule);

        saveToFile();
    }

    // --------------------------------
    // Get Feeding List
    // --------------------------------

    public ArrayList<FeedingSchedule> getFeedingList() {

        return feedingList;
    }

    // --------------------------------
    // Remove Feeding Schedule
    // --------------------------------

    public void removeSchedule(int index) {

        if (index >= 0 &&
                index < feedingList.size()) {

            feedingList.remove(index);

            saveToFile();
        }
    }

    // --------------------------------
    // Save Changes
    // --------------------------------

    public void saveChanges() {

        saveToFile();
    }

    // --------------------------------
    // Save Data to File
    // --------------------------------

    private void saveToFile() {

        StringBuilder data =
                new StringBuilder();

        for (FeedingSchedule schedule :
                feedingList) {

            data.append(
                    schedule.getScheduleId()
            ).append("|");

            data.append(
                    schedule.getPetName()
            ).append("|");

            data.append(
                    schedule.getFeedingTime()
            ).append("|");

            data.append(
                    schedule.getFoodType()
            ).append("|");

            data.append(
                    schedule.getFrequency()
            );

            data.append("\n");
        }

        FileManager.save(
                FILE_NAME,
                data.toString()
        );
    }

    // --------------------------------
    // Load Data from File
    // --------------------------------

    private void loadFromFile() {

        String data =
                FileManager.read(
                        FILE_NAME
                );

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

            if (parts.length == 5) {

                try {

                    FeedingSchedule schedule =
                            new FeedingSchedule(

                                    Integer.parseInt(
                                            parts[0]
                                    ),

                                    parts[1],

                                    parts[2],

                                    parts[3],

                                    parts[4]
                            );

                    feedingList.add(
                            schedule
                    );

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid feeding schedule ID."
                    );
                }
            }
        }
    }

}