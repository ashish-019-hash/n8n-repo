const request = require('supertest');
const app = require('../src/index');
const { User } = require('../src/models');
const { sequelize } = require('../src/config/database');
const { generateToken } = require('../src/utils/jwt');

describe('Validation Rules', () => {
  let authToken;

  beforeAll(async () => {
    await sequelize.sync({ force: true });
    
    const user = await User.create({
      secUsrId: 'VALUSER',
      secUsrFname: 'Validation',
      secUsrLname: 'Test',
      secUsrPwd: 'val123',
      secUsrType: 'U'
    });
    
    authToken = generateToken(user.secUsrId);
  });

  afterAll(async () => {
    await sequelize.close();
  });

  describe('RULE-VAL-001: User ID Validation', () => {
    it('should reject user ID longer than 8 characters', async () => {
      const res = await request(app)
        .post('/api/auth/register')
        .send({
          userId: 'TOOLONGID',
          firstName: 'Test',
          lastName: 'User',
          password: 'pass123',
          userType: 'U'
        });

      expect(res.statusCode).toBe(400);
      expect(res.body.error).toBe('Validation failed');
    });
  });

  describe('RULE-VAL-007: Card Number Validation', () => {
    it('should reject card number not exactly 16 digits', async () => {
      const res = await request(app)
        .post('/api/cards')
        .set('Authorization', `Bearer ${authToken}`)
        .send({
          cardNum: '12345',
          cardAcctId: 1000000001,
          cardCvvCd: 123,
          cardEmbossedName: 'Test User',
          cardExpiraionDate: '2025-12-31',
          cardActiveStatus: 'A'
        });

      expect(res.statusCode).toBe(400);
      expect(res.body.error).toBe('Validation failed');
    });
  });

  describe('RULE-VAL-012: Transaction Amount Validation', () => {
    it('should reject zero or negative transaction amount', async () => {
      const res = await request(app)
        .post('/api/transactions')
        .set('Authorization', `Bearer ${authToken}`)
        .send({
          tranTypeCd: 'PU',
          tranCatCd: 1,
          tranSource: 'POS',
          tranDesc: 'Test Transaction',
          tranAmt: 0,
          tranCardNum: '1234567890123456'
        });

      expect(res.statusCode).toBe(400);
      expect(res.body.error).toBe('Validation failed');
    });
  });

  describe('RULE-VAL-025: Name Validation', () => {
    it('should reject names with invalid characters', async () => {
      const res = await request(app)
        .post('/api/customers')
        .set('Authorization', `Bearer ${authToken}`)
        .send({
          custId: 1000000001,
          custFirstName: 'Test123',
          custLastName: 'User',
          custSsn: 123456789
        });

      expect(res.statusCode).toBe(400);
      expect(res.body.error).toBe('Validation failed');
    });
  });

  describe('RULE-VAL-031: Phone Number Format', () => {
    it('should accept valid phone number format XXX-XXX-XXXX', async () => {
      const res = await request(app)
        .post('/api/customers')
        .set('Authorization', `Bearer ${authToken}`)
        .send({
          custId: 1000000002,
          custFirstName: 'John',
          custLastName: 'Doe',
          custSsn: 123456789,
          custPhoneNum1: '555-123-4567'
        });

      expect(res.statusCode).toBe(201);
      expect(res.body.success).toBe(true);
    });

    it('should reject invalid phone number format', async () => {
      const res = await request(app)
        .post('/api/customers')
        .set('Authorization', `Bearer ${authToken}`)
        .send({
          custId: 1000000003,
          custFirstName: 'Jane',
          custLastName: 'Doe',
          custSsn: 987654321,
          custPhoneNum1: '5551234567'
        });

      expect(res.statusCode).toBe(400);
      expect(res.body.error).toBe('Validation failed');
    });
  });
});
