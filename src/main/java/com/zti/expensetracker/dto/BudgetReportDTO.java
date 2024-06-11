package com.zti.expensetracker.dto;

import java.util.List;

public class BudgetReportDTO {
    private List<UserBalanceDTO> userBalances;
    private List<SettlementDTO> settlements;

    public List<UserBalanceDTO> getUserBalances() {
        return userBalances;
    }

    public void setUserBalances(List<UserBalanceDTO> userBalances) {
        this.userBalances = userBalances;
    }

    public List<SettlementDTO> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<SettlementDTO> settlements) {
        this.settlements = settlements;
    }
}
