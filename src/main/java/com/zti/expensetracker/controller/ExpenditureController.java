package com.zti.expensetracker.controller;

import com.zti.expensetracker.dto.BudgetReportDTO;
import com.zti.expensetracker.dto.UserBalanceDTO;
import com.zti.expensetracker.dto.SettlementDTO;
import com.zti.expensetracker.dto.ExpenditureDTO;
import com.zti.expensetracker.model.Budget;
import com.zti.expensetracker.model.Expenditure;
import com.zti.expensetracker.model.ExpenditureBudget;
import com.zti.expensetracker.model.User;
import com.zti.expensetracker.model.BudgetUser;
import com.zti.expensetracker.repository.BudgetRepository;
import com.zti.expensetracker.repository.ExpenditureBudgetRepository;
import com.zti.expensetracker.repository.ExpenditureRepository;
import com.zti.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/expenditures")
public class ExpenditureController {

    private final ExpenditureRepository expenditureRepository;
    private final UserRepository userRepository;
    private final BudgetRepository budgetRepository;
    private final ExpenditureBudgetRepository expenditureBudgetRepository;

    @Autowired
    public ExpenditureController(ExpenditureRepository expenditureRepository, UserRepository userRepository, BudgetRepository budgetRepository, ExpenditureBudgetRepository expenditureBudgetRepository) {
        this.expenditureRepository = expenditureRepository;
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
        this.expenditureBudgetRepository = expenditureBudgetRepository;
    }

    @PostMapping
    public ResponseEntity<Expenditure> createExpenditure(@RequestBody ExpenditureDTO expenditureDTO) {
        User creator = userRepository.findById(expenditureDTO.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Budget budget = budgetRepository.findById(expenditureDTO.getBudgetId())
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        Expenditure expenditure = new Expenditure();
        expenditure.setName(expenditureDTO.getName());
        expenditure.setCreator(creator);
        expenditure.setDescription(expenditureDTO.getDescription());
        expenditure.setAmount(expenditureDTO.getAmount());
        expenditure.setDate(expenditureDTO.getDate() != null ? expenditureDTO.getDate() : new Date());

        Expenditure savedExpenditure = expenditureRepository.save(expenditure);

        ExpenditureBudget expenditureBudget = new ExpenditureBudget(savedExpenditure, budget);
        expenditureBudgetRepository.save(expenditureBudget);

        return ResponseEntity.ok(savedExpenditure);
    }

    @GetMapping("/budget/{budgetId}")
    public ResponseEntity<List<Expenditure>> getExpendituresByBudgetId(@PathVariable Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        List<Expenditure> expenditures = expenditureBudgetRepository.findByBudget(budget)
                .stream()
                .map(ExpenditureBudget::getExpenditure)
                .collect(Collectors.toList());

        return ResponseEntity.ok(expenditures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expenditure> getExpenditureById(@PathVariable Long id) {
        Optional<Expenditure> expenditure = expenditureRepository.findById(id);
        return expenditure.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/report/{budgetId}")
    public ResponseEntity<BudgetReportDTO> generateReport(@PathVariable Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        Set<User> users = budget.getUsers().stream()
                .map(BudgetUser::getUser)
                .collect(Collectors.toSet());

        List<Expenditure> expenditures = expenditureBudgetRepository.findByBudget(budget)
                .stream()
                .map(ExpenditureBudget::getExpenditure)
                .collect(Collectors.toList());

        Map<User, BigDecimal> userBalances = new HashMap<>();

        for (User user : users) {
            userBalances.put(user, BigDecimal.ZERO);
        }

        for (Expenditure expenditure : expenditures) {
            User creator = expenditure.getCreator();
            BigDecimal amountPerUser = expenditure.getAmount().divide(BigDecimal.valueOf(users.size()), BigDecimal.ROUND_HALF_UP);

            for (User user : users) {
                if (user.equals(creator)) {
                    userBalances.put(user, userBalances.get(user).add(expenditure.getAmount().subtract(amountPerUser)));
                } else {
                    userBalances.put(user, userBalances.get(user).subtract(amountPerUser));
                }
            }
        }

        List<UserBalanceDTO> userBalanceDTOS = userBalances.entrySet().stream()
                .map(entry -> {
                    UserBalanceDTO dto = new UserBalanceDTO();
                    dto.setUserId(entry.getKey().getId());
                    dto.setUsername(entry.getKey().getLogin());
                    dto.setBalance(entry.getValue());
                    return dto;
                })
                .collect(Collectors.toList());

        List<SettlementDTO> settlements = calculateSettlements(userBalances);

        BudgetReportDTO report = new BudgetReportDTO();
        report.setUserBalances(userBalanceDTOS);
        report.setSettlements(settlements);

        return ResponseEntity.ok(report);
    }

    private List<SettlementDTO> calculateSettlements(Map<User, BigDecimal> userBalances) {
        List<SettlementDTO> settlements = new ArrayList<>();
        List<Map.Entry<User, BigDecimal>> positiveBalances = userBalances.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(BigDecimal.ZERO) > 0)
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        List<Map.Entry<User, BigDecimal>> negativeBalances = userBalances.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(BigDecimal.ZERO) < 0)
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toList());

        int i = 0, j = 0;
        while (i < positiveBalances.size() && j < negativeBalances.size()) {
            BigDecimal positiveBalance = positiveBalances.get(i).getValue();
            BigDecimal negativeBalance = negativeBalances.get(j).getValue();

            BigDecimal settlementAmount = positiveBalance.min(negativeBalance.abs());
            settlements.add(createSettlement(negativeBalances.get(j).getKey(), positiveBalances.get(i).getKey(), settlementAmount));

            positiveBalances.get(i).setValue(positiveBalance.subtract(settlementAmount));
            negativeBalances.get(j).setValue(negativeBalance.add(settlementAmount));

            if (positiveBalances.get(i).getValue().compareTo(BigDecimal.ZERO) == 0) {
                i++;
            }
            if (negativeBalances.get(j).getValue().compareTo(BigDecimal.ZERO) == 0) {
                j++;
            }
        }

        return settlements;
    }

    private SettlementDTO createSettlement(User fromUser, User toUser, BigDecimal amount) {
        SettlementDTO settlement = new SettlementDTO();
        settlement.setFromUserId(fromUser.getId());
        settlement.setToUserId(toUser.getId());
        settlement.setAmount(amount);
        return settlement;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenditure(@PathVariable Long id) {
        Expenditure expenditure = expenditureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expenditure not found"));

        expenditureRepository.delete(expenditure);
        return ResponseEntity.ok().build();
    }
    
}
