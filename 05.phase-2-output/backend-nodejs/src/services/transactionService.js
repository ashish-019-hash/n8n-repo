const { Transaction, Card, Account, TransactionType, TransactionCategory, DailyTransaction } = require('../models');
const { Op } = require('sequelize');
const logger = require('../utils/logger');

class TransactionService {
  async getTransactionById(transactionId) {
    try {
      const transaction = await Transaction.findByPk(transactionId, {
        include: [
          { model: Card, as: 'Card' },
          { model: TransactionType, as: 'TransactionType' }
        ]
      });

      if (!transaction) {
        throw new Error('Transaction not found');
      }

      return transaction;
    } catch (error) {
      logger.error('Error fetching transaction:', { transactionId, error: error.message });
      throw error;
    }
  }

  async getTransactionsByCard(cardNumber, filters = {}) {
    try {
      const where = { tranCardNum: cardNumber };

      if (filters.startDate && filters.endDate) {
        where.tranOrigTs = {
          [Op.between]: [filters.startDate, filters.endDate]
        };
      }

      if (filters.typeCd) {
        where.tranTypeCd = filters.typeCd;
      }

      const transactions = await Transaction.findAll({
        where,
        include: [{ model: TransactionType, as: 'TransactionType' }],
        order: [['tranOrigTs', 'DESC']],
        limit: filters.limit || 50
      });

      return transactions;
    } catch (error) {
      logger.error('Error fetching transactions by card:', { cardNumber, error: error.message });
      throw error;
    }
  }

  async getTransactionsByAccount(accountId, filters = {}) {
    try {
      const cards = await Card.findAll({
        where: { cardAcctId: accountId },
        attributes: ['cardNum']
      });

      const cardNumbers = cards.map(card => card.cardNum);

      if (cardNumbers.length === 0) {
        return [];
      }

      const where = { tranCardNum: { [Op.in]: cardNumbers } };

      if (filters.startDate && filters.endDate) {
        where.tranOrigTs = {
          [Op.between]: [filters.startDate, filters.endDate]
        };
      }

      const transactions = await Transaction.findAll({
        where,
        include: [
          { model: Card, as: 'Card' },
          { model: TransactionType, as: 'TransactionType' }
        ],
        order: [['tranOrigTs', 'DESC']],
        limit: filters.limit || 100
      });

      return transactions;
    } catch (error) {
      logger.error('Error fetching transactions by account:', { accountId, error: error.message });
      throw error;
    }
  }

  async createTransaction(transactionData) {
    try {
      const card = await Card.findByPk(transactionData.tranCardNum);
      
      if (!card) {
        throw new Error('Card not found');
      }

      if (card.cardActiveStatus !== 'A') {
        throw new Error('Card is not active');
      }

      const account = await Account.findByPk(card.cardAcctId);
      
      if (!account) {
        throw new Error('Account not found');
      }

      if (account.acctActiveStatus !== 'A') {
        throw new Error('Account is not active');
      }

      const tranId = `T${Date.now()}${Math.random().toString(36).substr(2, 4)}`;
      const timestamp = new Date().toISOString();

      const transaction = await Transaction.create({
        ...transactionData,
        tranId,
        tranOrigTs: timestamp,
        tranProcTs: timestamp
      });

      const newBalance = parseFloat(account.acctCurrBal) + parseFloat(transactionData.tranAmt);
      await account.update({ acctCurrBal: newBalance });

      logger.info(`Transaction created: ${tranId} for card ${transactionData.tranCardNum}`);
      
      return transaction;
    } catch (error) {
      logger.error('Error creating transaction:', { error: error.message });
      throw error;
    }
  }

  async getTransactionSummary(cardNumber, startDate, endDate) {
    try {
      const transactions = await Transaction.findAll({
        where: {
          tranCardNum: cardNumber,
          tranOrigTs: {
            [Op.between]: [startDate, endDate]
          }
        }
      });

      const summary = {
        totalTransactions: transactions.length,
        totalAmount: 0,
        byCategory: {}
      };

      transactions.forEach(transaction => {
        summary.totalAmount += parseFloat(transaction.tranAmt);
        
        const category = transaction.tranCatCd;
        if (!summary.byCategory[category]) {
          summary.byCategory[category] = {
            count: 0,
            amount: 0
          };
        }
        summary.byCategory[category].count++;
        summary.byCategory[category].amount += parseFloat(transaction.tranAmt);
      });

      return summary;
    } catch (error) {
      logger.error('Error generating transaction summary:', { cardNumber, error: error.message });
      throw error;
    }
  }
}

module.exports = new TransactionService();
