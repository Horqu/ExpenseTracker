import React from 'react';
import BudgetList from '../components/BudgetList';
import LogoutButton from '../components/LogoutButton';

function BudgetsPage() {
  return (
    <div className="container">
      <header>
        <h1>Budgets</h1>
        <LogoutButton />
      </header>
      <BudgetList />
    </div>
  );
}

export default BudgetsPage;

