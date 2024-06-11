package com.zti.expensetracker.repository;

import com.zti.expensetracker.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    
}
