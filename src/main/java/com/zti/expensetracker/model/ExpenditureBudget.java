package com.zti.expensetracker.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "expenditure_budget")
public class ExpenditureBudget implements Serializable {

    @EmbeddedId
    private ExpenditureBudgetId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("expenditureId")
    private Expenditure expenditure;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("budgetId")
    private Budget budget;

    public ExpenditureBudget() {}

    public ExpenditureBudget(Expenditure expenditure, Budget budget) {
        this.id = new ExpenditureBudgetId(expenditure.getId(), budget.getId());
        this.expenditure = expenditure;
        this.budget = budget;
    }

    public ExpenditureBudgetId getId() {
        return id;
    }

    public void setId(ExpenditureBudgetId id) {
        this.id = id;
    }

    public Expenditure getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Expenditure expenditure) {
        this.expenditure = expenditure;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
