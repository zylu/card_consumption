package com.huohuzhihui.common.enums;

/**
 * 登录类型
 * 
 * @author lizhihe
 */
public enum LoginType
{
    WEB(1, "WEB"),
    WX(2, "微信"),
    DEVICE(3, "设备"),
    ;

    private final int code;
    private final String name;

    LoginType(int code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public int getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }
}
