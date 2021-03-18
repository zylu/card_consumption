package com.huohuzhihui.trade.constant;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public enum TradeSourceConstants
{

    FROM_APP(1,"手机APP"),
    FROM_SELFDEVICE(2,"自助终端"),
    FROM_WXAPP(3,"微信小程序"),
    FROM_ADMIN(4,"管理后台"),
    FROM_POS(5,"POS");

    /*值*/
    private Integer  code;
    /*描述*/
    private String desc;

    TradeSourceConstants(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
