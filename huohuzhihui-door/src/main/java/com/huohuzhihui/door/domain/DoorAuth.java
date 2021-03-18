package com.huohuzhihui.door.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.huohuzhihui.common.utils.DateUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

/**
 * 门禁授权对象 t_door_auth
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
public class DoorAuth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 门的id */
    @Excel(name = "门的id")
    private Long doorId;

    /** 一卡通账户id */
    @Excel(name = "一卡通账户id")
    private Long accId;

    /** 授权起始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "授权起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 授权终止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "授权终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 删除标记：0未删除，1删除 */
    private Integer delFlag;

    /** 门禁卡号：bsCardNo%(256*256) + ((int)(bsCardNo/(256*256))%256)*100000 */
    @Excel(name = "门禁卡号：bsCardNo%(256*256) + ((int)(bsCardNo/(256*256))%256)*100000")
    private String cardNo;

    /** $column.columnComment */
    @Excel(name = "门禁卡号：bsCardNo%(256*256) + ((int)(bsCardNo/(256*256))%256)*100000")
    private String stuCode;

    /** 是否已写入门禁机：0，未写入，1已写入 */
    @Excel(name = "是否已写入门禁机：0，未写入，1已写入")
    private Integer isWrited;

    /** 宝石卡号 */
    @Excel(name = "宝石卡号")
    private String bsCardNo;

    /** 扩展权限：1-可查看门禁记录；2：可授权给他人 */
    @Excel(name = "扩展权限：1-可查看门禁记录；2：可授权给他人")
    private String authExtend;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDoorId(Long doorId) 
    {
        this.doorId = doorId;
    }

    public Long getDoorId() 
    {
        return doorId;
    }
    public void setAccId(Long accId) 
    {
        this.accId = accId;
    }

    public Long getAccId() 
    {
        return accId;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime()
    {
        if(endTime != null){
            return DateUtils.dateTime(endTime);
        }
         return null;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
    }
    public void setStuCode(String stuCode) 
    {
        this.stuCode = stuCode;
    }

    public String getStuCode() 
    {
        return stuCode;
    }
    public void setIsWrited(Integer isWrited) 
    {
        this.isWrited = isWrited;
    }

    public Integer getIsWrited() 
    {
        return isWrited;
    }
    public void setBsCardNo(String bsCardNo) 
    {
        this.bsCardNo = bsCardNo;
    }

    public String getBsCardNo() 
    {
        return bsCardNo;
    }
    public void setAuthExtend(String authExtend) 
    {
        this.authExtend = authExtend;
    }

    public String getAuthExtend() 
    {
        return authExtend;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("doorId", getDoorId())
            .append("accId", getAccId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("cardNo", getCardNo())
            .append("stuCode", getStuCode())
            .append("isWrited", getIsWrited())
            .append("bsCardNo", getBsCardNo())
            .append("authExtend", getAuthExtend())
            .toString();
    }
}
