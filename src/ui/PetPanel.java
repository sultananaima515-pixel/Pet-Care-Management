package ui;

import model.Pet;
import service.PetService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PetPanel extends JPanel {

    // Text Fields
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtBreed;
    private JTextField txtAge;

    // Combo Boxes
    private JComboBox<String> cmbGender;
    private JComboBox<String> cmbHealth;

    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Table
    private JTable table;
    private DefaultTableModel model;

    // Service
    private PetService petService = new PetService();

    public PetPanel() {

        setLayout(new BorderLayout(10, 10));

        //-----------------------------
        // Title
        //-----------------------------
        JLabel title = new JLabel("Pet Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        //-----------------------------
        // Form Panel
        //-----------------------------
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        txtId = new JTextField();
        txtName = new JTextField();
        txtBreed = new JTextField();
        txtAge = new JTextField();

        cmbGender = new JComboBox<>();
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");

        cmbHealth = new JComboBox<>();
        cmbHealth.addItem("Healthy");
        cmbHealth.addItem("Sick");

        formPanel.add(new JLabel("Pet ID"));
        formPanel.add(txtId);

        formPanel.add(new JLabel("Pet Name"));
        formPanel.add(txtName);

        formPanel.add(new JLabel("Breed"));
        formPanel.add(txtBreed);

        formPanel.add(new JLabel("Age"));
        formPanel.add(txtAge);

        formPanel.add(new JLabel("Gender"));
        formPanel.add(cmbGender);

        formPanel.add(new JLabel("Health"));
        formPanel.add(cmbHealth);

        //-----------------------------
        // Buttons
        //-----------------------------
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        //-----------------------------
        // Table
        //-----------------------------
        String[] columns = {
                "ID",
                "Name",
                "Breed",
                "Age",
                "Gender",
                "Health"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        //-----------------------------
        // Left Panel
        //-----------------------------
        JPanel leftPanel = new JPanel(new BorderLayout());

        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        //-----------------------------
        // Add Button
        //-----------------------------
        btnAdd.addActionListener(e -> {

            Pet pet = new Pet(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtBreed.getText(),
                    Integer.parseInt(txtAge.getText()),
                    cmbGender.getSelectedItem().toString(),
                    cmbHealth.getSelectedItem().toString()
            );

            petService.addPet(pet);
            petService.saveChanges();

            loadTable();

            clearFields();

        });

        //-----------------------------
        // Update Button
        //-----------------------------
        btnUpdate.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                Pet pet = petService.getPetList().get(row);

                pet.setPetId(Integer.parseInt(txtId.getText()));
                pet.setName(txtName.getText());
                pet.setBreed(txtBreed.getText());
                pet.setAge(Integer.parseInt(txtAge.getText()));
                pet.setGender(cmbGender.getSelectedItem().toString());
                pet.setHealthStatus(cmbHealth.getSelectedItem().toString());

                petService.saveChanges();
                loadTable();

                clearFields();
            }

        });

        //-----------------------------
        // Delete Button
        //-----------------------------
        btnDelete.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                petService.removePet(row);

                petService.saveChanges();
                loadTable();

                clearFields();
            }

        });

        //-----------------------------
        // Clear Button
        //-----------------------------
        btnClear.addActionListener(e -> clearFields());

        //-----------------------------
        // Table Selection
        //-----------------------------
        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                txtId.setText(model.getValueAt(row, 0).toString());
                txtName.setText(model.getValueAt(row, 1).toString());
                txtBreed.setText(model.getValueAt(row, 2).toString());
                txtAge.setText(model.getValueAt(row, 3).toString());

                cmbGender.setSelectedItem(model.getValueAt(row, 4));
                cmbHealth.setSelectedItem(model.getValueAt(row, 5));

            }

        });

    }

    //-----------------------------
    // Load Table
    //-----------------------------
    private void loadTable() {

        model.setRowCount(0);

        for (Pet pet : petService.getPetList()) {

            model.addRow(new Object[]{
                    pet.getPetId(),
                    pet.getName(),
                    pet.getBreed(),
                    pet.getAge(),
                    pet.getGender(),
                    pet.getHealthStatus()
            });

        }

    }

    //-----------------------------
    // Clear Fields
    //-----------------------------
    private void clearFields() {

        txtId.setText("");
        txtName.setText("");
        txtBreed.setText("");
        txtAge.setText("");

        cmbGender.setSelectedIndex(0);
        cmbHealth.setSelectedIndex(0);

    }

}