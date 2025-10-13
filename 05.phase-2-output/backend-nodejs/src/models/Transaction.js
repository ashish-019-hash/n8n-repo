const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Transaction = sequelize.define('Transaction', {
  tranId: {
    type: DataTypes.STRING(16),
    primaryKey: true,
    allowNull: false,
    field: 'tran_id'
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
  tranSource: {
    type: DataTypes.STRING(10),
    allowNull: false,
    field: 'tran_source'
  },
  tranDesc: {
    type: DataTypes.STRING(100),
    allowNull: false,
    field: 'tran_desc'
  },
  tranAmt: {
    type: DataTypes.DECIMAL(11, 2),
    allowNull: false,
    field: 'tran_amt'
  },
  tranMerchantId: {
    type: DataTypes.BIGINT,
    field: 'tran_merchant_id'
  },
  tranMerchantName: {
    type: DataTypes.STRING(50),
    field: 'tran_merchant_name'
  },
  tranMerchantCity: {
    type: DataTypes.STRING(50),
    field: 'tran_merchant_city'
  },
  tranMerchantZip: {
    type: DataTypes.STRING(10),
    field: 'tran_merchant_zip'
  },
  tranCardNum: {
    type: DataTypes.STRING(16),
    allowNull: false,
    field: 'tran_card_num'
  },
  tranOrigTs: {
    type: DataTypes.STRING(26),
    field: 'tran_orig_ts'
  },
  tranProcTs: {
    type: DataTypes.STRING(26),
    field: 'tran_proc_ts'
  }
}, {
  tableName: 'transactions',
  timestamps: true
});

module.exports = Transaction;
