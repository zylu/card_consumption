package com.huohuzhihui.merchant.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

/**
 * 商户商品对象 mer_goods
 * 
 * @author zylu
 * @date 2020-11-14
 */
public class MerGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 时段 */
    @Excel(name = "时段")
    private String timeId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 库存 */
    @Excel(name = "库存")
    private Integer goodsNumber;

    /** 商户 */
    @Excel(name = "商户")
    private String merchantId;

    /** 备注 */
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
    public void setTimeId(String timeId) 
    {
        this.timeId = timeId;
    }

    public String getTimeId() 
    {
        return timeId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setGoodsNumber(Integer goodsNumber) 
    {
        this.goodsNumber = goodsNumber;
    }

    public Integer getGoodsNumber() 
    {
        return goodsNumber;
    }
    public void setMerchantId(String merchantId) 
    {
        this.merchantId = merchantId;
    }

    public String getMerchantId() 
    {
        return merchantId;
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
            .append("timeId", getTimeId())
            .append("name", getName())
            .append("price", getPrice())
            .append("goodsNumber", getGoodsNumber())
            .append("merchantId", getMerchantId())
            .append("remarks", getRemarks())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
