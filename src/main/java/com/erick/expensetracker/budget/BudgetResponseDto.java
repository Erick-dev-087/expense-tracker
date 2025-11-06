package com.erick.expensetracker.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* DTO for budget retrieval requests.
* Contains only data the clients get to see when they make a get request(All the data including server generated)
 */

public class BudgetResponseDto {

    private Long id;
    private String category;
    private BigDecimal amount;
    private BudgetPeriodType periodType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BudgetStatus status;

    public BudgetResponseDto() {
    }

    public BudgetResponseDto(Long id, String category, BigDecimal amount,
                             BudgetPeriodType periodType, LocalDate startDate, LocalDate endDate,
                             LocalDateTime createdAt, LocalDateTime updatedAt, BudgetStatus status) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.periodType = periodType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BudgetStatus getStatus() {
        return status;
    }

    public void setStatus(BudgetStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BudgetResponseDto{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", periodType=" + periodType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                '}';
    }
}