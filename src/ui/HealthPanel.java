package ui;

import model.HealthRecord;
import service.HealthService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class HealthPanel extends JPanel {

    // Text Fields
    private JTextField txtId;
    private JTextField txtPetName;
    private JTextField txtWeight;
    private JTextField txtTemperature;
    private JTextField txtDisease;
    private JTextField txtAllergy;
    private JTextField txtDate;
    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Table
    private JTable table;
    private DefaultTableModel model;

    // Service
    private HealthService healthService = new HealthService();

    public HealthPanel() {

        setLayout(new BorderLayout(10, 10));

        //-----------------------------
        // Title
        //-----------------------------
        JLabel title = new JLabel("Health Record Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        //-----------------------------
        // Form Panel
        //-----------------------------
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        txtId = new JTextField();
        txtPetName = new JTextField();
        txtWeight = new JTextField();
        txtTemperature = new JTextField();
        txtDisease = new JTextField();
        txtAllergy = new JTextField();
        txtDate = new JTextField();

        formPanel.add(new JLabel("Record ID"));
        formPanel.add(txtId);

        formPanel.add(new JLabel("Pet Name"));
        formPanel.add(txtPetName);

        formPanel.add(new JLabel("Weight"));
        formPanel.add(txtWeight);

        formPanel.add(new JLabel("Temperature"));
        formPanel.add(txtTemperature);

        formPanel.add(new JLabel("Disease"));
        formPanel.add(txtDisease);

        formPanel.add(new JLabel("Allergy"));
        formPanel.add(txtAllergy);

        formPanel.add(new JLabel("Checkup Date"));
        formPanel.add(txtDate);
        

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

                "Record ID",

                "Pet Name",

                "Weight",

                "Temperature",

                "Disease",

                "Allergy",

                "Checkup Date"

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

                    HealthRecord record = new HealthRecord(

                            Integer.parseInt(txtId.getText()),

                            txtPetName.getText(),

                            Double.parseDouble(txtWeight.getText()),

                            Double.parseDouble(txtTemperature.getText()),

                            txtDisease.getText(),

                            txtAllergy.getText(),

                            txtDate.getText()

                    );

                    healthService.addRecord(record);

                    healthService.saveChanges();
                    loadTable();

                    clearFields();
                });



        //-----------------------------
        // Update Button
        //-----------------------------
        btnUpdate.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                HealthRecord record =
                        healthService.getRecords().get(row);

                record.setRecordId(Integer.parseInt(txtId.getText()));

                record.setPetName(txtPetName.getText());

                record.setWeight(Double.parseDouble(txtWeight.getText()));

                record.setTemperature(Double.parseDouble(txtTemperature.getText()));

                record.setDisease(txtDisease.getText());

                record.setAllergy(txtAllergy.getText());

                record.setCheckupDate(txtDate.getText());

                healthService.saveChanges();

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

                healthService.deleteRecord(row);

                healthService.saveChanges();
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
                txtPetName.setText(model.getValueAt(row,1).toString());

                txtWeight.setText(model.getValueAt(row,2).toString());

                txtTemperature.setText(model.getValueAt(row,3).toString());

                txtDisease.setText(model.getValueAt(row,4).toString());

                txtAllergy.setText(model.getValueAt(row,5).toString());

                txtDate.setText(model.getValueAt(row,6).toString());


            }

        });

    }

    //-----------------------------
    // Load Table
    //-----------------------------

        private void loadTable(){

            model.setRowCount(0);

            for(HealthRecord record : healthService.getRecords()){

                model.addRow(new Object[]{

                        record.getRecordId(),

                        record.getPetName(),

                        record.getWeight(),

                        record.getTemperature(),

                        record.getDisease(),

                        record.getAllergy(),

                        record.getCheckupDate()

                });

            }

        }
    //-----------------------------
    // Clear Fields
    //-----------------------------
    private void clearFields() {

        txtId.setText("");
            txtPetName.setText("");

            txtWeight.setText("");

            txtTemperature.setText("");

            txtDisease.setText("");

            txtAllergy.setText("");

            txtDate.setText("");

    }

}