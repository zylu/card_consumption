package com.huohuzhihui.trade.service;

import java.util.List;
import com.huohuzhihui.trade.domain.AccTradeRecord;

/**
 * 交易记录Service接口
 * 
 * @author huohuzhihui
 * @date 2020-11-14
 */
public interface IAccTradeRecordService 
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
     * 批量删除交易记录
     * 
     * @param ids 需要删除的交易记录ID
     * @return 结果
     */
    public int deleteAccTradeRecordByIds(Long[] ids);

    /**
     * 删除交易记录信息
     * 
     * @param id 交易记录ID
     * @return 结果
     */
    public int deleteAccTradeRecordById(Long id);

    /**
     * 根据账户充值订单号查询交易记录
     * @param rechargeOrderid  账户充值订单号
     * @return 交易记录
     */
    public AccTradeRecord selectAccTradeRecordByAccountRechargeOrderId(Long rechargeOrderid);
}
