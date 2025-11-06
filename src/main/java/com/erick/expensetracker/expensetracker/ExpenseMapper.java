package com.erick.expensetracker.expensetracker;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;



@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    // Method 1: CREATE - RequestDto to Entity (ignore server-managed fields)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    ExpenseEntity toEntity(ExpenseRequestDto dto);

    //Method 2: READ - Entity to ResponseDTO
    ExpenseResponseDto toResponseDto(ExpenseEntity entity);

    // Method 3: UPDATE - UpdateDto to Entity (ignore server-managed fields)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    ExpenseEntity toEntity(ExpenseUpdateDto dto);

    // Method 4: READ Multiple - List conversion
    List<ExpenseResponseDto> toResponseDtoList(List<ExpenseEntity> entities);

}