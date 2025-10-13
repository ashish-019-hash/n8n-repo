const { body, param } = require('express-validator');

const cardNumberValidator = [
  param('cardNumber')
    .notEmpty().withMessage('Card number is required')
    .isLength({ min: 16, max: 16 }).withMessage('Card number must be exactly 16 digits')
    .isNumeric().withMessage('Card number must contain only digits')
];

const createCardValidator = [
  body('cardNum')
    .notEmpty().withMessage('Card number is required')
    .isLength({ min: 16, max: 16 }).withMessage('Card number must be exactly 16 digits')
    .isNumeric().withMessage('Card number must contain only digits'),
  
  body('cardAcctId')
    .notEmpty().withMessage('Account ID is required')
    .isInt({ min: 0 }).withMessage('Account ID must be a positive integer'),
  
  body('cardCvvCd')
    .notEmpty().withMessage('CVV is required')
    .isInt({ min: 100, max: 999 }).withMessage('CVV must be a 3-digit number'),
  
  body('cardEmbossedName')
    .notEmpty().withMessage('Cardholder name is required')
    .isLength({ max: 50 }).withMessage('Cardholder name must not exceed 50 characters')
    .trim(),
  
  body('cardExpiraionDate')
    .notEmpty().withMessage('Expiration date is required')
    .matches(/^\d{4}-\d{2}-\d{2}$/).withMessage('Date must be in YYYY-MM-DD format'),
  
  body('cardActiveStatus')
    .notEmpty().withMessage('Card status is required')
    .isIn(['A', 'C', 'S']).withMessage('Card status must be A (Active), C (Closed), or S (Stolen)')
];

const updateCardValidator = [
  param('cardNumber')
    .notEmpty().withMessage('Card number is required')
    .isLength({ min: 16, max: 16 }).withMessage('Card number must be exactly 16 digits')
    .isNumeric().withMessage('Card number must contain only digits'),
  
  body('cardActiveStatus')
    .optional()
    .isIn(['A', 'C', 'S']).withMessage('Card status must be A (Active), C (Closed), or S (Stolen)'),
  
  body('cardExpiraionDate')
    .optional()
    .matches(/^\d{4}-\d{2}-\d{2}$/).withMessage('Date must be in YYYY-MM-DD format')
];

module.exports = {
  cardNumberValidator,
  createCardValidator,
  updateCardValidator
};
