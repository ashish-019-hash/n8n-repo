const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const bcrypt = require('bcrypt');

const User = sequelize.define('User', {
  secUsrId: {
    type: DataTypes.STRING(8),
    primaryKey: true,
    allowNull: false,
    field: 'sec_usr_id'
  },
  secUsrFname: {
    type: DataTypes.STRING(20),
    allowNull: false,
    field: 'sec_usr_fname'
  },
  secUsrLname: {
    type: DataTypes.STRING(20),
    allowNull: false,
    field: 'sec_usr_lname'
  },
  secUsrPwd: {
    type: DataTypes.STRING(100),
    allowNull: false,
    field: 'sec_usr_pwd'
  },
  secUsrType: {
    type: DataTypes.STRING(1),
    allowNull: false,
    validate: {
      isIn: [['A', 'U']]
    },
    field: 'sec_usr_type'
  }
}, {
  tableName: 'users',
  timestamps: true,
  hooks: {
    beforeCreate: async (user) => {
      if (user.secUsrPwd) {
        const salt = await bcrypt.genSalt(10);
        user.secUsrPwd = await bcrypt.hash(user.secUsrPwd, salt);
      }
    },
    beforeUpdate: async (user) => {
      if (user.changed('secUsrPwd')) {
        const salt = await bcrypt.genSalt(10);
        user.secUsrPwd = await bcrypt.hash(user.secUsrPwd, salt);
      }
    }
  }
});

User.prototype.comparePassword = async function(password) {
  return await bcrypt.compare(password, this.secUsrPwd);
};

module.exports = User;
