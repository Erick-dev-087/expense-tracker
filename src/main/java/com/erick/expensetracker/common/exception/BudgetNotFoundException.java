package com.erick.expensetracker.common.exception;

public class BudgetNotFoundException extends BudgetException {

    public BudgetNotFoundException(String message) {
        super(message);
    }

    public BudgetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BudgetNotFoundException(Throwable cause){
        super(cause);
    }
}