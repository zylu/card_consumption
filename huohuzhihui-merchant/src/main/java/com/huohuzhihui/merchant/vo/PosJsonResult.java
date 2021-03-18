package com.huohuzhihui.merchant.vo;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PosJsonResult implements Serializable {
    @JsonProperty(value = "Code")
    private Integer code;

    @JsonProperty(value = "Message")
    private String message;

    @JsonProperty(value = "Data")
    private Object data;

    @JsonProperty(value = "company")
    private String company;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
