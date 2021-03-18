package com.huohuzhihui.account.mapper;

import com.huohuzhihui.account.domain.AccSubsidy;
import com.huohuzhihui.account.domain.TradeVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 补贴Mapper接口
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
public interface AccTradeMapper
{

    /**
     * 查询交易列表
     * 
     * @param tradeVo 交易
     * @return 交易集合
     */
    public List<TradeVo> selectAccTradeList(TradeVo tradeVo);

    /**
     * 查询总收入
     * @param tradeVo
     * @return
     */
    public java.math.BigDecimal getSumRecharge(TradeVo tradeVo);

    /**
     * 查询总支出
     * @param tradeVo
     * @return
     */
    public java.math.BigDecimal getSumCost(TradeVo tradeVo);
}
