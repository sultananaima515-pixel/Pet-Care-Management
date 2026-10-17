package service;

import model.Owner;

import java.util.ArrayList;

public class OwnerService {

    private ArrayList<Owner> ownerList =
            new ArrayList<>();

    private final String FILE_NAME =
            "owners.txt";

    // --------------------------------
    // Constructor
    // --------------------------------

    public OwnerService() {

        loadFromFile();
    }

    // --------------------------------
    // Add Owner
    // --------------------------------

    public void addOwner(Owner owner) {

        ownerList.add(owner);

        saveToFile();
    }

    // --------------------------------
    // Get Owner List
    // --------------------------------

    public ArrayList<Owner> getOwnerList() {

        return ownerList;
    }

    // --------------------------------
    // Delete Owner
    // --------------------------------

    public void removeOwner(int index) {

        if (index >= 0 &&
                index < ownerList.size()) {

            ownerList.remove(index);

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

        for (Owner owner : ownerList) {

            data.append(
                    owner.getOwnerId()
            ).append("|");

            data.append(
                    owner.getName()
            ).append("|");

            data.append(
                    owner.getPhone()
            ).append("|");

            data.append(
                    owner.getEmail()
            ).append("|");

            data.append(
                    owner.getAddress()
            ).append("|");

            data.append(
                    owner.getPetName()
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

            if (parts.length == 6) {

                try {

                    Owner owner =
                            new Owner(

                                    Integer.parseInt(
                                            parts[0]
                                    ),

                                    parts[1],

                                    parts[2],

                                    parts[3],

                                    parts[4],

                                    parts[5]
                            );

                    ownerList.add(owner);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid owner ID."
                    );
                }
            }
        }
    }
}