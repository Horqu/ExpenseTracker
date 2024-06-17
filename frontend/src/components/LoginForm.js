import React, { useState, useContext } from 'react';
import { UserContext } from './UserProvider';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

function LoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useContext(UserContext);
  // const { setUser } = useContext(UserContext);
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error('Login failed');
      }

      const data = await response.json();
      const decodedToken = jwtDecode(data.token);
      const userId = decodedToken.userId;

      // Store the user ID in the context
      // setUser({ id: userId, token: data.token });
      login(data.token);

      // Optionally, you can store the token in localStorage for persistence
      // localStorage.setItem('token', data.token);

      // Redirect to the budgets page
      navigate('/budgets');
    } catch (error) {
      console.error('Error during login:', error);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={e => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={e => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>Log in</button>
    </div>
  );
}

export default LoginForm;
