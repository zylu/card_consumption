package com.huohuzhihui.account.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 账户充值对象 acc_recharge
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
public class AccRecharge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 充值人 */
    @Excel(name = "充值人")
    private Long userId;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 支付渠道 */
    @Excel(name = "支付渠道")
    private String payChannel;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long status;
    /** 充值人姓名 */
    private String userName;
    /** 充值人电话 */
    private String mobile;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setPayChannel(String payChannel) 
    {
        this.payChannel = payChannel;
    }

    public String getPayChannel() 
    {
        return payChannel;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("amount", getAmount())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("payChannel", getPayChannel())
            .append("status", getStatus())
            .toString();
    }
}
