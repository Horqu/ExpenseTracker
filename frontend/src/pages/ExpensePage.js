import React from 'react';
import ExpenseDetails from '../components/ExpenseDetails';
import LogoutButton from '../components/LogoutButton';

function ExpensePage() {
  return (
    <div className="container">
      <header>
        <h1>Expenditure details</h1>
        <LogoutButton />
      </header>
      <ExpenseDetails />
    </div>
  );
}

export default ExpensePage;
