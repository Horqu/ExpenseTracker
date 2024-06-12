import React from 'react';
import BudgetList from '../components/BudgetList';

function BudgetsPage() {
  return (
    <div className="container">
      <header>
        <h1>Budżety</h1>
      </header>
      <BudgetList />
    </div>
  );
}

export default BudgetsPage;

