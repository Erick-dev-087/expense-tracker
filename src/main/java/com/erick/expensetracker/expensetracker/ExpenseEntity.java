package com.erick.expensetracker.expensetracker;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    
    private LocalDate date;

    private String category;

    private String description;

    // Server-managed timestamp fields
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Status field for soft deletes and state management
    @Column(name = "status")
    private String status = "ACTIVE";

    public ExpenseEntity() {}

    public ExpenseEntity(String title, BigDecimal amount, LocalDate date, String category, String description) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;

    }

    public Long getId() {return id;}

    public void setId(Long id){this.id=id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public BigDecimal getAmount() {return amount;}

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    // Getters and setters for timestamp fields
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    }


