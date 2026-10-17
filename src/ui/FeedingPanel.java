package ui;

import model.FeedingSchedule;
import service.FeedingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FeedingPanel extends JPanel {

    // --------------------------------
    // Text Fields
    // --------------------------------

    private JTextField txtId;
    private JTextField txtPetName;
    private JTextField txtFoodType;
    private JTextField txtTime;
    private JTextField txtFrequency;

    // --------------------------------
    // Buttons
    // --------------------------------

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // --------------------------------
    // Table
    // --------------------------------

    private JTable table;
    private DefaultTableModel model;

    // --------------------------------
    // Service
    // --------------------------------

    private FeedingService feedingService =
            new FeedingService();


    // ==============================================
    // Constructor
    // ==============================================

    public FeedingPanel() {

        setLayout(new BorderLayout(10, 10));


        // --------------------------------
        // Title
        // --------------------------------

        JLabel title = new JLabel(
                "Feeding Schedule Management",
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
                new JPanel(
                        new GridLayout(5, 2, 10, 10)
                );


        txtId = new JTextField();
        txtPetName = new JTextField();
        txtFoodType = new JTextField();
        txtTime = new JTextField();
        txtFrequency = new JTextField();


        formPanel.add(
                new JLabel("Schedule ID")
        );

        formPanel.add(txtId);


        formPanel.add(
                new JLabel("Pet Name")
        );

        formPanel.add(txtPetName);


        formPanel.add(
                new JLabel("Food Type")
        );

        formPanel.add(txtFoodType);


        formPanel.add(
                new JLabel("Feeding Time")
        );

        formPanel.add(txtTime);


        formPanel.add(
                new JLabel("Frequency")
        );

        formPanel.add(txtFrequency);


        // --------------------------------
        // Buttons
        // --------------------------------

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");


        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);


        // --------------------------------
        // Table
        // --------------------------------

        String[] columns = {

                "Schedule ID",
                "Pet Name",
                "Food Type",
                "Feeding Time",
                "Frequency"

        };


        model =
                new DefaultTableModel(
                        columns,
                        0
                );


        table =
                new JTable(model);


        JScrollPane scrollPane =
                new JScrollPane(table);


        // --------------------------------
        // Left Panel
        // --------------------------------

        JPanel leftPanel =
                new JPanel(
                        new BorderLayout()
                );


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


        // ==============================================
        // ADD BUTTON
        // ==============================================

        btnAdd.addActionListener(e -> {

            try {

                int id =
                        Integer.parseInt(
                                txtId.getText()
                        );


                FeedingSchedule schedule =
                        new FeedingSchedule(

                                id,

                                txtPetName.getText(),

                                txtFoodType.getText(),

                                txtTime.getText(),

                                txtFrequency.getText()

                        );


                feedingService.addSchedule(
                        schedule
                );


                feedingService.saveChanges();
                loadTable();

                clearFields();


                JOptionPane.showMessageDialog(
                        this,
                        "Feeding schedule added successfully."
                );


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Schedule ID must be a number."
                );

            }

        });


        // ==============================================
        // UPDATE BUTTON
        // ==============================================

        btnUpdate.addActionListener(e -> {

            int row =
                    table.getSelectedRow();


            if (row >= 0) {

                try {

                    FeedingSchedule schedule =
                            feedingService
                                    .getFeedingList()
                                    .get(row);


                    schedule.setScheduleId(
                            Integer.parseInt(
                                    txtId.getText()
                            )
                    );


                    schedule.setPetName(
                            txtPetName.getText()
                    );


                    schedule.setFoodType(
                            txtFoodType.getText()
                    );


                    schedule.setFeedingTime(
                            txtTime.getText()
                    );


                    schedule.setFrequency(
                            txtFrequency.getText()
                    );





                    feedingService.saveChanges();
                    loadTable();

                    clearFields();


                    JOptionPane.showMessageDialog(
                            this,
                            "Schedule updated successfully."
                    );


                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Schedule ID must be a number."
                    );

                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a schedule first."
                );

            }

        });


        // ==============================================
        // DELETE BUTTON
        // ==============================================

        btnDelete.addActionListener(e -> {

            int row =
                    table.getSelectedRow();


            if (row >= 0) {

                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Delete this feeding schedule?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );


                if (choice ==
                        JOptionPane.YES_OPTION) {

                    feedingService.removeSchedule(
                            row
                    );

                    feedingService.saveChanges();

                    loadTable();

                    clearFields();


                    JOptionPane.showMessageDialog(
                            this,
                            "Schedule deleted successfully."
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a schedule first."
                );

            }

        });


        // ==============================================
        // CLEAR BUTTON
        // ==============================================

        btnClear.addActionListener(
                e -> clearFields()
        );


        // ==============================================
        // TABLE SELECTION
        // ==============================================

        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    int row =
                            table.getSelectedRow();


                    if (row >= 0) {

                        txtId.setText(
                                model.getValueAt(
                                        row,
                                        0
                                ).toString()
                        );


                        txtPetName.setText(
                                model.getValueAt(
                                        row,
                                        1
                                ).toString()
                        );


                        txtFoodType.setText(
                                model.getValueAt(
                                        row,
                                        2
                                ).toString()
                        );


                        txtTime.setText(
                                model.getValueAt(
                                        row,
                                        3
                                ).toString()
                        );


                        txtFrequency.setText(
                                model.getValueAt(
                                        row,
                                        4
                                ).toString()
                        );

                    }

                });


        // --------------------------------
        // Load Existing Data
        // --------------------------------

        loadTable();
    }


    // ==============================================
    // LOAD TABLE
    // ==============================================

    private void loadTable() {

        model.setRowCount(0);


        for (FeedingSchedule schedule :
                feedingService.getFeedingList()) {

            model.addRow(
                    new Object[]{

                            schedule.getScheduleId(),

                            schedule.getPetName(),

                            schedule.getFoodType(),

                            schedule.getFeedingTime(),

                            schedule.getFrequency()

                    }
            );

        }

    }


    // ==============================================
    // CLEAR FIELDS
    // ==============================================

    private void clearFields() {

        txtId.setText("");

        txtPetName.setText("");

        txtFoodType.setText("");

        txtTime.setText("");

        txtFrequency.setText("");

        table.clearSelection();
    }

}