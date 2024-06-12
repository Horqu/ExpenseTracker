import React from 'react';
import ExpenseDetails from '../components/ExpenseDetails';

function ExpensePage() {
  return (
    <div className="container">
      <header>
        <h1>Szczegóły Wydatku</h1>
      </header>
      <ExpenseDetails />
    </div>
  );
}

export default ExpensePage;
