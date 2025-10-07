package com.erick.expensetracker.expensetracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Unit tests for ExpenseMapper
 */
public class ExpenseMapperTest {
    
    private ExpenseMapper mapper;
    
    @BeforeEach
    void setUp() {
        // Use the generated mapper instance
        mapper = ExpenseMapper.INSTANCE;
    }
    
    @Test
    void testRequestDtoToEntity() {
        // Arrange - Create a RequestDto
        ExpenseRequestDto requestDto = new ExpenseRequestDto();
        requestDto.setTitle("Test Coffee");
        requestDto.setAmount(new BigDecimal("5.50"));
        requestDto.setDate(LocalDate.of(2024, 10, 7));
        requestDto.setCategory("Food");
        requestDto.setDescription("Morning coffee");
        
        // Act - Convert to Entity
        ExpenseEntity entity = mapper.toEntity(requestDto);
        
        // Assert - Verify mapping
        assertNotNull(entity);
        assertEquals("Test Coffee", entity.getTitle());
        assertEquals(new BigDecimal("5.50"), entity.getAmount());
        assertEquals(LocalDate.of(2024, 10, 7), entity.getDate());
        assertEquals("Food", entity.getCategory());
        assertEquals("Morning coffee", entity.getDescription());
        
        // Verify server-managed fields are ignored
        assertNull(entity.getId());
        assertNull(entity.getCreatedAt());
        assertNull(entity.getUpdatedAt());
        assertEquals("ACTIVE", entity.getStatus()); // Default value
    }
    
    @Test
    void testEntityToResponseDto() {
        // Arrange - Create an Entity (simulating database data)
        ExpenseEntity entity = new ExpenseEntity();
        entity.setId(123L);
        entity.setTitle("Test Lunch");
        entity.setAmount(new BigDecimal("12.99"));
        entity.setDate(LocalDate.of(2024, 10, 7));
        entity.setCategory("Food");
        entity.setDescription("Business lunch");
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setStatus("ACTIVE");
        
        // Act - Convert to ResponseDto
        ExpenseResponseDto responseDto = mapper.toResponseDto(entity);
        
        // Assert - Verify all fields are mapped
        assertNotNull(responseDto);
        assertEquals(123L, responseDto.getId());
        assertEquals("Test Lunch", responseDto.getTitle());
        assertEquals(new BigDecimal("12.99"), responseDto.getAmount());
        assertEquals(LocalDate.of(2024, 10, 7), responseDto.getDate());
        assertEquals("Food", responseDto.getCategory());
        assertEquals("Business lunch", responseDto.getDescription());
        assertNotNull(responseDto.getCreatedAt());
        assertNotNull(responseDto.getUpdatedAt());
        assertEquals("ACTIVE", responseDto.getStatus());
    }
    
    @Test
    void testNullHandling() {
        // Test that mapper handles null gracefully
        ExpenseEntity nullEntity = null;
        ExpenseResponseDto result = mapper.toResponseDto(nullEntity);
        assertNull(result);
    }
}