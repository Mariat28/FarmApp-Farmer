package com.example.glosales.Adapterclasses;

public class ExpenseClass {
    private String expense_name;
    private String expense_amount;

    public ExpenseClass(String expense_name, String expense_amount) {
        this.expense_name = expense_name;
        this.expense_amount = expense_amount;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public String getExpense_amount() {
        return expense_amount;
    }
}
