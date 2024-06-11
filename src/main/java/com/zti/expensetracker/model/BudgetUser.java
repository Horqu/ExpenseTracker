package com.zti.expensetracker.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "budget_user")
public class BudgetUser implements Serializable {

    @EmbeddedId
    private BudgetUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("budgetId")
    private Budget budget;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    public BudgetUser() {}

    public BudgetUser(Budget budget, User user) {
        this.id = new BudgetUserId(budget.getId(), user.getId());
        this.budget = budget;
        this.user = user;
    }

    public BudgetUserId getId() {
        return id;
    }

    public void setId(BudgetUserId id) {
        this.id = id;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
