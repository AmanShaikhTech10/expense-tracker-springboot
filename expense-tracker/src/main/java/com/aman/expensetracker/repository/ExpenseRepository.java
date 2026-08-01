package com.aman.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aman.expensetracker.entity.Expense;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	List<Expense> findByCategoryIgnoreCase(String category);

	List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
	
	List<Expense> findByAmountBetween(Double minAmount, Double maxAmount);
	
	List<Expense> findByTitleContainingIgnoreCase(String keyword);
}