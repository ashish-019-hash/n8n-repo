const customerService = require('../services/customerService');

class CustomerController {
  async getCustomer(req, res, next) {
    try {
      const { customerId } = req.params;
      const customer = await customerService.getCustomerById(customerId);
      
      res.json({
        success: true,
        data: customer
      });
    } catch (error) {
      next(error);
    }
  }

  async getAllCustomers(req, res, next) {
    try {
      const { lastName } = req.query;
      const customers = await customerService.getAllCustomers({ lastName });
      
      res.json({
        success: true,
        data: customers
      });
    } catch (error) {
      next(error);
    }
  }

  async createCustomer(req, res, next) {
    try {
      const customer = await customerService.createCustomer(req.body);
      
      res.status(201).json({
        success: true,
        message: 'Customer created successfully',
        data: customer
      });
    } catch (error) {
      next(error);
    }
  }

  async updateCustomer(req, res, next) {
    try {
      const { customerId } = req.params;
      const customer = await customerService.updateCustomer(customerId, req.body);
      
      res.json({
        success: true,
        message: 'Customer updated successfully',
        data: customer
      });
    } catch (error) {
      next(error);
    }
  }

  async getCustomerAccounts(req, res, next) {
    try {
      const { customerId } = req.params;
      const accounts = await customerService.getCustomerAccounts(customerId);
      
      res.json({
        success: true,
        data: accounts
      });
    } catch (error) {
      next(error);
    }
  }

  async getCustomerCards(req, res, next) {
    try {
      const { customerId } = req.params;
      const cards = await customerService.getCustomerCards(customerId);
      
      res.json({
        success: true,
        data: cards
      });
    } catch (error) {
      next(error);
    }
  }
}

module.exports = new CustomerController();
