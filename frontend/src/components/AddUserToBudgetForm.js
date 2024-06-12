import React, { useState } from 'react';

function AddUserToBudgetForm({ budgetId }) {
  const [email, setEmail] = useState('');

  const handleAddUser = () => {
    // Implement the logic to add a user to the budget
    // Example:
    // addUserToBudget({ email, budgetId });
  };

  return (
    <div>
      <h2>Dodaj Użytkownika do Budżetu</h2>
      <input
        type="email"
        placeholder="Email użytkownika"
        value={email}
        onChange={e => setEmail(e.target.value)}
      />
      <button onClick={handleAddUser}>Dodaj Użytkownika</button>
    </div>
  );
}

export default AddUserToBudgetForm;
