const { Customer, CardXref, Account, Card } = require('../models');
const logger = require('../utils/logger');

class CustomerService {
  async getCustomerById(customerId) {
    try {
      const customer = await Customer.findByPk(customerId, {
        include: [
          {
            model: CardXref,
            as: 'CardXrefs',
            include: [
              { model: Account, as: 'Account' },
              { model: Card, as: 'Card' }
            ]
          }
        ]
      });

      if (!customer) {
        throw new Error('Customer not found');
      }

      return customer;
    } catch (error) {
      logger.error('Error fetching customer:', { customerId, error: error.message });
      throw error;
    }
  }

  async getAllCustomers(filters = {}) {
    try {
      const where = {};

      if (filters.lastName) {
        where.custLastName = filters.lastName;
      }

      const customers = await Customer.findAll({
        where,
        order: [['custId', 'ASC']]
      });

      return customers;
    } catch (error) {
      logger.error('Error fetching customers:', { error: error.message });
      throw error;
    }
  }

  async createCustomer(customerData) {
    try {
      const customer = await Customer.create(customerData);
      logger.info(`Customer created: ${customer.custId}`);
      return customer;
    } catch (error) {
      logger.error('Error creating customer:', { error: error.message });
      throw error;
    }
  }

  async updateCustomer(customerId, updateData) {
    try {
      const customer = await Customer.findByPk(customerId);
      
      if (!customer) {
        throw new Error('Customer not found');
      }

      await customer.update(updateData);
      logger.info(`Customer updated: ${customerId}`);
      
      return customer;
    } catch (error) {
      logger.error('Error updating customer:', { customerId, error: error.message });
      throw error;
    }
  }

  async getCustomerAccounts(customerId) {
    try {
      const cardXrefs = await CardXref.findAll({
        where: { xrefCustId: customerId },
        include: [
          { model: Account, as: 'Account' },
          { model: Card, as: 'Card' }
        ]
      });

      const accounts = [...new Map(
        cardXrefs.map(xref => [xref.xrefAcctId, xref.Account])
      ).values()];

      return accounts;
    } catch (error) {
      logger.error('Error fetching customer accounts:', { customerId, error: error.message });
      throw error;
    }
  }

  async getCustomerCards(customerId) {
    try {
      const cardXrefs = await CardXref.findAll({
        where: { xrefCustId: customerId },
        include: [{ model: Card, as: 'Card' }]
      });

      return cardXrefs.map(xref => xref.Card);
    } catch (error) {
      logger.error('Error fetching customer cards:', { customerId, error: error.message });
      throw error;
    }
  }
}

module.exports = new CustomerService();
