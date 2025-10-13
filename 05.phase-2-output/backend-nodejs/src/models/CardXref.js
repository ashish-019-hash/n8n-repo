const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const CardXref = sequelize.define('CardXref', {
  xrefCardNum: {
    type: DataTypes.STRING(16),
    primaryKey: true,
    allowNull: false,
    field: 'xref_card_num'
  },
  xrefCustId: {
    type: DataTypes.BIGINT,
    allowNull: false,
    field: 'xref_cust_id'
  },
  xrefAcctId: {
    type: DataTypes.BIGINT,
    allowNull: false,
    field: 'xref_acct_id'
  }
}, {
  tableName: 'card_xrefs',
  timestamps: true
});

module.exports = CardXref;
