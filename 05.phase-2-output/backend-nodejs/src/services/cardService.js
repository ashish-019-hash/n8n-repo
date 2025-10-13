const { Card, Account, Transaction, CardXref } = require('../models');
const logger = require('../utils/logger');

class CardService {
  async getCardByNumber(cardNumber) {
    try {
      const card = await Card.findByPk(cardNumber, {
        include: [
          { model: Account, as: 'Account' },
          { model: CardXref, as: 'CardXrefs' }
        ]
      });

      if (!card) {
        throw new Error('Card not found');
      }

      return card;
    } catch (error) {
      logger.error('Error fetching card:', { cardNumber, error: error.message });
      throw error;
    }
  }

  async getCardsByAccount(accountId) {
    try {
      const cards = await Card.findAll({
        where: { cardAcctId: accountId },
        order: [['cardNum', 'ASC']]
      });

      return cards;
    } catch (error) {
      logger.error('Error fetching cards by account:', { accountId, error: error.message });
      throw error;
    }
  }

  async createCard(cardData) {
    try {
      const account = await Account.findByPk(cardData.cardAcctId);
      
      if (!account) {
        throw new Error('Account not found');
      }

      const card = await Card.create(cardData);
      logger.info(`Card created: ${card.cardNum}`);
      
      return card;
    } catch (error) {
      logger.error('Error creating card:', { error: error.message });
      throw error;
    }
  }

  async updateCard(cardNumber, updateData) {
    try {
      const card = await Card.findByPk(cardNumber);
      
      if (!card) {
        throw new Error('Card not found');
      }

      await card.update(updateData);
      logger.info(`Card updated: ${cardNumber}`);
      
      return card;
    } catch (error) {
      logger.error('Error updating card:', { cardNumber, error: error.message });
      throw error;
    }
  }

  async activateCard(cardNumber) {
    try {
      const card = await Card.findByPk(cardNumber);
      
      if (!card) {
        throw new Error('Card not found');
      }

      await card.update({ cardActiveStatus: 'A' });
      logger.info(`Card activated: ${cardNumber}`);
      
      return card;
    } catch (error) {
      logger.error('Error activating card:', { cardNumber, error: error.message });
      throw error;
    }
  }

  async deactivateCard(cardNumber) {
    try {
      const card = await Card.findByPk(cardNumber);
      
      if (!card) {
        throw new Error('Card not found');
      }

      await card.update({ cardActiveStatus: 'C' });
      logger.info(`Card deactivated: ${cardNumber}`);
      
      return card;
    } catch (error) {
      logger.error('Error deactivating card:', { cardNumber, error: error.message });
      throw error;
    }
  }

  async reportStolenCard(cardNumber) {
    try {
      const card = await Card.findByPk(cardNumber);
      
      if (!card) {
        throw new Error('Card not found');
      }

      await card.update({ cardActiveStatus: 'S' });
      logger.info(`Card reported stolen: ${cardNumber}`);
      
      return card;
    } catch (error) {
      logger.error('Error reporting stolen card:', { cardNumber, error: error.message });
      throw error;
    }
  }
}

module.exports = new CardService();
