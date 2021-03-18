package com.huohuzhihui.merchant.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class PosCost implements Serializable{

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("consumption_Mone")
    private BigDecimal consumptionMone;

    @JsonProperty("full_name")
    private String fullName;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getConsumptionMone() {
        return consumptionMone;
    }

    public void setConsumptionMone(BigDecimal consumptionMone) {
        this.consumptionMone = consumptionMone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
