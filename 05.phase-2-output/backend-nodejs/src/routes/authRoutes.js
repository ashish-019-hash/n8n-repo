const express = require('express');
const router = express.Router();
const authController = require('../controllers/authController');
const { loginValidator, registerValidator } = require('../validators/authValidator');
const validate = require('../middlewares/validator');
const { authenticate } = require('../middlewares/auth');

router.post('/login', loginValidator, validate, authController.login);
router.post('/register', registerValidator, validate, authController.register);
router.get('/me', authenticate, authController.getCurrentUser);

module.exports = router;
