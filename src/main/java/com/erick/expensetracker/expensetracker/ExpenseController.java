package com.erick.expensetracker.expensetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.time.LocalDate;


@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ExpenseEntity createExpense(@Valid @RequestBody ExpenseEntity expense){
        
        return expenseService.createExpense(expense);
    }
    
    @GetMapping
    public List<ExpenseEntity> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ExpenseEntity getExpenseById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/category/{category}")
    public List<ExpenseEntity> getExpensesByCategory(@PathVariable String category){
        return expenseService.getExpensesByCategory(category);
    }

    @GetMapping("/date-range")
    public List<ExpenseEntity> getExpensesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate){
        
        return expenseService.getExpensesByDateRange(startDate, endDate);
    }

    @PutMapping("/{id}")
    public ExpenseEntity updateExpense(@PathVariable Long id,@Valid @RequestBody ExpenseEntity expenseDetails){
        
        return expenseService.updateExpense(id, expenseDetails);

    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
    }

    @GetMapping("/count/")
    public Long getTotalExpenseCount(){
        return expenseService.getTotalExpenseCount();
    }

    @GetMapping("/count/category/{category}")
    public Long getExpenseCountByCategory(@PathVariable String category){
        return expenseService.getExpenseCountByCategory(category);

    }

    @GetMapping("/count/date-range")
    public long getExpenseCountByDateRange(
        @RequestParam LocalDate startDate,
        @RequestParam LocalDate endDate
    ){

    return expenseService.getExpenseCountByDateRange(startDate, endDate);
    }
}

