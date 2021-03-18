package com.huohuzhihui.merchant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huohuzhihui.common.annotation.Excel;
import com.huohuzhihui.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 调课对象 sc_adjust_course
 * 
 * @author Zhihe.Li
 * @date 2020-08-15
 */
@Data
public class MerCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    @Excel(name = "订单号")
    private String orderNo;

    @Excel(name = "消费金额")
    private BigDecimal amount;

    @Excel(name = "人员ID")
    private Long userId;

    @Excel(name = "设备号")
    private String posNo;

    @Excel(name = "所属商户")
    private Long merchantId;

    @Excel(name = "卡号")
    private String cardNo;

    @Excel(name = "消费时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String userName;
    private String mobile;

}
