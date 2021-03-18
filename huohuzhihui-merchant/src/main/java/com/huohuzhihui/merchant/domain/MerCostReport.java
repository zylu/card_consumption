package com.huohuzhihui.merchant.domain;

import com.huohuzhihui.common.annotation.Excel;

public class MerCostReport {
    /**
     * 交易日期
     */
    private String tradeDate;
    /**
     * 商户名称
     */
    @Excel(name = "商户名称")
    private String merchantName;
    /**
     * 交易总金额
     */
    @Excel(name = "金额")
    private String sum;
    /**
     * 交易总笔数
     */
    @Excel(name = "笔数")
    private String count;

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
