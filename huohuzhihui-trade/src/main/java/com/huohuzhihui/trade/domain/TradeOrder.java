package com.huohuzhihui.trade.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

/**
 * 订单对象 trade_order
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
public class TradeOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 交易类型：0充值1消费 */
    @Excel(name = "账户操作类型：见acc_oper_type表")
    private Integer type;

    /** 账户ID */
    @Excel(name = "账户ID")
    private Long accountId;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 订单状态：0:待支付，1：交易成功；2、交易失败 */
    @Excel(name = "订单状态：0:待支付，1：交易成功；2、交易失败")
    private Integer status;

    /** 订单来源：1、手机APP；2、自助终端;3、微信小程序；4:、管理后台 */
    @Excel(name = "订单来源：1、手机APP；2、自助终端;3、微信小程序；4:、管理后台;5:POS")
    private String source;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long deviceId;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 删除标示 */
    private String delFlag;

    /**用户名称*/
    private String userName;
    /**手机号*/
    private String mobile;

    private Long merchantId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setSource(String source)
    {
        this.source = source;
    }

    public String getSource()
    {
        return source;
    }
    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("accountId", getAccountId())
            .append("amount", getAmount())
            .append("status", getStatus())
            .append("source", getSource())
            .append("deviceId", getDeviceId())
            .append("remarks", getRemarks())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
