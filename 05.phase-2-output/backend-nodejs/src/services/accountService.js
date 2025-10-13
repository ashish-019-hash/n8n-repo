const { Account, Card, Transaction, TransactionCategoryBalance } = require('../models');
const { Op } = require('sequelize');
const logger = require('../utils/logger');

class AccountService {
  async getAccountById(accountId) {
    try {
      const account = await Account.findByPk(accountId, {
        include: [
          { model: Card, as: 'Cards' },
          { model: TransactionCategoryBalance, as: 'TransactionCategoryBalances' }
        ]
      });

      if (!account) {
        throw new Error('Account not found');
      }

      return account;
    } catch (error) {
      logger.error('Error fetching account:', { accountId, error: error.message });
      throw error;
    }
  }

  async getAllAccounts(filters = {}) {
    try {
      const where = {};
      
      if (filters.status) {
        where.acctActiveStatus = filters.status;
      }

      const accounts = await Account.findAll({
        where,
        include: [{ model: Card, as: 'Cards' }],
        order: [['acctId', 'ASC']]
      });

      return accounts;
    } catch (error) {
      logger.error('Error fetching accounts:', { error: error.message });
      throw error;
    }
  }

  async createAccount(accountData) {
    try {
      const account = await Account.create(accountData);
      logger.info(`Account created: ${account.acctId}`);
      return account;
    } catch (error) {
      logger.error('Error creating account:', { error: error.message });
      throw error;
    }
  }

  async updateAccount(accountId, updateData) {
    try {
      const account = await Account.findByPk(accountId);
      
      if (!account) {
        throw new Error('Account not found');
      }

      await account.update(updateData);
      logger.info(`Account updated: ${accountId}`);
      
      return account;
    } catch (error) {
      logger.error('Error updating account:', { accountId, error: error.message });
      throw error;
    }
  }

  async getAccountBalance(accountId) {
    try {
      const account = await Account.findByPk(accountId);
      
      if (!account) {
        throw new Error('Account not found');
      }

      return {
        accountId: account.acctId,
        currentBalance: account.acctCurrBal,
        creditLimit: account.acctCreditLimit,
        availableCredit: parseFloat(account.acctCreditLimit) - parseFloat(account.acctCurrBal),
        currentCycleCredit: account.acctCurrCycCredit,
        currentCycleDebit: account.acctCurrCycDebit
      };
    } catch (error) {
      logger.error('Error fetching account balance:', { accountId, error: error.message });
      throw error;
    }
  }

  async processPayment(accountId, paymentAmount) {
    try {
      const account = await Account.findByPk(accountId);
      
      if (!account) {
        throw new Error('Account not found');
      }

      if (paymentAmount <= 0) {
        throw new Error('Payment amount must be greater than 0');
      }

      const newBalance = parseFloat(account.acctCurrBal) - parseFloat(paymentAmount);
      
      await account.update({
        acctCurrBal: newBalance
      });

      logger.info(`Payment processed for account ${accountId}: $${paymentAmount}`);
      
      return {
        accountId: account.acctId,
        paymentAmount,
        previousBalance: account.acctCurrBal,
        newBalance
      };
    } catch (error) {
      logger.error('Error processing payment:', { accountId, error: error.message });
      throw error;
    }
  }
}

module.exports = new AccountService();
