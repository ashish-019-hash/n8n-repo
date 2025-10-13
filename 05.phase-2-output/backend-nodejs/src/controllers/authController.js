const authService = require('../services/authService');
const logger = require('../utils/logger');

class AuthController {
  async login(req, res, next) {
    try {
      const { userId, password } = req.body;
      const result = await authService.login(userId, password);
      
      res.json({
        success: true,
        data: result
      });
    } catch (error) {
      next(error);
    }
  }

  async register(req, res, next) {
    try {
      const { userId, firstName, lastName, password, userType } = req.body;
      const result = await authService.register({
        userId,
        firstName,
        lastName,
        password,
        userType
      });
      
      res.status(201).json({
        success: true,
        message: 'User registered successfully',
        data: result
      });
    } catch (error) {
      next(error);
    }
  }

  async getCurrentUser(req, res, next) {
    try {
      res.json({
        success: true,
        data: {
          userId: req.user.secUsrId,
          firstName: req.user.secUsrFname,
          lastName: req.user.secUsrLname,
          userType: req.user.secUsrType
        }
      });
    } catch (error) {
      next(error);
    }
  }
}

module.exports = new AuthController();
