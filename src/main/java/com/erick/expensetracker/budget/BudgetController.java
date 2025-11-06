package com.erick.expensetracker.budget;

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
@RequestMapping("/budgets")
public class BudgetController {

    private static final Logger log = LoggerFactory.getLogger(BudgetController.class);

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private BudgetMapper budgetMapper;

    @PostMapping
    public ResponseEntity<BudgetResponseDto> createBudget(@Valid @RequestBody BudgetRequestDto budgetRequestDto){
        BudgetEntity budgetEntity = budgetMapper.toBudgetEntity(budgetRequestDto);
        BudgetEntity savedEntity = budgetService.createBudget(budgetEntity);

        BudgetResponseDto budgetResponseDto = budgetMapper.toBudgetResponseDto(savedEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(budgetResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponseDto>> getAllBudgets(){
        List<BudgetEntity> list = budgetService.getAllBudgets();

        List<BudgetResponseDto> budgetResponseDtoList = budgetMapper.toBudgetResponseDtoList(list);

        return ResponseEntity.status(HttpStatus.OK).body(budgetResponseDtoList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponseDto> getBudgetById(@PathVariable Long id){
        BudgetEntity budgetEntity = budgetService.getBudgetById(id);

        BudgetResponseDto budgetResponseDto = budgetMapper.toBudgetResponseDto(budgetEntity);

        return ResponseEntity.status(HttpStatus.OK).body(budgetResponseDto);

    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<BudgetResponseDto>> getBudgetsByCategory(@PathVariable String category){
        List<BudgetEntity> list = budgetService.getBudgetsByCategory(category);

        List<BudgetResponseDto> budgetResponseDtoList = budgetMapper.toBudgetResponseDtoList(list);

        return ResponseEntity.status(HttpStatus.OK).body(budgetResponseDtoList);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BudgetResponseDto>> getBudgetsByStatus(@PathVariable BudgetStatus status){
        List<BudgetEntity> list = budgetService.getBudgetsByStatus(status);

        List<BudgetResponseDto> budgetResponseDtoList = budgetMapper.toBudgetResponseDtoList(list);

        return  ResponseEntity.status(HttpStatus.OK).body(budgetResponseDtoList);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<BudgetResponseDto>> getBudgetsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate){
        List<BudgetEntity> list = budgetService.getBudgetsByDateRange(startDate, endDate);

        List<BudgetResponseDto>budgetResponseDtoList = budgetMapper.toBudgetResponseDtoList(list);
        return ResponseEntity.status(HttpStatus.OK).body(budgetResponseDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponseDto> updateBudget(@PathVariable Long id,
                                                          @Valid @RequestBody BudgetUpdateDto budgetUpdateDto){
        BudgetEntity budgetEntity = budgetMapper.toBudgetEntity(budgetUpdateDto);

        BudgetEntity updatedEntity = budgetService.updateBudget(id, budgetEntity);

        BudgetResponseDto budgetResponseDto = budgetMapper.toBudgetResponseDto(updatedEntity);
        return ResponseEntity.status(HttpStatus.OK).body(budgetResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id){
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long>  countBudgets(){
        Long budgetCount = budgetService.getTotalBudgetCount();
        return ResponseEntity.status(HttpStatus.OK).body(budgetCount);
    }

    @GetMapping("/count/category/{category}")
    public ResponseEntity<Long>  countBudgetsByCategory(@PathVariable String category){
        Long budgetCount = budgetService.getBudgetCountByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(budgetCount);
    }

    @GetMapping("/count/date-range")
    public ResponseEntity<Long>  countBudgetsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ){
        Long budgetCount = budgetService.getBudgetCountByDateRange(startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(budgetCount);
    }

    @GetMapping("/active/{category}")
    public ResponseEntity<List<BudgetResponseDto>> getActiveBudgetsByCategory(@PathVariable String category){
        List<BudgetEntity> list = budgetService.getActiveBudgetsByCategory(category);
        List<BudgetResponseDto> budgetResponseDtoList = budgetMapper.toBudgetResponseDtoList(list);

        return ResponseEntity.status(HttpStatus.OK).body(budgetResponseDtoList);
    }

    @GetMapping("/{id}/budget-status")
    public ResponseEntity<BudgetStatusDTO> getBudgetStatusById(@PathVariable Long id){
        BudgetStatusDTO statusDTO = budgetService.getBudgetStatus(id);
        return  ResponseEntity.status(HttpStatus.OK).body(statusDTO);
    }




}
