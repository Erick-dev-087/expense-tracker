package com.erick.expensetracker.expensetracker;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * DTO for expense retrieval requests.
 * Contains only the data that clients get when make a request
 */

public class ExpenseResponseDto  {

   //Eseential Fields
    private Long id;
    private String title;
    private BigDecimal amount;
    private LocalDate date;
    private String category;
    private String description;

    //Server-managed fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;

   public ExpenseResponseDto() {}

   public ExpenseResponseDto(
    Long id, String title, BigDecimal amount, LocalDate date, String category,
    String description, LocalDateTime createdAt, LocalDateTime updatedAt,
    String status
   ){
    this.id = id;
    this.title = title;
    this.amount = amount;
    this.date = date;
    this.category = category;
    this.description = description;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.status = status;
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExpenseResponseDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status='" + status + '\'' +
                '}';
    }
}
