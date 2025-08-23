-- Insert employees
-- password is 'password'
INSERT INTO employees (username, password, name, role, manager_id, created_at)
VALUES
    ('john.doe', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'John Doe', 'CTO', NULL, CURRENT_TIMESTAMP),
    ('jane.smith', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'Jane Smith', 'MANAGER', 1, CURRENT_TIMESTAMP),
    ('bob.jones', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'Bob Jones', 'TEAM_LEAD', 2, CURRENT_TIMESTAMP),
    ('alice.brown', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'Alice Brown', 'DEVELOPER', 2, CURRENT_TIMESTAMP),
    ('charlie.white', '$2a$10$agYlOt7wO99xtRseJdWmvu/ES2KBlpV5sBgfXhnJWEY1zFvxK5YlW', 'Charlie White', 'DEVELOPER', 2, CURRENT_TIMESTAMP);

-- Insert projects
INSERT INTO projects (name, manager_id, created_at)
VALUES
    ('Project Alpha', 2, CURRENT_TIMESTAMP),
    ('Project Beta', 2, CURRENT_TIMESTAMP);

-- Insert project-developer assignments
INSERT INTO project_developers (project_id, employee_id)
VALUES
    (1, 4), -- Alice Brown on Project Alpha
    (1, 5), -- Charlie White on Project Alpha
    (2, 4); -- Alice Brown on Project Beta