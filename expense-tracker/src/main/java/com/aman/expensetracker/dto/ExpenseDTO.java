package com.aman.expensetracker.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ExpenseDTO {
	@NotBlank
    private String title;
	@NotNull
	@Positive
    private Double amount;
	@NotBlank
    private String category;
	@NotNull
    private LocalDate date;

    public ExpenseDTO() {
    }

    public ExpenseDTO(String title, Double amount, String category, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}