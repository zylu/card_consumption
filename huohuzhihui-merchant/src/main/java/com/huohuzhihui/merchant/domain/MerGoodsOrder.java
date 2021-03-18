package com.huohuzhihui.merchant.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

/**
 * 商户商品订单对象 mer_goods_order
 * 
 * @author zylu
 * @date 2020-11-14
 */
public class MerGoodsOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单总价 */
    @Excel(name = "订单总价")
    private BigDecimal price;

    /** 使用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useDate;

    /** 订单状态：0:待付款；1：已付款待使用；2：已使用；3：已退款 */
    @Excel(name = "订单状态：0:待付款；1：已付款待使用；2：已使用；3：已退款")
    private Integer status;

    /** 时段 */
    @Excel(name = "时段")
    private String timeId;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 删除标示 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setUseDate(Date useDate) 
    {
        this.useDate = useDate;
    }

    public Date getUseDate() 
    {
        return useDate;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setTimeId(String timeId) 
    {
        this.timeId = timeId;
    }

    public String getTimeId() 
    {
        return timeId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("price", getPrice())
            .append("useDate", getUseDate())
            .append("status", getStatus())
            .append("timeId", getTimeId())
            .append("remarks", getRemarks())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
