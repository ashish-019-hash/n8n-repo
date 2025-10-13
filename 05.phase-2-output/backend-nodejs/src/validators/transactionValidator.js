const { body, query } = require('express-validator');

const createTransactionValidator = [
  body('tranTypeCd')
    .notEmpty().withMessage('Transaction type is required')
    .isLength({ min: 2, max: 2 }).withMessage('Transaction type must be exactly 2 characters'),
  
  body('tranCatCd')
    .notEmpty().withMessage('Transaction category is required')
    .isInt({ min: 0 }).withMessage('Transaction category must be a positive integer'),
  
  body('tranSource')
    .notEmpty().withMessage('Transaction source is required')
    .isLength({ max: 10 }).withMessage('Transaction source must not exceed 10 characters'),
  
  body('tranDesc')
    .notEmpty().withMessage('Transaction description is required')
    .isLength({ max: 100 }).withMessage('Transaction description must not exceed 100 characters'),
  
  body('tranAmt')
    .notEmpty().withMessage('Transaction amount is required')
    .isFloat({ min: 0.01 }).withMessage('Transaction amount must be greater than 0'),
  
  body('tranCardNum')
    .notEmpty().withMessage('Card number is required')
    .isLength({ min: 16, max: 16 }).withMessage('Card number must be exactly 16 digits')
    .isNumeric().withMessage('Card number must contain only digits'),
  
  body('tranMerchantName')
    .optional()
    .isLength({ max: 50 }).withMessage('Merchant name must not exceed 50 characters')
];

const transactionQueryValidator = [
  query('cardNumber')
    .optional()
    .isLength({ min: 16, max: 16 }).withMessage('Card number must be exactly 16 digits')
    .isNumeric().withMessage('Card number must contain only digits'),
  
  query('startDate')
    .optional()
    .matches(/^\d{4}-\d{2}-\d{2}$/).withMessage('Start date must be in YYYY-MM-DD format'),
  
  query('endDate')
    .optional()
    .matches(/^\d{4}-\d{2}-\d{2}$/).withMessage('End date must be in YYYY-MM-DD format'),
  
  query('limit')
    .optional()
    .isInt({ min: 1, max: 100 }).withMessage('Limit must be between 1 and 100')
];

module.exports = {
  createTransactionValidator,
  transactionQueryValidator
};
