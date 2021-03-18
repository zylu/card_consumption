package com.huohuzhihui.common.core.domain.entity;

import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商户设备对象 mer_device
 * 
 * @author zylu
 * @date 2020-11-14
 */
public class LoginMerDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String no;

    /** 设备序列号 */
    @Excel(name = "设备序列号")
    private String sn;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String ip;

    /** 端口号 */
    @Excel(name = "端口号")
    private String port;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceTypeId;

    /**所属商户ID*/
    private Long merchantId;
    /**所属商户名称*/
    private String merchantName;

    /** 备注信息 */
    private String remarks;

    /** 删除标记 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNo(String no) 
    {
        this.no = no;
    }

    public String getNo() 
    {
        return no;
    }
    public void setSn(String sn) 
    {
        this.sn = sn;
    }

    public String getSn() 
    {
        return sn;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setPort(String port) 
    {
        this.port = port;
    }

    public String getPort() 
    {
        return port;
    }
    public void setDeviceTypeId(String deviceTypeId) 
    {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeId() 
    {
        return deviceTypeId;
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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("no", getNo())
            .append("sn", getSn())
            .append("ip", getIp())
            .append("port", getPort())
            .append("deviceTypeId", getDeviceTypeId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
