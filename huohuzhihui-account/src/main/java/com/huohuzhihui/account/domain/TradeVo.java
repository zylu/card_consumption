package com.huohuzhihui.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeVo {
    private Long userId;

    private Integer type;
    /**交易金额*/
    private BigDecimal amount;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String beginTime;
    private String endTime;
}
