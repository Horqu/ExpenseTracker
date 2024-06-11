package com.zti.expensetracker.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BudgetUserId implements Serializable {

    private Long budgetId;
    private Long userId;

    public BudgetUserId() {}

    public BudgetUserId(Long budgetId, Long userId) {
        this.budgetId = budgetId;
        this.userId = userId;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetUserId that = (BudgetUserId) o;
        return Objects.equals(budgetId, that.budgetId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetId, userId);
    }
}
