package com.carddemo.entity;

import java.io.Serializable;
import java.util.Objects;

public class CardCrossReferenceId implements Serializable {

    private String xrefCardNum;
    private Long xrefCustId;

    public CardCrossReferenceId() {}

    public CardCrossReferenceId(String xrefCardNum, Long xrefCustId) {
        this.xrefCardNum = xrefCardNum;
        this.xrefCustId = xrefCustId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardCrossReferenceId that = (CardCrossReferenceId) o;
        return Objects.equals(xrefCardNum, that.xrefCardNum) && Objects.equals(xrefCustId, that.xrefCustId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xrefCardNum, xrefCustId);
    }

}
