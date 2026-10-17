package service;

import model.HealthRecord;

import java.util.ArrayList;

public class HealthService {

    private ArrayList<HealthRecord> records =
            new ArrayList<>();

    private final String FILE_NAME = "health.txt";

    public HealthService() {
        loadFromFile();
    }

    // Add
    public void addRecord(HealthRecord record) {

        records.add(record);

        saveToFile();
    }

    // Get records
    public ArrayList<HealthRecord> getRecords() {

        return records;
    }

    // Delete
    public void deleteRecord(int index) {

        if (index >= 0 && index < records.size()) {

            records.remove(index);

            saveToFile();
        }
    }

    // Save after update
    public void saveChanges() {

        saveToFile();
    }

    // Save to file
    private void saveToFile() {

        StringBuilder data =
                new StringBuilder();

        for (HealthRecord record : records) {

            data.append(record.getRecordId())
                    .append("|");

            data.append(record.getPetName())
                    .append("|");

            data.append(record.getWeight())
                    .append("|");

            data.append(record.getTemperature())
                    .append("|");

            data.append(record.getDisease())
                    .append("|");

            data.append(record.getAllergy())
                    .append("|");

            data.append(record.getCheckupDate())
                    .append("\n");
        }

        FileManager.save(
                FILE_NAME,
                data.toString()
        );
    }

    // Load from file
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

                    HealthRecord record =
                            new HealthRecord(

                                    Integer.parseInt(parts[0]),
                                    parts[1],
                                    Double.parseDouble(parts[2]),
                                    Double.parseDouble(parts[3]),
                                    parts[4],
                                    parts[5],
                                    parts[6]
                            );

                    records.add(record);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid health record data."
                    );
                }
            }
        }
    }
}