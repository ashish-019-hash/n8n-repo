package com.carddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "daily_transactions")
public class DailyTransaction {

    @Id
    @NotNull
    @Size(max = 16)
    @Column(name = "dalytran_id", length = 16)
    private String dalytranId;

    @NotNull
    @Size(max = 2)
    @Column(name = "dalytran_type_cd", length = 2)
    private String dalytranTypeCd;

    @NotNull
    @Column(name = "dalytran_cat_cd")
    private Integer dalytranCatCd;

    @Size(max = 10)
    @Column(name = "dalytran_source", length = 10)
    private String dalytranSource;

    @Size(max = 100)
    @Column(name = "dalytran_desc", length = 100)
    private String dalytranDesc;

    @NotNull
    @Column(name = "dalytran_amt", precision = 11, scale = 2)
    private BigDecimal dalytranAmt;

    @Column(name = "dalytran_merchant_id")
    private Long dalytranMerchantId;

    @Size(max = 50)
    @Column(name = "dalytran_merchant_name", length = 50)
    private String dalytranMerchantName;

    @Size(max = 50)
    @Column(name = "dalytran_merchant_city", length = 50)
    private String dalytranMerchantCity;

    @Size(max = 10)
    @Column(name = "dalytran_merchant_zip", length = 10)
    private String dalytranMerchantZip;

    @NotNull
    @Size(max = 16)
    @Column(name = "dalytran_card_num", length = 16)
    private String dalytranCardNum;

    @Size(max = 26)
    @Column(name = "dalytran_orig_ts", length = 26)
    private String dalytranOrigTs;

    @Size(max = 26)
    @Column(name = "dalytran_proc_ts", length = 26)
    private String dalytranProcTs;

    public DailyTransaction() {}

    public String getDalytranId() {
        return dalytranId;
    }

    public void setDalytranId(String dalytranId) {
        this.dalytranId = dalytranId;
    }

    public String getDalytranTypeCd() {
        return dalytranTypeCd;
    }

    public void setDalytranTypeCd(String dalytranTypeCd) {
        this.dalytranTypeCd = dalytranTypeCd;
    }

    public Integer getDalytranCatCd() {
        return dalytranCatCd;
    }

    public void setDalytranCatCd(Integer dalytranCatCd) {
        this.dalytranCatCd = dalytranCatCd;
    }

    public String getDalytranSource() {
        return dalytranSource;
    }

    public void setDalytranSource(String dalytranSource) {
        this.dalytranSource = dalytranSource;
    }

    public String getDalytranDesc() {
        return dalytranDesc;
    }

    public void setDalytranDesc(String dalytranDesc) {
        this.dalytranDesc = dalytranDesc;
    }

    public BigDecimal getDalytranAmt() {
        return dalytranAmt;
    }

    public void setDalytranAmt(BigDecimal dalytranAmt) {
        this.dalytranAmt = dalytranAmt;
    }

    public Long getDalytranMerchantId() {
        return dalytranMerchantId;
    }

    public void setDalytranMerchantId(Long dalytranMerchantId) {
        this.dalytranMerchantId = dalytranMerchantId;
    }

    public String getDalytranMerchantName() {
        return dalytranMerchantName;
    }

    public void setDalytranMerchantName(String dalytranMerchantName) {
        this.dalytranMerchantName = dalytranMerchantName;
    }

    public String getDalytranMerchantCity() {
        return dalytranMerchantCity;
    }

    public void setDalytranMerchantCity(String dalytranMerchantCity) {
        this.dalytranMerchantCity = dalytranMerchantCity;
    }

    public String getDalytranMerchantZip() {
        return dalytranMerchantZip;
    }

    public void setDalytranMerchantZip(String dalytranMerchantZip) {
        this.dalytranMerchantZip = dalytranMerchantZip;
    }

    public String getDalytranCardNum() {
        return dalytranCardNum;
    }

    public void setDalytranCardNum(String dalytranCardNum) {
        this.dalytranCardNum = dalytranCardNum;
    }

    public String getDalytranOrigTs() {
        return dalytranOrigTs;
    }

    public void setDalytranOrigTs(String dalytranOrigTs) {
        this.dalytranOrigTs = dalytranOrigTs;
    }

    public String getDalytranProcTs() {
        return dalytranProcTs;
    }

    public void setDalytranProcTs(String dalytranProcTs) {
        this.dalytranProcTs = dalytranProcTs;
    }

}
