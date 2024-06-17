import React, { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';
import { UserContext } from './UserProvider';
import apiFetch from '../api/api';
import NewBudgetForm from './NewBudgetForm';

function BudgetList() {
  const { user } = useContext(UserContext);
  const [budgets, setBudgets] = useState([]);

  useEffect(() => {
    const fetchBudgets = async () => {
      try {
        const data = await apiFetch(`/api/budgets/user/${user.id}`);
        setBudgets(data);
      } catch (error) {
        console.error('Failed to fetch budgets', error);
      }
    };

    if (user) {
      fetchBudgets();
    }
  }, [user]);

  return (
    <div className="container">
      <h1>Budgets</h1>
      <ul>
        {budgets.map(budget => (
          <li key={budget.id}>
            <Link to={`/budget/${budget.id}`}>
              <h2>{budget.name}</h2>
              <p>{budget.description}</p>
              <p>{budget.date}</p>
              <p>Owner: {budget.owner.login}</p>
            </Link>
          </li>
        ))}
      </ul>
      <NewBudgetForm />
    </div>
  );
}

export default BudgetList;
