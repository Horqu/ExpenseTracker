package com.zti.expensetracker.repository;

import com.zti.expensetracker.model.BudgetUser;
import com.zti.expensetracker.model.BudgetUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetUserRepository extends JpaRepository<BudgetUser, BudgetUserId> {
    
}
