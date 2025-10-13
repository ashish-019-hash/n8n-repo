const cardService = require('../services/cardService');

class CardController {
  async getCard(req, res, next) {
    try {
      const { cardNumber } = req.params;
      const card = await cardService.getCardByNumber(cardNumber);
      
      res.json({
        success: true,
        data: card
      });
    } catch (error) {
      next(error);
    }
  }

  async getCardsByAccount(req, res, next) {
    try {
      const { accountId } = req.params;
      const cards = await cardService.getCardsByAccount(accountId);
      
      res.json({
        success: true,
        data: cards
      });
    } catch (error) {
      next(error);
    }
  }

  async createCard(req, res, next) {
    try {
      const card = await cardService.createCard(req.body);
      
      res.status(201).json({
        success: true,
        message: 'Card created successfully',
        data: card
      });
    } catch (error) {
      next(error);
    }
  }

  async updateCard(req, res, next) {
    try {
      const { cardNumber } = req.params;
      const card = await cardService.updateCard(cardNumber, req.body);
      
      res.json({
        success: true,
        message: 'Card updated successfully',
        data: card
      });
    } catch (error) {
      next(error);
    }
  }

  async activateCard(req, res, next) {
    try {
      const { cardNumber } = req.params;
      const card = await cardService.activateCard(cardNumber);
      
      res.json({
        success: true,
        message: 'Card activated successfully',
        data: card
      });
    } catch (error) {
      next(error);
    }
  }

  async deactivateCard(req, res, next) {
    try {
      const { cardNumber } = req.params;
      const card = await cardService.deactivateCard(cardNumber);
      
      res.json({
        success: true,
        message: 'Card deactivated successfully',
        data: card
      });
    } catch (error) {
      next(error);
    }
  }

  async reportStolenCard(req, res, next) {
    try {
      const { cardNumber } = req.params;
      const card = await cardService.reportStolenCard(cardNumber);
      
      res.json({
        success: true,
        message: 'Card reported as stolen',
        data: card
      });
    } catch (error) {
      next(error);
    }
  }
}

module.exports = new CardController();
