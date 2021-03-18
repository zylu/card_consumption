package com.huohuzhihui.account.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

/**
 * 卡类型对象 acc_card_type
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
public class AccCardType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 卡类型名称 */
    @Excel(name = "卡类型名称")
    private String name;

    /** 押金 */
    @Excel(name = "押金")
    private BigDecimal deposit;

    /** 管理费 */
    @Excel(name = "管理费")
    private BigDecimal addfare;

    /** 新发卡成本费(单位为元) */
    @Excel(name = "新发卡成本费(单位为元)")
    private BigDecimal firstcost;

    /** 补卡成本费(单位为元) */
    @Excel(name = "补卡成本费(单位为元)")
    private BigDecimal addcost;

    /** 有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date avdate;

    /** 交易次数限制 */
    @Excel(name = "交易次数限制")
    private Long timelimit;

    /** 消费折扣 */
    @Excel(name = "消费折扣")
    private BigDecimal discount;

    /** 备注信息 */
    @Excel(name = "备注信息")
    private String remarks;

    /** 删除标记  0未删除 1删除 */
    private Integer delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDeposit(BigDecimal deposit) 
    {
        this.deposit = deposit;
    }

    public BigDecimal getDeposit() 
    {
        return deposit;
    }
    public void setAddfare(BigDecimal addfare) 
    {
        this.addfare = addfare;
    }

    public BigDecimal getAddfare() 
    {
        return addfare;
    }
    public void setFirstcost(BigDecimal firstcost) 
    {
        this.firstcost = firstcost;
    }

    public BigDecimal getFirstcost() 
    {
        return firstcost;
    }
    public void setAddcost(BigDecimal addcost) 
    {
        this.addcost = addcost;
    }

    public BigDecimal getAddcost() 
    {
        return addcost;
    }
    public void setAvdate(Date avdate) 
    {
        this.avdate = avdate;
    }

    public Date getAvdate() 
    {
        return avdate;
    }
    public void setTimelimit(Long timelimit) 
    {
        this.timelimit = timelimit;
    }

    public Long getTimelimit() 
    {
        return timelimit;
    }
    public void setDiscount(BigDecimal discount) 
    {
        this.discount = discount;
    }

    public BigDecimal getDiscount() 
    {
        return discount;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("deposit", getDeposit())
            .append("addfare", getAddfare())
            .append("firstcost", getFirstcost())
            .append("addcost", getAddcost())
            .append("avdate", getAvdate())
            .append("timelimit", getTimelimit())
            .append("discount", getDiscount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
