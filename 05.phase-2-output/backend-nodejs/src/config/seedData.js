const { User, TransactionType, TransactionCategory } = require('../models');
const logger = require('../utils/logger');

const seedDatabase = async () => {
  try {
    const userCount = await User.count();
    if (userCount === 0) {
      await User.create({
        secUsrId: 'ADMIN',
        secUsrFname: 'Admin',
        secUsrLname: 'User',
        secUsrPwd: 'admin123',
        secUsrType: 'A'
      });
      
      await User.create({
        secUsrId: 'USER01',
        secUsrFname: 'Test',
        secUsrLname: 'User',
        secUsrPwd: 'user123',
        secUsrType: 'U'
      });
      
      logger.info('Default users created');
    }

    const tranTypeCount = await TransactionType.count();
    if (tranTypeCount === 0) {
      const transactionTypes = [
        { tranType: 'PU', tranTypeDesc: 'Purchase' },
        { tranType: 'CA', tranTypeDesc: 'Cash Advance' },
        { tranType: 'FE', tranTypeDesc: 'Fee' },
        { tranType: 'RE', tranTypeDesc: 'Refund' },
        { tranType: 'PA', tranTypeDesc: 'Payment' }
      ];
      
      await TransactionType.bulkCreate(transactionTypes);
      logger.info('Transaction types created');
    }

    const tranCatCount = await TransactionCategory.count();
    if (tranCatCount === 0) {
      const categories = [
        { tranTypeCd: 'PU', tranCatCd: 1, tranCatTypeDesc: 'Groceries' },
        { tranTypeCd: 'PU', tranCatCd: 2, tranCatTypeDesc: 'Gas' },
        { tranTypeCd: 'PU', tranCatCd: 3, tranCatTypeDesc: 'Dining' },
        { tranTypeCd: 'PU', tranCatCd: 4, tranCatTypeDesc: 'Entertainment' },
        { tranTypeCd: 'PU', tranCatCd: 5, tranCatTypeDesc: 'Shopping' },
        { tranTypeCd: 'CA', tranCatCd: 1, tranCatTypeDesc: 'ATM Withdrawal' },
        { tranTypeCd: 'FE', tranCatCd: 1, tranCatTypeDesc: 'Late Fee' },
        { tranTypeCd: 'FE', tranCatCd: 2, tranCatTypeDesc: 'Annual Fee' },
        { tranTypeCd: 'RE', tranCatCd: 1, tranCatTypeDesc: 'Purchase Refund' },
        { tranTypeCd: 'PA', tranCatCd: 1, tranCatTypeDesc: 'Bill Payment' }
      ];
      
      await TransactionCategory.bulkCreate(categories);
      logger.info('Transaction categories created');
    }

    logger.info('Database seeding completed');
  } catch (error) {
    logger.error('Error seeding database:', error);
    throw error;
  }
};

module.exports = { seedDatabase };
