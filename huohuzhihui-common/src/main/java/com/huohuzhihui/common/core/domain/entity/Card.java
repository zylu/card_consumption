package com.huohuzhihui.common.core.domain.entity;

import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 账户下的卡对象 acc_card
 * 
 * @author zylu
 * @date 2020-11-14
 */
public class Card extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 卡类型 */
    @Excel(name = "卡类型")
    private String cardTypeId;

    /** 卡号 */
    @Excel(name = "卡号")
    private String cardNo;

    /** 账户 */
    @Excel(name = "用户ID")
    private Long userId;

    private String userName;//人员姓名

    /** 卡状态：0，正常，1挂失，2注销 */
    @Excel(name = "卡状态：0，正常，1挂失，2注销")
    private Integer status;

    private Integer operType;//操作类型：1新开卡2补卡


    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /**是否虚拟卡：0不是，1是*/
    private Integer virtualcard;


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
    public void setCardTypeId(String cardTypeId) 
    {
        this.cardTypeId = cardTypeId;
    }

    public String getCardTypeId() 
    {
        return cardTypeId;
    }
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
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

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Integer getVirtualcard() {
        return virtualcard;
    }

    public void setVirtualcard(Integer virtualcard) {
        this.virtualcard = virtualcard;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cardTypeId", getCardTypeId())
            .append("cardNo", getCardNo())
            .append("userId", getUserId())
            .append("status", getStatus())
            .append("remarks", getRemarks())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
