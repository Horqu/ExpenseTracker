package com.zti.expensetracker.repository;

import com.zti.expensetracker.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    
    @Query("SELECT DISTINCT b FROM Budget b LEFT JOIN BudgetUser bu ON b.id = bu.budget.id WHERE b.owner.id = :userId OR bu.user.id = :userId")
    List<Budget> findByOwnerIdOrBudgetUser(@Param("userId") Long userId);
}
