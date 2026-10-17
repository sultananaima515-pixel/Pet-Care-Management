package model;

public class Expense {

    private int expenseId;
    private String petName;
    private String category;
    private double amount;
    private String date;
    private String description;

    public Expense() {
    }

    public Expense(int expenseId,
                   String petName,
                   String category,
                   double amount,
                   String date,
                   String description) {

        this.expenseId = expenseId;
        this.petName = petName;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}