package com.erick.expensetracker.expensetracker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

ExpenseEntity createExpense(ExpenseEntity expense);

List<ExpenseEntity> getAllExpenses();
ExpenseEntity getExpenseById(Long id);
ExpenseEntity updateExpense(Long id, ExpenseEntity expense);

void deleteExpense(Long id);

List<ExpenseEntity> getExpensesByCategory(String category);
    
List<ExpenseEntity> getExpensesByDateRange(LocalDate startDate, LocalDate endDate);
    
BigDecimal getTotalExpenseAmount();
    
long getTotalExpenseCount();

long getExpenseCountByCategory(String category);

long getExpenseCountByDateRange(LocalDate startDate, LocalDate endDate);


    
} 