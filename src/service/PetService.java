package service;

import model.Pet;

import java.util.ArrayList;

public class PetService {

    private ArrayList<Pet> petList =
            new ArrayList<>();

    private final String FILE_NAME =
            "pets.txt";

    public PetService() {

        loadFromFile();
    }

    // --------------------------------
    // Add Pet
    // --------------------------------

    public void addPet(Pet pet) {

        petList.add(pet);

        saveToFile();
    }

    // --------------------------------
    // Get Pet List
    // --------------------------------

    public ArrayList<Pet> getPetList() {

        return petList;
    }

    // --------------------------------
    // Delete Pet
    // --------------------------------

    public void removePet(int index) {

        if (index >= 0 &&
                index < petList.size()) {

            petList.remove(index);

            saveToFile();
        }
    }

    // --------------------------------
    // Save Data
    // --------------------------------

    private void saveToFile() {

        StringBuilder data =
                new StringBuilder();

        for (Pet pet : petList) {

            data.append(
                    pet.getPetId()
            ).append("|");

            data.append(
                    pet.getName()
            ).append("|");

            data.append(
                    pet.getBreed()
            ).append("|");

            data.append(
                    pet.getAge()
            ).append("|");

            data.append(
                    pet.getGender()
            ).append("|");

            data.append(
                    pet.getHealthStatus()
            );

            data.append("\n");
        }

        FileManager.save(
                FILE_NAME,
                data.toString()
        );
    }

    // --------------------------------
    // Load Data
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

            if (parts.length == 6) {

                try {

                    Pet pet = new Pet(

                            Integer.parseInt(
                                    parts[0]
                            ),

                            parts[1],

                            parts[2],

                            Integer.parseInt(
                                    parts[3]
                            ),

                            parts[4],

                            parts[5]
                    );

                    petList.add(pet);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid pet data."
                    );
                }
            }
        }
    }
    public void saveChanges() {

        saveToFile();
    }
}
