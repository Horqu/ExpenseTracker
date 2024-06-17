import React, { useContext } from 'react';
import { UserContext } from './UserProvider';
import { useNavigate } from 'react-router-dom';

function LogoutButton() {
  const { logout } = useContext(UserContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return <button className="logout-button" onClick={handleLogout}>Logout</button>;
}

export default LogoutButton;
