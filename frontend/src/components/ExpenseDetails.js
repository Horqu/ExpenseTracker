import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function ExpenseDetails() {
  const { id } = useParams();
  const [expense, setExpense] = useState(null);

  useEffect(() => {
    // Fetch expense details
    // Example:
    // setExpense(fetchExpense(id));
  }, [id]);

  if (!expense) return <div>Loading...</div>;

  return (
    <div>
      <h1>Szczegóły Wydatku: {expense.name}</h1>
      <p>Kwota: {expense.amount}</p>
      <button onClick={() => deleteExpense(id)}>Usuń Wydatek</button>
    </div>
  );
}

function deleteExpense(id) {
  // Implement the logic to delete the expense
}

export default ExpenseDetails;
