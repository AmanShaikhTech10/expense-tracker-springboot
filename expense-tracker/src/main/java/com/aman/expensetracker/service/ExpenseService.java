package com.aman.expensetracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.aman.expensetracker.dto.ExpenseDTO;

public interface ExpenseService {

	ExpenseDTO saveExpense(ExpenseDTO expenseDTO);

	List<ExpenseDTO> getAllExpenses();

	List<ExpenseDTO> getExpensesByCategory(String category);

	List<ExpenseDTO> getExpensesByDateRange(LocalDate startDate, LocalDate endDate);

	List<ExpenseDTO> getExpensesByAmountRange(Double minAmount, Double maxAmount);

	List<ExpenseDTO> getExpensesByTitle(String keyword);
	
	Double getTotalExpense();
	
	ExpenseDTO getHighestExpense();
	
	ExpenseDTO getLowestExpense();
	
	ExpenseDTO getExpenseById(Long id);

	ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO);

	void deleteExpense(Long id);

	Page<ExpenseDTO> getAllExpenses(int pageNo, int pageSize, String sortBy, String sortDir);
}