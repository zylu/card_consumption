package com.huohuzhihui.door.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

/**
 * 门禁对象 t_door
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
public class Door extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 门名称 */
    @Excel(name = "门名称")
    private String name;

    /** 删除标记 */
    private Integer delFlag;

    /** 门禁控制器锁号id */
    @Excel(name = "门禁控制器锁号id")
    private Long accessControlLockId;

    /** 所属分组 */
    @Excel(name = "所属分组")
    private Long doorGroupId;

    /** 所属区域 */
    @Excel(name = "所属区域")
    private String doorAreaId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
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
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }
    public void setAccessControlLockId(Long accessControlLockId) 
    {
        this.accessControlLockId = accessControlLockId;
    }

    public Long getAccessControlLockId() 
    {
        return accessControlLockId;
    }
    public void setDoorGroupId(Long doorGroupId) 
    {
        this.doorGroupId = doorGroupId;
    }

    public Long getDoorGroupId() 
    {
        return doorGroupId;
    }
    public void setDoorAreaId(String doorAreaId) 
    {
        this.doorAreaId = doorAreaId;
    }

    public String getDoorAreaId() 
    {
        return doorAreaId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("remark", getRemark())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .append("accessControlLockId", getAccessControlLockId())
            .append("doorGroupId", getDoorGroupId())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("doorAreaId", getDoorAreaId())
            .toString();
    }
}
