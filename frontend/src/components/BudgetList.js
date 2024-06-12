import React, { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';
import { UserContext } from './UserProvider';
import NewBudgetForm from './NewBudgetForm';

function BudgetList() {
  const { user } = useContext(UserContext);
  const [budgets, setBudgets] = useState([]);

  useEffect(() => {
    // Fetch budgets for the logged-in user
    // Example:
    // setBudgets(fetchBudgets(user.id));
  }, [user]);

  return (
    <div>
      <h1>Budżety</h1>
      <ul>
        {budgets.map(budget => (
          <li key={budget.id}>
            <Link to={`/budget/${budget.id}`}>{budget.name}</Link>
          </li>
        ))}
      </ul>
      <NewBudgetForm />
    </div>
  );
}

export default BudgetList;
