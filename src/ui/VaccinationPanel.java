package ui;

import model.VaccinationRecord;
import service.VaccinationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VaccinationPanel extends JPanel {

    // Text Fields
    private JTextField txtId;
    private JTextField txtPetName;
    private JTextField txtVaccineName;
    private JTextField txtVaccinationDate;
    private JTextField txtDueDate;
    private JTextField txtVeterinarian;

    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Table
    private JTable table;
    private DefaultTableModel model;

    // Service
    private VaccinationService vaccinationService =
            new VaccinationService();

    public VaccinationPanel() {

        setLayout(new BorderLayout(10, 10));

        // -----------------------------
        // Title
        // -----------------------------

        JLabel title = new JLabel(
                "Vaccination Management",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);

        // -----------------------------
        // Form Panel
        // -----------------------------

        JPanel formPanel =
                new JPanel(new GridLayout(6, 2, 10, 10));

        txtId = new JTextField();
        txtPetName = new JTextField();
        txtVaccineName = new JTextField();
        txtVaccinationDate = new JTextField();
        txtDueDate = new JTextField();
        txtVeterinarian = new JTextField();

        formPanel.add(new JLabel("Vaccination ID"));
        formPanel.add(txtId);

        formPanel.add(new JLabel("Pet Name"));
        formPanel.add(txtPetName);

        formPanel.add(new JLabel("Vaccine Name"));
        formPanel.add(txtVaccineName);

        formPanel.add(new JLabel("Vaccination Date"));
        formPanel.add(txtVaccinationDate);

        formPanel.add(new JLabel("Due Date"));
        formPanel.add(txtDueDate);

        formPanel.add(new JLabel("Veterinarian"));
        formPanel.add(txtVeterinarian);

        // -----------------------------
        // Buttons
        // -----------------------------

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // -----------------------------
        // Table
        // -----------------------------

        String[] columns = {

                "Vaccination ID",
                "Pet Name",
                "Vaccine Name",
                "Vaccination Date",
                "Due Date",
                "Veterinarian"

        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(table);

        // -----------------------------
        // Left Panel
        // -----------------------------

        JPanel leftPanel =
                new JPanel(new BorderLayout());

        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // -----------------------------
        // Add Button
        // -----------------------------

        btnAdd.addActionListener(e -> {

            try {

                VaccinationRecord record =
                        new VaccinationRecord(

                                Integer.parseInt(
                                        txtId.getText()),

                                txtPetName.getText(),

                                txtVaccineName.getText(),

                                txtVaccinationDate.getText(),

                                txtDueDate.getText(),

                                txtVeterinarian.getText()
                        );

                vaccinationService
                        .addVaccination(record);

                vaccinationService.saveChanges();
                loadTable();

                clearFields();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vaccination ID must be a number."
                );
            }

        });

        // -----------------------------
        // Update Button
        // -----------------------------

        btnUpdate.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                try {

                    VaccinationRecord record =
                            vaccinationService
                                    .getVaccinationList()
                                    .get(row);

                    record.setVaccinationId(
                            Integer.parseInt(
                                    txtId.getText()));

                    record.setPetName(
                            txtPetName.getText());

                    record.setVaccineName(
                            txtVaccineName.getText());

                    record.setVaccinationDate(
                            txtVaccinationDate.getText());

                    record.setDueDate(
                            txtDueDate.getText());

                    record.setVeterinarian(
                            txtVeterinarian.getText());


                    vaccinationService.saveChanges();
                    loadTable();

                    clearFields();

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Vaccination ID must be a number."
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a record to update."
                );
            }

        });

        // -----------------------------
        // Delete Button
        // -----------------------------

        btnDelete.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Delete this vaccination record?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (choice ==
                        JOptionPane.YES_OPTION) {

                    vaccinationService
                            .removeVaccination(row);

                    vaccinationService.saveChanges();
                    loadTable();

                    clearFields();
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a record to delete."
                );
            }

        });

        // -----------------------------
        // Clear Button
        // -----------------------------

        btnClear.addActionListener(
                e -> clearFields()
        );

        // -----------------------------
        // Table Selection
        // -----------------------------

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

                        txtVaccineName.setText(
                                model.getValueAt(
                                        row, 2
                                ).toString()
                        );

                        txtVaccinationDate.setText(
                                model.getValueAt(
                                        row, 3
                                ).toString()
                        );

                        txtDueDate.setText(
                                model.getValueAt(
                                        row, 4
                                ).toString()
                        );

                        txtVeterinarian.setText(
                                model.getValueAt(
                                        row, 5
                                ).toString()
                        );
                    }

                });


    }

    // -----------------------------
    // Load Table
    // -----------------------------

    private void loadTable() {

        model.setRowCount(0);

        for (VaccinationRecord record :
                vaccinationService
                        .getVaccinationList()) {

            model.addRow(new Object[]{

                    record.getVaccinationId(),
                    record.getPetName(),
                    record.getVaccineName(),
                    record.getVaccinationDate(),
                    record.getDueDate(),
                    record.getVeterinarian()

            });
        }
    }

    // -----------------------------
    // Clear Fields
    // -----------------------------

    private void clearFields() {

        txtId.setText("");
        txtPetName.setText("");
        txtVaccineName.setText("");
        txtVaccinationDate.setText("");
        txtDueDate.setText("");
        txtVeterinarian.setText("");

        table.clearSelection();
    }
}