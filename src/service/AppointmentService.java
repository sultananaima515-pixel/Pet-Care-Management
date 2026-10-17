package service;

import model.Appointment;

import java.util.ArrayList;

public class AppointmentService {

    private ArrayList<Appointment> appointmentList =
            new ArrayList<>();

    private final String FILE_NAME =
            "appointments.txt";

    public AppointmentService() {
        loadFromFile();
    }

    // Add
    public void addAppointment(
            Appointment appointment) {

        appointmentList.add(appointment);

        saveToFile();
    }

    // Get
    public ArrayList<Appointment>
    getAppointmentList() {

        return appointmentList;
    }

    // Delete
    public void removeAppointment(int index) {

        if (index >= 0 &&
                index < appointmentList.size()) {

            appointmentList.remove(index);

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

        for (Appointment appointment :
                appointmentList) {

            data.append(
                    appointment.getAppointmentId()
            ).append("|");

            data.append(
                    appointment.getPetName()
            ).append("|");

            data.append(
                    appointment.getDoctorName()
            ).append("|");

            data.append(
                    appointment.getAppointmentDate()
            ).append("|");

            data.append(
                    appointment.getAppointmentTime()
            ).append("|");

            data.append(
                    appointment.getPurpose()
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

                    Appointment appointment =
                            new Appointment(

                                    Integer.parseInt(parts[0]),
                                    parts[1],
                                    parts[2],
                                    parts[3],
                                    parts[4],
                                    parts[5],
                                    parts[6]
                            );

                    appointmentList.add(
                            appointment
                    );

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid appointment data."
                    );
                }
            }
        }
    }

}