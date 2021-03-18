package com.huohuzhihui.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.entity.SysUser;

import java.math.BigDecimal;
import java.util.Date;

public class SubsidyVo {
    private Long userId;
    @Excel(name = "姓名")
    private String userName;
    @Excel(name = "手机号")
    private String mobile;
    @Excel(name = "补贴金额")
    private BigDecimal amount;
    private String source;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "补贴时间",dateFormat="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 开始时间 */
    @JsonIgnore
    private String beginTime;

    /** 结束时间 */
    @JsonIgnore
    private String endTime;

    /** 创建者 */
    @Excel(name = "操作人")
    private String createBy;


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
