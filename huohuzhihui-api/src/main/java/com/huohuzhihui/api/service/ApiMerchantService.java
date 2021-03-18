package com.huohuzhihui.api.service;

/**
 * 订单Service接口
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
public interface ApiMerchantService
{
    /**
     *
     * @param source 订单来源
     * @param accoundId 消费账户ID
     * @param deviceNo 消费设备编号
     * @param payChannel 支付渠道
     * @param cardNo 卡号
     * @param amount 消费金额
     * @param goodsId 购买商品ID
     * @param tradeNo 交易流水号
     * @return 消费结果
     */
    public int cost(String source,Long accoundId,String deviceNo,String payChannel, String cardNo,String amount,String goodsId,String tradeNo);

    /**
     * 设备号登陆认证
     * @param deviceNo
     * @return
     */
    String loginByDevice(String deviceNo);
}
