const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Account = sequelize.define('Account', {
  acctId: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    allowNull: false,
    field: 'acct_id'
  },
  acctActiveStatus: {
    type: DataTypes.STRING(1),
    allowNull: false,
    validate: {
      isIn: [['A', 'C']]
    },
    field: 'acct_active_status'
  },
  acctCurrBal: {
    type: DataTypes.DECIMAL(12, 2),
    allowNull: false,
    defaultValue: 0,
    field: 'acct_curr_bal'
  },
  acctCreditLimit: {
    type: DataTypes.DECIMAL(12, 2),
    allowNull: false,
    field: 'acct_credit_limit'
  },
  acctCashCreditLimit: {
    type: DataTypes.DECIMAL(12, 2),
    field: 'acct_cash_credit_limit'
  },
  acctOpenDate: {
    type: DataTypes.STRING(10),
    field: 'acct_open_date'
  },
  acctExpiraionDate: {
    type: DataTypes.STRING(10),
    field: 'acct_expiraion_date'
  },
  acctReissueDate: {
    type: DataTypes.STRING(10),
    field: 'acct_reissue_date'
  },
  acctCurrCycCredit: {
    type: DataTypes.DECIMAL(12, 2),
    field: 'acct_curr_cyc_credit'
  },
  acctCurrCycDebit: {
    type: DataTypes.DECIMAL(12, 2),
    field: 'acct_curr_cyc_debit'
  },
  acctAddrZip: {
    type: DataTypes.STRING(10),
    field: 'acct_addr_zip'
  },
  acctGroupId: {
    type: DataTypes.STRING(10),
    field: 'acct_group_id'
  }
}, {
  tableName: 'accounts',
  timestamps: true
});

module.exports = Account;
