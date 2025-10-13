const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Customer = sequelize.define('Customer', {
  custId: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    allowNull: false,
    field: 'cust_id'
  },
  custFirstName: {
    type: DataTypes.STRING(25),
    allowNull: false,
    field: 'cust_first_name'
  },
  custMiddleName: {
    type: DataTypes.STRING(25),
    field: 'cust_middle_name'
  },
  custLastName: {
    type: DataTypes.STRING(25),
    allowNull: false,
    field: 'cust_last_name'
  },
  custAddrLine1: {
    type: DataTypes.STRING(50),
    field: 'cust_addr_line_1'
  },
  custAddrLine2: {
    type: DataTypes.STRING(50),
    field: 'cust_addr_line_2'
  },
  custAddrLine3: {
    type: DataTypes.STRING(50),
    field: 'cust_addr_line_3'
  },
  custAddrStateCd: {
    type: DataTypes.STRING(2),
    field: 'cust_addr_state_cd'
  },
  custAddrCountryCd: {
    type: DataTypes.STRING(3),
    field: 'cust_addr_country_cd'
  },
  custAddrZip: {
    type: DataTypes.STRING(10),
    field: 'cust_addr_zip'
  },
  custPhoneNum1: {
    type: DataTypes.STRING(15),
    field: 'cust_phone_num_1'
  },
  custPhoneNum2: {
    type: DataTypes.STRING(15),
    field: 'cust_phone_num_2'
  },
  custSsn: {
    type: DataTypes.BIGINT,
    allowNull: false,
    field: 'cust_ssn'
  },
  custGovtIssuedId: {
    type: DataTypes.STRING(20),
    field: 'cust_govt_issued_id'
  },
  custDobYyyyMmDd: {
    type: DataTypes.STRING(10),
    field: 'cust_dob_yyyy_mm_dd'
  },
  custEftAccountId: {
    type: DataTypes.STRING(10),
    field: 'cust_eft_account_id'
  },
  custPriCardHolderInd: {
    type: DataTypes.STRING(1),
    field: 'cust_pri_card_holder_ind'
  },
  custFicoCreditScore: {
    type: DataTypes.INTEGER,
    validate: {
      min: 300,
      max: 850
    },
    field: 'cust_fico_credit_score'
  }
}, {
  tableName: 'customers',
  timestamps: true
});

module.exports = Customer;
