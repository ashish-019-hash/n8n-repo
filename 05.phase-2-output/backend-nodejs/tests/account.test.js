const request = require('supertest');
const app = require('../src/index');
const { Account, User } = require('../src/models');
const { sequelize } = require('../src/config/database');
const { generateToken } = require('../src/utils/jwt');

describe('Account API', () => {
  let authToken;
  let testAccountId = 1000000001;

  beforeAll(async () => {
    await sequelize.sync({ force: true });
    
    const user = await User.create({
      secUsrId: 'TESTUSER',
      secUsrFname: 'Test',
      secUsrLname: 'User',
      secUsrPwd: 'test123',
      secUsrType: 'U'
    });
    
    authToken = generateToken(user.secUsrId);
  });

  afterAll(async () => {
    await sequelize.close();
  });

  describe('POST /api/accounts', () => {
    it('should create a new account', async () => {
      const res = await request(app)
        .post('/api/accounts')
        .set('Authorization', `Bearer ${authToken}`)
        .send({
          acctId: testAccountId,
          acctActiveStatus: 'A',
          acctCurrBal: 1500.00,
          acctCreditLimit: 5000.00,
          acctOpenDate: '2024-01-01'
        });

      expect(res.statusCode).toBe(201);
      expect(res.body.success).toBe(true);
      expect(res.body.data.acctId).toBe(testAccountId);
    });

    it('should fail without authentication', async () => {
      const res = await request(app)
        .post('/api/accounts')
        .send({
          acctId: 1000000002,
          acctActiveStatus: 'A',
          acctCreditLimit: 5000.00
        });

      expect(res.statusCode).toBe(401);
    });
  });

  describe('GET /api/accounts/:accountId', () => {
    it('should get account by ID', async () => {
      const res = await request(app)
        .get(`/api/accounts/${testAccountId}`)
        .set('Authorization', `Bearer ${authToken}`);

      expect(res.statusCode).toBe(200);
      expect(res.body.success).toBe(true);
      expect(res.body.data.acctId).toBe(testAccountId);
    });
  });

  describe('POST /api/accounts/:accountId/payment', () => {
    it('should process payment and update balance', async () => {
      const res = await request(app)
        .post(`/api/accounts/${testAccountId}/payment`)
        .set('Authorization', `Bearer ${authToken}`)
        .send({
          paymentAmount: 500.00
        });

      expect(res.statusCode).toBe(200);
      expect(res.body.success).toBe(true);
      expect(res.body.data.newBalance).toBe(1000.00);
    });
  });
});
