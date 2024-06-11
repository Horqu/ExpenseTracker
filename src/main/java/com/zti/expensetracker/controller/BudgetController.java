package com.zti.expensetracker.controller;

import com.zti.expensetracker.model.Budget;
import com.zti.expensetracker.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Budget>> getBudgetsByUserId(@PathVariable Long userId) {
        List<Budget> budgets = budgetRepository.findByOwnerIdOrBudgetUser(userId);
        return ResponseEntity.ok(budgets);
    }
}
