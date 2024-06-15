import React, { useState } from 'react';
import apiFetch from '../api/api';

function AddUserToBudgetForm({ budgetId }) {
  const [email, setEmail] = useState('');

  const handleAddUser = async () => {
    const data = {
      email,
      budgetId,
    };

    try {
      const response = await apiFetch('/api/budgets/linkByEmail', {
        method: 'POST',
        body: JSON.stringify(data),
      });
      if (response) {
        console.log('User added to budget successfully', response);
      } else {
        console.log('User added to budget successfully');
      }
      window.location.reload();
    } catch (error) {
      console.error('Error adding user to budget:', error);
    }
  };

  return (
    <div className="container">
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
