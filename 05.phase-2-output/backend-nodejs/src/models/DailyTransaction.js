const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const DailyTransaction = sequelize.define('DailyTransaction', {
  dalytranId: {
    type: DataTypes.STRING(16),
    primaryKey: true,
    allowNull: false,
    field: 'dalytran_id'
  },
  dalytranTypeCd: {
    type: DataTypes.STRING(2),
    allowNull: false,
    field: 'dalytran_type_cd'
  },
  dalytranCatCd: {
    type: DataTypes.INTEGER,
    allowNull: false,
    field: 'dalytran_cat_cd'
  },
  dalytranSource: {
    type: DataTypes.STRING(10),
    allowNull: false,
    field: 'dalytran_source'
  },
  dalytranDesc: {
    type: DataTypes.STRING(100),
    allowNull: false,
    field: 'dalytran_desc'
  },
  dalytranAmt: {
    type: DataTypes.DECIMAL(11, 2),
    allowNull: false,
    field: 'dalytran_amt'
  },
  dalytranMerchantId: {
    type: DataTypes.BIGINT,
    field: 'dalytran_merchant_id'
  },
  dalytranMerchantName: {
    type: DataTypes.STRING(50),
    field: 'dalytran_merchant_name'
  },
  dalytranMerchantCity: {
    type: DataTypes.STRING(50),
    field: 'dalytran_merchant_city'
  },
  dalytranMerchantZip: {
    type: DataTypes.STRING(10),
    field: 'dalytran_merchant_zip'
  },
  dalytranCardNum: {
    type: DataTypes.STRING(16),
    allowNull: false,
    field: 'dalytran_card_num'
  },
  dalytranOrigTs: {
    type: DataTypes.STRING(26),
    field: 'dalytran_orig_ts'
  },
  dalytranProcTs: {
    type: DataTypes.STRING(26),
    field: 'dalytran_proc_ts'
  }
}, {
  tableName: 'daily_transactions',
  timestamps: true
});

module.exports = DailyTransaction;
