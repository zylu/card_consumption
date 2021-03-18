package com.huohuzhihui.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;

/**
 * 云存储对象 t_cloud_storage
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
public class CloudStorage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 存储厂商 */
    @Excel(name = "存储厂商")
    private String storageName;

    /** 访问地址 */
    @Excel(name = "访问地址")
    private String storageUrl;

    /** 账号 */
    @Excel(name = "账号")
    private String storageKey;

    /** 秘钥 */
    @Excel(name = "秘钥")
    private String storageSecret;

    /** 空间名 */
    @Excel(name = "空间名")
    private String storageBucket;

    /** 是否是默认存储：0，不是，1，是 */
    @Excel(name = "是否是默认存储：0，不是，1，是")
    private Long isDefault;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStorageName(String storageName) 
    {
        this.storageName = storageName;
    }

    public String getStorageName() 
    {
        return storageName;
    }
    public void setStorageUrl(String storageUrl) 
    {
        this.storageUrl = storageUrl;
    }

    public String getStorageUrl() 
    {
        return storageUrl;
    }
    public void setStorageKey(String storageKey) 
    {
        this.storageKey = storageKey;
    }

    public String getStorageKey() 
    {
        return storageKey;
    }
    public void setStorageSecret(String storageSecret) 
    {
        this.storageSecret = storageSecret;
    }

    public String getStorageSecret() 
    {
        return storageSecret;
    }
    public void setStorageBucket(String storageBucket) 
    {
        this.storageBucket = storageBucket;
    }

    public String getStorageBucket() 
    {
        return storageBucket;
    }
    public void setIsDefault(Long isDefault) 
    {
        this.isDefault = isDefault;
    }

    public Long getIsDefault() 
    {
        return isDefault;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storageName", getStorageName())
            .append("storageUrl", getStorageUrl())
            .append("storageKey", getStorageKey())
            .append("storageSecret", getStorageSecret())
            .append("storageBucket", getStorageBucket())
            .append("isDefault", getIsDefault())
            .toString();
    }
}
