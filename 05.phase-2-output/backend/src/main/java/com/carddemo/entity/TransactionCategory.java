package com.carddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "transaction_categories")
@IdClass(TransactionCategoryId.class)
public class TransactionCategory {

    @Id
    @NotNull
    @Size(max = 2)
    @Column(name = "tran_type_cd", length = 2)
    private String tranTypeCd;

    @Id
    @NotNull
    @Column(name = "tran_cat_cd")
    private Integer tranCatCd;

    @NotNull
    @Size(max = 50)
    @Column(name = "tran_cat_type_desc", length = 50)
    private String tranCatTypeDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tran_type_cd", insertable = false, updatable = false)
    private TransactionType transactionType;

    @OneToMany(mappedBy = "transactionCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "transactionCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionCategoryBalance> transactionCategoryBalances;

    public TransactionCategory() {}

    public String getTranTypeCd() {
        return tranTypeCd;
    }

    public void setTranTypeCd(String tranTypeCd) {
        this.tranTypeCd = tranTypeCd;
    }

    public Integer getTranCatCd() {
        return tranCatCd;
    }

    public void setTranCatCd(Integer tranCatCd) {
        this.tranCatCd = tranCatCd;
    }

    public String getTranCatTypeDesc() {
        return tranCatTypeDesc;
    }

    public void setTranCatTypeDesc(String tranCatTypeDesc) {
        this.tranCatTypeDesc = tranCatTypeDesc;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionCategoryBalance> getTransactionCategoryBalances() {
        return transactionCategoryBalances;
    }

    public void setTransactionCategoryBalances(List<TransactionCategoryBalance> transactionCategoryBalances) {
        this.transactionCategoryBalances = transactionCategoryBalances;
    }

}
