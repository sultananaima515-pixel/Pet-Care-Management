package ui;

import model.Activity;
import service.ActivityService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ActivityPanel extends JPanel {

    // Text Fields
    private JTextField txtId;
    private JTextField txtPetName;
    private JTextField txtDate;
    private JTextField txtDuration;
    private JTextField txtNotes;

    // Combo Box
    private JComboBox<String> cmbActivityType;

    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Table
    private JTable table;
    private DefaultTableModel model;

    // Service
    private ActivityService activityService =
            new ActivityService();

    public ActivityPanel() {

        setLayout(new BorderLayout(10, 10));

        // --------------------------------
        // Title
        // --------------------------------

        JLabel title = new JLabel(
                "Pet Activity Tracking",
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
                new JPanel(new GridLayout(6, 2, 10, 10));

        txtId = new JTextField();
        txtPetName = new JTextField();
        txtDate = new JTextField();
        txtDuration = new JTextField();
        txtNotes = new JTextField();

        cmbActivityType = new JComboBox<>();

        cmbActivityType.addItem("Walking");
        cmbActivityType.addItem("Playing");
        cmbActivityType.addItem("Training");
        cmbActivityType.addItem("Grooming");

        formPanel.add(
                new JLabel("Activity ID")
        );
        formPanel.add(txtId);

        formPanel.add(
                new JLabel("Pet Name")
        );
        formPanel.add(txtPetName);

        formPanel.add(
                new JLabel("Activity Type")
        );
        formPanel.add(cmbActivityType);

        formPanel.add(
                new JLabel("Date")
        );
        formPanel.add(txtDate);

        formPanel.add(
                new JLabel("Duration")
        );
        formPanel.add(txtDuration);

        formPanel.add(
                new JLabel("Notes")
        );
        formPanel.add(txtNotes);

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

                "Activity ID",
                "Pet Name",
                "Activity Type",
                "Date",
                "Duration",
                "Notes"

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
        // Add Button
        // --------------------------------

        btnAdd.addActionListener(e -> {

            try {

                Activity activity =
                        new Activity(

                                Integer.parseInt(
                                        txtId.getText()
                                ),

                                txtPetName.getText(),

                                cmbActivityType
                                        .getSelectedItem()
                                        .toString(),

                                txtDate.getText(),

                                txtDuration.getText(),

                                txtNotes.getText()
                        );

                activityService.addActivity(
                        activity
                );

                activityService.saveChanges();
                loadTable();

                clearFields();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Activity ID must be a number."
                );
            }
        });

        // --------------------------------
        // Update Button
        // --------------------------------

        btnUpdate.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row >= 0) {

                try {

                    Activity activity =
                            activityService
                                    .getActivityList()
                                    .get(row);

                    activity.setActivityId(
                            Integer.parseInt(
                                    txtId.getText()
                            )
                    );

                    activity.setPetName(
                            txtPetName.getText()
                    );

                    activity.setActivityType(
                            cmbActivityType
                                    .getSelectedItem()
                                    .toString()
                    );

                    activity.setActivityDate(
                            txtDate.getText()
                    );

                    activity.setDuration(
                            txtDuration.getText()
                    );

                    activity.setNotes(
                            txtNotes.getText()
                    );

                    activityService.saveChanges();
                    loadTable();

                    clearFields();

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Activity ID must be a number."
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Select an activity first."
                );
            }
        });

        // --------------------------------
        // Delete Button
        // --------------------------------

        btnDelete.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row >= 0) {

                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Delete this activity?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (choice ==
                        JOptionPane.YES_OPTION) {

                    activityService
                            .removeActivity(row);

                    activityService.saveChanges();
                    loadTable();

                    clearFields();
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Select an activity first."
                );
            }
        });

        // --------------------------------
        // Clear Button
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

                        cmbActivityType
                                .setSelectedItem(
                                        model.getValueAt(
                                                row, 2
                                        ).toString()
                                );

                        txtDate.setText(
                                model.getValueAt(
                                        row, 3
                                ).toString()
                        );

                        txtDuration.setText(
                                model.getValueAt(
                                        row, 4
                                ).toString()
                        );

                        txtNotes.setText(
                                model.getValueAt(
                                        row, 5
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

        for (Activity activity :
                activityService
                        .getActivityList()) {

            model.addRow(new Object[]{

                    activity.getActivityId(),
                    activity.getPetName(),
                    activity.getActivityType(),
                    activity.getActivityDate(),
                    activity.getDuration(),
                    activity.getNotes()

            });
        }
    }

    // --------------------------------
    // Clear Fields
    // --------------------------------

    private void clearFields() {

        txtId.setText("");
        txtPetName.setText("");
        txtDate.setText("");
        txtDuration.setText("");
        txtNotes.setText("");

        cmbActivityType.setSelectedIndex(0);

        table.clearSelection();
    }
}