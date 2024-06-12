import React from 'react';
import BudgetDetails from '../components/BudgetDetails';
import NewExpenseForm from '../components/NewExpenseForm';
import AddUserToBudgetForm from '../components/AddUserToBudgetForm';

function BudgetPage() {
  return (
    <div className="container">
      <header>
        <h1>Szczegóły Budżetu</h1>
      </header>
      <BudgetDetails />
      <NewExpenseForm />
      <AddUserToBudgetForm />
    </div>
  );
}

export default BudgetPage;
