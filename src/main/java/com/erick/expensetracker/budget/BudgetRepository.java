package com.erick.expensetracker.budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface BudgetRepository  extends JpaRepository<BudgetEntity,Long> {

    //Find budgets by category
    List<BudgetEntity>findByCategory(String category);

    // Find budgets by status
    List<BudgetEntity> findByStatus(BudgetStatus status);

    // Find budgets by category and status
    List<BudgetEntity> findByCategoryAndStatus(String category, BudgetStatus status);

    //Find budgets within a date range
    List<BudgetEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate date, LocalDate sameDate
    );
    Optional<BudgetEntity> findByCategoryAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String category,
            BudgetStatus status,
            LocalDate startDate,
            LocalDate endDate
    );

    // 6️ Find budgets by period type
    // Use case: "Show me all MONTHLY budgets"
    List<BudgetEntity> findByPeriodType(BudgetPeriodType periodType);

    // 7️ Find expired budgets (where endDate is in the past)
    // Use case: "Auto-expire old budgets"
    List<BudgetEntity> findByEndDateBeforeAndStatus(LocalDate date, BudgetStatus status);
}


