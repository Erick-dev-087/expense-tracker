package com.erick.expensetracker.budget;


import java.time.LocalDate;
import java.util.List;

public interface BudgetService {

    BudgetEntity createBudget(BudgetEntity budgetEntity);

    List<BudgetEntity> getAllBudgets();
    BudgetEntity getBudgetById(Long id);
    BudgetEntity updateBudget(Long id, BudgetEntity budgetEntity);

    List<BudgetEntity> getBudgetsByCategory(String category);
    List<BudgetEntity> getBudgetsByStatus(BudgetStatus status);
    List<BudgetEntity> getBudgetsByDateRange(LocalDate startDate, LocalDate endDate);


    void deleteBudget(Long id);

    long getTotalBudgetCount();
    long getBudgetCountByCategory(String category);
    long getBudgetCountByDateRange(LocalDate startDate, LocalDate endDate);

    List<BudgetEntity> getActiveBudgetsByCategory(String category);

    BudgetStatusDTO getBudgetStatus(Long id);

}