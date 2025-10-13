const express = require('express');
const router = express.Router();
const accountController = require('../controllers/accountController');
const { accountIdValidator, createAccountValidator, updateAccountValidator } = require('../validators/accountValidator');
const validate = require('../middlewares/validator');
const { authenticate } = require('../middlewares/auth');

router.use(authenticate);

router.get('/', accountController.getAllAccounts);
router.get('/:accountId', accountIdValidator, validate, accountController.getAccount);
router.post('/', createAccountValidator, validate, accountController.createAccount);
router.put('/:accountId', updateAccountValidator, validate, accountController.updateAccount);
router.get('/:accountId/balance', accountIdValidator, validate, accountController.getAccountBalance);
router.post('/:accountId/payment', accountIdValidator, validate, accountController.processPayment);

module.exports = router;
