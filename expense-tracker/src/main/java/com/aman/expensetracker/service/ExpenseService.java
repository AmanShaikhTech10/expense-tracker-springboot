package com.aman.expensetracker.service;

import java.util.List;

import com.aman.expensetracker.entity.Expense;

public interface ExpenseService {

    Expense saveExpense(Expense expense);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, Expense expense);

    void deleteExpense(Long id);
}