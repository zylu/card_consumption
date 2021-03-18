package com.huohuzhihui.trade.service;

import java.util.List;
import com.huohuzhihui.trade.domain.TradeChannel;

/**
 * 交易渠道Service接口
 * 
 * @author huohuzhihui
 * @date 2020-11-17
 */
public interface ITradeChannelService 
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
     * 批量删除交易渠道
     * 
     * @param ids 需要删除的交易渠道ID
     * @return 结果
     */
    public int deleteTradeChannelByIds(Long[] ids);

    /**
     * 删除交易渠道信息
     * 
     * @param id 交易渠道ID
     * @return 结果
     */
    public int deleteTradeChannelById(Long id);
}
