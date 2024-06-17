import React, { useState } from 'react';

function NewExpenseForm({ budgetId }) {
  const [name, setName] = useState('');
  const [amount, setAmount] = useState('');

  const handleAddExpense = () => {
    // Implement the logic to add a new expense
    // Example:
    // addExpense({ name, amount, budgetId });
  };

  return (
    <div>
      <h2>Add new expenditure</h2>
      <input
        type="text"
        placeholder="Title of expenditure"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <input
        type="number"
        placeholder="Amount"
        value={amount}
        onChange={e => setAmount(e.target.value)}
      />
      <button onClick={handleAddExpense}>Add expenditure</button>
    </div>
  );
}

export default NewExpenseForm;
