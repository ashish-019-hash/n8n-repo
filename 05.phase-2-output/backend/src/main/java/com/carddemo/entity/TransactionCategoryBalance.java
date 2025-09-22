package com.carddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction_category_balances")
@IdClass(TransactionCategoryBalanceId.class)
public class TransactionCategoryBalance {

    @Id
    @NotNull
    @Column(name = "trancat_acct_id")
    private Long trancatAcctId;

    @Id
    @NotNull
    @Column(name = "trancat_type_cd", length = 2)
    private String trancatTypeCd;

    @Id
    @NotNull
    @Column(name = "trancat_cd")
    private Integer trancatCd;

    @Column(name = "tran_cat_bal", precision = 11, scale = 2)
    private BigDecimal tranCatBal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trancat_acct_id", insertable = false, updatable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "trancat_type_cd", referencedColumnName = "tran_type_cd", insertable = false, updatable = false),
        @JoinColumn(name = "trancat_cd", referencedColumnName = "tran_cat_cd", insertable = false, updatable = false)
    })
    private TransactionCategory transactionCategory;

    public TransactionCategoryBalance() {}

    public Long getTrancatAcctId() {
        return trancatAcctId;
    }

    public void setTrancatAcctId(Long trancatAcctId) {
        this.trancatAcctId = trancatAcctId;
    }

    public String getTrancatTypeCd() {
        return trancatTypeCd;
    }

    public void setTrancatTypeCd(String trancatTypeCd) {
        this.trancatTypeCd = trancatTypeCd;
    }

    public Integer getTrancatCd() {
        return trancatCd;
    }

    public void setTrancatCd(Integer trancatCd) {
        this.trancatCd = trancatCd;
    }

    public BigDecimal getTranCatBal() {
        return tranCatBal;
    }

    public void setTranCatBal(BigDecimal tranCatBal) {
        this.tranCatBal = tranCatBal;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionCategory getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(TransactionCategory transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

}
