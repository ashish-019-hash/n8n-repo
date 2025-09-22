package com.carddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "disclosure_groups")
@IdClass(DisclosureGroupId.class)
public class DisclosureGroup {

    @Id
    @NotNull
    @Size(max = 10)
    @Column(name = "dis_acct_group_id", length = 10)
    private String disAcctGroupId;

    @Id
    @NotNull
    @Size(max = 2)
    @Column(name = "dis_tran_type_cd", length = 2)
    private String disTranTypeCd;

    @Id
    @NotNull
    @Column(name = "dis_tran_cat_cd")
    private Integer disTranCatCd;

    @Column(name = "dis_int_rate", precision = 6, scale = 2)
    private BigDecimal disIntRate;

    public DisclosureGroup() {}

    public String getDisAcctGroupId() {
        return disAcctGroupId;
    }

    public void setDisAcctGroupId(String disAcctGroupId) {
        this.disAcctGroupId = disAcctGroupId;
    }

    public String getDisTranTypeCd() {
        return disTranTypeCd;
    }

    public void setDisTranTypeCd(String disTranTypeCd) {
        this.disTranTypeCd = disTranTypeCd;
    }

    public Integer getDisTranCatCd() {
        return disTranCatCd;
    }

    public void setDisTranCatCd(Integer disTranCatCd) {
        this.disTranCatCd = disTranCatCd;
    }

    public BigDecimal getDisIntRate() {
        return disIntRate;
    }

    public void setDisIntRate(BigDecimal disIntRate) {
        this.disIntRate = disIntRate;
    }

}
