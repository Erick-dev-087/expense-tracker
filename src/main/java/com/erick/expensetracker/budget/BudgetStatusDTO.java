package com.erick.expensetracker.budget;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for budget health check response.
 * Shows how much has been spent vs the Budget.
 */

public class BudgetStatusDTO {

    //Budget info
    private Long budgetId;
    private String category;
    private BigDecimal amount;

    //Spending info
    private BigDecimal spentAmount;
    private BigDecimal remainingAmount;
    private double percentageUsed;

    //Status
    private BudgetHealthStatus status;
    private boolean isOverBudget;

    //Date info
    private LocalDate startDate;
    private LocalDate endDate;
    private int daysInPeriod;
    private int daysElapsed;
    private int daysRemaining;

    // Statistics
    private BigDecimal averageDailySpending;
    private BigDecimal projectedTotal;

    public BudgetStatusDTO(Long id, String category, BigDecimal amount, 
    BigDecimal amountSpent, BigDecimal remainingAmount, double percentageUsed, 
    BudgetHealthStatus healthStatus, boolean isOverBudget, LocalDate startDate, 
    LocalDate endDate, int daysInPeriod, int elapsedDays, int daysRemaining, 
    BigDecimal averageDailySpending, BigDecimal projectedTotal) {
        // Assign fields in the same order as parameters passed from the service
        this.budgetId = id;
        this.category = category;
        this.amount = amount;
        this.spentAmount = amountSpent;
        this.remainingAmount = remainingAmount;
        this.percentageUsed = percentageUsed;
        this.status = healthStatus;
        this.isOverBudget = isOverBudget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysInPeriod = daysInPeriod;
        this.daysElapsed = elapsedDays;
        this.daysRemaining = daysRemaining;
        this.averageDailySpending = averageDailySpending;
        this.projectedTotal = projectedTotal;
    }

    public BudgetStatusDTO(Long budgetId, BigDecimal amount, String category,
                           BigDecimal spentAmount, BigDecimal remainingAmount,
                           double percentageUsed, BudgetHealthStatus status,
                           boolean isOverBudget, LocalDate startDate,
                           LocalDate endDate, int daysInPeriod, int daysElapsed,
                           int daysRemaining, BigDecimal averageDailySpending, BigDecimal projectedTotal) {
        this.budgetId = budgetId;
        this.amount = amount;
        this.category = category;
        this.spentAmount = spentAmount;
        this.remainingAmount = remainingAmount;
        this.percentageUsed = percentageUsed;
        this.status = status;
        this.isOverBudget = isOverBudget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysInPeriod = daysInPeriod;
        this.daysElapsed = daysElapsed;
        this.daysRemaining = daysRemaining;
        this.averageDailySpending = averageDailySpending;
        this.projectedTotal = projectedTotal;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
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

    public BigDecimal getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(BigDecimal spentAmount) {
        this.spentAmount = spentAmount;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public double getPercentageUsed() {
        return percentageUsed;
    }

    public void setPercentageUsed(double percentageUsed) {
        this.percentageUsed = percentageUsed;
    }

    public BudgetHealthStatus getStatus() {
        return status;
    }

    public void setStatus(BudgetHealthStatus status) {
        this.status = status;
    }

    public boolean isOverBudget() {
        return isOverBudget;
    }

    public void setOverBudget(boolean overBudget) {
        isOverBudget = overBudget;
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

    public int getDaysInPeriod() {
        return daysInPeriod;
    }

    public void setDaysInPeriod(int daysInPeriod) {
        this.daysInPeriod = daysInPeriod;
    }

    public int getDaysElapsed() {
        return daysElapsed;
    }

    public void setDaysElapsed(int daysElapsed) {
        this.daysElapsed = daysElapsed;
    }

    public BigDecimal getAverageDailySpending() {
        return averageDailySpending;
    }

    public void setAverageDailySpending(BigDecimal averageDailySpending) {
        this.averageDailySpending = averageDailySpending;
    }

    public BigDecimal getProjectedTotal() {
        return projectedTotal;
    }

    public void setProjectedTotal(BigDecimal projectedTotal) {
        this.projectedTotal = projectedTotal;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }
}
