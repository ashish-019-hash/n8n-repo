const { body, param } = require('express-validator');

const accountIdValidator = [
  param('accountId')
    .notEmpty().withMessage('Account ID is required')
    .isInt({ min: 0 }).withMessage('Account ID must be a positive integer')
];

const createAccountValidator = [
  body('acctId')
    .notEmpty().withMessage('Account ID is required')
    .isInt({ min: 0 }).withMessage('Account ID must be a positive integer'),
  
  body('acctActiveStatus')
    .notEmpty().withMessage('Account status is required')
    .isIn(['A', 'C']).withMessage('Account status must be A (Active) or C (Closed)'),
  
  body('acctCreditLimit')
    .notEmpty().withMessage('Credit limit is required')
    .isFloat({ min: 0 }).withMessage('Credit limit must be a positive number'),
  
  body('acctCashCreditLimit')
    .optional()
    .isFloat({ min: 0 }).withMessage('Cash credit limit must be a positive number'),
  
  body('acctOpenDate')
    .optional()
    .matches(/^\d{4}-\d{2}-\d{2}$/).withMessage('Date must be in YYYY-MM-DD format'),
  
  body('acctExpiraionDate')
    .optional()
    .matches(/^\d{4}-\d{2}-\d{2}$/).withMessage('Date must be in YYYY-MM-DD format'),
  
  body('acctAddrZip')
    .optional()
    .isLength({ max: 10 }).withMessage('ZIP code must not exceed 10 characters')
];

const updateAccountValidator = [
  param('accountId')
    .notEmpty().withMessage('Account ID is required')
    .isInt({ min: 0 }).withMessage('Account ID must be a positive integer'),
  
  body('acctActiveStatus')
    .optional()
    .isIn(['A', 'C']).withMessage('Account status must be A (Active) or C (Closed)'),
  
  body('acctCreditLimit')
    .optional()
    .isFloat({ min: 0 }).withMessage('Credit limit must be a positive number'),
  
  body('acctCashCreditLimit')
    .optional()
    .isFloat({ min: 0 }).withMessage('Cash credit limit must be a positive number')
];

module.exports = {
  accountIdValidator,
  createAccountValidator,
  updateAccountValidator
};
