package com.huohuzhihui.trade.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单统计图对象
 */
public class TradeOrderChart implements Serializable {
    /**
     * 年份
     */
    private String yearName;
    /**
     * 月份
     */
    private String monthName;
    /**
     * 天
     */
    private Integer dayName;
    /**
     * 收款金额
     */
    private BigDecimal amount;

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Integer getDayName() {
        return dayName;
    }

    public void setDayName(Integer dayName) {
        this.dayName = dayName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
