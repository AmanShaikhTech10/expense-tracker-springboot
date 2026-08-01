package com.aman.expensetracker.dto;

public class MonthlySummaryDTO {

    private Integer year;
    private Integer month;
    private Double total;

    public MonthlySummaryDTO() {
    }

    public MonthlySummaryDTO(Integer year, Integer month, Double total) {
        this.year = year;
        this.month = month;
        this.total = total;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}