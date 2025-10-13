const { User } = require('../models');
const { generateToken } = require('../utils/jwt');
const logger = require('../utils/logger');

class AuthService {
  async login(userId, password) {
    try {
      const user = await User.findByPk(userId);
      
      if (!user) {
        logger.warn(`Login attempt failed: User ${userId} not found`);
        throw new Error('Invalid credentials');
      }

      const isPasswordValid = await user.comparePassword(password);
      
      if (!isPasswordValid) {
        logger.warn(`Login attempt failed: Invalid password for user ${userId}`);
        throw new Error('Invalid credentials');
      }

      const token = generateToken(user.secUsrId);
      
      logger.info(`User ${userId} logged in successfully`);
      
      return {
        token,
        user: {
          userId: user.secUsrId,
          firstName: user.secUsrFname,
          lastName: user.secUsrLname,
          userType: user.secUsrType
        }
      };
    } catch (error) {
      logger.error('Login error:', { error: error.message });
      throw error;
    }
  }

  async register(userData) {
    try {
      const existingUser = await User.findByPk(userData.userId);
      
      if (existingUser) {
        throw new Error('User ID already exists');
      }

      const user = await User.create({
        secUsrId: userData.userId,
        secUsrFname: userData.firstName,
        secUsrLname: userData.lastName,
        secUsrPwd: userData.password,
        secUsrType: userData.userType
      });

      logger.info(`New user registered: ${userData.userId}`);

      return {
        userId: user.secUsrId,
        firstName: user.secUsrFname,
        lastName: user.secUsrLname,
        userType: user.secUsrType
      };
    } catch (error) {
      logger.error('Registration error:', { error: error.message });
      throw error;
    }
  }
}

module.exports = new AuthService();
