package com.huohuzhihui.trade.mapper;

import java.util.List;
import com.huohuzhihui.trade.domain.AccTradeRecord;

/**
 * 交易记录Mapper接口
 * 
 * @author huohuzhihui
 * @date 2020-11-14
 */
public interface AccTradeRecordMapper 
{
    /**
     * 查询交易记录
     * 
     * @param id 交易记录ID
     * @return 交易记录
     */
    public AccTradeRecord selectAccTradeRecordById(Long id);

    /**
     * 查询交易记录列表
     * 
     * @param accTradeRecord 交易记录
     * @return 交易记录集合
     */
    public List<AccTradeRecord> selectAccTradeRecordList(AccTradeRecord accTradeRecord);

    /**
     * 新增交易记录
     * 
     * @param accTradeRecord 交易记录
     * @return 结果
     */
    public int insertAccTradeRecord(AccTradeRecord accTradeRecord);

    /**
     * 修改交易记录
     * 
     * @param accTradeRecord 交易记录
     * @return 结果
     */
    public int updateAccTradeRecord(AccTradeRecord accTradeRecord);

    /**
     * 删除交易记录
     * 
     * @param id 交易记录ID
     * @return 结果
     */
    public int deleteAccTradeRecordById(Long id);

    /**
     * 批量删除交易记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccTradeRecordByIds(Long[] ids);

    /**
     * 根据账户充值订单号查询交易记录
     * @param rechargeOrderid 账户充值订单号
     * @return 交易记录
     */
    public AccTradeRecord selectAccTradeRecordByAccountRechargeOrderId(Long rechargeOrderid);

    /**
     * 根据订单号查询
     * @param tradeNo
     * @return
     */
    public AccTradeRecord selectAccTradeRecordByTradeNo(String tradeNo);

    public AccTradeRecord selectAccTradeRecordByOrderId(Long tradeOrderId);
}
