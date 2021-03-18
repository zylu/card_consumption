package com.huohuzhihui.trade.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.huohuzhihui.trade.domain.AccTradeRecord;
import com.huohuzhihui.trade.domain.TradeOrder;
import com.huohuzhihui.trade.domain.TradeOrderChart;
import com.huohuzhihui.trade.domain.TradeOrderReport;
import org.apache.ibatis.annotations.Param;

/**
 * 订单Mapper接口
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
public interface TradeOrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public TradeOrder selectTradeOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param tradeOrder 订单
     * @return 订单集合
     */
    public List<TradeOrder> selectTradeOrderList(TradeOrder tradeOrder);

    /**
     * 新增订单
     * 
     * @param tradeOrder 订单
     * @return 结果
     */
    public int insertTradeOrder(TradeOrder tradeOrder);

    /**
     * 修改订单
     * 
     * @param tradeOrder 订单
     * @return 结果
     */
    public int updateTradeOrder(TradeOrder tradeOrder);

    /**
     * 删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteTradeOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTradeOrderByIds(Long[] ids);

    /**
     * 订单统计
     * @param tradeOrder
     * @return
     */
    BigDecimal getOrderStatistics(TradeOrder tradeOrder);

    /**
     *
     * @param today
     * @return
     */
    Long getUserPayCount(Date today);
    /**
     * * 根据交易流水号查询交易记录
     * @param tradeNo 交易流水号
     * @return 交易记录
     */
    public AccTradeRecord selectTradeOrderByTradeNo(String tradeNo);

    /**
     * 统计商户的营收
     * @return 营收汇总
     */
    public List<TradeOrderReport> getMerchantStatistics(TradeOrder tradeOrder);
    /**
     * 获取当月订单
     * @param type 订单类型
     * @return
     */
    List<TradeOrderChart> getCurrentMothOrder(@Param("type")Integer type);
}
