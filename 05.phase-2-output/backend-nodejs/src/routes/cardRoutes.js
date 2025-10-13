const express = require('express');
const router = express.Router();
const cardController = require('../controllers/cardController');
const { cardNumberValidator, createCardValidator, updateCardValidator } = require('../validators/cardValidator');
const validate = require('../middlewares/validator');
const { authenticate } = require('../middlewares/auth');

router.use(authenticate);

router.get('/:cardNumber', cardNumberValidator, validate, cardController.getCard);
router.get('/account/:accountId', cardController.getCardsByAccount);
router.post('/', createCardValidator, validate, cardController.createCard);
router.put('/:cardNumber', updateCardValidator, validate, cardController.updateCard);
router.post('/:cardNumber/activate', cardNumberValidator, validate, cardController.activateCard);
router.post('/:cardNumber/deactivate', cardNumberValidator, validate, cardController.deactivateCard);
router.post('/:cardNumber/report-stolen', cardNumberValidator, validate, cardController.reportStolenCard);

module.exports = router;
