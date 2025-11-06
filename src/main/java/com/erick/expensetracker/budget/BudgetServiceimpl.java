package com.erick.expensetracker.budget;

import com.erick.expensetracker.common.exception.BudgetNotFoundException;
import com.erick.expensetracker.expensetracker.ExpenseEntity;
import com.erick.expensetracker.expensetracker.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class BudgetServiceimpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public BudgetEntity createBudget(BudgetEntity budget) {
        // Ensure newly created budgets have a sensible default status
        if (budget.getStatus() == null) {
            budget.setStatus(BudgetStatus.ACTIVE);
        }
        return budgetRepository.save(budget);
    }

    @Override
    public List<BudgetEntity> getAllBudgets(){
        return budgetRepository.findAll();
    }

    @Override
    public BudgetEntity getBudgetById(Long id){
        return budgetRepository.findById(id)
                .orElseThrow(()-> new BudgetNotFoundException("Budget not found with id: " + id));

    }

    @Override
    public BudgetEntity updateBudget(Long id, BudgetEntity budget){
        if(!budgetRepository.existsById(id)){
            throw new BudgetNotFoundException("Budget not found with id: " + id);
        }
        budget.setId(id);
        return budgetRepository.save(budget);

    }

    @Override
    public void deleteBudget(Long id){
        if(!budgetRepository.existsById(id)){
            throw new BudgetNotFoundException("Budget not found with id: " + id);

        }
        budgetRepository.deleteById(id);
    }

    @Override
    public List<BudgetEntity> getBudgetsByCategory(String category){
        return budgetRepository.findByCategory(category);

    }

    @Override
    public List<BudgetEntity> getBudgetsByStatus(BudgetStatus status){
        return budgetRepository.findByStatus(status);

    }

    @Override
    public List<BudgetEntity> getBudgetsByDateRange(LocalDate startDate, LocalDate endDate) {
        return budgetRepository.findAll().stream()
                .filter(budget -> 
                    !budget.getEndDate().isBefore(startDate) && 
                    !budget.getStartDate().isAfter(endDate)
                )
                .toList();
}

    @Override
    public long getTotalBudgetCount(){
        return budgetRepository.count();
    }

    @Override
    public long getBudgetCountByCategory(String cat
    ){
        return budgetRepository.count();
    }

    @Override
    public long getBudgetCountByDateRange(LocalDate startDate, LocalDate endDate){
        return budgetRepository.count();
    }

    @Override
    public List<BudgetEntity> getActiveBudgetsByCategory(String category){
        return budgetRepository.findByCategoryAndStatus(category, BudgetStatus.ACTIVE);
    }

    @Override
    public BudgetStatusDTO getBudgetStatus(Long budgetId){
        //get budget
        BudgetEntity budget = getBudgetById(budgetId);

        //Get all expenses for a category within budget date range
        List<ExpenseEntity> expenses = expenseRepository.findByCategoryAndDateBetween(
                budget.getCategory(),
                budget.getStartDate(),
                budget.getEndDate()
        );

        BigDecimal amountSpent = expenses.stream()
                .map(ExpenseEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal remainingAmount = budget.getAmount().subtract(amountSpent);

        double percentageUsed = budget.getAmount().compareTo(BigDecimal.ZERO) > 0
                ? amountSpent.divide(budget.getAmount(), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100)).doubleValue()
                :  0.0;

        LocalDate today = LocalDate.now();
        long daysInPeriod = ChronoUnit.DAYS.between(budget.getStartDate(), budget.getEndDate());
        long elapsedDays = ChronoUnit.DAYS.between(budget.getStartDate(), today);
        long daysRemaining = ChronoUnit.DAYS.between(today, budget.getEndDate());

        if(today.isBefore(budget.getStartDate())){
            elapsedDays = 0;
            daysRemaining = daysInPeriod;

        } else if (today.isAfter(budget.getEndDate())){
            elapsedDays = daysInPeriod;
            daysRemaining = 0;
        }

        BigDecimal averageDailySpending = elapsedDays > 0
                ? amountSpent.divide(BigDecimal.valueOf(elapsedDays),2,RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        BigDecimal projectedTotal = elapsedDays > 0
                ? averageDailySpending.multiply(BigDecimal.valueOf(daysInPeriod))
                : BigDecimal.ZERO;


        boolean isOverBudget = amountSpent.compareTo(budget.getAmount()) > 0;

        BudgetHealthStatus healthStatus;
        if(percentageUsed >= 100){
            healthStatus = BudgetHealthStatus.OVER_BUDGET;
        } else if (percentageUsed > 50 && daysRemaining > daysInPeriod / 2) {
            healthStatus = BudgetHealthStatus.AT_RISK;
        } else{
            healthStatus = BudgetHealthStatus.ON_TRACK;
        }
        String category = budget.getCategory();
        BigDecimal amount = budget.getAmount();

        return new BudgetStatusDTO(
                budget.getId(),
                category,
                amount,
                amountSpent,
                remainingAmount,
                percentageUsed,
                healthStatus, // <-- Convert enum to String if needed
                isOverBudget,
                budget.getStartDate(),
                budget.getEndDate(),
                (int) daysInPeriod,
                (int) elapsedDays,
                (int) daysRemaining,
                averageDailySpending,
                projectedTotal
        );

    }


}