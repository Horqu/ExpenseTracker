package com.zti.expensetracker.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExpenditureBudgetId implements Serializable {

    private Long expenditureId;
    private Long budgetId;

    public ExpenditureBudgetId() {}

    public ExpenditureBudgetId(Long expenditureId, Long budgetId) {
        this.expenditureId = expenditureId;
        this.budgetId = budgetId;
    }

    public Long getExpenditureId() {
        return expenditureId;
    }

    public void setExpenditureId(Long expenditureId) {
        this.expenditureId = expenditureId;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenditureBudgetId that = (ExpenditureBudgetId) o;
        return Objects.equals(expenditureId, that.expenditureId) &&
                Objects.equals(budgetId, that.budgetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenditureId, budgetId);
    }
}
