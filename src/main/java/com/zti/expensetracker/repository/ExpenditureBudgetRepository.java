package com.zti.expensetracker.repository;

import com.zti.expensetracker.model.Budget;
import com.zti.expensetracker.model.ExpenditureBudget;
import com.zti.expensetracker.model.ExpenditureBudgetId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenditureBudgetRepository extends JpaRepository<ExpenditureBudget, ExpenditureBudgetId> {
    List<ExpenditureBudget> findByBudget(Budget budget);
}
