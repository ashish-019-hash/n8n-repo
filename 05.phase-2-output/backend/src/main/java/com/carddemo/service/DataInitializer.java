package com.carddemo.service;

import com.carddemo.entity.*;
import com.carddemo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    
    @Autowired
    private TransactionCategoryRepository transactionCategoryRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CardCrossReferenceRepository cardCrossReferenceRepository;
    
    @Autowired
    private TransactionCategoryBalanceRepository transactionCategoryBalanceRepository;
    
    @Autowired
    private DisclosureGroupRepository disclosureGroupRepository;
    
    @Autowired
    private DailyTransactionRepository dailyTransactionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() == 0) {
            logger.info("Initializing sample data for CardDemo database...");
            initializeSampleData();
            logger.info("Sample data initialization completed successfully!");
        } else {
            logger.info("Sample data already exists, skipping initialization");
        }
    }

    private void initializeSampleData() {
        initializeTransactionTypes();
        
        initializeTransactionCategories();
        
        List<Customer> customers = initializeCustomers();
        
        List<Account> accounts = initializeAccounts();
        
        List<Card> cards = initializeCards(accounts);
        
        initializeCardCrossReferences(customers, cards);
        
        initializeUsers();
        
        initializeTransactions(cards);
        
        initializeTransactionCategoryBalances(accounts);
        
        initializeDisclosureGroups();
        
        initializeDailyTransactions();
    }

    private void initializeTransactionTypes() {
        List<TransactionType> transactionTypes = Arrays.asList(
            createTransactionType("01", "Purchase"),
            createTransactionType("02", "Cash Advance"),
            createTransactionType("03", "Payment"),
            createTransactionType("04", "Fee"),
            createTransactionType("05", "Interest")
        );
        transactionTypeRepository.saveAll(transactionTypes);
        logger.debug("Initialized {} transaction types", transactionTypes.size());
    }

    private TransactionType createTransactionType(String code, String description) {
        TransactionType type = new TransactionType();
        type.setTranType(code);
        type.setTranTypeDesc(description);
        return type;
    }

    private void initializeTransactionCategories() {
        List<TransactionCategory> categories = Arrays.asList(
            createTransactionCategory("01", 1, "Retail Purchase"),
            createTransactionCategory("01", 2, "Online Purchase"),
            createTransactionCategory("01", 3, "Gas Station"),
            createTransactionCategory("02", 1, "ATM Cash Advance"),
            createTransactionCategory("03", 1, "Payment Received"),
            createTransactionCategory("04", 1, "Annual Fee"),
            createTransactionCategory("05", 1, "Interest Charge")
        );
        transactionCategoryRepository.saveAll(categories);
        logger.debug("Initialized {} transaction categories", categories.size());
    }

    private TransactionCategory createTransactionCategory(String typeCode, Integer catCode, String description) {
        TransactionCategory category = new TransactionCategory();
        category.setTranTypeCd(typeCode);
        category.setTranCatCd(catCode);
        category.setTranCatTypeDesc(description);
        return category;
    }

    private List<Customer> initializeCustomers() {
        List<Customer> customers = Arrays.asList(
            createCustomer(1000000001L, "John", "M", "Doe", "123 Main St", "Apt 1", "New York", "NY", "10001", "USA", "555-0101", "555-0102", "john.doe@email.com", "1985-03-15"),
            createCustomer(1000000002L, "Jane", "A", "Smith", "456 Oak Ave", "", "Los Angeles", "CA", "90210", "USA", "555-0201", "555-0202", "jane.smith@email.com", "1990-07-22"),
            createCustomer(1000000003L, "Robert", "J", "Johnson", "789 Pine Rd", "Suite 5", "Chicago", "IL", "60601", "USA", "555-0301", "555-0302", "robert.johnson@email.com", "1978-11-08"),
            createCustomer(1000000004L, "Emily", "R", "Davis", "321 Elm St", "", "Houston", "TX", "77001", "USA", "555-0401", "555-0402", "emily.davis@email.com", "1992-04-30"),
            createCustomer(1000000005L, "Michael", "T", "Wilson", "654 Maple Dr", "Unit 12", "Phoenix", "AZ", "85001", "USA", "555-0501", "555-0502", "michael.wilson@email.com", "1983-09-12")
        );
        customerRepository.saveAll(customers);
        logger.debug("Initialized {} customers", customers.size());
        return customers;
    }

    private Customer createCustomer(Long id, String firstName, String middleName, String lastName, 
                                  String addr1, String addr2, String city, String state, String zip, 
                                  String country, String phone1, String phone2, String email, String dob) {
        Customer customer = new Customer();
        customer.setCustId(id);
        customer.setCustFirstName(firstName);
        customer.setCustMiddleName(middleName);
        customer.setCustLastName(lastName);
        customer.setCustAddrLine1(addr1);
        customer.setCustAddrLine2(addr2);
        customer.setCustAddrLine3("");
        customer.setCustAddrStateCd(state);
        customer.setCustAddrCountryCd(country);
        customer.setCustAddrZip(zip);
        customer.setCustPhoneNum1(phone1);
        customer.setCustPhoneNum2(phone2);
        customer.setCustSsn(generateSSNLong(id));
        customer.setCustGovtIssuedId(generateGovtId(id));
        customer.setCustDobYyyyMmDd(dob);
        customer.setCustEftAccountId(generateEftAccount(id));
        customer.setCustPriCardHolderInd("1");
        customer.setCustFicoCreditScore(generateFicoScore(id));
        return customer;
    }

    private Long generateSSNLong(Long id) {
        return id % 1000000000L;
    }

    private String generateGovtId(Long id) {
        return String.format("DL%08d", id % 100000000L);
    }

    private String generateEftAccount(Long id) {
        return String.format("EFT%07d", id % 10000000L);
    }

    private Integer generateFicoScore(Long id) {
        return 650 + (int)(id % 200);
    }

    private Integer generateCvv(String cardNum) {
        return 100 + (int)(Long.parseLong(cardNum.substring(12)) % 900);
    }

    private List<Account> initializeAccounts() {
        List<Account> accounts = Arrays.asList(
            createAccount(1L, "A", "10001", new BigDecimal("5000.00"), new BigDecimal("1500.75"), new BigDecimal("3499.25"), new BigDecimal("250.00"), new BigDecimal("125.50"), "2023-01-15", "2028-01-15", "2024-01-15"),
            createAccount(2L, "A", "10002", new BigDecimal("3000.00"), new BigDecimal("875.25"), new BigDecimal("2124.75"), new BigDecimal("150.00"), new BigDecimal("75.25"), "2023-03-20", "2028-03-20", "2024-03-20"),
            createAccount(3L, "A", "10003", new BigDecimal("7500.00"), new BigDecimal("2250.50"), new BigDecimal("5249.50"), new BigDecimal("375.00"), new BigDecimal("187.75"), "2023-05-10", "2028-05-10", "2024-05-10"),
            createAccount(4L, "A", "10004", new BigDecimal("2500.00"), new BigDecimal("650.00"), new BigDecimal("1850.00"), new BigDecimal("125.00"), new BigDecimal("62.50"), "2023-07-25", "2028-07-25", "2024-07-25"),
            createAccount(5L, "A", "10005", new BigDecimal("4000.00"), new BigDecimal("1200.75"), new BigDecimal("2799.25"), new BigDecimal("200.00"), new BigDecimal("100.00"), "2023-09-05", "2028-09-05", "2024-09-05")
        );
        accountRepository.saveAll(accounts);
        logger.debug("Initialized {} accounts", accounts.size());
        return accounts;
    }

    private Account createAccount(Long id, String status, String zip, BigDecimal creditLimit, 
                                BigDecimal currBal, BigDecimal currCycCredit, BigDecimal cashCreditLimit, 
                                BigDecimal currCycDebit, String openDate, String expDate, String reissueDate) {
        Account account = new Account();
        account.setAcctId(id);
        account.setAcctActiveStatus(status);
        account.setAcctAddrZip(zip);
        account.setAcctCreditLimit(creditLimit);
        account.setAcctCurrBal(currBal);
        account.setAcctCurrCycCredit(currCycCredit);
        account.setAcctCashCreditLimit(cashCreditLimit);
        account.setAcctCurrCycDebit(currCycDebit);
        account.setAcctOpenDate(openDate);
        account.setAcctExpiraionDate(expDate);
        account.setAcctReissueDate(reissueDate);
        account.setAcctGroupId("GRP001");
        return account;
    }

    private List<Card> initializeCards(List<Account> accounts) {
        List<Card> cards = Arrays.asList(
            createCard("4111111111111111", 1L, "A", "2028-12", "John Doe"),
            createCard("4222222222222222", 2L, "A", "2028-12", "Jane Smith"),
            createCard("4333333333333333", 3L, "A", "2028-12", "Robert Johnson"),
            createCard("4444444444444444", 4L, "A", "2028-12", "Emily Davis"),
            createCard("4555555555555555", 5L, "A", "2028-12", "Michael Wilson")
        );
        cardRepository.saveAll(cards);
        logger.debug("Initialized {} cards", cards.size());
        return cards;
    }

    private Card createCard(String cardNum, Long acctId, String status, String expDate, String embossedName) {
        Card card = new Card();
        card.setCardNum(cardNum);
        card.setCardAcctId(acctId);
        card.setCardActiveStatus(status);
        card.setCardExpiraionDate(expDate);
        card.setCardEmbossedName(embossedName);
        card.setCardCvvCd(generateCvv(cardNum));
        return card;
    }

    private void initializeCardCrossReferences(List<Customer> customers, List<Card> cards) {
        List<CardCrossReference> crossRefs = Arrays.asList(
            createCardCrossReference(1000000001L, "4111111111111111"),
            createCardCrossReference(1000000002L, "4222222222222222"),
            createCardCrossReference(1000000003L, "4333333333333333"),
            createCardCrossReference(1000000004L, "4444444444444444"),
            createCardCrossReference(1000000005L, "4555555555555555")
        );
        cardCrossReferenceRepository.saveAll(crossRefs);
        logger.debug("Initialized {} card cross references", crossRefs.size());
    }

    private CardCrossReference createCardCrossReference(Long custId, String cardNum) {
        CardCrossReference crossRef = new CardCrossReference();
        crossRef.setXrefCustId(custId);
        crossRef.setXrefCardNum(cardNum);
        crossRef.setXrefAcctId(custId - 1000000000L);
        return crossRef;
    }

    private void initializeUsers() {
        List<User> users = Arrays.asList(
            createUser("ADMIN001", "admin123", "Admin", "User", "A"),
            createUser("USER0001", "user123", "John", "Doe", "U"),
            createUser("USER0002", "user456", "Jane", "Smith", "U"),
            createUser("MANAGER1", "mgr123", "Bob", "Manager", "M")
        );
        userRepository.saveAll(users);
        logger.debug("Initialized {} users", users.size());
    }

    private User createUser(String userId, String password, String firstName, String lastName, String userType) {
        User user = new User();
        user.setSecUsrId(userId);
        user.setSecUsrPwd(password);
        user.setSecUsrFname(firstName);
        user.setSecUsrLname(lastName);
        user.setSecUsrType(userType);
        return user;
    }

    private void initializeTransactions(List<Card> cards) {
        List<Transaction> transactions = Arrays.asList(
            createTransaction("TXN000000001", "4111111111111111", "01", 1, new BigDecimal("125.50"), "Amazon", "Seattle", "WA", "98101", 1001L, "2024-09-20 14:30:00", "2024-09-20 14:31:15", "Online Purchase"),
            createTransaction("TXN000000002", "4222222222222222", "01", 3, new BigDecimal("45.75"), "Shell Gas", "Los Angeles", "CA", "90210", 2001L, "2024-09-21 08:15:00", "2024-09-21 08:16:30", "Fuel Purchase"),
            createTransaction("TXN000000003", "4333333333333333", "01", 1, new BigDecimal("89.99"), "Target", "Chicago", "IL", "60601", 3001L, "2024-09-22 16:45:00", "2024-09-22 16:46:45", "Retail Purchase"),
            createTransaction("TXN000000004", "4444444444444444", "02", 1, new BigDecimal("200.00"), "ATM Cash", "Houston", "TX", "77001", 4001L, "2024-09-23 10:20:00", "2024-09-23 10:21:00", "Cash Advance"),
            createTransaction("TXN000000005", "4555555555555555", "03", 1, new BigDecimal("-500.00"), "Payment", "Phoenix", "AZ", "85001", 5001L, "2024-09-24 12:00:00", "2024-09-24 12:01:00", "Payment Received")
        );
        transactionRepository.saveAll(transactions);
        logger.debug("Initialized {} transactions", transactions.size());
    }

    private Transaction createTransaction(String tranId, String cardNum, String typeCode, Integer catCode, 
                                       BigDecimal amount, String merchantName, String merchantCity, 
                                       String merchantState, String merchantZip, Long merchantId, 
                                       String origTs, String procTs, String desc) {
        Transaction transaction = new Transaction();
        transaction.setTranId(tranId);
        transaction.setTranCardNum(cardNum);
        transaction.setTranTypeCd(typeCode);
        transaction.setTranCatCd(catCode);
        transaction.setTranAmt(amount);
        transaction.setTranMerchantName(merchantName);
        transaction.setTranMerchantCity(merchantCity);
        transaction.setTranMerchantZip(merchantZip);
        transaction.setTranMerchantId(merchantId);
        transaction.setTranOrigTs(origTs);
        transaction.setTranProcTs(procTs);
        transaction.setTranDesc(desc);
        transaction.setTranSource("POS");
        return transaction;
    }

    private void initializeTransactionCategoryBalances(List<Account> accounts) {
        List<TransactionCategoryBalance> balances = Arrays.asList(
            createTransactionCategoryBalance(1L, "01", 1, new BigDecimal("1250.75")),
            createTransactionCategoryBalance(2L, "01", 3, new BigDecimal("345.50")),
            createTransactionCategoryBalance(3L, "01", 1, new BigDecimal("2100.25")),
            createTransactionCategoryBalance(4L, "02", 1, new BigDecimal("200.00")),
            createTransactionCategoryBalance(5L, "03", 1, new BigDecimal("-500.00"))
        );
        transactionCategoryBalanceRepository.saveAll(balances);
        logger.debug("Initialized {} transaction category balances", balances.size());
    }

    private TransactionCategoryBalance createTransactionCategoryBalance(Long acctId, String typeCode, Integer catCode, BigDecimal balance) {
        TransactionCategoryBalance tcBalance = new TransactionCategoryBalance();
        tcBalance.setTrancatAcctId(acctId);
        tcBalance.setTrancatTypeCd(typeCode);
        tcBalance.setTrancatCd(catCode);
        tcBalance.setTranCatBal(balance);
        return tcBalance;
    }

    private void initializeDisclosureGroups() {
        List<DisclosureGroup> disclosureGroups = Arrays.asList(
            createDisclosureGroup("GRP001", "01", 1, new BigDecimal("18.99")),
            createDisclosureGroup("GRP001", "02", 1, new BigDecimal("24.99")),
            createDisclosureGroup("GRP001", "04", 1, new BigDecimal("39.00"))
        );
        disclosureGroupRepository.saveAll(disclosureGroups);
        logger.debug("Initialized {} disclosure groups", disclosureGroups.size());
    }

    private DisclosureGroup createDisclosureGroup(String groupId, String typeCode, Integer catCode, BigDecimal intRate) {
        DisclosureGroup disclosureGroup = new DisclosureGroup();
        disclosureGroup.setDisAcctGroupId(groupId);
        disclosureGroup.setDisTranTypeCd(typeCode);
        disclosureGroup.setDisTranCatCd(catCode);
        disclosureGroup.setDisIntRate(intRate);
        return disclosureGroup;
    }

    private void initializeDailyTransactions() {
        List<DailyTransaction> dailyTransactions = Arrays.asList(
            createDailyTransaction("DT0000001", "01", 1, new BigDecimal("125.50"), "4111111111111111", "TXN000000001", "Amazon", "Seattle", "WA", "98101", 1001L, "2024-09-20 14:30:00", "2024-09-20 14:31:15"),
            createDailyTransaction("DT0000002", "01", 3, new BigDecimal("45.75"), "4222222222222222", "TXN000000002", "Shell Gas", "Los Angeles", "CA", "90210", 2001L, "2024-09-21 08:15:00", "2024-09-21 08:16:30"),
            createDailyTransaction("DT0000003", "01", 1, new BigDecimal("89.99"), "4333333333333333", "TXN000000003", "Target", "Chicago", "IL", "60601", 3001L, "2024-09-22 16:45:00", "2024-09-22 16:46:45")
        );
        dailyTransactionRepository.saveAll(dailyTransactions);
        logger.debug("Initialized {} daily transactions", dailyTransactions.size());
    }

    private DailyTransaction createDailyTransaction(String dalytranId, String typeCode, Integer catCode, 
                                                  BigDecimal amount, String cardNum, String tranId, 
                                                  String merchantName, String merchantCity, String merchantState, 
                                                  String merchantZip, Long merchantId, String origTs, String procTs) {
        DailyTransaction dailyTransaction = new DailyTransaction();
        dailyTransaction.setDalytranId(dalytranId);
        dailyTransaction.setDalytranTypeCd(typeCode);
        dailyTransaction.setDalytranCatCd(catCode);
        dailyTransaction.setDalytranAmt(amount);
        dailyTransaction.setDalytranCardNum(cardNum);
        dailyTransaction.setDalytranId(tranId);
        dailyTransaction.setDalytranMerchantName(merchantName);
        dailyTransaction.setDalytranMerchantCity(merchantCity);
        dailyTransaction.setDalytranMerchantZip(merchantZip);
        dailyTransaction.setDalytranMerchantId(merchantId);
        dailyTransaction.setDalytranOrigTs(origTs);
        dailyTransaction.setDalytranProcTs(procTs);
        dailyTransaction.setDalytranSource("POS");
        return dailyTransaction;
    }
}
