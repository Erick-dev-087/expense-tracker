package com.erick.expensetracker.common.handler;

import com.erick.expensetracker.common.DTO.ErrorResponse;
import com.erick.expensetracker.common.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler that catches exceptions from all controllers
 * and converts them into consistent HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles ExpenseNotFoundException - when expense doesn't exist
     * Returns 404 NOT FOUND with detailed error information
     */
    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ExpenseNotFoundException e, WebRequest request) {
        logger.warn("Expense not found: {}", e.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
            "EXPENSE_NOT_FOUND",
            e.getMessage(),
            request.getDescription(false).replace("uri=", ""),
            HttpStatus.NOT_FOUND.value()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handles InvalidExpenseDataException - when request data is invalid
     * Returns 400 BAD REQUEST with validation error details
     */
    @ExceptionHandler(InvalidExpenseDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidData(InvalidExpenseDataException e, WebRequest request) {
        logger.warn("Invalid expense data: {}", e.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
            "INVALID_EXPENSE_DATA",
            e.getMessage(),
            request.getDescription(false).replace("uri=", ""),
            HttpStatus.BAD_REQUEST.value()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handles BudgetNotFoundException- where the budget is not found
     */
    @ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBudgetNotFound(BudgetNotFoundException e, WebRequest request) {
        logger.warn("Budget not found: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "BUDGET_NOT_FOUND",
                e.getMessage(),
                request.getDescription(false).replace("uri=",""),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handles InvalidBudgetDataException - where the budget has  invalid data
     */

    @ExceptionHandler(InvalidBudgetDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBudgetData(InvalidBudgetDataException e, WebRequest request) {
        logger.warn("Invalid budget data: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_BUDGET_DATA",
                e.getMessage(),
                request.getDescription(false).replace("uri=",""),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    /**
     * Handles all other unexpected exceptions
     * Returns 500 INTERNAL SERVER ERROR with safe error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception e, WebRequest request) {
        logger.error("Unexpected error occurred: {}", e.getMessage(), e);
        
        ErrorResponse errorResponse = new ErrorResponse(
            "INTERNAL_SERVER_ERROR",
            "An unexpected error occurred. Please try again later.", // Safe message for clients
            request.getDescription(false).replace("uri=", ""),
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}