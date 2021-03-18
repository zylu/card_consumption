package com.huohuzhihui.merchant.exception;

public class PosException extends RuntimeException {

    private Integer code;
    private String message;

    public PosException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
