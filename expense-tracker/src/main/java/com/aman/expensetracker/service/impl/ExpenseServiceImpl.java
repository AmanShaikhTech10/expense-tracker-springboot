package com.aman.expensetracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.expensetracker.dto.ExpenseDTO;
import com.aman.expensetracker.entity.Expense;
import com.aman.expensetracker.exception.ResourceNotFoundException;
import com.aman.expensetracker.mapper.ExpenseMapper;
import com.aman.expensetracker.repository.ExpenseRepository;
import com.aman.expensetracker.service.ExpenseService;

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

        return expenses.stream()
                .map(ExpenseMapper::mapToExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDTO getExpenseById(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense not found with id: " + id));

        return ExpenseMapper.mapToExpenseDTO(expense);
    }

    @Override
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {

        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense not found with id: " + id));

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
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense not found with id: " + id));

        expenseRepository.delete(expense);
    }
}