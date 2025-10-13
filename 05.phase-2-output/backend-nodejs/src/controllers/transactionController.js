const transactionService = require('../services/transactionService');

class TransactionController {
  async getTransaction(req, res, next) {
    try {
      const { transactionId } = req.params;
      const transaction = await transactionService.getTransactionById(transactionId);
      
      res.json({
        success: true,
        data: transaction
      });
    } catch (error) {
      next(error);
    }
  }

  async getTransactionsByCard(req, res, next) {
    try {
      const { cardNumber } = req.params;
      const { startDate, endDate, typeCd, limit } = req.query;
      const transactions = await transactionService.getTransactionsByCard(cardNumber, {
        startDate,
        endDate,
        typeCd,
        limit
      });
      
      res.json({
        success: true,
        data: transactions
      });
    } catch (error) {
      next(error);
    }
  }

  async getTransactionsByAccount(req, res, next) {
    try {
      const { accountId } = req.params;
      const { startDate, endDate, limit } = req.query;
      const transactions = await transactionService.getTransactionsByAccount(accountId, {
        startDate,
        endDate,
        limit
      });
      
      res.json({
        success: true,
        data: transactions
      });
    } catch (error) {
      next(error);
    }
  }

  async createTransaction(req, res, next) {
    try {
      const transaction = await transactionService.createTransaction(req.body);
      
      res.status(201).json({
        success: true,
        message: 'Transaction created successfully',
        data: transaction
      });
    } catch (error) {
      next(error);
    }
  }

  async getTransactionSummary(req, res, next) {
    try {
      const { cardNumber } = req.params;
      const { startDate, endDate } = req.query;
      const summary = await transactionService.getTransactionSummary(cardNumber, startDate, endDate);
      
      res.json({
        success: true,
        data: summary
      });
    } catch (error) {
      next(error);
    }
  }
}

module.exports = new TransactionController();
