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
      <h2>Dodaj Nowy Wydatek</h2>
      <input
        type="text"
        placeholder="Nazwa wydatku"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <input
        type="number"
        placeholder="Kwota"
        value={amount}
        onChange={e => setAmount(e.target.value)}
      />
      <button onClick={handleAddExpense}>Dodaj Wydatek</button>
    </div>
  );
}

export default NewExpenseForm;
