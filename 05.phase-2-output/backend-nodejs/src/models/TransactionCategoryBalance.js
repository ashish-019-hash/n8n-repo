const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const TransactionCategoryBalance = sequelize.define('TransactionCategoryBalance', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  trancatAcctId: {
    type: DataTypes.BIGINT,
    allowNull: false,
    field: 'trancat_acct_id'
  },
  trancatTypeCd: {
    type: DataTypes.STRING(2),
    allowNull: false,
    field: 'trancat_type_cd'
  },
  trancatCd: {
    type: DataTypes.INTEGER,
    allowNull: false,
    field: 'trancat_cd'
  },
  tranCatBal: {
    type: DataTypes.DECIMAL(11, 2),
    allowNull: false,
    defaultValue: 0,
    field: 'tran_cat_bal'
  }
}, {
  tableName: 'transaction_category_balances',
  timestamps: true
});

module.exports = TransactionCategoryBalance;
