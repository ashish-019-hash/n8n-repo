# CardDemo Backend API

A comprehensive Node.js + PostgreSQL backend for the CardDemo credit card management system, modernized from a legacy COBOL application.

## 🚀 Features

- **Complete RESTful API** with 30+ endpoints
- **JWT Authentication** with role-based access control (Admin/User)
- **45 Validation Rules** implemented across all entities
- **Business Logic** including payment processing and transaction management
- **11 Database Models** with proper relationships
- **Production-grade** error handling and logging
- **Comprehensive Test Suite** with Jest
- **API Documentation** with example requests

## 📋 Prerequisites

- Node.js (v14 or higher)
- PostgreSQL (v12 or higher)
- npm or yarn

## 🔧 Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd 05.phase-2-output/backend-nodejs
```

2. Install dependencies:
```bash
npm install
```

3. Configure environment variables:
```bash
cp .env.example .env
```

Edit `.env` file with your PostgreSQL credentials:
```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=carddemo
DB_USER=your_username
DB_PASSWORD=your_password
PORT=3000
NODE_ENV=development
JWT_SECRET=your-secret-key
JWT_EXPIRES_IN=24h
```

4. Create PostgreSQL database:
```bash
createdb carddemo
```

5. Start the server:
```bash
npm run dev
```

The API will be available at `http://localhost:3000/api`

## 📁 Project Structure

```
backend-nodejs/
├── src/
│   ├── config/          # Database and configuration
│   ├── controllers/     # Request handlers
│   ├── middlewares/     # Auth, validation, error handling
│   ├── models/          # Sequelize models (11 entities)
│   ├── routes/          # API route definitions
│   ├── services/        # Business logic layer
│   ├── utils/           # Helpers and utilities
│   └── validators/      # Request validation rules
├── tests/               # Test suites
├── logs/                # Application logs
└── index.js            # Application entry point
```

## 🗄️ Database Models

1. **Customer** - Customer information and demographics
2. **Account** - Credit card accounts with balances and limits
3. **Card** - Credit card details and status
4. **Transaction** - Transaction records
5. **User** - System users (Admin/Regular)
6. **CardXref** - Customer-Account-Card relationships
7. **TransactionType** - Transaction type definitions
8. **TransactionCategory** - Transaction categories
9. **TransactionCategoryBalance** - Category-wise balances
10. **DisclosureGroup** - Interest rate disclosures
11. **DailyTransaction** - Daily transaction staging

## 🔐 Authentication

The API uses JWT (JSON Web Tokens) for authentication. Include the token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

### Default Users

- **Admin User**: `ADMIN` / `admin123`
- **Regular User**: `USER01` / `user123`

## 📡 API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login
- `GET /api/auth/me` - Get current user (authenticated)

### Accounts
- `GET /api/accounts` - Get all accounts
- `GET /api/accounts/:accountId` - Get account by ID
- `POST /api/accounts` - Create new account
- `PUT /api/accounts/:accountId` - Update account
- `GET /api/accounts/:accountId/balance` - Get account balance
- `POST /api/accounts/:accountId/payment` - Process payment (RULE-CALC-007)

### Cards
- `GET /api/cards/:cardNumber` - Get card details
- `GET /api/cards/account/:accountId` - Get all cards for account
- `POST /api/cards` - Create new card
- `PUT /api/cards/:cardNumber` - Update card
- `POST /api/cards/:cardNumber/activate` - Activate card
- `POST /api/cards/:cardNumber/deactivate` - Deactivate card
- `POST /api/cards/:cardNumber/report-stolen` - Report card as stolen

### Transactions
- `GET /api/transactions/:transactionId` - Get transaction by ID
- `GET /api/transactions/card/:cardNumber` - Get transactions by card
- `GET /api/transactions/account/:accountId` - Get transactions by account
- `GET /api/transactions/card/:cardNumber/summary` - Get transaction summary
- `POST /api/transactions` - Create new transaction

### Customers
- `GET /api/customers` - Get all customers
- `GET /api/customers/:customerId` - Get customer by ID
- `POST /api/customers` - Create new customer
- `PUT /api/customers/:customerId` - Update customer
- `GET /api/customers/:customerId/accounts` - Get customer accounts
- `GET /api/customers/:customerId/cards` - Get customer cards

### Health Check
- `GET /api/health` - API health status

## 📝 Example Requests

### Register User
```bash
curl -X POST http://localhost:3000/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "NEWUSER",
    "firstName": "John",
    "lastName": "Doe",
    "password": "pass123",
    "userType": "U"
  }'
```

### Login
```bash
curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "ADMIN",
    "password": "admin123"
  }'
```

### Create Account
```bash
curl -X POST http://localhost:3000/api/accounts \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your-token>" \
  -d '{
    "acctId": 1000000001,
    "acctActiveStatus": "A",
    "acctCurrBal": 1500.00,
    "acctCreditLimit": 5000.00,
    "acctOpenDate": "2024-01-01"
  }'
```

### Process Payment (Business Rule RULE-CALC-007)
```bash
curl -X POST http://localhost:3000/api/accounts/1000000001/payment \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your-token>" \
  -d '{
    "paymentAmount": 500.00
  }'
```

## ✅ Validation Rules

The API implements all 45 validation rules from the legacy COBOL system:

### Authentication (RULE-VAL-001 to RULE-VAL-002)
- User ID length (1-8 characters)
- Password length (1-8 characters)

### Account Management (RULE-VAL-003 to RULE-VAL-006)
- Account ID validation
- Account status (A=Active, C=Closed)
- Credit limit validation
- Balance validation

### Card Management (RULE-VAL-007 to RULE-VAL-011)
- Card number (16 digits)
- CVV code (3 digits)
- Expiration date format
- Card status (A=Active, C=Closed, S=Stolen)

### Transaction Processing (RULE-VAL-012 to RULE-VAL-018)
- Transaction amount (positive, non-zero)
- Transaction type validation
- Merchant information validation

### Date/Time (RULE-VAL-019 to RULE-VAL-024)
- Date format (YYYY-MM-DD)
- Timestamp validation
- Date range validation

### Personal Information (RULE-VAL-025 to RULE-VAL-045)
- Name validation (letters, spaces, hyphens only)
- SSN validation (9 digits)
- Phone number format (XXX-XXX-XXXX)
- ZIP code format
- State code (2 letters)
- FICO score range (300-850)

## 🧪 Testing

Run the test suite:
```bash
npm test
```

Run tests with coverage:
```bash
npm run test
```

Run tests in watch mode:
```bash
npm run test:watch
```

## 🔍 Logging

Application logs are written to:
- Console (development mode)
- Files in `logs/` directory (dated log files)

Log levels: ERROR, WARN, INFO, DEBUG

## 🚦 Error Handling

The API provides consistent error responses:

```json
{
  "error": "Error message",
  "details": [
    {
      "field": "fieldName",
      "message": "Validation error message"
    }
  ]
}
```

HTTP Status Codes:
- `200` - Success
- `201` - Created
- `400` - Bad Request / Validation Error
- `401` - Unauthorized
- `403` - Forbidden
- `404` - Not Found
- `409` - Conflict (duplicate entry)
- `500` - Internal Server Error

## 🔒 Security

- Passwords hashed with bcrypt
- JWT tokens for authentication
- Helmet.js for security headers
- CORS enabled
- Input validation on all endpoints
- SQL injection protection via Sequelize ORM

## 📦 Production Deployment

1. Set `NODE_ENV=production` in `.env`
2. Use a strong `JWT_SECRET`
3. Configure production database credentials
4. Use a process manager (PM2, systemd)
5. Set up reverse proxy (nginx)
6. Enable HTTPS
7. Configure logging to external service

## 🤝 API Compatibility

This backend is designed to be compatible with Angular frontends. Response formats follow standard REST conventions:

```json
{
  "success": true,
  "message": "Optional message",
  "data": { /* response data */ }
}
```

## 📚 Business Rules Implemented

### RULE-CALC-007: Bill Payment Balance Update
When processing a payment:
1. Validates payment amount > 0
2. Updates account balance: `new_balance = current_balance - payment_amount`
3. Returns payment confirmation with previous and new balance

## 🐛 Troubleshooting

### Database Connection Issues
- Verify PostgreSQL is running: `pg_isready`
- Check database exists: `psql -l`
- Verify credentials in `.env`

### Port Already in Use
- Change `PORT` in `.env`
- Or kill process: `lsof -ti:3000 | xargs kill`

### Module Not Found
- Reinstall dependencies: `rm -rf node_modules && npm install`

## 📄 License

ISC

## 👥 Support

For issues or questions, please create an issue in the repository.

---

Built with ❤️ for CardDemo Modernization Project
