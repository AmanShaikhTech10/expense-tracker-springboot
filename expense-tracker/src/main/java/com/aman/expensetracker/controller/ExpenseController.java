package com.aman.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aman.expensetracker.entity.Expense;
import com.aman.expensetracker.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully";
    }
}