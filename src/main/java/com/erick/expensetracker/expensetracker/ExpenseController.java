package com.erick.expensetracker.expensetracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseMapper expenseMapper;

    @PostMapping
    public ResponseEntity<ExpenseResponseDto> createExpense(@Valid @RequestBody ExpenseRequestDto requestDto){

        ExpenseEntity entity = expenseMapper.toEntity(requestDto);
        ExpenseEntity savedEntity = expenseService.createExpense(entity);

        ExpenseResponseDto responseDto = expenseMapper.toResponseDto(savedEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }
    
    
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpenses(){
        // Step 1: Get entities from service (database format)
        List<ExpenseEntity> entities = expenseService.getAllExpenses();
        
        // Step 2: Convert to DTOs (client format) - MapStruct magic!
        List<ExpenseResponseDto> responseDtos = expenseMapper.toResponseDtoList(entities);
        
        // Step 3: Return professional response
        return ResponseEntity.ok(responseDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> getExpenseById(@PathVariable Long id){
        ExpenseEntity entity = expenseService.getExpenseById(id);
        ExpenseResponseDto responseDto = expenseMapper.toResponseDto(entity);

        return ResponseEntity.ok(responseDto);
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

