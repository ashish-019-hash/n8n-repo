const express = require('express');
const router = express.Router();
const customerController = require('../controllers/customerController');
const { customerIdValidator, createCustomerValidator, updateCustomerValidator } = require('../validators/customerValidator');
const validate = require('../middlewares/validator');
const { authenticate } = require('../middlewares/auth');

router.use(authenticate);

router.get('/', customerController.getAllCustomers);
router.get('/:customerId', customerIdValidator, validate, customerController.getCustomer);
router.post('/', createCustomerValidator, validate, customerController.createCustomer);
router.put('/:customerId', updateCustomerValidator, validate, customerController.updateCustomer);
router.get('/:customerId/accounts', customerIdValidator, validate, customerController.getCustomerAccounts);
router.get('/:customerId/cards', customerIdValidator, validate, customerController.getCustomerCards);

module.exports = router;
