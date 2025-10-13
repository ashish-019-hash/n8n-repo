const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Card = sequelize.define('Card', {
  cardNum: {
    type: DataTypes.STRING(16),
    primaryKey: true,
    allowNull: false,
    validate: {
      len: [16, 16],
      isNumeric: true
    },
    field: 'card_num'
  },
  cardAcctId: {
    type: DataTypes.BIGINT,
    allowNull: false,
    field: 'card_acct_id'
  },
  cardCvvCd: {
    type: DataTypes.INTEGER,
    allowNull: false,
    validate: {
      len: [3, 3]
    },
    field: 'card_cvv_cd'
  },
  cardEmbossedName: {
    type: DataTypes.STRING(50),
    allowNull: false,
    field: 'card_embossed_name'
  },
  cardExpiraionDate: {
    type: DataTypes.STRING(10),
    allowNull: false,
    field: 'card_expiraion_date'
  },
  cardActiveStatus: {
    type: DataTypes.STRING(1),
    allowNull: false,
    validate: {
      isIn: [['A', 'C', 'S']]
    },
    field: 'card_active_status'
  }
}, {
  tableName: 'cards',
  timestamps: true
});

module.exports = Card;
