import React, { useState, useContext } from 'react';
import { UserContext } from './UserProvider';

function RegisterForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { setUser } = useContext(UserContext);

  const handleRegister = () => {
    // Implement the registration logic
    // Example:
    // const user = registerUser(email, password);
    // setUser(user);
  };

  return (
    <div>
      <h2>Rejestracja</h2>
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
      <button onClick={handleRegister}>Zarejestruj się</button>
    </div>
  );
}

export default RegisterForm;
