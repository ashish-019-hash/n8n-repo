# CardDemo API Documentation

## Base URL
```
http://localhost:3000/api
```

## Authentication

All endpoints except `/auth/login` and `/auth/register` require JWT authentication.

Include the JWT token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## Response Format

All API responses follow this format:

### Success Response
```json
{
  "success": true,
  "message": "Optional success message",
  "data": { /* response data */ }
}
```

### Error Response
```json
{
  "error": "Error message",
  "details": [
    {
      "field": "fieldName",
      "message": "Validation error message",
      "value": "invalid value"
    }
  ]
}
```

## Endpoints

### 1. Authentication

#### POST /api/auth/register
Register a new user.

**Request Body:**
```json
{
  "userId": "NEWUSER",
  "firstName": "John",
  "lastName": "Doe",
  "password": "pass123",
  "userType": "U"
}
```

**Validation Rules:**
- `userId`: 1-8 alphanumeric characters (RULE-VAL-001)
- `password`: 1-8 characters (RULE-VAL-002)
- `userType`: 'A' (Admin) or 'U' (User)

**Response:** 201 Created
```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "userId": "NEWUSER",
    "firstName": "John",
    "lastName": "Doe",
    "userType": "U"
  }
}
```

#### POST /api/auth/login
Authenticate user and receive JWT token.

**Request Body:**
```json
{
  "userId": "ADMIN",
  "password": "admin123"
}
```

**Response:** 200 OK
```json
{
  "success": true,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "userId": "ADMIN",
      "firstName": "Admin",
      "lastName": "User",
      "userType": "A"
    }
  }
}
```

#### GET /api/auth/me
Get current authenticated user information.

**Headers:** Authorization: Bearer <token>

**Response:** 200 OK
```json
{
  "success": true,
  "data": {
    "userId": "ADMIN",
    "firstName": "Admin",
    "lastName": "User",
    "userType": "A"
  }
}
```

### 2. Accounts

#### GET /api/accounts
Get all accounts with optional filtering.

**Query Parameters:**
- `status` (optional): 'A' (Active) or 'C' (Closed)

**Response:** 200 OK
```json
{
  "success": true,
  "data": [
    {
      "acctId": 1000000001,
      "acctActiveStatus": "A",
      "acctCurrBal": "1500.00",
      "acctCreditLimit": "5000.00",
      "Cards": [...]
    }
  ]
}
```

#### GET /api/accounts/:accountId
Get account details by ID.

**Validation:** accountId must be a positive integer (RULE-VAL-003)

**Response:** 200 OK

#### POST /api/accounts
Create a new account.

**Request Body:**
```json
{
  "acctId": 1000000001,
  "acctActiveStatus": "A",
  "acctCurrBal": 0,
  "acctCreditLimit": 5000.00,
  "acctCashCreditLimit": 1000.00,
  "acctOpenDate": "2024-01-01",
  "acctAddrZip": "12345"
}
```

**Validation Rules:**
- `acctId`: Required, positive integer
- `acctActiveStatus`: 'A' or 'C' (RULE-VAL-004)
- `acctCreditLimit`: Required, positive number (RULE-VAL-005)
- Date format: YYYY-MM-DD (RULE-VAL-019)

**Response:** 201 Created

#### PUT /api/accounts/:accountId
Update account information.

**Request Body:**
```json
{
  "acctActiveStatus": "A",
  "acctCreditLimit": 7500.00
}
```

**Response:** 200 OK

#### GET /api/accounts/:accountId/balance
Get account balance and credit information.

**Response:** 200 OK
```json
{
  "success": true,
  "data": {
    "accountId": 1000000001,
    "currentBalance": "1500.00",
    "creditLimit": "5000.00",
    "availableCredit": "3500.00",
    "currentCycleCredit": "200.00",
    "currentCycleDebit": "1700.00"
  }
}
```

#### POST /api/accounts/:accountId/payment
Process bill payment (RULE-CALC-007).

**Request Body:**
```json
{
  "paymentAmount": 500.00
}
```

**Validation:** paymentAmount must be > 0

**Business Rule:** New balance = Current balance - Payment amount

**Response:** 200 OK
```json
{
  "success": true,
  "message": "Payment processed successfully",
  "data": {
    "accountId": 1000000001,
    "paymentAmount": 500.00,
    "previousBalance": "1500.00",
    "newBalance": "1000.00"
  }
}
```

### 3. Cards

#### GET /api/cards/:cardNumber
Get card details by card number.

**Validation:** cardNumber must be exactly 16 digits (RULE-VAL-007)

**Response:** 200 OK

#### GET /api/cards/account/:accountId
Get all cards for an account.

**Response:** 200 OK
```json
{
  "success": true,
  "data": [
    {
      "cardNum": "4111111111111111",
      "cardAcctId": 1000000001,
      "cardEmbossedName": "JOHN DOE",
      "cardExpiraionDate": "2025-12-31",
      "cardActiveStatus": "A"
    }
  ]
}
```

#### POST /api/cards
Create a new card.

**Request Body:**
```json
{
  "cardNum": "4111111111111111",
  "cardAcctId": 1000000001,
  "cardCvvCd": 123,
  "cardEmbossedName": "JOHN DOE",
  "cardExpiraionDate": "2025-12-31",
  "cardActiveStatus": "A"
}
```

**Validation Rules:**
- `cardNum`: Exactly 16 digits (RULE-VAL-007)
- `cardCvvCd`: 3-digit number (RULE-VAL-008)
- `cardExpiraionDate`: YYYY-MM-DD format (RULE-VAL-009)
- `cardActiveStatus`: 'A', 'C', or 'S' (RULE-VAL-010)

**Response:** 201 Created

#### PUT /api/cards/:cardNumber
Update card information.

**Request Body:**
```json
{
  "cardActiveStatus": "C",
  "cardExpiraionDate": "2026-12-31"
}
```

**Response:** 200 OK

#### POST /api/cards/:cardNumber/activate
Activate a card.

**Response:** 200 OK
```json
{
  "success": true,
  "message": "Card activated successfully",
  "data": { /* card data */ }
}
```

#### POST /api/cards/:cardNumber/deactivate
Deactivate a card.

**Response:** 200 OK

#### POST /api/cards/:cardNumber/report-stolen
Report card as stolen.

**Response:** 200 OK
```json
{
  "success": true,
  "message": "Card reported as stolen",
  "data": { /* card data with status 'S' */ }
}
```

### 4. Transactions

#### GET /api/transactions/:transactionId
Get transaction by ID.

**Response:** 200 OK

#### GET /api/transactions/card/:cardNumber
Get all transactions for a card.

**Query Parameters:**
- `startDate` (optional): YYYY-MM-DD
- `endDate` (optional): YYYY-MM-DD
- `typeCd` (optional): Transaction type code
- `limit` (optional): 1-100 (default 50)

**Response:** 200 OK
```json
{
  "success": true,
  "data": [
    {
      "tranId": "T1697123456ABCD",
      "tranTypeCd": "PU",
      "tranCatCd": 1,
      "tranSource": "POS",
      "tranDesc": "GROCERY STORE",
      "tranAmt": "45.67",
      "tranMerchantName": "Whole Foods",
      "tranCardNum": "4111111111111111",
      "tranOrigTs": "2024-01-15T14:30:00.000Z"
    }
  ]
}
```

#### GET /api/transactions/account/:accountId
Get all transactions for an account.

**Query Parameters:** Same as card transactions

**Response:** 200 OK

#### GET /api/transactions/card/:cardNumber/summary
Get transaction summary for a card.

**Query Parameters:**
- `startDate` (required): YYYY-MM-DD
- `endDate` (required): YYYY-MM-DD

**Response:** 200 OK
```json
{
  "success": true,
  "data": {
    "totalTransactions": 45,
    "totalAmount": "1234.56",
    "byCategory": {
      "1": {
        "count": 15,
        "amount": "456.78"
      },
      "2": {
        "count": 10,
        "amount": "234.56"
      }
    }
  }
}
```

#### POST /api/transactions
Create a new transaction.

**Request Body:**
```json
{
  "tranTypeCd": "PU",
  "tranCatCd": 1,
  "tranSource": "POS",
  "tranDesc": "GROCERY STORE",
  "tranAmt": 45.67,
  "tranCardNum": "4111111111111111",
  "tranMerchantName": "Whole Foods",
  "tranMerchantCity": "New York",
  "tranMerchantZip": "10001"
}
```

**Validation Rules:**
- `tranAmt`: Must be > 0 (RULE-VAL-012)
- `tranCardNum`: 16 digits (RULE-VAL-007)
- Card must be active
- Account must be active

**Business Logic:**
- Auto-generates transaction ID
- Sets timestamps
- Updates account balance

**Response:** 201 Created

### 5. Customers

#### GET /api/customers
Get all customers with optional filtering.

**Query Parameters:**
- `lastName` (optional): Filter by last name

**Response:** 200 OK

#### GET /api/customers/:customerId
Get customer details by ID.

**Validation:** customerId must be positive integer (RULE-VAL-027)

**Response:** 200 OK
```json
{
  "success": true,
  "data": {
    "custId": 1000000001,
    "custFirstName": "John",
    "custLastName": "Doe",
    "custPhoneNum1": "555-123-4567",
    "custAddrZip": "12345",
    "custFicoCreditScore": 750,
    "CardXrefs": [...]
  }
}
```

#### POST /api/customers
Create a new customer.

**Request Body:**
```json
{
  "custId": 1000000001,
  "custFirstName": "John",
  "custMiddleName": "M",
  "custLastName": "Doe",
  "custAddrLine1": "123 Main St",
  "custAddrStateCd": "NY",
  "custAddrCountryCd": "USA",
  "custAddrZip": "12345",
  "custPhoneNum1": "555-123-4567",
  "custSsn": 123456789,
  "custDobYyyyMmDd": "1990-01-15",
  "custFicoCreditScore": 750
}
```

**Validation Rules:**
- `custFirstName`, `custLastName`: Letters, spaces, hyphens only (RULE-VAL-025, RULE-VAL-026)
- `custSsn`: 9-digit number (RULE-VAL-028)
- `custPhoneNum1`: XXX-XXX-XXXX format (RULE-VAL-031)
- `custAddrStateCd`: 2 letters (RULE-VAL-033)
- `custAddrZip`: XXXXX or XXXXX-XXXX (RULE-VAL-036)
- `custFicoCreditScore`: 300-850 (RULE-VAL-045)

**Response:** 201 Created

#### PUT /api/customers/:customerId
Update customer information.

**Request Body:**
```json
{
  "custFirstName": "Jonathan",
  "custPhoneNum1": "555-987-6543"
}
```

**Response:** 200 OK

#### GET /api/customers/:customerId/accounts
Get all accounts for a customer.

**Response:** 200 OK

#### GET /api/customers/:customerId/cards
Get all cards for a customer.

**Response:** 200 OK

### 6. Health Check

#### GET /api/health
Check API health status.

**Response:** 200 OK
```json
{
  "success": true,
  "message": "CardDemo API is running",
  "timestamp": "2024-01-15T10:30:00.000Z"
}
```

## Error Codes

- `400` - Bad Request / Validation Error
- `401` - Unauthorized (missing or invalid token)
- `403` - Forbidden (insufficient permissions)
- `404` - Not Found
- `409` - Conflict (duplicate entry)
- `500` - Internal Server Error

## Rate Limiting

Currently not implemented. Consider adding rate limiting for production deployment.

## CORS

CORS is enabled for all origins in development. Configure appropriately for production.

## Testing

Use the provided test suite:
```bash
npm test
```

Or test endpoints manually with curl:
```bash
# Login
curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"userId":"ADMIN","password":"admin123"}'

# Get accounts (with token)
curl -X GET http://localhost:3000/api/accounts \
  -H "Authorization: Bearer <your-token>"
```
