import React, { createContext, useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';

export const UserContext = createContext();

function UserProvider({ children }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        setUser({ id: decodedToken.userId, token });
      } catch (error) {
        console.error('Failed to decode token', error);
      }
    }
  }, []);

  const login = (token) => {
    const decodedToken = jwtDecode(token);
    setUser({ id: decodedToken.userId, token });
    localStorage.setItem('token', token);
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem('token');
  };

  return (
    <UserContext.Provider value={{ user, login, logout }}>
      {children}
    </UserContext.Provider>
  );
}

// function UserProvider({ children }) {
//   const [user, setUser] = useState(() => {
//     const token = localStorage.getItem('token');
//     if (token) {
//       try {
//         const decodedToken = jwtDecode(token);
//         return { id: decodedToken.id, token };
//       } catch (error) {
//         console.error('Failed to decode token', error);
//         return null;
//       }
//     }
//     return null;
//   });

//   return (
//     <UserContext.Provider value={{ user, setUser }}>
//       {children}
//     </UserContext.Provider>
//   );
// }

export default UserProvider;
