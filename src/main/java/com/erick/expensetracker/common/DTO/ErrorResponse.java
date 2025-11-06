package com.erick.expensetracker.common.DTO;

import java.time.LocalDateTime;

/**
 * Standardized error response format for all API errors.
 * Provides consistent structure across all endpoints.
 */
public class ErrorResponse {
    
    private String error;           // Error code (e.g., "EXPENSE_NOT_FOUND")
    private String message;         // Human-readable description of the problem
    private LocalDateTime timestamp; // When the error occurred
    private String path;            // Which endpoint caused the error
    private int status;             // HTTP status code
    
    // Constructor for creating error responses
    public ErrorResponse(String error, String message, String path, int status) {
        this.error = error;
        this.message = message;
        this.path = path;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and setters
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    
    @Override
    public String toString() {
        return "ErrorResponse{" +
               "error='" + error + '\'' +
               ", message='" + message + '\'' +
               ", timestamp=" + timestamp +
               ", path='" + path + '\'' +
               ", status=" + status +
               '}';
    }
}