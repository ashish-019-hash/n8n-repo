const Customer = require('./Customer');
const Account = require('./Account');
const Card = require('./Card');
const Transaction = require('./Transaction');
const User = require('./User');
const CardXref = require('./CardXref');
const TransactionType = require('./TransactionType');
const TransactionCategory = require('./TransactionCategory');
const TransactionCategoryBalance = require('./TransactionCategoryBalance');
const DisclosureGroup = require('./DisclosureGroup');
const DailyTransaction = require('./DailyTransaction');

Account.hasMany(Card, { foreignKey: 'cardAcctId', sourceKey: 'acctId' });
Card.belongsTo(Account, { foreignKey: 'cardAcctId', targetKey: 'acctId' });

Card.hasMany(Transaction, { foreignKey: 'tranCardNum', sourceKey: 'cardNum' });
Transaction.belongsTo(Card, { foreignKey: 'tranCardNum', targetKey: 'cardNum' });

Customer.hasMany(CardXref, { foreignKey: 'xrefCustId', sourceKey: 'custId' });
CardXref.belongsTo(Customer, { foreignKey: 'xrefCustId', targetKey: 'custId' });

Account.hasMany(CardXref, { foreignKey: 'xrefAcctId', sourceKey: 'acctId' });
CardXref.belongsTo(Account, { foreignKey: 'xrefAcctId', targetKey: 'acctId' });

Card.hasMany(CardXref, { foreignKey: 'xrefCardNum', sourceKey: 'cardNum' });
CardXref.belongsTo(Card, { foreignKey: 'xrefCardNum', targetKey: 'cardNum' });

Transaction.belongsTo(TransactionType, { foreignKey: 'tranTypeCd', targetKey: 'tranType' });
TransactionType.hasMany(Transaction, { foreignKey: 'tranTypeCd', sourceKey: 'tranType' });

Account.hasMany(TransactionCategoryBalance, { foreignKey: 'trancatAcctId', sourceKey: 'acctId' });
TransactionCategoryBalance.belongsTo(Account, { foreignKey: 'trancatAcctId', targetKey: 'acctId' });

module.exports = {
  Customer,
  Account,
  Card,
  Transaction,
  User,
  CardXref,
  TransactionType,
  TransactionCategory,
  TransactionCategoryBalance,
  DisclosureGroup,
  DailyTransaction
};
