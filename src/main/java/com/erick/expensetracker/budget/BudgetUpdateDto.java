package com.erick.expensetracker.budget;


import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for budget update requests.
 * Contains data that clients need when making an update(The things that they provide)
 *
 */

public class BudgetUpdateDto {

    @NotBlank(message= "Category is required")
    private String category;

    @NotNull(message = "Amount is required")
    @DecimalMin(value ="0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

   
    @NotNull(message = "Period type is required")
    private BudgetPeriodType periodType;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    public BudgetUpdateDto() {
    }

    public BudgetUpdateDto(String category, BigDecimal amount,
                           BudgetPeriodType periodType,
                           LocalDate endDate, LocalDate startDate) {
        this.category = category;
        this.amount = amount;
        this.periodType = periodType;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BudgetPeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(BudgetPeriodType periodType) {
        this.periodType = periodType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BudgetUpdateDto{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                ", periodType=" + periodType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
