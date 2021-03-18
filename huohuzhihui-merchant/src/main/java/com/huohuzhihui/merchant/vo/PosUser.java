package com.huohuzhihui.merchant.vo;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class PosUser {
    @JsonProperty("Unique_number")
    private Long uniqueNumber;

    @JsonProperty("Card_class")
    private String cardClass;

    @JsonProperty("Card_state")
    private Integer cardState;

    @JsonProperty("UID")
    private String uid;

    @JsonProperty("Card_Number")
    private String cardNumber;

    @JsonProperty("Full_Name")
    private String fullName;

    @JsonProperty("Balance")
    private BigDecimal balance;

    @JsonProperty("Opening_date")
    private String openingDate;//2007/12/7 14:21:26

    @JsonProperty("Limit_times1")
    private Integer limitTimes1;

    @JsonProperty("Limit_times2")
    private Integer limitTimes2;

    @JsonProperty("Limit_times3")
    private Integer limitTimes3;

    @JsonProperty("Limit_times4")
    private Integer limitTimes4;

    @JsonProperty("Consumption_quota")
    private Double consumptionQuota;

    @JsonProperty("Term_of_validity")
    private Date termOfValidity;


    @JsonProperty("imgData")
    private String imgData; //压缩图片100*100

    public Long getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(Long uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public Integer getLimitTimes1() {
        return limitTimes1;
    }

    public void setLimitTimes1(Integer limitTimes1) {
        this.limitTimes1 = limitTimes1;
    }

    public Integer getLimitTimes2() {
        return limitTimes2;
    }

    public void setLimitTimes2(Integer limitTimes2) {
        this.limitTimes2 = limitTimes2;
    }

    public Integer getLimitTimes3() {
        return limitTimes3;
    }

    public void setLimitTimes3(Integer limitTimes3) {
        this.limitTimes3 = limitTimes3;
    }

    public Integer getLimitTimes4() {
        return limitTimes4;
    }

    public void setLimitTimes4(Integer limitTimes4) {
        this.limitTimes4 = limitTimes4;
    }

    public Double getConsumptionQuota() {
        return consumptionQuota;
    }

    public void setConsumptionQuota(Double consumptionQuota) {
        this.consumptionQuota = consumptionQuota;
    }

    public String getImgData() {
        return imgData;
    }

    public void setImgData(String imgData) {
        this.imgData = imgData;
    }

    public String getCardClass() {
        return cardClass;
    }

    public void setCardClass(String cardClass) {
        this.cardClass = cardClass;
    }

    public Date getTermOfValidity() {
        return termOfValidity;
    }

    public void setTermOfValidity(Date termOfValidity) {
        this.termOfValidity = termOfValidity;
    }
}
