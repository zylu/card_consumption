package com.huohuzhihui.trade.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.trade.mapper.TradeChannelMapper;
import com.huohuzhihui.trade.domain.TradeChannel;
import com.huohuzhihui.trade.service.ITradeChannelService;

/**
 * 交易渠道Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-11-17
 */
@Service
public class TradeChannelServiceImpl implements ITradeChannelService 
{
    @Autowired
    private TradeChannelMapper tradeChannelMapper;

    /**
     * 查询交易渠道
     * 
     * @param id 交易渠道ID
     * @return 交易渠道
     */
    @Override
    public TradeChannel selectTradeChannelById(Long id)
    {
        return tradeChannelMapper.selectTradeChannelById(id);
    }

    /**
     * 查询交易渠道列表
     * 
     * @param tradeChannel 交易渠道
     * @return 交易渠道
     */
    @Override
    public List<TradeChannel> selectTradeChannelList(TradeChannel tradeChannel)
    {
        return tradeChannelMapper.selectTradeChannelList(tradeChannel);
    }

    /**
     * 新增交易渠道
     * 
     * @param tradeChannel 交易渠道
     * @return 结果
     */
    @Override
    public int insertTradeChannel(TradeChannel tradeChannel)
    {
        tradeChannel.setCreateTime(DateUtils.getNowDate());
        return tradeChannelMapper.insertTradeChannel(tradeChannel);
    }

    /**
     * 修改交易渠道
     * 
     * @param tradeChannel 交易渠道
     * @return 结果
     */
    @Override
    public int updateTradeChannel(TradeChannel tradeChannel)
    {
        tradeChannel.setUpdateTime(DateUtils.getNowDate());
        return tradeChannelMapper.updateTradeChannel(tradeChannel);
    }

    /**
     * 批量删除交易渠道
     * 
     * @param ids 需要删除的交易渠道ID
     * @return 结果
     */
    @Override
    public int deleteTradeChannelByIds(Long[] ids)
    {
        return tradeChannelMapper.deleteTradeChannelByIds(ids);
    }

    /**
     * 删除交易渠道信息
     * 
     * @param id 交易渠道ID
     * @return 结果
     */
    @Override
    public int deleteTradeChannelById(Long id)
    {
        return tradeChannelMapper.deleteTradeChannelById(id);
    }
}
