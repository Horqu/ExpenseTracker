import React from 'react';
import LoginForm from '../components/LoginForm';
import RegisterForm from '../components/RegisterForm';

function HomePage() {
  return (
    <div className="container">
      <header>
        <h1>Strona Główna</h1>
      </header>
      <LoginForm />
      <RegisterForm />
    </div>
  );
}

export default HomePage;
