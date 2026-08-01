package com.aman.expensetracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aman.expensetracker.dto.ExpenseDTO;
import com.aman.expensetracker.service.ExpenseService;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping
	public ResponseEntity<ExpenseDTO> addExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {

		ExpenseDTO savedExpense = expenseService.saveExpense(expenseDTO);

		return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {

		return ResponseEntity.ok(expenseService.getAllExpenses());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ExpenseDTO>> getAllExpensesWithPagination(

			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortDir) {

		return ResponseEntity.ok(expenseService.getAllExpenses(pageNo, pageSize, sortBy, sortDir));
	}

	@GetMapping("/date")
	public ResponseEntity<List<ExpenseDTO>> getExpensesByDateRange(

			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,

			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

		return ResponseEntity.ok(expenseService.getExpensesByDateRange(startDate, endDate));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<ExpenseDTO>> getExpensesByCategory(@PathVariable String category) {

		return ResponseEntity.ok(expenseService.getExpensesByCategory(category));
	}
	
	@GetMapping("/title")
	public ResponseEntity<List<ExpenseDTO>> getExpensesByTitle(
	        @RequestParam String keyword) {

	    return ResponseEntity.ok(
	            expenseService.getExpensesByTitle(keyword));
	}

	@GetMapping("/amount")
	public ResponseEntity<List<ExpenseDTO>> getExpensesByAmountRange(

			@RequestParam Double minAmount, @RequestParam Double maxAmount) {

		return ResponseEntity.ok(expenseService.getExpensesByAmountRange(minAmount, maxAmount));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {

		return ResponseEntity.ok(expenseService.getExpenseById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expenseDTO) {

		return ResponseEntity.ok(expenseService.updateExpense(id, expenseDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteExpense(@PathVariable Long id) {

		expenseService.deleteExpense(id);

		return ResponseEntity.ok("Expense deleted successfully");
	}
	
	@GetMapping("/dashboard/total")
	public ResponseEntity<Double> getTotalExpense() {
	    return ResponseEntity.ok(expenseService.getTotalExpense());
	}
	
	@GetMapping("/dashboard/highest")
	public ResponseEntity<ExpenseDTO> getHighestExpense() {

	    return ResponseEntity.ok(expenseService.getHighestExpense());
	}
	
	@GetMapping("/dashboard/lowest")
	public ResponseEntity<ExpenseDTO> getLowestExpense() {

	    return ResponseEntity.ok(expenseService.getLowestExpense());
	}
	
}