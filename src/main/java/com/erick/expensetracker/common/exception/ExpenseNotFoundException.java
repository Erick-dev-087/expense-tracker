package com.erick.expensetracker.common.exception;

/*
 * Thrown when an expense cannot be found in the database
 * Used for GET,POST, DELETE, PUT requests on non-existent data 
 */

public class ExpenseNotFoundException extends ExpenseException{

    
    /*
     * Creates exception with custom message
     * @param message Description of what wasn't found
     */

    public ExpenseNotFoundException(String message){
        super(message);
    }
    /**
     * Creates exception with message and underlying cause
     * @param message Description of what wasn't found
     * @param cause The underlying exception that caused this
     */
    public ExpenseNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    /**
     * Creates exception wrapping another exception
     * @param cause The underlying exception
     */
    public ExpenseNotFoundException(Throwable cause){
        super(cause);
    }




}