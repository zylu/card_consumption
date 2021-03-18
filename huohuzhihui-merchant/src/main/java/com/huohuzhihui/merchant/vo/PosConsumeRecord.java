package com.huohuzhihui.merchant.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 消费记录
 */
public class PosConsumeRecord implements Serializable{

    @JsonProperty("Card_Number")
    private String cardNumber;

    @JsonProperty("bizDate")
    private String bizDate;//2020-04-09 14:22:01

    @JsonProperty("Full_Name")
    private String fullName;

    @JsonProperty("bizAmount")
    private Double bizAmount;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("Id")
    private String id;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBizDate() {
        return bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getBizAmount() {
        return bizAmount;
    }

    public void setBizAmount(Double bizAmount) {
        this.bizAmount = bizAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
