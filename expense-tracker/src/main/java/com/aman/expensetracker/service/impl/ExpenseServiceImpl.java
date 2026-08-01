package com.aman.expensetracker.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.expensetracker.dto.CategorySummaryDTO;
import com.aman.expensetracker.dto.ExpenseDTO;
import com.aman.expensetracker.entity.Expense;
import com.aman.expensetracker.exception.ResourceNotFoundException;
import com.aman.expensetracker.mapper.ExpenseMapper;
import com.aman.expensetracker.repository.ExpenseRepository;
import com.aman.expensetracker.service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public ExpenseDTO saveExpense(ExpenseDTO expenseDTO) {

		Expense expense = ExpenseMapper.mapToExpense(expenseDTO);

		Expense savedExpense = expenseRepository.save(expense);

		return ExpenseMapper.mapToExpenseDTO(savedExpense);
	}

	@Override
	public List<ExpenseDTO> getAllExpenses() {

		List<Expense> expenses = expenseRepository.findAll();

		return expenses.stream().map(ExpenseMapper::mapToExpenseDTO).collect(Collectors.toList());
	}

	@Override
	public ExpenseDTO getExpenseById(Long id) {

		Expense expense = expenseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

		return ExpenseMapper.mapToExpenseDTO(expense);
	}

	@Override
	public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {

		Expense existingExpense = expenseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

		existingExpense.setTitle(expenseDTO.getTitle());
		existingExpense.setAmount(expenseDTO.getAmount());
		existingExpense.setCategory(expenseDTO.getCategory());
		existingExpense.setDate(expenseDTO.getDate());

		Expense updatedExpense = expenseRepository.save(existingExpense);

		return ExpenseMapper.mapToExpenseDTO(updatedExpense);
	}

	@Override
	public void deleteExpense(Long id) {

		Expense expense = expenseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

		expenseRepository.delete(expense);
	}

	@Override
	public Page<ExpenseDTO> getAllExpenses(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Expense> expenses = expenseRepository.findAll(pageable);

		return expenses.map(ExpenseMapper::mapToExpenseDTO);
	}

	@Override
	public List<ExpenseDTO> getExpensesByCategory(String category) {

		List<Expense> expenses = expenseRepository.findByCategoryIgnoreCase(category);

		return expenses.stream().map(ExpenseMapper::mapToExpenseDTO).collect(Collectors.toList());
	}

	@Override
	public List<ExpenseDTO> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {

		List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate);

		return expenses.stream().map(ExpenseMapper::mapToExpenseDTO).collect(Collectors.toList());
	}

	@Override
	public List<ExpenseDTO> getExpensesByAmountRange(Double minAmount, Double maxAmount) {

		List<Expense> expenses = expenseRepository.findByAmountBetween(minAmount, maxAmount);

		return expenses.stream().map(ExpenseMapper::mapToExpenseDTO).collect(Collectors.toList());
	}

	@Override
	public List<ExpenseDTO> getExpensesByTitle(String keyword) {

		List<Expense> expenses = expenseRepository.findByTitleContainingIgnoreCase(keyword);

		return expenses.stream().map(ExpenseMapper::mapToExpenseDTO).collect(Collectors.toList());
	}

	@Override
	public Double getTotalExpense() {
		return expenseRepository.getTotalExpense();
	}

	@Override
	public ExpenseDTO getHighestExpense() {

		Expense expense = expenseRepository.findTopByOrderByAmountDesc();

		if (expense == null) {
			throw new ResourceNotFoundException("No expenses found");
		}

		return ExpenseMapper.mapToExpenseDTO(expense);
	}

	@Override
	public ExpenseDTO getLowestExpense() {

		Expense expense = expenseRepository.findTopByOrderByAmountAsc();

		if (expense == null) {
			throw new ResourceNotFoundException("No expenses found");
		}

		return ExpenseMapper.mapToExpenseDTO(expense);
	}

	@Override
	public List<CategorySummaryDTO> getCategorySummary() {
		return expenseRepository.getCategorySummary();
	}

}