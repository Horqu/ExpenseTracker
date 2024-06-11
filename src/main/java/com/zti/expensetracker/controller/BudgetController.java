package com.zti.expensetracker.controller;

import com.zti.expensetracker.dto.BudgetDTO;
import com.zti.expensetracker.dto.UserBudgetDTO;
import com.zti.expensetracker.dto.UserEmailBudgetDTO;
import com.zti.expensetracker.model.Budget;
import com.zti.expensetracker.model.User;
import com.zti.expensetracker.model.BudgetUser;
import com.zti.expensetracker.repository.BudgetRepository;
import com.zti.expensetracker.repository.UserRepository;
import com.zti.expensetracker.repository.BudgetUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final BudgetUserRepository budgetUserRepository;

    @Autowired
    public BudgetController(BudgetRepository budgetRepository, UserRepository userRepository, BudgetUserRepository budgetUserRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.budgetUserRepository = budgetUserRepository;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Budget>> getBudgetsByUserId(@PathVariable Long userId) {
        List<Budget> budgets = budgetRepository.findByOwnerIdOrBudgetUser(userId);
        return ResponseEntity.ok(budgets);
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody BudgetDTO budgetDTO) {
        User owner = userRepository.findById(budgetDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Budget budget = new Budget();
        budget.setName(budgetDTO.getName());
        budget.setOwner(owner);
        budget.setDescription(budgetDTO.getDescription());
        budget.setDate(budgetDTO.getDate());

        Budget savedBudget = budgetRepository.save(budget);
        return ResponseEntity.ok(savedBudget);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        return budget.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/link")
    public ResponseEntity<Void> linkUserToBudget(@RequestBody UserBudgetDTO userBudgetDTO) {
        User user = userRepository.findById(userBudgetDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Budget budget = budgetRepository.findById(userBudgetDTO.getBudgetId())
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        BudgetUser budgetUser = new BudgetUser(budget, user);
        budgetUserRepository.save(budgetUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/linkByEmail")
    public ResponseEntity<Void> linkUserToBudgetByEmail(@RequestBody UserEmailBudgetDTO userEmailBudgetDTO) {
        User user = userRepository.findByEmail(userEmailBudgetDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Budget budget = budgetRepository.findById(userEmailBudgetDTO.getBudgetId())
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        BudgetUser budgetUser = new BudgetUser(budget, user);
        budgetUserRepository.save(budgetUser);

        return ResponseEntity.ok().build();
    }

}
