package com.erick.expensetracker.budget;


import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
*DTO for budget creation requests.
* Contains only data that clients need to see when creating a budget(What they enter)
 * */

public class BudgetRequestDto {

    @NotBlank(message= "Category is required")
    private String category;

    @NotNull(message = "Amount is required")
    @DecimalMin(value ="0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    
    @NotNull(message = "Period type is required")
    @JsonAlias({"period","periodType"})
    private BudgetPeriodType periodType;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    public BudgetRequestDto() {}

    public BudgetRequestDto(String category, BigDecimal amount, BudgetPeriodType periodType,
                            LocalDate startDate, LocalDate endDate) {
        this.category = category;
        this.amount = amount;
        this.periodType = periodType;
        this.startDate = startDate;
        this.endDate = endDate;
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
        return "BudgetRequestDTO{" +
                "category=" + category + '\'' +
                "amount=" + amount + '\'' +
                "periodType=" + periodType + '\'' +
                "startDate=" + startDate + '\'' +
                "endDate=" + endDate + '\'' +
                '}';

    }
}