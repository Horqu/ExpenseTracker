import React, { useState, useContext } from 'react';
import { UserContext } from './UserProvider';
import apiFetch from '../api/api';

function NewBudgetForm() {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [date, setDate] = useState('');
  const { user } = useContext(UserContext);

  const handleAddBudget = async () => {
    if (!user) {
      console.error('User is not logged in');
      return;
    }

    const newBudget = {
      name,
      ownerId: user.id,
      description,
      date,
    };

    try {
      const response = await apiFetch('/api/budgets', {
        method: 'POST',
        body: JSON.stringify(newBudget),
      });
      console.log('Budget added successfully', response);
      // Optionally, reset form fields or give feedback to the user

      // Dodaj użytkownika do budżetu
      const data = {
        userId: user.id,  // Załóżmy, że masz email w kontekście użytkownika
        budgetId: response.id,
      };

      await apiFetch('/api/budgets/link', {
        method: 'POST',
        body: JSON.stringify(data),
      });
      console.log('User linked to budget successfully');

      window.location.reload();
    } catch (error) {
      console.error('Error adding budget:', error);
    }
  };

  return (
    <div className="container">
      <h2>Add new budget</h2>
      <input
        type="text"
        placeholder="Title of budget"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <input
        type="text"
        placeholder="Description"
        value={description}
        onChange={e => setDescription(e.target.value)}
      />
      <input
        type="date"
        value={date}
        onChange={e => setDate(e.target.value)}
      />
      <button onClick={handleAddBudget}>Add budget</button>
    </div>
  );
}

export default NewBudgetForm;
