const express = require('express');
const router = express.Router();
const transactionController = require('../controllers/transactionController');
const { createTransactionValidator, transactionQueryValidator } = require('../validators/transactionValidator');
const validate = require('../middlewares/validator');
const { authenticate } = require('../middlewares/auth');

router.use(authenticate);

router.get('/:transactionId', transactionController.getTransaction);
router.get('/card/:cardNumber', transactionQueryValidator, validate, transactionController.getTransactionsByCard);
router.get('/account/:accountId', transactionQueryValidator, validate, transactionController.getTransactionsByAccount);
router.get('/card/:cardNumber/summary', transactionQueryValidator, validate, transactionController.getTransactionSummary);
router.post('/', createTransactionValidator, validate, transactionController.createTransaction);

module.exports = router;
