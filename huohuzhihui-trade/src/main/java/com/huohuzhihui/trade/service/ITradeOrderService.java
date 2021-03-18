package com.huohuzhihui.trade.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.huohuzhihui.trade.domain.AccTradeRecord;
import com.huohuzhihui.trade.domain.TradeOrder;
import com.huohuzhihui.trade.domain.TradeOrderChart;
import com.huohuzhihui.trade.domain.TradeOrderReport;

/**
 * 订单Service接口
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
public interface ITradeOrderService 
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
     * @param type 类型：0充值1消费
     * @param userId 用户ID
     * @param accountName 账户所属人员姓名
     * @param amount 金额
     * @param source 订单来源：1、手机APP；2、自助终端;3、微信小程序；4:、管理后台
     * @param deviceId 设备ID
     * @param channelCode 支付渠道编码
     * @param createBy 订单创建人
     * @param cardNo 一卡通卡号
     * @param tradeNo 第三方交易流水号
     * @return 结果
     */
    public int insertTradeOrder(int type, Long userId,String userName, BigDecimal amount, String source, Long deviceId, String channelCode,String createBy,String cardNo,String tradeNo);

    /**
     * 修改订单
     * 
     * @param tradeOrder 订单
     * @return 结果
     */
    public int updateTradeOrder(TradeOrder tradeOrder);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    public int deleteTradeOrderByIds(Long[] ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteTradeOrderById(Long id);


    /**
     * 订单统计
     * @param tradeOrder
     * @return
     */
    BigDecimal getOrderStatistics(TradeOrder tradeOrder);

    /**
     *  获取当日消费人数
     * @param today
     * @return
     */
    Long getUserPayCount(Date today);

    /** 一卡通支付通知
     * @param tradeNo 交易流水号
     * @return
     */
    public int cardPayNotify(String tradeNo,int status);


    /**
     * 获取商户营收报表
     * @param merchantId 商户ID
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 报表
     */
    public List<TradeOrderReport> getOrderStatisticsByMerchantId(Long merchantId, String beginDate, String endDate);

    /**
     * 获取当月订单
     * @param type 订单类型
     * @return
     */
    List<TradeOrderChart> getCurrentMothOrder(Integer type);

    /**
     * 根据第三方交易流水号查询订单支付记录
     * @param tradeNo
     * @return
     */
    public AccTradeRecord selectAccTradeRecordByTradeNo(String tradeNo);


    public void tradeSuccess(Long tradeOrderId);

    public void tradeSuccess(String tradeNo);
}
