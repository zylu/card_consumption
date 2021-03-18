package com.huohuzhihui.trade.service.impl;

import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.common.utils.SnowFlakeUtil;
import com.huohuzhihui.common.utils.StringUtils;
import com.huohuzhihui.trade.domain.*;
import com.huohuzhihui.trade.mapper.AccTradeRecordMapper;
import com.huohuzhihui.trade.mapper.TradeChannelMapper;
import com.huohuzhihui.trade.mapper.TradeOrderMapper;
import com.huohuzhihui.trade.service.ITradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
@Service
public class TradeOrderServiceImpl implements ITradeOrderService 
{
    @Autowired
    private TradeOrderMapper tradeOrderMapper;
    @Autowired
    private AccTradeRecordMapper accTradeRecordMapper;
    @Autowired
    private TradeChannelMapper tradeChannelMapper;


    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public TradeOrder selectTradeOrderById(Long id)
    {
        return tradeOrderMapper.selectTradeOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param tradeOrder 订单
     * @return 订单
     */
    @Override
    public List<TradeOrder> selectTradeOrderList(TradeOrder tradeOrder)
    {
        return tradeOrderMapper.selectTradeOrderList(tradeOrder);
    }

    @Override
    public int insertTradeOrder(int type, Long userId,String userName, BigDecimal amount, String source, Long deviceId, String channelCode,String createBy,String cardNo,String tradeNo) {
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setType(type);
        tradeOrder.setAccountId(userId);
        tradeOrder.setAmount(amount);
        if("XJ".equalsIgnoreCase(channelCode) || "BUTIE".equalsIgnoreCase(channelCode)){//现金
            tradeOrder.setStatus(1);
        }else{
            tradeOrder.setStatus(2);
        }
        tradeOrder.setSource(source);
        tradeOrder.setDeviceId(deviceId);
        tradeOrder.setCreateTime(DateUtils.getNowDate());
        tradeOrder.setCreateBy(createBy);

        this.tradeOrderMapper.insertTradeOrder(tradeOrder);

        //插入交易记录
        AccTradeRecord accTradeRecord = new AccTradeRecord();
        accTradeRecord.setType(type);
        accTradeRecord.setTradeNo(tradeNo);
        accTradeRecord.setTradeOrderId(tradeOrder.getId());
        accTradeRecord.setCreateTime(DateUtils.getNowDate());
        accTradeRecord.setAccountId(userId);
        accTradeRecord.setAccountName(userName);
        accTradeRecord.setAmount(amount);
        accTradeRecord.setChannelCode(channelCode);
        accTradeRecord.setCardNo(cardNo);

        if(!StringUtils.isEmpty(channelCode)){
            TradeChannel tradeChannel = tradeChannelMapper.selectTradeChannelByChannelCode(channelCode);
            accTradeRecord.setChannelName(tradeChannel.getChannelName());
        }

        if("XJ".equalsIgnoreCase(channelCode) || "BUTIE".equalsIgnoreCase(channelCode)){//线下现金
            accTradeRecord.setStatus(1);
            accTradeRecord.setTradeTime(DateUtils.getNowDate());
        }else{
            accTradeRecord.setStatus(2);
        }
        return accTradeRecordMapper.insertAccTradeRecord(accTradeRecord);
    }



    /**
     * 修改订单
     * 
     * @param tradeOrder 订单
     * @return 结果
     */
    @Override
    public int updateTradeOrder(TradeOrder tradeOrder)
    {
        tradeOrder.setUpdateTime(DateUtils.getNowDate());
        return tradeOrderMapper.updateTradeOrder(tradeOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    @Override
    public int deleteTradeOrderByIds(Long[] ids)
    {
        return tradeOrderMapper.deleteTradeOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteTradeOrderById(Long id)
    {
        return tradeOrderMapper.deleteTradeOrderById(id);
    }

    @Override
    public BigDecimal getOrderStatistics(TradeOrder tradeOrder) {
        return tradeOrderMapper.getOrderStatistics(tradeOrder);
    }

    @Override
    public Long getUserPayCount(Date today) {
        return tradeOrderMapper.getUserPayCount(today);
    }
    @Override
    public int cardPayNotify(String tradeNo, int status) {
        AccTradeRecord accTradeRecord = this.accTradeRecordMapper.selectAccTradeRecordByTradeNo(tradeNo);
        if(accTradeRecord.getStatus()==2){
            accTradeRecord.setStatus(status);
            accTradeRecord.setUpdateTime(DateUtils.getNowDate());
            accTradeRecord.setUpdateBy("系统");
            accTradeRecordMapper.updateAccTradeRecord(accTradeRecord);

            //更新订单状态
            long orderId = accTradeRecord.getTradeOrderId();
            TradeOrder tradeOrder = this.tradeOrderMapper.selectTradeOrderById(orderId);
            if(status==1){
                tradeOrder.setStatus(1);
            }
            if(status==2){
                tradeOrder.setStatus(2);
            }
            tradeOrder.setUpdateTime(DateUtils.getNowDate());
            tradeOrder.setUpdateBy("系统");
            return tradeOrderMapper.updateTradeOrder(tradeOrder);
        }
        return 0;
    }

    @Override
    public List<TradeOrderReport> getOrderStatisticsByMerchantId(Long merchantId, String beginDate, String endDate) {
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setBeginTime(beginDate);
        tradeOrder.setEndTime(endDate);
        tradeOrder.setMerchantId(merchantId);
        return tradeOrderMapper.getMerchantStatistics(tradeOrder);
    }

    @Override
    public List<TradeOrderChart> getCurrentMothOrder(Integer type) {
        return tradeOrderMapper.getCurrentMothOrder(type);
    }

    @Override
    public AccTradeRecord selectAccTradeRecordByTradeNo(String tradeNo) {
        return this.tradeOrderMapper.selectTradeOrderByTradeNo(tradeNo);
    }


    @Override
    public void tradeSuccess(Long tradeOrderId) {
        AccTradeRecord accTradeRecord = accTradeRecordMapper.selectAccTradeRecordByOrderId(tradeOrderId);
        if(accTradeRecord.getStatus()==2){
            accTradeRecord.setStatus(1);
            accTradeRecord.setUpdateTime(DateUtils.getNowDate());
            accTradeRecord.setUpdateBy("系统");
            accTradeRecordMapper.updateAccTradeRecord(accTradeRecord);

            //更新订单状态
            TradeOrder tradeOrder = this.tradeOrderMapper.selectTradeOrderById(tradeOrderId);
            if(tradeOrder.getStatus()==0){
                tradeOrder.setStatus(1);
            }
            tradeOrder.setUpdateTime(DateUtils.getNowDate());
            tradeOrder.setUpdateBy("系统");
            tradeOrderMapper.updateTradeOrder(tradeOrder);
        }
    }

    @Override
    public void tradeSuccess(String tradeNo) {
        AccTradeRecord accTradeRecord = accTradeRecordMapper.selectAccTradeRecordByTradeNo(tradeNo);
        if(accTradeRecord.getStatus()==2){
            accTradeRecord.setStatus(1);
            accTradeRecord.setUpdateTime(DateUtils.getNowDate());
            accTradeRecord.setUpdateBy("系统");
            accTradeRecordMapper.updateAccTradeRecord(accTradeRecord);

            //更新订单状态
            TradeOrder tradeOrder = this.tradeOrderMapper.selectTradeOrderById(accTradeRecord.getTradeOrderId());
            if(tradeOrder.getStatus()==0){
                tradeOrder.setStatus(1);
            }
            tradeOrder.setUpdateTime(DateUtils.getNowDate());
            tradeOrder.setUpdateBy("系统");
            tradeOrderMapper.updateTradeOrder(tradeOrder);
        }
    }
}
