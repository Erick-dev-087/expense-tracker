package com.erick.expensetracker.expensetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Override
    public ExpenseEntity createExpense(ExpenseEntity expense) {
       
        return expenseRepository.save(expense);
    }
    
    @Override
    public List<ExpenseEntity> getAllExpenses() {
        return expenseRepository.findAll();
    }
    
    @Override
    public ExpenseEntity getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }
    
    @Override
    public ExpenseEntity updateExpense(Long id, ExpenseEntity expense) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with id: " + id);
        }
        expense.setId(id);
        return expenseRepository.save(expense);
    }
    
    @Override
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }
    
    @Override
    public List<ExpenseEntity> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }
    
    @Override
    public List<ExpenseEntity> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate);
    }
    
    @Override
    public BigDecimal getTotalExpenseAmount() {
        return expenseRepository.findAll()
                .stream()
                .map(ExpenseEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    @Override
    public long getTotalExpenseCount() {
        return expenseRepository.count();
    }

    @Override
    public long getExpenseCountByCategory(String category) {
        return expenseRepository.findByCategory(category).size();
    }

    @Override
    public long getExpenseCountByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate).size();
    }
    
}