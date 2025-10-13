const accountService = require('../services/accountService');

class AccountController {
  async getAccount(req, res, next) {
    try {
      const { accountId } = req.params;
      const account = await accountService.getAccountById(accountId);
      
      res.json({
        success: true,
        data: account
      });
    } catch (error) {
      next(error);
    }
  }

  async getAllAccounts(req, res, next) {
    try {
      const { status } = req.query;
      const accounts = await accountService.getAllAccounts({ status });
      
      res.json({
        success: true,
        data: accounts
      });
    } catch (error) {
      next(error);
    }
  }

  async createAccount(req, res, next) {
    try {
      const account = await accountService.createAccount(req.body);
      
      res.status(201).json({
        success: true,
        message: 'Account created successfully',
        data: account
      });
    } catch (error) {
      next(error);
    }
  }

  async updateAccount(req, res, next) {
    try {
      const { accountId } = req.params;
      const account = await accountService.updateAccount(accountId, req.body);
      
      res.json({
        success: true,
        message: 'Account updated successfully',
        data: account
      });
    } catch (error) {
      next(error);
    }
  }

  async getAccountBalance(req, res, next) {
    try {
      const { accountId } = req.params;
      const balance = await accountService.getAccountBalance(accountId);
      
      res.json({
        success: true,
        data: balance
      });
    } catch (error) {
      next(error);
    }
  }

  async processPayment(req, res, next) {
    try {
      const { accountId } = req.params;
      const { paymentAmount } = req.body;
      const result = await accountService.processPayment(accountId, paymentAmount);
      
      res.json({
        success: true,
        message: 'Payment processed successfully',
        data: result
      });
    } catch (error) {
      next(error);
    }
  }
}

module.exports = new AccountController();
