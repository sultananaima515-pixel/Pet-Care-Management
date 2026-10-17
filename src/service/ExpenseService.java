package service;

import model.Expense;

import java.util.ArrayList;

public class ExpenseService {

    private ArrayList<Expense> expenseList =
            new ArrayList<>();

    private final String FILE_NAME =
            "expenses.txt";

    public ExpenseService() {
        loadFromFile();
    }

    // Add
    public void addExpense(Expense expense) {

        expenseList.add(expense);

        saveToFile();
    }

    // Get
    public ArrayList<Expense> getExpenseList() {

        return expenseList;
    }

    // Delete
    public void removeExpense(int index) {

        if (index >= 0 &&
                index < expenseList.size()) {

            expenseList.remove(index);

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

        for (Expense expense :
                expenseList) {

            data.append(
                    expense.getExpenseId()
            ).append("|");

            data.append(
                    expense.getPetName()
            ).append("|");

            data.append(
                    expense.getCategory()
            ).append("|");

            data.append(
                    expense.getAmount()
            ).append("|");

            data.append(
                    expense.getDate()
            ).append("|");

            data.append(
                    expense.getDescription()
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

            if (parts.length == 6) {

                try {

                    Expense expense =
                            new Expense(

                                    Integer.parseInt(parts[0]),
                                    parts[1],
                                    parts[2],
                                    Double.parseDouble(parts[3]),
                                    parts[4],
                                    parts[5]
                            );

                    expenseList.add(expense);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid expense data."
                    );
                }
            }
        }
    }
}