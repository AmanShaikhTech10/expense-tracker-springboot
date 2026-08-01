package com.aman.expensetracker.mapper;

import com.aman.expensetracker.dto.ExpenseDTO;
import com.aman.expensetracker.entity.Expense;

public class ExpenseMapper {

    // Entity -> DTO
    public static ExpenseDTO mapToExpenseDTO(Expense expense) {

        return new ExpenseDTO(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate());
    }

    // DTO -> Entity
    public static Expense mapToExpense(ExpenseDTO expenseDTO) {

        return new Expense(
                expenseDTO.getId(),
                expenseDTO.getTitle(),
                expenseDTO.getAmount(),
                expenseDTO.getCategory(),
                expenseDTO.getDate());
    }
}