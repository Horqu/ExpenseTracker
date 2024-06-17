import React from 'react';
import BudgetDetails from '../components/BudgetDetails';
import LogoutButton from '../components/LogoutButton';
import NewExpenseForm from '../components/NewExpenseForm';
import AddUserToBudgetForm from '../components/AddUserToBudgetForm';

function BudgetPage() {
  return (
    <div className="container">
      <header>
        <h1>Budget details</h1>
        <LogoutButton />
      </header>
      <BudgetDetails />
      {/* <NewExpenseForm /> */}
      {/* <AddUserToBudgetForm /> */}
    </div>
  );
}

export default BudgetPage;
