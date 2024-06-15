import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import BudgetsPage from './pages/BudgetsPage';
import BudgetPage from './pages/BudgetPage';
import ExpensePage from './pages/ExpensePage';
import ReportPage from './pages/ReportPage';
import UserProvider from './components/UserProvider';

function App() {
  return (
    <UserProvider>
      <Router>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/budgets" element={<BudgetsPage />} />
          <Route path="/budget/:id" element={<BudgetPage />} />
          <Route path="/expense/:id" element={<ExpensePage />} />
          <Route path="/report/:id" element={<ReportPage />} />
        </Routes>
      </Router>
    </UserProvider>
  );
}

export default App;
