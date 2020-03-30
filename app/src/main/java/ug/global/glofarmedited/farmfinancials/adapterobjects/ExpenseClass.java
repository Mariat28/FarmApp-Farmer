package ug.global.glofarmedited.farmfinancials.adapterobjects;

public class ExpenseClass {
    private String expensename;
    private String expenseamount;

    public ExpenseClass() {
    }

    public ExpenseClass(String expensename, String expenseamount) {
        this.expensename = expensename;
        this.expenseamount = expenseamount;
    }

    public String getExpensename() {
        return expensename;
    }

    public String getExpenseamount() {
        return expenseamount;
    }
}
