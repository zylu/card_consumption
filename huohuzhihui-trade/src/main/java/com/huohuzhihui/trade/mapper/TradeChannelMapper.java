package com.huohuzhihui.trade.mapper;

import java.util.List;
import com.huohuzhihui.trade.domain.TradeChannel;

/**
 * 交易渠道Mapper接口
 * 
 * @author huohuzhihui
 * @date 2020-11-17
 */
public interface TradeChannelMapper 
{
    /**
     * 查询交易渠道
     * 
     * @param id 交易渠道ID
     * @return 交易渠道
     */
    public TradeChannel selectTradeChannelById(Long id);

    /**
     * 查询交易渠道列表
     * 
     * @param tradeChannel 交易渠道
     * @return 交易渠道集合
     */
    public List<TradeChannel> selectTradeChannelList(TradeChannel tradeChannel);

    /**
     * 新增交易渠道
     * 
     * @param tradeChannel 交易渠道
     * @return 结果
     */
    public int insertTradeChannel(TradeChannel tradeChannel);

    /**
     * 修改交易渠道
     * 
     * @param tradeChannel 交易渠道
     * @return 结果
     */
    public int updateTradeChannel(TradeChannel tradeChannel);

    /**
     * 删除交易渠道
     * 
     * @param id 交易渠道ID
     * @return 结果
     */
    public int deleteTradeChannelById(Long id);

    /**
     * 批量删除交易渠道
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTradeChannelByIds(Long[] ids);

    /**
     * 根据支付渠道编码查询支付渠道
     * @param channelCode
     * @return
     */
    public TradeChannel selectTradeChannelByChannelCode(String channelCode);
}
