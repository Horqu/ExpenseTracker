package com.zti.expensetracker.dto;

public class UserEmailBudgetDTO {
    
    private String email;
    private Long budgetId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }
}
