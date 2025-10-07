package com.erick.expensetracker.expensetracker;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for expense creation requests.
 * Contains only the data that clients need to send when creating an expense.
 */
public class ExpenseRequestDto {


    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @NotBlank(message = "Category is required")
    private String category;
    
    private String description;

    public ExpenseRequestDto() {}

    public ExpenseRequestDto(String title, BigDecimal amount, LocalDate date,String category, String description){
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public String getTitle(){return title;}
    public void setTitle(String title) {this.title = title;}

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "ExpenseRequestDto{" +
               "title='" + title + '\'' +
               ", amount=" + amount +
               ", date=" + date +
               ", category='" + category + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
