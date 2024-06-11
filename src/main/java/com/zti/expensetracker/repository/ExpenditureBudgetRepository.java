package com.zti.expensetracker.repository;

import com.zti.expensetracker.model.ExpenditureBudget;
import com.zti.expensetracker.model.ExpenditureBudgetId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureBudgetRepository extends JpaRepository<ExpenditureBudget, ExpenditureBudgetId> {
    
}
