const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const DisclosureGroup = sequelize.define('DisclosureGroup', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  disAcctGroupId: {
    type: DataTypes.STRING(10),
    allowNull: false,
    field: 'dis_acct_group_id'
  },
  disTranTypeCd: {
    type: DataTypes.STRING(2),
    allowNull: false,
    field: 'dis_tran_type_cd'
  },
  disTranCatCd: {
    type: DataTypes.INTEGER,
    allowNull: false,
    field: 'dis_tran_cat_cd'
  },
  disIntRate: {
    type: DataTypes.DECIMAL(6, 2),
    allowNull: false,
    field: 'dis_int_rate'
  }
}, {
  tableName: 'disclosure_groups',
  timestamps: true
});

module.exports = DisclosureGroup;
