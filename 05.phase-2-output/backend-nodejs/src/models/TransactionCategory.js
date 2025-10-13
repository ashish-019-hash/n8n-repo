const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const TransactionCategory = sequelize.define('TransactionCategory', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  tranTypeCd: {
    type: DataTypes.STRING(2),
    allowNull: false,
    field: 'tran_type_cd'
  },
  tranCatCd: {
    type: DataTypes.INTEGER,
    allowNull: false,
    field: 'tran_cat_cd'
  },
  tranCatTypeDesc: {
    type: DataTypes.STRING(50),
    allowNull: false,
    field: 'tran_cat_type_desc'
  }
}, {
  tableName: 'transaction_categories',
  timestamps: true
});

module.exports = TransactionCategory;
