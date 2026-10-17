package ui;

import model.Expense;
import service.ExpenseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ExpensePanel extends JPanel {

    // Text Fields
    private JTextField txtId;
    private JTextField txtPetName;
    private JTextField txtAmount;
    private JTextField txtDate;
    private JTextField txtDescription;

    // Combo Box
    private JComboBox<String> cmbCategory;

    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    // Table
    private JTable table;
    private DefaultTableModel model;

    private JLabel lblTotal;
    // Service
    private ExpenseService expenseService =
            new ExpenseService();

    public ExpensePanel() {

        setLayout(new BorderLayout(10, 10));

        // --------------------------------
        // Title
        // --------------------------------

        JLabel title = new JLabel(
                "Expense Management",
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
        txtAmount = new JTextField();
        txtDate = new JTextField();
        txtDescription = new JTextField();

        cmbCategory = new JComboBox<>();

        cmbCategory.addItem("Food");
        cmbCategory.addItem("Medicine");
        cmbCategory.addItem("Grooming");
        cmbCategory.addItem("Veterinary Visit");
        cmbCategory.addItem("Accessories");

        formPanel.add(
                new JLabel("Expense ID")
        );
        formPanel.add(txtId);

        formPanel.add(
                new JLabel("Pet Name")
        );
        formPanel.add(txtPetName);

        formPanel.add(
                new JLabel("Category")
        );
        formPanel.add(cmbCategory);

        formPanel.add(
                new JLabel("Amount")
        );
        formPanel.add(txtAmount);

        formPanel.add(
                new JLabel("Date")
        );
        formPanel.add(txtDate);

        formPanel.add(
                new JLabel("Description")
        );
        formPanel.add(txtDescription);

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

                "Expense ID",
                "Pet Name",
                "Category",
                "Amount",
                "Date",
                "Description"

        };

        model = new DefaultTableModel(
                columns,
                0
        );

        table = new JTable(model);

        lblTotal = new JLabel("Total Expense: 0.00");

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


        JPanel bottomPanel = new JPanel(
                new BorderLayout()
        );

        bottomPanel.add(
                lblTotal,
                BorderLayout.WEST
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
        // --------------------------------
        // Add Button
        // --------------------------------

        btnAdd.addActionListener(e -> {

            try {

                Expense expense =
                        new Expense(

                                Integer.parseInt(
                                        txtId.getText()
                                ),

                                txtPetName.getText(),

                                cmbCategory
                                        .getSelectedItem()
                                        .toString(),

                                Double.parseDouble(
                                        txtAmount.getText()
                                ),

                                txtDate.getText(),

                                txtDescription.getText()
                        );

                expenseService.addExpense(
                        expense
                );
                expenseService.saveChanges();

                loadTable();

                clearFields();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "ID must be an integer and amount must be a number."
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

                    Expense expense =
                            expenseService
                                    .getExpenseList()
                                    .get(row);

                    expense.setExpenseId(
                            Integer.parseInt(
                                    txtId.getText()
                            )
                    );

                    expense.setPetName(
                            txtPetName.getText()
                    );

                    expense.setCategory(
                            cmbCategory
                                    .getSelectedItem()
                                    .toString()
                    );

                    expense.setAmount(
                            Double.parseDouble(
                                    txtAmount.getText()
                            )
                    );

                    expense.setDate(
                            txtDate.getText()
                    );

                    expense.setDescription(
                            txtDescription.getText()
                    );

                    expenseService.saveChanges();
                    loadTable();

                    clearFields();

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "ID must be an integer and amount must be a number."
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Select an expense first."
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
                                "Delete this expense?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (choice ==
                        JOptionPane.YES_OPTION) {

                    expenseService
                            .removeExpense(row);

                    expenseService.saveChanges();
                    loadTable();

                    clearFields();
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Select an expense first."
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

                        cmbCategory.setSelectedItem(
                                model.getValueAt(
                                        row, 2
                                ).toString()
                        );

                        txtAmount.setText(
                                model.getValueAt(
                                        row, 3
                                ).toString()
                        );

                        txtDate.setText(
                                model.getValueAt(
                                        row, 4
                                ).toString()
                        );

                        txtDescription.setText(
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

        double total = 0;

        for (Expense expense :
                expenseService.getExpenseList()) {

            model.addRow(new Object[]{

                    expense.getExpenseId(),
                    expense.getPetName(),
                    expense.getCategory(),
                    expense.getAmount(),
                    expense.getDate(),
                    expense.getDescription()

            });

            total += expense.getAmount();
        }

        lblTotal.setText(
                String.format(
                        "Total Expense: %.2f",
                        total
                )
        );
    }

    // --------------------------------
    // Clear Fields
    // --------------------------------

    private void clearFields() {

        txtId.setText("");
        txtPetName.setText("");
        txtAmount.setText("");
        txtDate.setText("");
        txtDescription.setText("");

        cmbCategory.setSelectedIndex(0);

        table.clearSelection();
    }
}