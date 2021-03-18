package com.huohuzhihui.account.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

/**
 * 订单充值支付流水对象 acc_recharge_pay
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
public class AccRechargePay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 充值订单ID */
    @Excel(name = "充值订单ID")
    private Long rechargeId;

    /** 第三方支付流水号 */
    @Excel(name = "第三方支付流水号")
    private String outTradeNo;

    /** 支付请求数据 */
    @Excel(name = "支付请求数据")
    private String requestData;

    /** 支付响应数据 */
    @Excel(name = "支付响应数据")
    private String responseData;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRechargeId(Long rechargeId) 
    {
        this.rechargeId = rechargeId;
    }

    public Long getRechargeId() 
    {
        return rechargeId;
    }
    public void setOutTradeNo(String outTradeNo) 
    {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo() 
    {
        return outTradeNo;
    }
    public void setRequestData(String requestData) 
    {
        this.requestData = requestData;
    }

    public String getRequestData() 
    {
        return requestData;
    }
    public void setResponseData(String responseData) 
    {
        this.responseData = responseData;
    }

    public String getResponseData() 
    {
        return responseData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rechargeId", getRechargeId())
            .append("outTradeNo", getOutTradeNo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("requestData", getRequestData())
            .append("responseData", getResponseData())
            .toString();
    }
}
