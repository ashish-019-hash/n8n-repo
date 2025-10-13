const { body, param } = require('express-validator');

const customerIdValidator = [
  param('customerId')
    .notEmpty().withMessage('Customer ID is required')
    .isInt({ min: 0 }).withMessage('Customer ID must be a positive integer')
];

const createCustomerValidator = [
  body('custId')
    .notEmpty().withMessage('Customer ID is required')
    .isInt({ min: 0 }).withMessage('Customer ID must be a positive integer'),
  
  body('custFirstName')
    .notEmpty().withMessage('First name is required')
    .isLength({ max: 25 }).withMessage('First name must not exceed 25 characters')
    .matches(/^[a-zA-Z\s-]+$/).withMessage('First name must contain only letters, spaces, and hyphens'),
  
  body('custMiddleName')
    .optional()
    .isLength({ max: 25 }).withMessage('Middle name must not exceed 25 characters')
    .matches(/^[a-zA-Z\s-]+$/).withMessage('Middle name must contain only letters, spaces, and hyphens'),
  
  body('custLastName')
    .notEmpty().withMessage('Last name is required')
    .isLength({ max: 25 }).withMessage('Last name must not exceed 25 characters')
    .matches(/^[a-zA-Z\s-]+$/).withMessage('Last name must contain only letters, spaces, and hyphens'),
  
  body('custSsn')
    .notEmpty().withMessage('SSN is required')
    .isInt({ min: 100000000, max: 999999999 }).withMessage('SSN must be a 9-digit number'),
  
  body('custPhoneNum1')
    .optional()
    .matches(/^\d{3}-\d{3}-\d{4}$/).withMessage('Phone number must be in format XXX-XXX-XXXX'),
  
  body('custAddrStateCd')
    .optional()
    .isLength({ min: 2, max: 2 }).withMessage('State code must be exactly 2 characters')
    .isAlpha().withMessage('State code must contain only letters'),
  
  body('custAddrCountryCd')
    .optional()
    .isLength({ min: 3, max: 3 }).withMessage('Country code must be exactly 3 characters')
    .isAlpha().withMessage('Country code must contain only letters'),
  
  body('custAddrZip')
    .optional()
    .matches(/^\d{5}(-\d{4})?$/).withMessage('ZIP code must be in format XXXXX or XXXXX-XXXX'),
  
  body('custDobYyyyMmDd')
    .optional()
    .matches(/^\d{4}-\d{2}-\d{2}$/).withMessage('Date of birth must be in YYYY-MM-DD format'),
  
  body('custFicoCreditScore')
    .optional()
    .isInt({ min: 300, max: 850 }).withMessage('FICO score must be between 300 and 850')
];

const updateCustomerValidator = [
  param('customerId')
    .notEmpty().withMessage('Customer ID is required')
    .isInt({ min: 0 }).withMessage('Customer ID must be a positive integer'),
  
  body('custFirstName')
    .optional()
    .isLength({ max: 25 }).withMessage('First name must not exceed 25 characters')
    .matches(/^[a-zA-Z\s-]+$/).withMessage('First name must contain only letters, spaces, and hyphens'),
  
  body('custLastName')
    .optional()
    .isLength({ max: 25 }).withMessage('Last name must not exceed 25 characters')
    .matches(/^[a-zA-Z\s-]+$/).withMessage('Last name must contain only letters, spaces, and hyphens'),
  
  body('custPhoneNum1')
    .optional()
    .matches(/^\d{3}-\d{3}-\d{4}$/).withMessage('Phone number must be in format XXX-XXX-XXXX')
];

module.exports = {
  customerIdValidator,
  createCustomerValidator,
  updateCustomerValidator
};
