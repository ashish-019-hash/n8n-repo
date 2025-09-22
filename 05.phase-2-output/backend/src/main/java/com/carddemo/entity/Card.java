package com.carddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @NotNull
    @Size(max = 16)
    @Column(name = "card_num", length = 16)
    private String cardNum;

    @NotNull
    @Column(name = "card_acct_id")
    private Long cardAcctId;

    @NotNull
    @Column(name = "card_cvv_cd")
    private Integer cardCvvCd;

    @NotNull
    @Size(max = 50)
    @Column(name = "card_embossed_name", length = 50)
    private String cardEmbossedName;

    @Size(max = 10)
    @Column(name = "card_expiraion_date", length = 10)
    private String cardExpiraionDate;

    @NotNull
    @Size(max = 1)
    @Column(name = "card_active_status", length = 1)
    private String cardActiveStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_acct_id", insertable = false, updatable = false)
    private Account account;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CardCrossReference> cardCrossReferences;

    public Card() {}

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Long getCardAcctId() {
        return cardAcctId;
    }

    public void setCardAcctId(Long cardAcctId) {
        this.cardAcctId = cardAcctId;
    }

    public Integer getCardCvvCd() {
        return cardCvvCd;
    }

    public void setCardCvvCd(Integer cardCvvCd) {
        this.cardCvvCd = cardCvvCd;
    }

    public String getCardEmbossedName() {
        return cardEmbossedName;
    }

    public void setCardEmbossedName(String cardEmbossedName) {
        this.cardEmbossedName = cardEmbossedName;
    }

    public String getCardExpiraionDate() {
        return cardExpiraionDate;
    }

    public void setCardExpiraionDate(String cardExpiraionDate) {
        this.cardExpiraionDate = cardExpiraionDate;
    }

    public String getCardActiveStatus() {
        return cardActiveStatus;
    }

    public void setCardActiveStatus(String cardActiveStatus) {
        this.cardActiveStatus = cardActiveStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<CardCrossReference> getCardCrossReferences() {
        return cardCrossReferences;
    }

    public void setCardCrossReferences(List<CardCrossReference> cardCrossReferences) {
        this.cardCrossReferences = cardCrossReferences;
    }

}
