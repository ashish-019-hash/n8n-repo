const { body } = require('express-validator');

const loginValidator = [
  body('userId')
    .trim()
    .notEmpty().withMessage('User ID is required')
    .isLength({ min: 1, max: 8 }).withMessage('User ID must be 1-8 characters'),
  
  body('password')
    .trim()
    .notEmpty().withMessage('Password is required')
    .isLength({ min: 1, max: 8 }).withMessage('Password must be 1-8 characters')
];

const registerValidator = [
  body('userId')
    .trim()
    .notEmpty().withMessage('User ID is required')
    .isLength({ min: 1, max: 8 }).withMessage('User ID must be 1-8 characters')
    .matches(/^[a-zA-Z0-9]+$/).withMessage('User ID must be alphanumeric'),
  
  body('firstName')
    .trim()
    .notEmpty().withMessage('First name is required')
    .isLength({ max: 20 }).withMessage('First name must not exceed 20 characters'),
  
  body('lastName')
    .trim()
    .notEmpty().withMessage('Last name is required')
    .isLength({ max: 20 }).withMessage('Last name must not exceed 20 characters'),
  
  body('password')
    .trim()
    .notEmpty().withMessage('Password is required')
    .isLength({ min: 1, max: 8 }).withMessage('Password must be 1-8 characters'),
  
  body('userType')
    .trim()
    .notEmpty().withMessage('User type is required')
    .isIn(['A', 'U']).withMessage('User type must be A (Admin) or U (User)')
];

module.exports = { loginValidator, registerValidator };
