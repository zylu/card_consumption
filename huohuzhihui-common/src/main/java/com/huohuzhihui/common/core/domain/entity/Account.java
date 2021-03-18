package com.huohuzhihui.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户对象 acc_user_account
 * 
 * @author zylu
 * @date 2020-11-14
 */
public class Account extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal balance;

    /** 押金 */
    @Excel(name = "押金")
    private BigDecimal deposit;

    /** 管理费 */
    @Excel(name = "管理费")
    private BigDecimal addfare;

    /** 有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date avdate;

    /** 账户状态：0，正常，1冻结，2注销 */
    @Excel(name = "账户状态：0，正常，1冻结，2注销")
    private Integer status;

    /** 备注信息 */
    private String remarks;

    /** 删除标记  0未删除 1删除 */
    private Integer delFlag;

    private String userName;//用户姓名

    private String phonenumber;//用户电话
    /**
     * 版本号
     */
    private Integer version;

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
    public void setBalance(BigDecimal balance) 
    {
        this.balance = balance;
    }

    public BigDecimal getBalance() 
    {
        return balance;
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
    public void setAvdate(Date avdate) 
    {
        this.avdate = avdate;
    }

    public Date getAvdate() 
    {
        return avdate;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("balance", getBalance())
            .append("deposit", getDeposit())
            .append("addfare", getAddfare())
            .append("avdate", getAvdate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
