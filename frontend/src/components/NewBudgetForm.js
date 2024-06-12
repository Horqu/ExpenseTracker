import React, { useState, useContext } from 'react';
import { UserContext } from './UserProvider';

function NewBudgetForm() {
  const [name, setName] = useState('');
  const { user } = useContext(UserContext);

  const handleAddBudget = () => {
    // Implement the logic to add a new budget
    // Example:
    // addBudget({ name, userId: user.id });
  };

  return (
    <div>
      <h2>Dodaj Nowy Budżet</h2>
      <input
        type="text"
        placeholder="Nazwa budżetu"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <button onClick={handleAddBudget}>Dodaj Budżet</button>
    </div>
  );
}

export default NewBudgetForm;
