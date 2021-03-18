package com.huohuzhihui.merchant.vo;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PosInfo {
    @JsonProperty("IsOpen")
    private Integer IsOpen;

    @JsonProperty("updateConfig")
    private String updateConfig;

    @JsonProperty("updateUserInfo")
    private String updateUserInfo;//"1&2",//1：增量同步人员信息 2：同步时段 3：同步脱机设置4：获取公司

    public Integer getIsOpen() {
        return IsOpen;
    }

    public void setIsOpen(Integer isOpen) {
        IsOpen = isOpen;
    }

    public String getUpdateConfig() {
        return updateConfig;
    }

    public void setUpdateConfig(String updateConfig) {
        this.updateConfig = updateConfig;
    }

    public String getUpdateUserInfo() {
        return updateUserInfo;
    }

    public void setUpdateUserInfo(String updateUserInfo) {
        this.updateUserInfo = updateUserInfo;
    }
}
