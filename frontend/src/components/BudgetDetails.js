import React, { useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router-dom';
import { UserContext } from './UserProvider';
import { useNavigate } from 'react-router-dom';
import apiFetch from '../api/api';
import NewExpenditureForm from './NewExpenditureForm';
import AddUserToBudgetForm from './AddUserToBudgetForm';

function BudgetDetails() {
  const { id } = useParams();
  const { user } = useContext(UserContext);
  const [budget, setBudget] = useState(null);
  const [expenditures, setExpenditures] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchBudgetDetails = async () => {
      try {
        const budgetData = await apiFetch(`/api/budgets/${id}`);
        setBudget(budgetData);
        const expendituresData = await apiFetch(`/api/expenditures/budget/${id}`);
        setExpenditures(expendituresData);
      } catch (error) {
        console.error('Failed to fetch budget details:', error);
      }
    };

    fetchBudgetDetails();
  }, [id]);

  const deleteBudget = async (id) => {
    
    try {
      await apiFetch(`/api/budgets/${id}`, {
        method: 'DELETE',
      });
      console.log('Budget deleted successfully');
      // Optionally, redirect to another page
      navigate('/budgets');
    } catch (error) {
      console.error('Failed to delete budget:', error);
    }
  };

  const deleteExpenditure = async (expenditureId) => {
    try {
      await apiFetch(`/api/expenditures/${expenditureId}`, {
        method: 'DELETE',
      });
      console.log('Expenditure deleted successfully');
      // Odśwież listę wydatków po usunięciu
      window.location.reload();
    } catch (error) {
      console.error('Failed to delete expenditure:', error);
    }
  };

  const generateReport = async () => {
    try {
      const reportData = await apiFetch(`/api/expenditures/report/${id}`);
      console.log('Report generated successfully: ', reportData);
      // Save report data in history state
      navigate(`/report/${id}`, { state: { reportData } });
    } catch (error) {
      console.error('Failed to generate report:', error);
    }
  };

  if (!budget) return <div>Loading...</div>;

  return (
    <div className="container">
      <h1>{budget.name}</h1>
      <p>Owner: {budget.owner.login}</p>
      <p>{budget.description}</p>
      <p>{budget.date}</p>

      <h2>Expenditures</h2>
      <ul>
        {expenditures.map(exp => (
          <li key={exp.id}>
            <h3>{exp.name}</h3>
            <p>Amount: {exp.amount}</p>
            <p>Description: {exp.description}</p>
            <p>Date: {exp.date}</p>
            <p>Creator: {exp.creator.login}</p>
            <button onClick={() => deleteExpenditure(exp.id)}>Delete expenditure</button>
          </li>
        ))}
      </ul>

      <NewExpenditureForm budgetId={id} />
      <AddUserToBudgetForm budgetId={id} />
      <button onClick={() => deleteBudget(id)}>Delete budget</button>
      <button onClick={generateReport}>Generate raport</button>
    </div>
  );
}

export default BudgetDetails;
