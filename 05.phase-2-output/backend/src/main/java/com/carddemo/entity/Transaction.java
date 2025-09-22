package com.carddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @NotNull
    @Size(max = 16)
    @Column(name = "tran_id", length = 16)
    private String tranId;

    @NotNull
    @Size(max = 2)
    @Column(name = "tran_type_cd", length = 2)
    private String tranTypeCd;

    @NotNull
    @Column(name = "tran_cat_cd")
    private Integer tranCatCd;

    @Size(max = 10)
    @Column(name = "tran_source", length = 10)
    private String tranSource;

    @Size(max = 100)
    @Column(name = "tran_desc", length = 100)
    private String tranDesc;

    @NotNull
    @Column(name = "tran_amt", precision = 11, scale = 2)
    private BigDecimal tranAmt;

    @Column(name = "tran_merchant_id")
    private Long tranMerchantId;

    @Size(max = 50)
    @Column(name = "tran_merchant_name", length = 50)
    private String tranMerchantName;

    @Size(max = 50)
    @Column(name = "tran_merchant_city", length = 50)
    private String tranMerchantCity;

    @Size(max = 10)
    @Column(name = "tran_merchant_zip", length = 10)
    private String tranMerchantZip;

    @NotNull
    @Size(max = 16)
    @Column(name = "tran_card_num", length = 16)
    private String tranCardNum;

    @Size(max = 26)
    @Column(name = "tran_orig_ts", length = 26)
    private String tranOrigTs;

    @Size(max = 26)
    @Column(name = "tran_proc_ts", length = 26)
    private String tranProcTs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tran_card_num", insertable = false, updatable = false)
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tran_type_cd", insertable = false, updatable = false)
    private TransactionType transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "tran_type_cd", referencedColumnName = "tran_type_cd", insertable = false, updatable = false),
        @JoinColumn(name = "tran_cat_cd", referencedColumnName = "tran_cat_cd", insertable = false, updatable = false)
    })
    private TransactionCategory transactionCategory;

    public Transaction() {}

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

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

    public String getTranSource() {
        return tranSource;
    }

    public void setTranSource(String tranSource) {
        this.tranSource = tranSource;
    }

    public String getTranDesc() {
        return tranDesc;
    }

    public void setTranDesc(String tranDesc) {
        this.tranDesc = tranDesc;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public Long getTranMerchantId() {
        return tranMerchantId;
    }

    public void setTranMerchantId(Long tranMerchantId) {
        this.tranMerchantId = tranMerchantId;
    }

    public String getTranMerchantName() {
        return tranMerchantName;
    }

    public void setTranMerchantName(String tranMerchantName) {
        this.tranMerchantName = tranMerchantName;
    }

    public String getTranMerchantCity() {
        return tranMerchantCity;
    }

    public void setTranMerchantCity(String tranMerchantCity) {
        this.tranMerchantCity = tranMerchantCity;
    }

    public String getTranMerchantZip() {
        return tranMerchantZip;
    }

    public void setTranMerchantZip(String tranMerchantZip) {
        this.tranMerchantZip = tranMerchantZip;
    }

    public String getTranCardNum() {
        return tranCardNum;
    }

    public void setTranCardNum(String tranCardNum) {
        this.tranCardNum = tranCardNum;
    }

    public String getTranOrigTs() {
        return tranOrigTs;
    }

    public void setTranOrigTs(String tranOrigTs) {
        this.tranOrigTs = tranOrigTs;
    }

    public String getTranProcTs() {
        return tranProcTs;
    }

    public void setTranProcTs(String tranProcTs) {
        this.tranProcTs = tranProcTs;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionCategory getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(TransactionCategory transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

}
