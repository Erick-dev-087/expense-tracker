package com.erick.expensetracker.budget;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    //Method 1: CREATE - RequestDTO to Entity(ignore server-managed fields)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore=true)
    @Mapping(target = "status", ignore = true)
    BudgetEntity toBudgetEntity(BudgetRequestDto budgetRequestDto);

    //Method 2: READ -Entity to ResponseDTO
    BudgetResponseDto toBudgetResponseDto(BudgetEntity budgetEntity);

    //Method 3: UPDATE - UpdateDTO to Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target="updatedAt", ignore=true)
    @Mapping(target = "status", ignore = true)
    BudgetEntity toBudgetEntity(BudgetUpdateDto budgetUpdateDto);

    //Method 4: READ Multiple - List conversion Entities to ResponseDTO
    List<BudgetResponseDto> toBudgetResponseDtoList(List<BudgetEntity> budgetEntityList);

}