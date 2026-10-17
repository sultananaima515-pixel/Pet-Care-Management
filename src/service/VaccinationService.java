package service;

import model.VaccinationRecord;

import java.util.ArrayList;

public class VaccinationService {

    private ArrayList<VaccinationRecord> vaccinationList =
            new ArrayList<>();

    private final String FILE_NAME =
            "vaccinations.txt";

    public VaccinationService() {
        loadFromFile();
    }

    // Add
    public void addVaccination(VaccinationRecord vaccination) {

        vaccinationList.add(vaccination);

        saveToFile();
    }

    // Get
    public ArrayList<VaccinationRecord> getVaccinationList() {

        return vaccinationList;
    }

    // Delete
    public void removeVaccination(int index) {

        if (index >= 0 &&
                index < vaccinationList.size()) {

            vaccinationList.remove(index);

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

        for (VaccinationRecord vaccination :
                vaccinationList) {

            data.append(
                    vaccination.getVaccinationId()
            ).append("|");

            data.append(
                    vaccination.getPetName()
            ).append("|");

            data.append(
                    vaccination.getVaccineName()
            ).append("|");

            data.append(
                    vaccination.getVaccinationDate()
            ).append("|");

            data.append(
                    vaccination.getDueDate()
            ).append("|");

            data.append(
                    vaccination.getVeterinarian()
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

                    VaccinationRecord vaccination =
                            new VaccinationRecord(

                                    Integer.parseInt(parts[0]),
                                    parts[1],
                                    parts[2],
                                    parts[3],
                                    parts[4],
                                    parts[5]
                            );

                    vaccinationList.add(
                            vaccination
                    );

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid vaccination data."
                    );
                }
            }
        }
    }
}