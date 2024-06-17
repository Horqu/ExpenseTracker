import React, { useState, useContext } from 'react';
import { UserContext } from './UserProvider';
import apiFetch from '../api/api';

function NewExpenditureForm({ budgetId }) {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [amount, setAmount] = useState('');
  const [date, setDate] = useState('');
  const { user } = useContext(UserContext);

  const handleAddExpenditure = async () => {
    if (!user) {
      console.error('User is not logged in');
      return;
    }

    const newExpenditure = {
      name,
      creatorId: user.id,
      description,
      amount: parseFloat(amount),
      date,
      budgetId,
    };

    try {
      const response = await apiFetch('/api/expenditures', {
        method: 'POST',
        body: JSON.stringify(newExpenditure),
      });
      console.log('Expenditure added successfully', response);
      // Optionally, reset form fields or give feedback to the user

      window.location.reload();
    } catch (error) {
      console.error('Error adding expenditure:', error);
    }
  };

  return (
    <div className="container">
      <h2>Add new expenditure</h2>
      <input
        type="text"
        placeholder="Title of expenditure"
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
        type="number"
        placeholder="Amount"
        value={amount}
        onChange={e => setAmount(e.target.value)}
      />
      <input
        type="date"
        value={date}
        onChange={e => setDate(e.target.value)}
      />
      <button onClick={handleAddExpenditure}>Add expenditure</button>
    </div>
  );
}

export default NewExpenditureForm;
