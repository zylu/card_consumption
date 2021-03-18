package com.huohuzhihui.trade.constant;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public enum TradeConstants
{

    ACCOUNT_NOT_FOUND(801,"交易失败：账户不存在"),
    ACCOUNT_STATUS_EXCEPTION(802,"交易失败：账户状态异常"),
    CARD_NOT_FOUND(803,"交易失败：卡不存在"),
    CARD_STATUS_EXCEPTION(804,"交易失败：卡状态异常"),
    CARD_ONCE_LIMIT(805,"交易失败：不能超过单笔最高消费"),
    CARD_DAY_LIMIT(806,"交易失败：不能超过每日最高消费"),
    BALANCE_NOT_ENOUTH(807,"交易失败：账户余额不足"),
    DEVICE_NOT_FOUND(808,"交易失败：设备不存在"),
    TRADE_SUCCESS(200,"交易成功"),
    TRADE_FAIL(500,"交易失败:未知错误，请联系技术人员")    ;

    /*值*/
    private Integer  code;
    /*描述*/
    private String desc;

    TradeConstants(Integer code,String desc){
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
