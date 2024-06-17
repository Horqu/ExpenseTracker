import React, { useState } from 'react';
import apiFetch from '../api/api';

function RegisterForm() {
  const [login, setLogin] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');

  const handleRegister = async () => {
    const newUser = {
      login,
      email,
      password,
    };

    try {
      const response = await apiFetch('/auth/register', {
        method: 'POST',
        body: JSON.stringify(newUser),
      });

      console.log('User registered successfully', response);
      setMessage('Successfully registered!');
      
      // Reset form fields
      setLogin('');
      setEmail('');
      setPassword('');
    } catch (error) {
      console.error('Error during registration:', error);
      setMessage('Registration failed!');
    }
  };

  return (
    <div className="container">
      <h2>Register</h2>
      {message && <p>{message}</p>}
      <input
        type="text"
        placeholder="Username"
        value={login}
        onChange={e => setLogin(e.target.value)}
      />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={e => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={e => setPassword(e.target.value)}
      />
      <button onClick={handleRegister}>Register</button>
    </div>
  );
}

export default RegisterForm;

