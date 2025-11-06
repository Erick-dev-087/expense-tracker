package com.erick.expensetracker.expensetracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long>{

    //Find expenses by Category
    List<ExpenseEntity> findByCategory(String category);

    // Find expenses within a date range
    List<ExpenseEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<ExpenseEntity> findByDateAfter(LocalDate date);
    List<ExpenseEntity> findByDateBefore(LocalDate date);

    List<ExpenseEntity> findByCategoryAndDateBetween(String category, LocalDate startDate, LocalDate endDate);

    // Find expenses greater than a certain amount
    List<ExpenseEntity> findByAmountGreaterThanEqual(BigDecimal amount);
    List<ExpenseEntity> findByAmountLessThan(BigDecimal amount);

    List<ExpenseEntity> findByTitleContainingIgnoreCase(String keyword);
    List<ExpenseEntity> findByDescriptionContainingIgnoreCase(String keyword);

    
 
    
}
