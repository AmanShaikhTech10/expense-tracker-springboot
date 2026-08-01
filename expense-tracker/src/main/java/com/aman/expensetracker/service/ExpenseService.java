package com.aman.expensetracker.service;

import java.util.List;

import com.aman.expensetracker.dto.ExpenseDTO;

public interface ExpenseService {

	ExpenseDTO saveExpense(ExpenseDTO expenseDTO);

	List<ExpenseDTO> getAllExpenses();

	ExpenseDTO getExpenseById(Long id);

	ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO);

	void deleteExpense(Long id);
}