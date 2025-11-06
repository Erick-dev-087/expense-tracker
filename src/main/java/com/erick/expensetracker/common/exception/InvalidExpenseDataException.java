package com.erick.expensetracker.common.exception;

// Professional format:
/**
 * Thrown when an expense contains invalid data.
 * 
 * This exception is used for validation failures such as:
 * - Negative amounts
 * - Empty or blank titles
 * - Invalid categories
 * - Future dates (when not allowed)
 * - Data format violations
 */

public class InvalidExpenseDataException extends ExpenseException{

    /*
     * Creates exception with custom message
     * @param message Description of the invalid data
     */
    
    public InvalidExpenseDataException(String message){
        super(message);
    }

    /**
     * Creates exception with message and underlying cause
     * @param message Description of the data that was invalid
     * @param cause The underlying exception that caused this
     */
    public InvalidExpenseDataException(String message, Throwable cause){
        super(message, cause);
    }

    /**
     * Creates exception wrapping another exception
     * @param cause The underlying exception
     */
    public InvalidExpenseDataException(Throwable cause){
        super(cause);
    }
}