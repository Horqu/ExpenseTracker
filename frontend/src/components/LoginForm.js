import React, { useState, useContext } from 'react';
import { UserContext } from './UserProvider';

function LoginForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { setUser } = useContext(UserContext);

  const handleLogin = () => {
    // Implement the login logic
    // Example:
    // const user = loginUser(email, password);
    // setUser(user);
  };

  return (
    <div>
      <h2>Logowanie</h2>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={e => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="Hasło"
        value={password}
        onChange={e => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>Zaloguj się</button>
    </div>
  );
}

export default LoginForm;
