package com.aman.expensetracker.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class ExpenseDTO {

    @NotBlank(message = "Expense title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotBlank(message = "Category is required")
    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;

    @NotNull(message = "Expense date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public ExpenseDTO() {
    }

    public ExpenseDTO(String title, Double amount, String category, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters & Setters
}