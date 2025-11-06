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
    public ResponseEntity<List<ExpenseResponseDto>> getExpensesByCategory(@PathVariable String category){
        List<ExpenseEntity> entities =  expenseService.getExpensesByCategory(category);
        
        List<ExpenseResponseDto> responseDtos = expenseMapper.toResponseDtoList(entities);

        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ExpenseResponseDto>> getExpensesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate){
        
        List<ExpenseEntity> entities = expenseService.getExpensesByDateRange(startDate, endDate);

        List<ExpenseResponseDto> responseDtos = expenseMapper.toResponseDtoList(entities);

        return ResponseEntity.ok(responseDtos);
    }   

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseUpdateDto updateDto){
        
        // Step 1: Convert DTO to Entity for service layer
        ExpenseEntity entityToUpdate = expenseMapper.toEntity(updateDto);
        
        // Step 2: Service handles business logic and persistence
        ExpenseEntity updatedEntity = expenseService.updateExpense(id, entityToUpdate);

        // Step 3: Convert back to ResponseDto for client
        ExpenseResponseDto responseDto = expenseMapper.toResponseDto(updatedEntity);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void>  deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count/")
    public ResponseEntity<Long> getTotalExpenseCount(){
        Long expensesCount = expenseService.getTotalExpenseCount();
        return ResponseEntity.ok(expensesCount);
    }

    @GetMapping("/count/category/{category}")
    public ResponseEntity<Long> getExpenseCountByCategory(@PathVariable String category){
        Long categoryCount = expenseService.getExpenseCountByCategory(category);
        return ResponseEntity.ok(categoryCount);
    }

    @GetMapping("/count/date-range")
    public ResponseEntity<Long> getExpenseCountByDateRange(
        @RequestParam LocalDate startDate,
        @RequestParam LocalDate endDate
    ){
        Long dateRangeCount = expenseService.getExpenseCountByDateRange(startDate, endDate);
        return ResponseEntity.ok(dateRangeCount);
    }
}

