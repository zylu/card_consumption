package com.huohuzhihui.merchant.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 脱机设置
 */
public class PosOfflineSet implements Serializable{

    @JsonProperty("F_Id")
    private String fId;

    @JsonProperty("limitMoney")
    private Double limitMoney;

    @JsonProperty("limitCount")
    private Integer limitCount;

    @JsonProperty("machine_Number")
    private String machineNumber;

    @JsonProperty("F_CreatorTime")
    private String fCreatorTime;

    @JsonProperty("F_CompanyId")
    private String fCompanyId;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getfCreatorTime() {
        return fCreatorTime;
    }

    public void setfCreatorTime(String fCreatorTime) {
        this.fCreatorTime = fCreatorTime;
    }

    public String getfCompanyId() {
        return fCompanyId;
    }

    public void setfCompanyId(String fCompanyId) {
        this.fCompanyId = fCompanyId;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public Double getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(Double limitMoney) {
        this.limitMoney = limitMoney;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }
}
