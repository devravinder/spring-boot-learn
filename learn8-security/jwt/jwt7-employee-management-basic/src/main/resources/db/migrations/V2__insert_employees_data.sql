-- Insert CEO
INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('ceo', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'CEO', NULL, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert HR users
INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('hr1', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'HR', 1, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('hr2', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'HR', 1, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Managers
INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('manager1', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'MANAGER', 1, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('manager2', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'MANAGER', 1, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Employees
INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('emp1', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'EMPLOYEE', 4, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('emp2', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'EMPLOYEE', 4, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('emp3', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'EMPLOYEE', 5, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('emp4', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'EMPLOYEE', 5, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (username, password, role, manager_id, enabled, created_at, updated_at)
VALUES ('emp5', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'EMPLOYEE', 4, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);