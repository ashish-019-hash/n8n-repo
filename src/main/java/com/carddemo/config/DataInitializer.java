package com.carddemo.config;

import com.carddemo.entity.*;
import com.carddemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataInitializer {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private TransactionCategoryRepository transactionCategoryRepository;
    @Autowired
    private CardCrossReferenceRepository cardCrossReferenceRepository;
    @Autowired
    private TransactionCategoryBalanceRepository transactionCategoryBalanceRepository;
    @Autowired
    private DisclosureGroupRepository disclosureGroupRepository;
    @Autowired
    private DailyTransactionRepository dailyTransactionRepository;

    @PostConstruct
    public void initializeData() {
        if (customerRepository.count() == 0) {
            createSampleData();
        }
    }

    private void createSampleData() {
        createUsers();
        createTransactionTypes();
        createTransactionCategories();
        createCustomers();
        createAccounts();
        createCards();
        createCardCrossReferences();
        createDisclosureGroups();
        createTransactions();
        createDailyTransactions();
        createTransactionCategoryBalances();
    }

    private void createUsers() {
        User admin = new User();
        admin.setSecUsrId("ADMIN001");
        admin.setSecUsrFname("John");
        admin.setSecUsrLname("Administrator");
        admin.setSecUsrPwd("admin123");
        admin.setSecUsrType("A");
        userRepository.save(admin);

        User user1 = new User();
        user1.setSecUsrId("USER0001");
        user1.setSecUsrFname("Jane");
        user1.setSecUsrLname("Smith");
        user1.setSecUsrPwd("user123");
        user1.setSecUsrType("U");
        userRepository.save(user1);

        User user2 = new User();
        user2.setSecUsrId("USER0002");
        user2.setSecUsrFname("Bob");
        user2.setSecUsrLname("Johnson");
        user2.setSecUsrPwd("user456");
        user2.setSecUsrType("U");
        userRepository.save(user2);
    }

    private void createTransactionTypes() {
        TransactionType purchase = new TransactionType();
        purchase.setTranType("01");
        purchase.setTranTypeDesc("Purchase Transaction");
        transactionTypeRepository.save(purchase);

        TransactionType cashAdv = new TransactionType();
        cashAdv.setTranType("02");
        cashAdv.setTranTypeDesc("Cash Advance");
        transactionTypeRepository.save(cashAdv);

        TransactionType payment = new TransactionType();
        payment.setTranType("03");
        payment.setTranTypeDesc("Payment");
        transactionTypeRepository.save(payment);

        TransactionType refund = new TransactionType();
        refund.setTranType("04");
        refund.setTranTypeDesc("Refund");
        transactionTypeRepository.save(refund);

        TransactionType fee = new TransactionType();
        fee.setTranType("05");
        fee.setTranTypeDesc("Fee");
        transactionTypeRepository.save(fee);
    }

    private void createTransactionCategories() {
        TransactionCategory cat1 = new TransactionCategory();
        cat1.setTranTypeCd("01");
        cat1.setTranCatCd(1001);
        cat1.setTranCatTypeDesc("Retail Purchase");
        transactionCategoryRepository.save(cat1);

        TransactionCategory cat2 = new TransactionCategory();
        cat2.setTranTypeCd("01");
        cat2.setTranCatCd(1002);
        cat2.setTranCatTypeDesc("Online Purchase");
        transactionCategoryRepository.save(cat2);

        TransactionCategory cat3 = new TransactionCategory();
        cat3.setTranTypeCd("01");
        cat3.setTranCatCd(1003);
        cat3.setTranCatTypeDesc("Gas Station");
        transactionCategoryRepository.save(cat3);

        TransactionCategory cat4 = new TransactionCategory();
        cat4.setTranTypeCd("01");
        cat4.setTranCatCd(1004);
        cat4.setTranCatTypeDesc("Restaurant");
        transactionCategoryRepository.save(cat4);

        TransactionCategory cat5 = new TransactionCategory();
        cat5.setTranTypeCd("02");
        cat5.setTranCatCd(2001);
        cat5.setTranCatTypeDesc("ATM Cash Advance");
        transactionCategoryRepository.save(cat5);

        TransactionCategory cat6 = new TransactionCategory();
        cat6.setTranTypeCd("03");
        cat6.setTranCatCd(3001);
        cat6.setTranCatTypeDesc("Online Payment");
        transactionCategoryRepository.save(cat6);

        TransactionCategory cat7 = new TransactionCategory();
        cat7.setTranTypeCd("04");
        cat7.setTranCatCd(4001);
        cat7.setTranCatTypeDesc("Purchase Refund");
        transactionCategoryRepository.save(cat7);

        TransactionCategory cat8 = new TransactionCategory();
        cat8.setTranTypeCd("05");
        cat8.setTranCatCd(5001);
        cat8.setTranCatTypeDesc("Late Fee");
        transactionCategoryRepository.save(cat8);
    }

    private void createCustomers() {
        Customer customer1 = new Customer();
        customer1.setCustId(100000001L);
        customer1.setCustFirstName("Alice");
        customer1.setCustMiddleName("Marie");
        customer1.setCustLastName("Johnson");
        customer1.setCustAddrLine1("123 Main Street");
        customer1.setCustAddrLine2("Apt 4B");
        customer1.setCustAddrLine3("");
        customer1.setCustAddrStateCd("NY");
        customer1.setCustAddrCountryCd("USA");
        customer1.setCustAddrZip("10001");
        customer1.setCustPhoneNum1("555-123-4567");
        customer1.setCustPhoneNum2("555-987-6543");
        customer1.setCustSsn(123456789L);
        customer1.setCustGovtIssuedId("DL123456789");
        customer1.setCustDobYyyyMmDd("1985-03-15");
        customer1.setCustEftAccountId("EFT001");
        customer1.setCustPriCardHolderInd("Y");
        customer1.setCustFicoCreditScore(750);
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setCustId(100000002L);
        customer2.setCustFirstName("Robert");
        customer2.setCustMiddleName("James");
        customer2.setCustLastName("Smith");
        customer2.setCustAddrLine1("456 Oak Avenue");
        customer2.setCustAddrLine2("");
        customer2.setCustAddrLine3("");
        customer2.setCustAddrStateCd("CA");
        customer2.setCustAddrCountryCd("USA");
        customer2.setCustAddrZip("90210");
        customer2.setCustPhoneNum1("555-234-5678");
        customer2.setCustPhoneNum2("");
        customer2.setCustSsn(234567890L);
        customer2.setCustGovtIssuedId("DL234567890");
        customer2.setCustDobYyyyMmDd("1978-07-22");
        customer2.setCustEftAccountId("EFT002");
        customer2.setCustPriCardHolderInd("Y");
        customer2.setCustFicoCreditScore(680);
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setCustId(100000003L);
        customer3.setCustFirstName("Emily");
        customer3.setCustMiddleName("Rose");
        customer3.setCustLastName("Davis");
        customer3.setCustAddrLine1("789 Pine Street");
        customer3.setCustAddrLine2("Suite 200");
        customer3.setCustAddrLine3("");
        customer3.setCustAddrStateCd("TX");
        customer3.setCustAddrCountryCd("USA");
        customer3.setCustAddrZip("75201");
        customer3.setCustPhoneNum1("555-345-6789");
        customer3.setCustPhoneNum2("555-876-5432");
        customer3.setCustSsn(345678901L);
        customer3.setCustGovtIssuedId("DL345678901");
        customer3.setCustDobYyyyMmDd("1992-11-08");
        customer3.setCustEftAccountId("EFT003");
        customer3.setCustPriCardHolderInd("Y");
        customer3.setCustFicoCreditScore(820);
        customerRepository.save(customer3);

        Customer customer4 = new Customer();
        customer4.setCustId(100000004L);
        customer4.setCustFirstName("Michael");
        customer4.setCustMiddleName("David");
        customer4.setCustLastName("Wilson");
        customer4.setCustAddrLine1("321 Elm Drive");
        customer4.setCustAddrLine2("");
        customer4.setCustAddrLine3("");
        customer4.setCustAddrStateCd("FL");
        customer4.setCustAddrCountryCd("USA");
        customer4.setCustAddrZip("33101");
        customer4.setCustPhoneNum1("555-456-7890");
        customer4.setCustPhoneNum2("");
        customer4.setCustSsn(456789012L);
        customer4.setCustGovtIssuedId("DL456789012");
        customer4.setCustDobYyyyMmDd("1980-05-30");
        customer4.setCustEftAccountId("EFT004");
        customer4.setCustPriCardHolderInd("Y");
        customer4.setCustFicoCreditScore(720);
        customerRepository.save(customer4);

        Customer customer5 = new Customer();
        customer5.setCustId(100000005L);
        customer5.setCustFirstName("Sarah");
        customer5.setCustMiddleName("Ann");
        customer5.setCustLastName("Brown");
        customer5.setCustAddrLine1("654 Maple Lane");
        customer5.setCustAddrLine2("Unit 12");
        customer5.setCustAddrLine3("");
        customer5.setCustAddrStateCd("WA");
        customer5.setCustAddrCountryCd("USA");
        customer5.setCustAddrZip("98101");
        customer5.setCustPhoneNum1("555-567-8901");
        customer5.setCustPhoneNum2("555-765-4321");
        customer5.setCustSsn(567890123L);
        customer5.setCustGovtIssuedId("DL567890123");
        customer5.setCustDobYyyyMmDd("1988-12-14");
        customer5.setCustEftAccountId("EFT005");
        customer5.setCustPriCardHolderInd("Y");
        customer5.setCustFicoCreditScore(790);
        customerRepository.save(customer5);
    }

    private void createAccounts() {
        Account account1 = new Account();
        account1.setAcctId(10000000001L);
        account1.setAcctActiveStatus("Y");
        account1.setAcctCurrBal(new BigDecimal("2500.75"));
        account1.setAcctCreditLimit(new BigDecimal("5000.00"));
        account1.setAcctCashCreditLimit(new BigDecimal("1000.00"));
        account1.setAcctOpenDate("2020-01-15");
        account1.setAcctExpiraionDate("2025-01-15");
        account1.setAcctReissueDate("2023-01-15");
        account1.setAcctCurrCycCredit(new BigDecimal("0.00"));
        account1.setAcctCurrCycDebit(new BigDecimal("450.25"));
        account1.setAcctAddrZip("10001");
        account1.setAcctGroupId("GROUP001");
        accountRepository.save(account1);

        Account account2 = new Account();
        account2.setAcctId(10000000002L);
        account2.setAcctActiveStatus("Y");
        account2.setAcctCurrBal(new BigDecimal("1200.50"));
        account2.setAcctCreditLimit(new BigDecimal("3000.00"));
        account2.setAcctCashCreditLimit(new BigDecimal("600.00"));
        account2.setAcctOpenDate("2019-06-20");
        account2.setAcctExpiraionDate("2024-06-20");
        account2.setAcctReissueDate("2022-06-20");
        account2.setAcctCurrCycCredit(new BigDecimal("100.00"));
        account2.setAcctCurrCycDebit(new BigDecimal("320.75"));
        account2.setAcctAddrZip("10001");
        account2.setAcctGroupId("GROUP001");
        accountRepository.save(account2);

        Account account3 = new Account();
        account3.setAcctId(10000000003L);
        account3.setAcctActiveStatus("Y");
        account3.setAcctCurrBal(new BigDecimal("850.25"));
        account3.setAcctCreditLimit(new BigDecimal("2500.00"));
        account3.setAcctCashCreditLimit(new BigDecimal("500.00"));
        account3.setAcctOpenDate("2021-03-10");
        account3.setAcctExpiraionDate("2026-03-10");
        account3.setAcctReissueDate("2024-03-10");
        account3.setAcctCurrCycCredit(new BigDecimal("0.00"));
        account3.setAcctCurrCycDebit(new BigDecimal("275.50"));
        account3.setAcctAddrZip("90210");
        account3.setAcctGroupId("GROUP002");
        accountRepository.save(account3);

        Account account4 = new Account();
        account4.setAcctId(10000000004L);
        account4.setAcctActiveStatus("Y");
        account4.setAcctCurrBal(new BigDecimal("3200.00"));
        account4.setAcctCreditLimit(new BigDecimal("7500.00"));
        account4.setAcctCashCreditLimit(new BigDecimal("1500.00"));
        account4.setAcctOpenDate("2018-11-05");
        account4.setAcctExpiraionDate("2023-11-05");
        account4.setAcctReissueDate("2021-11-05");
        account4.setAcctCurrCycCredit(new BigDecimal("200.00"));
        account4.setAcctCurrCycDebit(new BigDecimal("680.90"));
        account4.setAcctAddrZip("75201");
        account4.setAcctGroupId("GROUP003");
        accountRepository.save(account4);

        Account account5 = new Account();
        account5.setAcctId(10000000005L);
        account5.setAcctActiveStatus("Y");
        account5.setAcctCurrBal(new BigDecimal("1850.75"));
        account5.setAcctCreditLimit(new BigDecimal("4000.00"));
        account5.setAcctCashCreditLimit(new BigDecimal("800.00"));
        account5.setAcctOpenDate("2020-08-12");
        account5.setAcctExpiraionDate("2025-08-12");
        account5.setAcctReissueDate("2023-08-12");
        account5.setAcctCurrCycCredit(new BigDecimal("50.00"));
        account5.setAcctCurrCycDebit(new BigDecimal("390.25"));
        account5.setAcctAddrZip("33101");
        account5.setAcctGroupId("GROUP002");
        accountRepository.save(account5);

        Account account6 = new Account();
        account6.setAcctId(10000000006L);
        account6.setAcctActiveStatus("Y");
        account6.setAcctCurrBal(new BigDecimal("950.00"));
        account6.setAcctCreditLimit(new BigDecimal("2000.00"));
        account6.setAcctCashCreditLimit(new BigDecimal("400.00"));
        account6.setAcctOpenDate("2022-02-28");
        account6.setAcctExpiraionDate("2027-02-28");
        account6.setAcctReissueDate("2025-02-28");
        account6.setAcctCurrCycCredit(new BigDecimal("0.00"));
        account6.setAcctCurrCycDebit(new BigDecimal("180.50"));
        account6.setAcctAddrZip("33101");
        account6.setAcctGroupId("GROUP002");
        accountRepository.save(account6);

        Account account7 = new Account();
        account7.setAcctId(10000000007L);
        account7.setAcctActiveStatus("Y");
        account7.setAcctCurrBal(new BigDecimal("2750.25"));
        account7.setAcctCreditLimit(new BigDecimal("6000.00"));
        account7.setAcctCashCreditLimit(new BigDecimal("1200.00"));
        account7.setAcctOpenDate("2019-12-01");
        account7.setAcctExpiraionDate("2024-12-01");
        account7.setAcctReissueDate("2022-12-01");
        account7.setAcctCurrCycCredit(new BigDecimal("150.00"));
        account7.setAcctCurrCycDebit(new BigDecimal("520.75"));
        account7.setAcctAddrZip("98101");
        account7.setAcctGroupId("GROUP003");
        accountRepository.save(account7);

        Account account8 = new Account();
        account8.setAcctId(10000000008L);
        account8.setAcctActiveStatus("N");
        account8.setAcctCurrBal(new BigDecimal("0.00"));
        account8.setAcctCreditLimit(new BigDecimal("1500.00"));
        account8.setAcctCashCreditLimit(new BigDecimal("300.00"));
        account8.setAcctOpenDate("2017-05-15");
        account8.setAcctExpiraionDate("2022-05-15");
        account8.setAcctReissueDate("2020-05-15");
        account8.setAcctCurrCycCredit(new BigDecimal("0.00"));
        account8.setAcctCurrCycDebit(new BigDecimal("0.00"));
        account8.setAcctAddrZip("98101");
        account8.setAcctGroupId("GROUP001");
        accountRepository.save(account8);
    }

    private void createCards() {
        Card card1 = new Card();
        card1.setCardNum("4111111111111111");
        card1.setCardAcctId(10000000001L);
        card1.setCardCvvCd(123);
        card1.setCardEmbossedName("ALICE M JOHNSON");
        card1.setCardExpiraionDate("2025-01-31");
        card1.setCardActiveStatus("Y");
        cardRepository.save(card1);

        Card card2 = new Card();
        card2.setCardNum("4111111111111112");
        card2.setCardAcctId(10000000002L);
        card2.setCardCvvCd(456);
        card2.setCardEmbossedName("ALICE M JOHNSON");
        card2.setCardExpiraionDate("2024-06-30");
        card2.setCardActiveStatus("Y");
        cardRepository.save(card2);

        Card card3 = new Card();
        card3.setCardNum("4222222222222222");
        card3.setCardAcctId(10000000003L);
        card3.setCardCvvCd(789);
        card3.setCardEmbossedName("ROBERT J SMITH");
        card3.setCardExpiraionDate("2026-03-31");
        card3.setCardActiveStatus("Y");
        cardRepository.save(card3);

        Card card4 = new Card();
        card4.setCardNum("4333333333333333");
        card4.setCardAcctId(10000000004L);
        card4.setCardCvvCd(321);
        card4.setCardEmbossedName("EMILY R DAVIS");
        card4.setCardExpiraionDate("2023-11-30");
        card4.setCardActiveStatus("Y");
        cardRepository.save(card4);

        Card card5 = new Card();
        card5.setCardNum("4444444444444444");
        card5.setCardAcctId(10000000005L);
        card5.setCardCvvCd(654);
        card5.setCardEmbossedName("MICHAEL D WILSON");
        card5.setCardExpiraionDate("2025-08-31");
        card5.setCardActiveStatus("Y");
        cardRepository.save(card5);

        Card card6 = new Card();
        card6.setCardNum("4555555555555555");
        card6.setCardAcctId(10000000006L);
        card6.setCardCvvCd(987);
        card6.setCardEmbossedName("MICHAEL D WILSON");
        card6.setCardExpiraionDate("2027-02-28");
        card6.setCardActiveStatus("Y");
        cardRepository.save(card6);

        Card card7 = new Card();
        card7.setCardNum("4666666666666666");
        card7.setCardAcctId(10000000007L);
        card7.setCardCvvCd(147);
        card7.setCardEmbossedName("SARAH A BROWN");
        card7.setCardExpiraionDate("2024-12-31");
        card7.setCardActiveStatus("Y");
        cardRepository.save(card7);

        Card card8 = new Card();
        card8.setCardNum("4777777777777777");
        card8.setCardAcctId(10000000008L);
        card8.setCardCvvCd(258);
        card8.setCardEmbossedName("SARAH A BROWN");
        card8.setCardExpiraionDate("2022-05-31");
        card8.setCardActiveStatus("N");
        cardRepository.save(card8);

        Card card9 = new Card();
        card9.setCardNum("4888888888888888");
        card9.setCardAcctId(10000000001L);
        card9.setCardCvvCd(369);
        card9.setCardEmbossedName("ALICE M JOHNSON");
        card9.setCardExpiraionDate("2025-01-31");
        card9.setCardActiveStatus("Y");
        cardRepository.save(card9);

        Card card10 = new Card();
        card10.setCardNum("4999999999999999");
        card10.setCardAcctId(10000000003L);
        card10.setCardCvvCd(741);
        card10.setCardEmbossedName("ROBERT J SMITH");
        card10.setCardExpiraionDate("2026-03-31");
        card10.setCardActiveStatus("Y");
        cardRepository.save(card10);

        Card card11 = new Card();
        card11.setCardNum("4000000000000000");
        card11.setCardAcctId(10000000004L);
        card11.setCardCvvCd(852);
        card11.setCardEmbossedName("EMILY R DAVIS");
        card11.setCardExpiraionDate("2023-11-30");
        card11.setCardActiveStatus("Y");
        cardRepository.save(card11);

        Card card12 = new Card();
        card12.setCardNum("4100000000000000");
        card12.setCardAcctId(10000000007L);
        card12.setCardCvvCd(963);
        card12.setCardEmbossedName("SARAH A BROWN");
        card12.setCardExpiraionDate("2024-12-31");
        card12.setCardActiveStatus("Y");
        cardRepository.save(card12);
    }

    private void createCardCrossReferences() {
        CardCrossReference xref1 = new CardCrossReference();
        xref1.setXrefCardNum("4111111111111111");
        xref1.setXrefCustId(100000001L);
        xref1.setXrefAcctId(10000000001L);
        cardCrossReferenceRepository.save(xref1);

        CardCrossReference xref2 = new CardCrossReference();
        xref2.setXrefCardNum("4111111111111112");
        xref2.setXrefCustId(100000001L);
        xref2.setXrefAcctId(10000000002L);
        cardCrossReferenceRepository.save(xref2);

        CardCrossReference xref3 = new CardCrossReference();
        xref3.setXrefCardNum("4222222222222222");
        xref3.setXrefCustId(100000002L);
        xref3.setXrefAcctId(10000000003L);
        cardCrossReferenceRepository.save(xref3);

        CardCrossReference xref4 = new CardCrossReference();
        xref4.setXrefCardNum("4333333333333333");
        xref4.setXrefCustId(100000003L);
        xref4.setXrefAcctId(10000000004L);
        cardCrossReferenceRepository.save(xref4);

        CardCrossReference xref5 = new CardCrossReference();
        xref5.setXrefCardNum("4444444444444444");
        xref5.setXrefCustId(100000004L);
        xref5.setXrefAcctId(10000000005L);
        cardCrossReferenceRepository.save(xref5);

        CardCrossReference xref6 = new CardCrossReference();
        xref6.setXrefCardNum("4555555555555555");
        xref6.setXrefCustId(100000004L);
        xref6.setXrefAcctId(10000000006L);
        cardCrossReferenceRepository.save(xref6);

        CardCrossReference xref7 = new CardCrossReference();
        xref7.setXrefCardNum("4666666666666666");
        xref7.setXrefCustId(100000005L);
        xref7.setXrefAcctId(10000000007L);
        cardCrossReferenceRepository.save(xref7);

        CardCrossReference xref8 = new CardCrossReference();
        xref8.setXrefCardNum("4777777777777777");
        xref8.setXrefCustId(100000005L);
        xref8.setXrefAcctId(10000000008L);
        cardCrossReferenceRepository.save(xref8);

        CardCrossReference xref9 = new CardCrossReference();
        xref9.setXrefCardNum("4888888888888888");
        xref9.setXrefCustId(100000001L);
        xref9.setXrefAcctId(10000000001L);
        cardCrossReferenceRepository.save(xref9);

        CardCrossReference xref10 = new CardCrossReference();
        xref10.setXrefCardNum("4999999999999999");
        xref10.setXrefCustId(100000002L);
        xref10.setXrefAcctId(10000000003L);
        cardCrossReferenceRepository.save(xref10);

        CardCrossReference xref11 = new CardCrossReference();
        xref11.setXrefCardNum("4000000000000000");
        xref11.setXrefCustId(100000003L);
        xref11.setXrefAcctId(10000000004L);
        cardCrossReferenceRepository.save(xref11);

        CardCrossReference xref12 = new CardCrossReference();
        xref12.setXrefCardNum("4100000000000000");
        xref12.setXrefCustId(100000005L);
        xref12.setXrefAcctId(10000000007L);
        cardCrossReferenceRepository.save(xref12);
    }

    private void createDisclosureGroups() {
        DisclosureGroup dg1 = new DisclosureGroup();
        dg1.setDisAcctGroupId("GROUP001");
        dg1.setDisTranTypeCd("01");
        dg1.setDisTranCatCd(1001);
        dg1.setDisIntRate(new BigDecimal("18.99"));
        disclosureGroupRepository.save(dg1);

        DisclosureGroup dg2 = new DisclosureGroup();
        dg2.setDisAcctGroupId("GROUP001");
        dg2.setDisTranTypeCd("02");
        dg2.setDisTranCatCd(2001);
        dg2.setDisIntRate(new BigDecimal("24.99"));
        disclosureGroupRepository.save(dg2);

        DisclosureGroup dg3 = new DisclosureGroup();
        dg3.setDisAcctGroupId("GROUP002");
        dg3.setDisTranTypeCd("01");
        dg3.setDisTranCatCd(1001);
        dg3.setDisIntRate(new BigDecimal("16.99"));
        disclosureGroupRepository.save(dg3);

        DisclosureGroup dg4 = new DisclosureGroup();
        dg4.setDisAcctGroupId("GROUP002");
        dg4.setDisTranTypeCd("02");
        dg4.setDisTranCatCd(2001);
        dg4.setDisIntRate(new BigDecimal("22.99"));
        disclosureGroupRepository.save(dg4);

        DisclosureGroup dg5 = new DisclosureGroup();
        dg5.setDisAcctGroupId("GROUP003");
        dg5.setDisTranTypeCd("01");
        dg5.setDisTranCatCd(1001);
        dg5.setDisIntRate(new BigDecimal("15.99"));
        disclosureGroupRepository.save(dg5);

        DisclosureGroup dg6 = new DisclosureGroup();
        dg6.setDisAcctGroupId("GROUP003");
        dg6.setDisTranTypeCd("02");
        dg6.setDisTranCatCd(2001);
        dg6.setDisIntRate(new BigDecimal("21.99"));
        disclosureGroupRepository.save(dg6);

        DisclosureGroup dg7 = new DisclosureGroup();
        dg7.setDisAcctGroupId("GROUP001");
        dg7.setDisTranTypeCd("05");
        dg7.setDisTranCatCd(5001);
        dg7.setDisIntRate(new BigDecimal("29.99"));
        disclosureGroupRepository.save(dg7);

        DisclosureGroup dg8 = new DisclosureGroup();
        dg8.setDisAcctGroupId("GROUP002");
        dg8.setDisTranTypeCd("05");
        dg8.setDisTranCatCd(5001);
        dg8.setDisIntRate(new BigDecimal("27.99"));
        disclosureGroupRepository.save(dg8);
    }

    private void createTransactions() {
        String currentTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));

        Transaction txn1 = new Transaction();
        txn1.setTranId("TXN000000000001");
        txn1.setTranTypeCd("01");
        txn1.setTranCatCd(1001);
        txn1.setTranSource("POS");
        txn1.setTranDesc("Grocery Store Purchase");
        txn1.setTranAmt(new BigDecimal("85.47"));
        txn1.setTranMerchantId(123456789L);
        txn1.setTranMerchantName("SuperMart Grocery");
        txn1.setTranMerchantCity("New York");
        txn1.setTranMerchantZip("10001");
        txn1.setTranCardNum("4111111111111111");
        txn1.setTranOrigTs(currentTimestamp);
        txn1.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn1);

        Transaction txn2 = new Transaction();
        txn2.setTranId("TXN000000000002");
        txn2.setTranTypeCd("01");
        txn2.setTranCatCd(1003);
        txn2.setTranSource("POS");
        txn2.setTranDesc("Gas Station Purchase");
        txn2.setTranAmt(new BigDecimal("45.20"));
        txn2.setTranMerchantId(234567890L);
        txn2.setTranMerchantName("Shell Gas Station");
        txn2.setTranMerchantCity("New York");
        txn2.setTranMerchantZip("10001");
        txn2.setTranCardNum("4111111111111111");
        txn2.setTranOrigTs(currentTimestamp);
        txn2.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn2);

        Transaction txn3 = new Transaction();
        txn3.setTranId("TXN000000000003");
        txn3.setTranTypeCd("01");
        txn3.setTranCatCd(1004);
        txn3.setTranSource("POS");
        txn3.setTranDesc("Restaurant Purchase");
        txn3.setTranAmt(new BigDecimal("67.89"));
        txn3.setTranMerchantId(345678901L);
        txn3.setTranMerchantName("Italian Bistro");
        txn3.setTranMerchantCity("New York");
        txn3.setTranMerchantZip("10001");
        txn3.setTranCardNum("4111111111111112");
        txn3.setTranOrigTs(currentTimestamp);
        txn3.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn3);

        Transaction txn4 = new Transaction();
        txn4.setTranId("TXN000000000004");
        txn4.setTranTypeCd("02");
        txn4.setTranCatCd(2001);
        txn4.setTranSource("ATM");
        txn4.setTranDesc("ATM Cash Advance");
        txn4.setTranAmt(new BigDecimal("200.00"));
        txn4.setTranMerchantId(456789012L);
        txn4.setTranMerchantName("Chase ATM");
        txn4.setTranMerchantCity("Beverly Hills");
        txn4.setTranMerchantZip("90210");
        txn4.setTranCardNum("4222222222222222");
        txn4.setTranOrigTs(currentTimestamp);
        txn4.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn4);

        Transaction txn5 = new Transaction();
        txn5.setTranId("TXN000000000005");
        txn5.setTranTypeCd("01");
        txn5.setTranCatCd(1002);
        txn5.setTranSource("ONLINE");
        txn5.setTranDesc("Online Purchase - Amazon");
        txn5.setTranAmt(new BigDecimal("129.99"));
        txn5.setTranMerchantId(567890123L);
        txn5.setTranMerchantName("Amazon.com");
        txn5.setTranMerchantCity("Seattle");
        txn5.setTranMerchantZip("98101");
        txn5.setTranCardNum("4333333333333333");
        txn5.setTranOrigTs(currentTimestamp);
        txn5.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn5);

        Transaction txn6 = new Transaction();
        txn6.setTranId("TXN000000000006");
        txn6.setTranTypeCd("03");
        txn6.setTranCatCd(3001);
        txn6.setTranSource("ONLINE");
        txn6.setTranDesc("Online Payment");
        txn6.setTranAmt(new BigDecimal("-500.00"));
        txn6.setTranMerchantId(678901234L);
        txn6.setTranMerchantName("CardDemo Payment");
        txn6.setTranMerchantCity("Dallas");
        txn6.setTranMerchantZip("75201");
        txn6.setTranCardNum("4333333333333333");
        txn6.setTranOrigTs(currentTimestamp);
        txn6.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn6);

        Transaction txn7 = new Transaction();
        txn7.setTranId("TXN000000000007");
        txn7.setTranTypeCd("01");
        txn7.setTranCatCd(1001);
        txn7.setTranSource("POS");
        txn7.setTranDesc("Department Store Purchase");
        txn7.setTranAmt(new BigDecimal("234.56"));
        txn7.setTranMerchantId(789012345L);
        txn7.setTranMerchantName("Macy's");
        txn7.setTranMerchantCity("Miami");
        txn7.setTranMerchantZip("33101");
        txn7.setTranCardNum("4444444444444444");
        txn7.setTranOrigTs(currentTimestamp);
        txn7.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn7);

        Transaction txn8 = new Transaction();
        txn8.setTranId("TXN000000000008");
        txn8.setTranTypeCd("04");
        txn8.setTranCatCd(4001);
        txn8.setTranSource("POS");
        txn8.setTranDesc("Purchase Refund");
        txn8.setTranAmt(new BigDecimal("-45.99"));
        txn8.setTranMerchantId(890123456L);
        txn8.setTranMerchantName("Best Buy");
        txn8.setTranMerchantCity("Seattle");
        txn8.setTranMerchantZip("98101");
        txn8.setTranCardNum("4666666666666666");
        txn8.setTranOrigTs(currentTimestamp);
        txn8.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn8);

        Transaction txn9 = new Transaction();
        txn9.setTranId("TXN000000000009");
        txn9.setTranTypeCd("05");
        txn9.setTranCatCd(5001);
        txn9.setTranSource("SYSTEM");
        txn9.setTranDesc("Late Payment Fee");
        txn9.setTranAmt(new BigDecimal("35.00"));
        txn9.setTranMerchantId(901234567L);
        txn9.setTranMerchantName("CardDemo System");
        txn9.setTranMerchantCity("New York");
        txn9.setTranMerchantZip("10001");
        txn9.setTranCardNum("4111111111111111");
        txn9.setTranOrigTs(currentTimestamp);
        txn9.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn9);

        Transaction txn10 = new Transaction();
        txn10.setTranId("TXN000000000010");
        txn10.setTranTypeCd("01");
        txn10.setTranCatCd(1004);
        txn10.setTranSource("POS");
        txn10.setTranDesc("Coffee Shop Purchase");
        txn10.setTranAmt(new BigDecimal("12.75"));
        txn10.setTranMerchantId(123987456L);
        txn10.setTranMerchantName("Starbucks");
        txn10.setTranMerchantCity("Seattle");
        txn10.setTranMerchantZip("98101");
        txn10.setTranCardNum("4666666666666666");
        txn10.setTranOrigTs(currentTimestamp);
        txn10.setTranProcTs(currentTimestamp);
        transactionRepository.save(txn10);
    }

    private void createDailyTransactions() {
        String currentTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));

        DailyTransaction dtxn1 = new DailyTransaction();
        dtxn1.setDalytranId("DTXN00000000001");
        dtxn1.setDalytranTypeCd("01");
        dtxn1.setDalytranCatCd(1001);
        dtxn1.setDalytranSource("POS");
        dtxn1.setDalytranDesc("Daily Grocery Store Purchase");
        dtxn1.setDalytranAmt(new BigDecimal("95.67"));
        dtxn1.setDalytranMerchantId(123456789L);
        dtxn1.setDalytranMerchantName("SuperMart Grocery");
        dtxn1.setDalytranMerchantCity("New York");
        dtxn1.setDalytranMerchantZip("10001");
        dtxn1.setDalytranCardNum("4111111111111111");
        dtxn1.setDalytranOrigTs(currentTimestamp);
        dtxn1.setDalytranProcTs(currentTimestamp);
        dailyTransactionRepository.save(dtxn1);

        DailyTransaction dtxn2 = new DailyTransaction();
        dtxn2.setDalytranId("DTXN00000000002");
        dtxn2.setDalytranTypeCd("01");
        dtxn2.setDalytranCatCd(1003);
        dtxn2.setDalytranSource("POS");
        dtxn2.setDalytranDesc("Daily Gas Station Purchase");
        dtxn2.setDalytranAmt(new BigDecimal("52.30"));
        dtxn2.setDalytranMerchantId(234567890L);
        dtxn2.setDalytranMerchantName("Shell Gas Station");
        dtxn2.setDalytranMerchantCity("Beverly Hills");
        dtxn2.setDalytranMerchantZip("90210");
        dtxn2.setDalytranCardNum("4222222222222222");
        dtxn2.setDalytranOrigTs(currentTimestamp);
        dtxn2.setDalytranProcTs(currentTimestamp);
        dailyTransactionRepository.save(dtxn2);

        DailyTransaction dtxn3 = new DailyTransaction();
        dtxn3.setDalytranId("DTXN00000000003");
        dtxn3.setDalytranTypeCd("01");
        dtxn3.setDalytranCatCd(1002);
        dtxn3.setDalytranSource("ONLINE");
        dtxn3.setDalytranDesc("Daily Online Purchase");
        dtxn3.setDalytranAmt(new BigDecimal("78.99"));
        dtxn3.setDalytranMerchantId(567890123L);
        dtxn3.setDalytranMerchantName("Amazon.com");
        dtxn3.setDalytranMerchantCity("Seattle");
        dtxn3.setDalytranMerchantZip("98101");
        dtxn3.setDalytranCardNum("4666666666666666");
        dtxn3.setDalytranOrigTs(currentTimestamp);
        dtxn3.setDalytranProcTs(currentTimestamp);
        dailyTransactionRepository.save(dtxn3);

        DailyTransaction dtxn4 = new DailyTransaction();
        dtxn4.setDalytranId("DTXN00000000004");
        dtxn4.setDalytranTypeCd("02");
        dtxn4.setDalytranCatCd(2001);
        dtxn4.setDalytranSource("ATM");
        dtxn4.setDalytranDesc("Daily ATM Cash Advance");
        dtxn4.setDalytranAmt(new BigDecimal("150.00"));
        dtxn4.setDalytranMerchantId(456789012L);
        dtxn4.setDalytranMerchantName("Bank of America ATM");
        dtxn4.setDalytranMerchantCity("Dallas");
        dtxn4.setDalytranMerchantZip("75201");
        dtxn4.setDalytranCardNum("4333333333333333");
        dtxn4.setDalytranOrigTs(currentTimestamp);
        dtxn4.setDalytranProcTs(currentTimestamp);
        dailyTransactionRepository.save(dtxn4);

        DailyTransaction dtxn5 = new DailyTransaction();
        dtxn5.setDalytranId("DTXN00000000005");
        dtxn5.setDalytranTypeCd("03");
        dtxn5.setDalytranCatCd(3001);
        dtxn5.setDalytranSource("ONLINE");
        dtxn5.setDalytranDesc("Daily Online Payment");
        dtxn5.setDalytranAmt(new BigDecimal("-300.00"));
        dtxn5.setDalytranMerchantId(678901234L);
        dtxn5.setDalytranMerchantName("CardDemo Payment");
        dtxn5.setDalytranMerchantCity("Miami");
        dtxn5.setDalytranMerchantZip("33101");
        dtxn5.setDalytranCardNum("4444444444444444");
        dtxn5.setDalytranOrigTs(currentTimestamp);
        dtxn5.setDalytranProcTs(currentTimestamp);
        dailyTransactionRepository.save(dtxn5);
    }

    private void createTransactionCategoryBalances() {
        TransactionCategoryBalance tcb1 = new TransactionCategoryBalance();
        tcb1.setTrancatAcctId(10000000001L);
        tcb1.setTrancatTypeCd("01");
        tcb1.setTrancatCd(1001);
        tcb1.setTranCatBal(new BigDecimal("450.25"));
        transactionCategoryBalanceRepository.save(tcb1);

        TransactionCategoryBalance tcb2 = new TransactionCategoryBalance();
        tcb2.setTrancatAcctId(10000000001L);
        tcb2.setTrancatTypeCd("01");
        tcb2.setTrancatCd(1003);
        tcb2.setTranCatBal(new BigDecimal("125.50"));
        transactionCategoryBalanceRepository.save(tcb2);

        TransactionCategoryBalance tcb3 = new TransactionCategoryBalance();
        tcb3.setTrancatAcctId(10000000002L);
        tcb3.setTrancatTypeCd("01");
        tcb3.setTrancatCd(1004);
        tcb3.setTranCatBal(new BigDecimal("320.75"));
        transactionCategoryBalanceRepository.save(tcb3);

        TransactionCategoryBalance tcb4 = new TransactionCategoryBalance();
        tcb4.setTrancatAcctId(10000000003L);
        tcb4.setTrancatTypeCd("02");
        tcb4.setTrancatCd(2001);
        tcb4.setTranCatBal(new BigDecimal("200.00"));
        transactionCategoryBalanceRepository.save(tcb4);

        TransactionCategoryBalance tcb5 = new TransactionCategoryBalance();
        tcb5.setTrancatAcctId(10000000004L);
        tcb5.setTrancatTypeCd("01");
        tcb5.setTrancatCd(1002);
        tcb5.setTranCatBal(new BigDecimal("680.90"));
        transactionCategoryBalanceRepository.save(tcb5);

        TransactionCategoryBalance tcb6 = new TransactionCategoryBalance();
        tcb6.setTrancatAcctId(10000000005L);
        tcb6.setTrancatTypeCd("01");
        tcb6.setTrancatCd(1001);
        tcb6.setTranCatBal(new BigDecimal("390.25"));
        transactionCategoryBalanceRepository.save(tcb6);

        TransactionCategoryBalance tcb7 = new TransactionCategoryBalance();
        tcb7.setTrancatAcctId(10000000006L);
        tcb7.setTrancatTypeCd("01");
        tcb7.setTrancatCd(1001);
        tcb7.setTranCatBal(new BigDecimal("180.50"));
        transactionCategoryBalanceRepository.save(tcb7);

        TransactionCategoryBalance tcb8 = new TransactionCategoryBalance();
        tcb8.setTrancatAcctId(10000000007L);
        tcb8.setTrancatTypeCd("01");
        tcb8.setTrancatCd(1001);
        tcb8.setTranCatBal(new BigDecimal("520.75"));
        transactionCategoryBalanceRepository.save(tcb8);

        TransactionCategoryBalance tcb9 = new TransactionCategoryBalance();
        tcb9.setTrancatAcctId(10000000007L);
        tcb9.setTrancatTypeCd("01");
        tcb9.setTrancatCd(1004);
        tcb9.setTranCatBal(new BigDecimal("95.30"));
        transactionCategoryBalanceRepository.save(tcb9);

        TransactionCategoryBalance tcb10 = new TransactionCategoryBalance();
        tcb10.setTrancatAcctId(10000000001L);
        tcb10.setTrancatTypeCd("05");
        tcb10.setTrancatCd(5001);
        tcb10.setTranCatBal(new BigDecimal("35.00"));
        transactionCategoryBalanceRepository.save(tcb10);
    }
}
