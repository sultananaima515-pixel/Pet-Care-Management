package ui;

import model.Appointment;
import service.AppointmentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppointmentPanel extends JPanel {

    // Text Fields
    private JTextField txtId;
    private JTextField txtPetName;
    private JTextField txtDoctor;
    private JTextField txtDate;
    private JTextField txtTime;
    private JTextField txtPurpose;

    // Combo Box
    private JComboBox<String> cmbStatus;

    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Table
    private JTable table;
    private DefaultTableModel model;

    // Service
    private AppointmentService appointmentService =
            new AppointmentService();

    public AppointmentPanel() {

        setLayout(new BorderLayout(10, 10));

        // --------------------------------
        // Title
        // --------------------------------

        JLabel title = new JLabel(
                "Veterinary Appointment Management",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(title, BorderLayout.NORTH);

        // --------------------------------
        // Form Panel
        // --------------------------------

        JPanel formPanel =
                new JPanel(new GridLayout(7, 2, 10, 10));

        txtId = new JTextField();
        txtPetName = new JTextField();
        txtDoctor = new JTextField();
        txtDate = new JTextField();
        txtTime = new JTextField();
        txtPurpose = new JTextField();

        cmbStatus = new JComboBox<>();

        cmbStatus.addItem("Scheduled");
        cmbStatus.addItem("Completed");
        cmbStatus.addItem("Cancelled");

        formPanel.add(
                new JLabel("Appointment ID")
        );
        formPanel.add(txtId);

        formPanel.add(
                new JLabel("Pet Name")
        );
        formPanel.add(txtPetName);

        formPanel.add(
                new JLabel("Doctor Name")
        );
        formPanel.add(txtDoctor);

        formPanel.add(
                new JLabel("Appointment Date")
        );
        formPanel.add(txtDate);

        formPanel.add(
                new JLabel("Appointment Time")
        );
        formPanel.add(txtTime);

        formPanel.add(
                new JLabel("Purpose")
        );
        formPanel.add(txtPurpose);

        formPanel.add(
                new JLabel("Status")
        );
        formPanel.add(cmbStatus);

        // --------------------------------
        // Buttons
        // --------------------------------

        btnAdd = new JButton("Book");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Cancel");
        btnClear = new JButton("Clear");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // --------------------------------
        // Table
        // --------------------------------

        String[] columns = {

                "Appointment ID",
                "Pet Name",
                "Doctor",
                "Date",
                "Time",
                "Purpose",
                "Status"

        };

        model = new DefaultTableModel(
                columns,
                0
        );

        table = new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(table);

        // --------------------------------
        // Left Panel
        // --------------------------------

        JPanel leftPanel =
                new JPanel(new BorderLayout());

        leftPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        leftPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(
                leftPanel,
                BorderLayout.WEST
        );

        add(
                scrollPane,
                BorderLayout.CENTER
        );

        // --------------------------------
        // Add / Book Appointment
        // --------------------------------

        btnAdd.addActionListener(e -> {

            try {

                Appointment appointment =
                        new Appointment(

                                Integer.parseInt(
                                        txtId.getText()
                                ),

                                txtPetName.getText(),

                                txtDoctor.getText(),

                                txtDate.getText(),

                                txtTime.getText(),

                                txtPurpose.getText(),

                                cmbStatus
                                        .getSelectedItem()
                                        .toString()
                        );

                appointmentService
                        .addAppointment(
                                appointment
                        );

                appointmentService.saveChanges();
                loadTable();

                clearFields();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment ID must be a number."
                );
            }

        });

        // --------------------------------
        // Update Appointment
        // --------------------------------

        btnUpdate.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row >= 0) {

                try {

                    Appointment appointment =
                            appointmentService
                                    .getAppointmentList()
                                    .get(row);

                    appointment.setAppointmentId(
                            Integer.parseInt(
                                    txtId.getText()
                            )
                    );

                    appointment.setPetName(
                            txtPetName.getText()
                    );

                    appointment.setDoctorName(
                            txtDoctor.getText()
                    );

                    appointment.setAppointmentDate(
                            txtDate.getText()
                    );

                    appointment.setAppointmentTime(
                            txtTime.getText()
                    );

                    appointment.setPurpose(
                            txtPurpose.getText()
                    );

                    appointment.setStatus(
                            cmbStatus
                                    .getSelectedItem()
                                    .toString()
                    );

                    appointmentService.saveChanges();
                    loadTable();

                    clearFields();

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Appointment ID must be a number."
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Select an appointment first."
                );
            }

        });

        // --------------------------------
        // Cancel Appointment
        // --------------------------------

        btnDelete.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row >= 0) {

                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Cancel this appointment?",
                                "Confirm Cancellation",
                                JOptionPane.YES_NO_OPTION
                        );

                if (choice ==
                        JOptionPane.YES_OPTION) {

                    Appointment appointment =
                            appointmentService
                                    .getAppointmentList()
                                    .get(row);

                    appointment.setStatus(
                            "Cancelled"
                    );

                    appointmentService.saveChanges();
                    loadTable();

                    clearFields();
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Select an appointment first."
                );
            }

        });

        // --------------------------------
        // Clear
        // --------------------------------

        btnClear.addActionListener(
                e -> clearFields()
        );

        // --------------------------------
        // Table Selection
        // --------------------------------

        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    int row =
                            table.getSelectedRow();

                    if (row >= 0) {

                        txtId.setText(
                                model.getValueAt(
                                        row, 0
                                ).toString()
                        );

                        txtPetName.setText(
                                model.getValueAt(
                                        row, 1
                                ).toString()
                        );

                        txtDoctor.setText(
                                model.getValueAt(
                                        row, 2
                                ).toString()
                        );

                        txtDate.setText(
                                model.getValueAt(
                                        row, 3
                                ).toString()
                        );

                        txtTime.setText(
                                model.getValueAt(
                                        row, 4
                                ).toString()
                        );

                        txtPurpose.setText(
                                model.getValueAt(
                                        row, 5
                                ).toString()
                        );

                        cmbStatus.setSelectedItem(
                                model.getValueAt(
                                        row, 6
                                ).toString()
                        );
                    }

                });
    }

    // --------------------------------
    // Load Table
    // --------------------------------

    private void loadTable() {

        model.setRowCount(0);

        for (Appointment appointment :
                appointmentService
                        .getAppointmentList()) {

            model.addRow(new Object[]{

                    appointment.getAppointmentId(),
                    appointment.getPetName(),
                    appointment.getDoctorName(),
                    appointment.getAppointmentDate(),
                    appointment.getAppointmentTime(),
                    appointment.getPurpose(),
                    appointment.getStatus()

            });
        }
    }

    // --------------------------------
    // Clear Fields
    // --------------------------------

    private void clearFields() {

        txtId.setText("");
        txtPetName.setText("");
        txtDoctor.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtPurpose.setText("");

        cmbStatus.setSelectedIndex(0);

        table.clearSelection();
    }
}