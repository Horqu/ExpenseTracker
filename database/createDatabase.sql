CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE budget (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id INTEGER NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    CONSTRAINT fk_owner
        FOREIGN KEY (owner_id) 
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE budget_user (
    budget_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    PRIMARY KEY (budget_id, user_id),
    CONSTRAINT fk_budget
        FOREIGN KEY(budget_id)
        REFERENCES budget(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE expenditure (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    creator_id INTEGER NOT NULL,
    description TEXT,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    CONSTRAINT fk_creator
        FOREIGN KEY (creator_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE expenditure_budget (
    expenditure_id INTEGER NOT NULL,
    budget_id INTEGER NOT NULL,
    PRIMARY KEY (expenditure_id, budget_id),
    CONSTRAINT fk_expenditure
        FOREIGN KEY(expenditure_id)
        REFERENCES expenditure(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_budget
        FOREIGN KEY(budget_id)
        REFERENCES budget(id)
        ON DELETE CASCADE
);