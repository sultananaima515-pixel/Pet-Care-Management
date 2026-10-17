package service;

import model.Reminder;

import java.util.ArrayList;

public class ReminderService {

    private ArrayList<Reminder> reminderList =
            new ArrayList<>();

    private final String FILE_NAME =
            "reminders.txt";

    public ReminderService() {
        loadFromFile();
    }

    // Add
    public void addReminder(Reminder reminder) {

        reminderList.add(reminder);

        saveToFile();
    }

    // Get
    public ArrayList<Reminder> getReminderList() {

        return reminderList;
    }

    // Delete
    public void removeReminder(int index) {

        if (index >= 0 &&
                index < reminderList.size()) {

            reminderList.remove(index);

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

        for (Reminder reminder :
                reminderList) {

            data.append(
                    reminder.getReminderId()
            ).append("|");

            data.append(
                    reminder.getPetName()
            ).append("|");

            data.append(
                    reminder.getReminderType()
            ).append("|");

            data.append(
                    reminder.getReminderDate()
            ).append("|");

            data.append(
                    reminder.getReminderTime()
            ).append("|");

            data.append(
                    reminder.getDescription()
            ).append("|");

            data.append(
                    reminder.getStatus()
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

            if (parts.length == 7) {

                try {

                    Reminder reminder =
                            new Reminder(

                                    Integer.parseInt(parts[0]),
                                    parts[1],
                                    parts[2],
                                    parts[3],
                                    parts[4],
                                    parts[5],
                                    parts[6]
                            );

                    reminderList.add(reminder);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid reminder data."
                    );
                }
            }
        }
    }
}