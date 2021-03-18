package com.huohuzhihui.door.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

/**
 * 门禁记录对象 t_door_record
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
public class DoorRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 门禁机SN号 */
    @Excel(name = "门禁机SN号")
    private String sn;

    /** 门禁机对应的锁号 */
    @Excel(name = "门禁机对应的锁号")
    private String lockNo;

    /** 进门还是出门：1进门；2出门 */
    @Excel(name = "进门还是出门：1进门；2出门")
    private Integer isIn;

    /** 是否有效：禁止/通行：0禁止，1通过 */
    @Excel(name = "是否有效：禁止/通行：0禁止，1通过")
    private Integer isValid;

    /** 刷卡的卡号 */
    @Excel(name = "刷卡的卡号")
    private String cardNo;

    /** 结果描述 */
    @Excel(name = "结果描述")
    private String description;

    /** 记录类型：1，刷卡；2，门磁，按钮，设备启动，远程；3，报警记录 */
    @Excel(name = "记录类型：1，刷卡；2，门磁，按钮，设备启动，远程；3，报警记录")
    private Integer type;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录类型：1，刷卡；2，门磁，按钮，设备启动，远程；3，报警记录", width = 30, dateFormat = "yyyy-MM-dd")
    private Date openTime;

    /** 学工号 */
    @Excel(name = "学工号")
    private String stuCode;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    private String accCname;

    /** 人员部门 */
    @Excel(name = "人员部门")
    private String depCname;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setSn(String sn) 
    {
        this.sn = sn;
    }

    public String getSn() 
    {
        return sn;
    }
    public void setLockNo(String lockNo) 
    {
        this.lockNo = lockNo;
    }

    public String getLockNo() 
    {
        return lockNo;
    }
    public void setIsIn(Integer isIn) 
    {
        this.isIn = isIn;
    }

    public Integer getIsIn() 
    {
        return isIn;
    }
    public void setIsValid(Integer isValid) 
    {
        this.isValid = isValid;
    }

    public Integer getIsValid() 
    {
        return isValid;
    }
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setOpenTime(Date openTime) 
    {
        this.openTime = openTime;
    }

    public Date getOpenTime() 
    {
        return openTime;
    }
    public void setStuCode(String stuCode) 
    {
        this.stuCode = stuCode;
    }

    public String getStuCode() 
    {
        return stuCode;
    }
    public void setAccCname(String accCname) 
    {
        this.accCname = accCname;
    }

    public String getAccCname() 
    {
        return accCname;
    }
    public void setDepCname(String depCname) 
    {
        this.depCname = depCname;
    }

    public String getDepCname() 
    {
        return depCname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sn", getSn())
            .append("lockNo", getLockNo())
            .append("isIn", getIsIn())
            .append("isValid", getIsValid())
            .append("cardNo", getCardNo())
            .append("createTime", getCreateTime())
            .append("description", getDescription())
            .append("type", getType())
            .append("openTime", getOpenTime())
            .append("stuCode", getStuCode())
            .append("accCname", getAccCname())
            .append("depCname", getDepCname())
            .toString();
    }
}
