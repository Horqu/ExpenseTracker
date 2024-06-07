-- Add users
INSERT INTO users (login, password, email) VALUES 
('user1', 'password1', 'user1@example.com'),
('user2', 'password2', 'user2@example.com'),
('user3', 'password3', 'user3@example.com');

-- Add budgets
INSERT INTO budget (name, owner_id, description, date) VALUES 
('Budget1', 1, 'Description for Budget1', '2024-01-01'),
('Budget2', 2, 'Description for Budget2', '2024-02-01');

-- Link users with budgets
INSERT INTO budget_user (budget_id, user_id) VALUES 
(1, 1),
(1, 2),
(2, 2),
(2, 3);

-- Add expenditures
INSERT INTO expenditure (name, creator_id, description, amount, date) VALUES 
('Expenditure1', 1, 'Description for Expenditure1', 100.50, '2024-01-05'),
('Expenditure2', 2, 'Description for Expenditure2', 200.75, '2024-02-10');

-- Link expenditures with budgets
INSERT INTO expenditure_budget (expenditure_id, budget_id) VALUES 
(1, 1),
(2, 2);
