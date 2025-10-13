const express = require('express');
const router = express.Router();

const authRoutes = require('./authRoutes');
const accountRoutes = require('./accountRoutes');
const cardRoutes = require('./cardRoutes');
const transactionRoutes = require('./transactionRoutes');
const customerRoutes = require('./customerRoutes');

router.use('/auth', authRoutes);
router.use('/accounts', accountRoutes);
router.use('/cards', cardRoutes);
router.use('/transactions', transactionRoutes);
router.use('/customers', customerRoutes);

router.get('/health', (req, res) => {
  res.json({
    success: true,
    message: 'CardDemo API is running',
    timestamp: new Date().toISOString()
  });
});

module.exports = router;
