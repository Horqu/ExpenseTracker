import React from 'react';
import { useLocation } from 'react-router-dom';

function ReportPage() {
  const location = useLocation();
  const { reportData } = location.state || {};

  console.log('Received report data:', reportData);

  if (!reportData) {
    return <div>No report data available</div>;
  }

  const userMap = reportData.userBalances.reduce((map, userBalance) => {
    map[userBalance.userId] = userBalance.username;
    return map;
  }, {});

  return (
    <div className="container">
      <h1>Raport</h1>

      <h2>Salda użytkowników</h2>
      <ul>
        {reportData.userBalances.map(userBalance => (
          <li key={userBalance.userId}>
            {userBalance.username}: {userBalance.balance}
          </li>
        ))}
      </ul>

      <h2>Rozliczenia</h2>
      <ul>
        {reportData.settlements.map(settlement => (
          <li key={`${settlement.fromUserId}-${settlement.toUserId}`}>
            Użytkownik {userMap[settlement.fromUserId]} powinien zapłacić użytkownikowi {userMap[settlement.toUserId]} kwotę {settlement.amount}
            {/* Użytkownik {settlement.fromUserId} powinien zapłacić użytkownikowi {settlement.toUserId} kwotę {settlement.amount} */}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ReportPage;
