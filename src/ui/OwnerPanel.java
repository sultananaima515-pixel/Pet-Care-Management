package ui;

import model.Owner;
import service.OwnerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OwnerPanel extends JPanel {

    // Text Fields
    private JTextField txtId;
    private JTextField txtOwnerName;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JTextField txtPetName;

    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Table
    private JTable table;
    private DefaultTableModel model;

    // Service
    private OwnerService ownerService = new OwnerService();

    public OwnerPanel() {

        setLayout(new BorderLayout(10, 10));

        // --------------------------------
        // Title
        // --------------------------------

        JLabel title = new JLabel(
                "Owner Management",
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
                        new GridLayout(6, 2, 10, 10)
                );

        txtId = new JTextField();
        txtOwnerName = new JTextField();
        txtPhone = new JTextField();
        txtEmail = new JTextField();
        txtAddress = new JTextField();
        txtPetName = new JTextField();

        formPanel.add(new JLabel("Owner ID"));
        formPanel.add(txtId);

        formPanel.add(new JLabel("Owner Name"));
        formPanel.add(txtOwnerName);

        formPanel.add(new JLabel("Phone"));
        formPanel.add(txtPhone);

        formPanel.add(new JLabel("Email"));
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Address"));
        formPanel.add(txtAddress);

        formPanel.add(new JLabel("Pet Name"));
        formPanel.add(txtPetName);

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

                "Owner ID",
                "Owner Name",
                "Phone",
                "Email",
                "Address",
                "Pet Name"

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
        // ADD BUTTON
        // --------------------------------

        btnAdd.addActionListener(e -> {

            try {

                Owner owner = new Owner(

                        Integer.parseInt(
                                txtId.getText()
                        ),

                        txtOwnerName.getText(),

                        txtPhone.getText(),

                        txtEmail.getText(),

                        txtAddress.getText(),

                        txtPetName.getText()
                );

                ownerService.addOwner(owner);

                ownerService.saveChanges();
                loadTable();

                clearFields();

                JOptionPane.showMessageDialog(
                        this,
                        "Owner added successfully."
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Owner ID must be a number."
                );
            }
        });

        // --------------------------------
        // UPDATE BUTTON
        // --------------------------------

        btnUpdate.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                try {

                    Owner owner =
                            ownerService
                                    .getOwnerList()
                                    .get(row);

                    owner.setOwnerId(
                            Integer.parseInt(
                                    txtId.getText()
                            )
                    );

                    owner.setName(
                            txtOwnerName.getText()
                    );

                    owner.setPhone(
                            txtPhone.getText()
                    );

                    owner.setEmail(
                            txtEmail.getText()
                    );

                    owner.setAddress(
                            txtAddress.getText()
                    );

                    owner.setPetName(
                            txtPetName.getText()
                    );

                    // Save updated data
                    ownerService.saveChanges();

                    loadTable();

                    clearFields();

                    JOptionPane.showMessageDialog(
                            this,
                            "Owner updated successfully."
                    );

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Owner ID must be a number."
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an owner first."
                );
            }
        });

        // --------------------------------
        // DELETE BUTTON
        // --------------------------------

        btnDelete.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this owner?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (choice ==
                        JOptionPane.YES_OPTION) {

                    ownerService.removeOwner(row);

                    ownerService.saveChanges();

                    loadTable();

                    clearFields();

                    JOptionPane.showMessageDialog(
                            this,
                            "Owner deleted successfully."
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an owner first."
                );
            }
        });

        // --------------------------------
        // CLEAR BUTTON
        // --------------------------------

        btnClear.addActionListener(
                e -> clearFields()
        );

        // --------------------------------
        // TABLE SELECTION
        // --------------------------------

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

                        txtOwnerName.setText(
                                model.getValueAt(
                                        row,
                                        1
                                ).toString()
                        );

                        txtPhone.setText(
                                model.getValueAt(
                                        row,
                                        2
                                ).toString()
                        );

                        txtEmail.setText(
                                model.getValueAt(
                                        row,
                                        3
                                ).toString()
                        );

                        txtAddress.setText(
                                model.getValueAt(
                                        row,
                                        4
                                ).toString()
                        );

                        txtPetName.setText(
                                model.getValueAt(
                                        row,
                                        5
                                ).toString()
                        );
                    }
                });

        // Load existing data when panel opens
        loadTable();
    }

    // --------------------------------
    // LOAD TABLE
    // --------------------------------

    private void loadTable() {

        model.setRowCount(0);

        for (Owner owner :
                ownerService.getOwnerList()) {

            model.addRow(new Object[]{

                    owner.getOwnerId(),
                    owner.getName(),
                    owner.getPhone(),
                    owner.getEmail(),
                    owner.getAddress(),
                    owner.getPetName()

            });
        }
    }

    // --------------------------------
    // CLEAR FIELDS
    // --------------------------------

    private void clearFields() {

        txtId.setText("");
        txtOwnerName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtPetName.setText("");

        table.clearSelection();
    }
}