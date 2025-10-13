const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const TransactionType = sequelize.define('TransactionType', {
  tranType: {
    type: DataTypes.STRING(2),
    primaryKey: true,
    allowNull: false,
    field: 'tran_type'
  },
  tranTypeDesc: {
    type: DataTypes.STRING(50),
    allowNull: false,
    field: 'tran_type_desc'
  }
}, {
  tableName: 'transaction_types',
  timestamps: true
});

module.exports = TransactionType;
