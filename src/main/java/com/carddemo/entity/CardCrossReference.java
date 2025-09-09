package com.carddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "card_cross_references")
@IdClass(CardCrossReferenceId.class)
public class CardCrossReference {

    @Id
    @NotNull
    @Size(max = 16)
    @Column(name = "xref_card_num", length = 16)
    private String xrefCardNum;

    @Id
    @NotNull
    @Column(name = "xref_cust_id")
    private Long xrefCustId;

    @NotNull
    @Column(name = "xref_acct_id")
    private Long xrefAcctId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "xref_card_num", insertable = false, updatable = false)
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "xref_cust_id", insertable = false, updatable = false)
    private Customer customer;

    public CardCrossReference() {}

    public String getXrefCardNum() {
        return xrefCardNum;
    }

    public void setXrefCardNum(String xrefCardNum) {
        this.xrefCardNum = xrefCardNum;
    }

    public Long getXrefCustId() {
        return xrefCustId;
    }

    public void setXrefCustId(Long xrefCustId) {
        this.xrefCustId = xrefCustId;
    }

    public Long getXrefAcctId() {
        return xrefAcctId;
    }

    public void setXrefAcctId(Long xrefAcctId) {
        this.xrefAcctId = xrefAcctId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
