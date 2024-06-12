import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';

function BudgetDetails() {
  const { id } = useParams();
  const [budget, setBudget] = useState(null);
  const [expenses, setExpenses] = useState([]);

  useEffect(() => {
    // Fetch budget details and expenses
    // Example:
    // const fetchedBudget = fetchBudget(id);
    // const fetchedExpenses = fetchExpenses(id);
    // setBudget(fetchedBudget);
    // setExpenses(fetchedExpenses);
  }, [id]);

  if (!budget) return <div>Loading...</div>;

  return (
    <div>
      <h1>Szczegóły Budżetu: {budget.name}</h1>
      <ul>
        {expenses.map(expense => (
          <li key={expense.id}>
            <Link to={`/expense/${expense.id}`}>{expense.name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default BudgetDetails;
