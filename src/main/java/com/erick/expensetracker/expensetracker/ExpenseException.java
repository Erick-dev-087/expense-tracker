package com.erick.expensetracker.expensetracker;


public class ExpenseException extends RuntimeException{
    
/**
 * This constructor handles cases where the data has an issue
 * 
 */
    public ExpenseException(String message){
        super(message);

    }

/**
 * This constructor handles cases where we have a database/request issue
 */

    public ExpenseException(String message, Throwable cause){
        super(message, cause);
    }

    /**
     * This constructor handles wrapping another exception 
     */
    public ExpenseException(Throwable cause){
        super(cause);
    }

    /*Default constructor
     */
    public ExpenseException(){
        super();
    }
}