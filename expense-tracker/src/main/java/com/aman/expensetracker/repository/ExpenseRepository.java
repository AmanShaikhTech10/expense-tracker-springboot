package com.aman.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aman.expensetracker.entity.Expense;
import java.util.List;
import java.time.LocalDate;
import com.aman.expensetracker.dto.CategorySummaryDTO;
import com.aman.expensetracker.dto.MonthlySummaryDTO;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	List<Expense> findByCategoryIgnoreCase(String category);

	List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
	
	List<Expense> findByAmountBetween(Double minAmount, Double maxAmount);
	
	List<Expense> findByTitleContainingIgnoreCase(String keyword);
	
	@Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
	Double getTotalExpense();
	
	@Query("SELECT new com.aman.expensetracker.dto.CategorySummaryDTO(e.category, SUM(e.amount)) " +
		       "FROM Expense e GROUP BY e.category")
		List<CategorySummaryDTO> getCategorySummary();
	
	@Query("SELECT new com.aman.expensetracker.dto.MonthlySummaryDTO(" +
		       "YEAR(e.date), MONTH(e.date), SUM(e.amount)) " +
		       "FROM Expense e " +
		       "GROUP BY YEAR(e.date), MONTH(e.date) " +
		       "ORDER BY YEAR(e.date), MONTH(e.date)")
		List<MonthlySummaryDTO> getMonthlySummary();
	
	Expense findTopByOrderByAmountDesc();
	
	Expense findTopByOrderByAmountAsc();
}