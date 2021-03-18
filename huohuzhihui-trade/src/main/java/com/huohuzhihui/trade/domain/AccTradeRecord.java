package com.huohuzhihui.trade.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 交易记录对象 acc_trade_record
 * 
 * @author huohuzhihui
 * @date 2020-11-14
 */
public class AccTradeRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 流水类型 1充值 2 消费 3补贴*/
    @Excel(name = "流水类型")
    private Integer type;

    /** 交易流水号 */
    @Excel(name = "交易流水号")
    private String tradeNo;

    /** 交易渠道编码 */
    @Excel(name = "交易渠道编码")
    private String channelCode;

    /** 交易渠道名称 */
    @Excel(name = "交易渠道名称")
    private String channelName;

    /** 交易订单号 */
    @Excel(name = "交易订单号")
    private Long tradeOrderId;

    /** 状态  1 已入账 2未入账 */
    @Excel(name = "状态")
    private Integer status;

    /** 账户名称 */
    @Excel(name = "账户名称")
    private String accountName;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tradeTime;

    /** 账户id */
    @Excel(name = "账户id")
    private Long accountId;

    /** 卡號 */
    @Excel(name = "卡號")
    private String cardNo;


    /**  '删除标示 0 未删除 1已删除' */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    public void setTradeNo(String tradeNo) 
    {
        this.tradeNo = tradeNo;
    }

    public String getTradeNo() 
    {
        return tradeNo;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }
    public void setTradeOrderId(Long tradeOrderId) 
    {
        this.tradeOrderId = tradeOrderId;
    }

    public Long getTradeOrderId() 
    {
        return tradeOrderId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setAccountName(String accountName) 
    {
        this.accountName = accountName;
    }

    public String getAccountName() 
    {
        return accountName;
    }
    public void setTradeTime(Date tradeTime) 
    {
        this.tradeTime = tradeTime;
    }

    public Date getTradeTime() 
    {
        return tradeTime;
    }
    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "AccTradeRecord{" +
                "id=" + id +
                ", amount=" + amount +
                ", type=" + type +
                ", tradeNo='" + tradeNo + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", channelName='" + channelName + '\'' +
                ", tradeOrderId=" + tradeOrderId +
                ", status=" + status +
                ", accountName='" + accountName + '\'' +
                ", tradeTime=" + tradeTime +
                ", accountId=" + accountId +
                ", cardNo='" + cardNo + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
