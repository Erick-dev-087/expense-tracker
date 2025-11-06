package com.erick.expensetracker.common.exception;

/**
 * Base exception for all budget-related exception
 */

public class BudgetException extends RuntimeException {

    /**
     * This constructor creates an exception with a custom message
     * @param message Description of what went wrong
     */
    public BudgetException(String message) {
        super(message);
    }

    /**
     * This constructor creates an exception with a custom message and the underlying cause
     * @param message Description of what went wrong
     * @param cause The underlying exception that caused this
     */
    public BudgetException(String message, Throwable cause) {
        super(message, cause);

    }

    /**
     * Creates exception wrapping another exception
     * @param cause
     */

    public BudgetException(Throwable cause) {
        super(cause);
    }
}