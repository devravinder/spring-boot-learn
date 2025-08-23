# Employee Management System

A comprehensive REST API application built with Spring Boot that provides role-based employee management functionality with JWT authentication.

## Features

### User Hierarchy
- **CEO** (Only one allowed) - Highest authority, can perform any action
- **HR** (Multiple allowed) - Can create/delete employees, manage user data
- **Manager** (Multiple allowed) - Can modify only reporting employee data
- **Employee** (Multiple allowed) - Basic user level

### Authorization Rules
- Employee creation/deletion: HR & CEO only
- Managers can modify only their direct subordinates (employees only)
- Role conversion (Employee to Manager): CEO only
- CEO can perform any action
- Method-level security with Spring Security

### Technical Stack
- **Framework**: Spring Boot 3.2.0
- **Security**: Spring Security with JWT tokens
- **Database**: H2 in-memory database
- **Migration**: Flyway
- **Build Tool**: Gradle
- **Configuration**: application.yaml

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login and token generation

### User Management
- `GET /users/me` - Get current logged-in user info
- `GET /employees` - Get all users (HR/CEO only)
- `GET /employees/{id}` - Get user by ID (with proper authorization)
- `POST /employees` - Create new user (HR/CEO only)
- `PUT /employees/{id}` - Update user (HR/CEO/Manager for subordinates)
- `DELETE /employees/{id}` - Delete user (HR/CEO only)
- `PUT /employees/{id}/promote-to-manager` - Change user role (CEO only)
- `GET /employees/{username}/update-password` - current user or HR/CEO

## Setup Instructions

1. **Clone the repository**
2. **Navigate to project directory**
3. **Run the application**:
   ```bash
   ./gradlew bootRun
   ```

4. **Access H2 Console** (optional):
    - URL: http://localhost:8080/h2-console
    - JDBC URL: jdbc:h2:file:/home/ravinder/Drive/work-spaces/java/intellij-projects/spring-boot-learn/learn8-security/security9-authorization-complete/src/main/resources/data.db
    - Username: admin
    - Password: admin

## Sample Users

All users have password: `password`

| Username | Role | Email | Manager |
|----------|------|-------|---------|
| ceo | CEO | john.smith@company.com | None |
| hr1 | HR | sarah.johnson@company.com | CEO |
| hr2 | HR | michael.brown@company.com | CEO |
| manager1 | MANAGER | david.wilson@company.com | CEO |
| manager2 | MANAGER | emily.davis@company.com | CEO |
| emp1 | EMPLOYEE | alice.miller@company.com | manager1 |
| emp2 | EMPLOYEE | bob.taylor@company.com | manager1 |
| emp3 | EMPLOYEE | carol.anderson@company.com | manager2 |
| emp4 | EMPLOYEE | daniel.thomas@company.com | manager2 |
| emp5 | EMPLOYEE | eva.jackson@company.com | manager1 |

## API Usage Examples

### 1. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "ceo",
    "password": "password123"
  }'
```

### 2. Get Current User Info
```bash
curl -X GET http://localhost:8080/api/users/me \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 3. Create New Employee (HR/CEO only)
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "username": "newemployee",
    "password": "password123",
    "firstName": "New",
    "lastName": "Employee",
    "email": "new.employee@company.com",
    "role": "EMPLOYEE",
    "managerId": 4
  }'
```

### 4. Update Employee (Manager for subordinates, HR/CEO for any)
```bash
curl -X PUT http://localhost:8080/api/users/6 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "firstName": "Updated",
    "lastName": "Name",
    "email": "updated.email@company.com"
  }'
```

### 5. Promote Employee to Manager (CEO only)
```bash
curl -X PUT http://localhost:8080/api/users/6/role \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "newRole": "MANAGER"
  }'
```

## Security Features

- **JWT Authentication**: Stateless authentication using JWT tokens
- **Method-level Security**: Authorization checks at service method level
- **Role-based Access Control**: Different permissions for different roles
- **Password Encryption**: BCrypt password encoding
- **Hierarchy-based Authorization**: Managers can only access their subordinates

## Database Schema

### Users Table
- id (Primary Key)
- username (Unique)
- password (Encrypted)
- first_name
- last_name
- email (Unique)
- role (ENUM: EMPLOYEE, MANAGER, HR, CEO)
- manager_id (Foreign Key to users.id)
- enabled (Boolean)
- created_at
- updated_at

## Authorization Matrix

| Action | Employee | Manager | HR | CEO |
|--------|----------|---------|----|----|
| View own profile | ✓ | ✓ | ✓ | ✓ |
| View all users | ✗ | ✗ | ✓ | ✓ |
| Create user | ✗ | ✗ | ✓ | ✓ |
| Update subordinate | ✗ | ✓ | ✓ | ✓ |
| Update any user | ✗ | ✗ | ✓ | ✓ |
| Delete user | ✗ | ✗ | ✓ | ✓ |
| Change role | ✗ | ✗ | ✗ | ✓ |
| View subordinates | ✗ | ✓ | ✓ | ✓ |

## Error Handling

The application includes comprehensive error handling with appropriate HTTP status codes:
- 400: Bad Request (validation errors, business logic violations)
- 401: Unauthorized (invalid credentials, missing token)
- 403: Forbidden (insufficient permissions)
- 404: Not Found (user not found)
-