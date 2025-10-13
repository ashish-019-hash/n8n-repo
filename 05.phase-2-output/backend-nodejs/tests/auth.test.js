const request = require('supertest');
const app = require('../src/index');
const { User } = require('../src/models');
const { sequelize } = require('../src/config/database');

describe('Auth API', () => {
  beforeAll(async () => {
    await sequelize.sync({ force: true });
  });

  afterAll(async () => {
    await sequelize.close();
  });

  describe('POST /api/auth/register', () => {
    it('should register a new user', async () => {
      const res = await request(app)
        .post('/api/auth/register')
        .send({
          userId: 'TEST01',
          firstName: 'Test',
          lastName: 'User',
          password: 'test123',
          userType: 'U'
        });

      expect(res.statusCode).toBe(201);
      expect(res.body.success).toBe(true);
      expect(res.body.data.userId).toBe('TEST01');
    });

    it('should fail with invalid data', async () => {
      const res = await request(app)
        .post('/api/auth/register')
        .send({
          userId: '',
          firstName: 'Test',
          lastName: 'User',
          password: 'test123'
        });

      expect(res.statusCode).toBe(400);
    });
  });

  describe('POST /api/auth/login', () => {
    beforeEach(async () => {
      await User.create({
        secUsrId: 'LOGIN01',
        secUsrFname: 'Login',
        secUsrLname: 'Test',
        secUsrPwd: 'login123',
        secUsrType: 'U'
      });
    });

    it('should login successfully', async () => {
      const res = await request(app)
        .post('/api/auth/login')
        .send({
          userId: 'LOGIN01',
          password: 'login123'
        });

      expect(res.statusCode).toBe(200);
      expect(res.body.success).toBe(true);
      expect(res.body.data.token).toBeDefined();
    });

    it('should fail with wrong password', async () => {
      const res = await request(app)
        .post('/api/auth/login')
        .send({
          userId: 'LOGIN01',
          password: 'wrongpass'
        });

      expect(res.statusCode).toBe(500);
    });
  });
});
