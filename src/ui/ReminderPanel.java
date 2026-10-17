package ui;

import model.Reminder;
import service.ReminderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReminderPanel extends JPanel {

    // Text Fields
    private JTextField txtId;
    private JTextField txtPetName;
    private JTextField txtDate;
    private JTextField txtTime;
    private JTextField txtDescription;

    // Combo Boxes
    private JComboBox<String> cmbType;
    private JComboBox<String> cmbStatus;

    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Table
    private JTable table;
    private DefaultTableModel model;

    private JLabel lblPending;
    // Service
    private ReminderService reminderService =
            new ReminderService();

    public ReminderPanel() {

        setLayout(new BorderLayout(10, 10));

        // --------------------------------
        // Title
        // --------------------------------

        JLabel title = new JLabel(
                "Reminder & Notification Management",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(title, BorderLayout.NORTH);

        // --------------------------------
        // Form
        // --------------------------------

        JPanel formPanel =
                new JPanel(new GridLayout(7, 2, 10, 10));

        txtId = new JTextField();
        txtPetName = new JTextField();
        txtDate = new JTextField();
        txtTime = new JTextField();
        txtDescription = new JTextField();

        cmbType = new JComboBox<>();

        cmbType.addItem("Feeding");
        cmbType.addItem("Vaccination");
        cmbType.addItem("Health Checkup");
        cmbType.addItem("Appointment");

        cmbStatus = new JComboBox<>();

        cmbStatus.addItem("Pending");
        cmbStatus.addItem("Completed");

        formPanel.add(
                new JLabel("Reminder ID")
        );
        formPanel.add(txtId);

        formPanel.add(
                new JLabel("Pet Name")
        );
        formPanel.add(txtPetName);

        formPanel.add(
                new JLabel("Reminder Type")
        );
        formPanel.add(cmbType);

        formPanel.add(
                new JLabel("Date")
        );
        formPanel.add(txtDate);

        formPanel.add(
                new JLabel("Time")
        );
        formPanel.add(txtTime);

        formPanel.add(
                new JLabel("Description")
        );
        formPanel.add(txtDescription);

        formPanel.add(
                new JLabel("Status")
        );
        formPanel.add(cmbStatus);

        // --------------------------------
        // Buttons
        // --------------------------------

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
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

                "Reminder ID",
                "Pet Name",
                "Type",
                "Date",
                "Time",
                "Description",
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


//        JPanel bottomPanel =
//                new JPanel(new BorderLayout());
//        bottomPanel.add(
//                lblPending,
//                BorderLayout.WEST
//        );
//
//        add(
//                bottomPanel,
//                BorderLayout.SOUTH
//        );
        // --------------------------------
        // Add
        // --------------------------------

        btnAdd.addActionListener(e -> {

            try {

                Reminder reminder =
                        new Reminder(

                                Integer.parseInt(
                                        txtId.getText()
                                ),

                                txtPetName.getText(),

                                cmbType
                                        .getSelectedItem()
                                        .toString(),

                                txtDate.getText(),

                                txtTime.getText(),

                                txtDescription.getText(),

                                cmbStatus
                                        .getSelectedItem()
                                        .toString()
                        );

                reminderService.addReminder(
                        reminder
                );

                reminderService.saveChanges();
                loadTable();

                clearFields();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Reminder ID must be a number."
                );
            }
        });

        // --------------------------------
        // Update
        // --------------------------------

        btnUpdate.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row >= 0) {

                try {

                    Reminder reminder =
                            reminderService
                                    .getReminderList()
                                    .get(row);

                    reminder.setReminderId(
                            Integer.parseInt(
                                    txtId.getText()
                            )
                    );

                    reminder.setPetName(
                            txtPetName.getText()
                    );

                    reminder.setReminderType(
                            cmbType
                                    .getSelectedItem()
                                    .toString()
                    );

                    reminder.setReminderDate(
                            txtDate.getText()
                    );

                    reminder.setReminderTime(
                            txtTime.getText()
                    );

                    reminder.setDescription(
                            txtDescription.getText()
                    );

                    reminder.setStatus(
                            cmbStatus
                                    .getSelectedItem()
                                    .toString()
                    );

                    reminderService.saveChanges();

                    loadTable();

                    clearFields();

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Reminder ID must be a number."
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a reminder first."
                );
            }
        });

        // --------------------------------
        // Delete
        // --------------------------------

        btnDelete.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row >= 0) {

                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Delete this reminder?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (choice ==
                        JOptionPane.YES_OPTION) {

                    reminderService
                            .removeReminder(row);

                    reminderService.saveChanges();
                    loadTable();

                    clearFields();
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a reminder first."
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

                        cmbType.setSelectedItem(
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

                        txtDescription.setText(
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

        int pending = 0;

        for (Reminder reminder :
                reminderService.getReminderList()) {

            model.addRow(new Object[]{

                    reminder.getReminderId(),
                    reminder.getPetName(),
                    reminder.getReminderType(),
                    reminder.getReminderDate(),
                    reminder.getReminderTime(),
                    reminder.getDescription(),
                    reminder.getStatus()

            });

            if (reminder.getStatus()
                    .equals("Pending")) {

                pending++;
            }
        }

        lblPending.setText(
                "Pending Reminders: " + pending
        );
    }
    // --------------------------------
    // Clear Fields
    // --------------------------------

    private void clearFields() {

        txtId.setText("");
        txtPetName.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtDescription.setText("");

        cmbType.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);

        table.clearSelection();
    }
}