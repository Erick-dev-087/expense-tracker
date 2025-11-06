package com.erick.expensetracker.common.exception;

/**
 * Thrown when an budget contains invalid data.
 *This exception is used for validation failures such as:
 * - Negative amounts
 * - Empty or blank titles
 * - Invalid categories
 * - Future dates (when not allowed)
 * - Data format violations
 */

public class InvalidBudgetDataException extends BudgetException {
    public InvalidBudgetDataException(String message) {
        super(message);

    }
    public InvalidBudgetDataException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidBudgetDataException(Throwable cause) {
        super(cause);
    }
}