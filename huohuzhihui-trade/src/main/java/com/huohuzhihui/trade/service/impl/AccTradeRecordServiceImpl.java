package com.huohuzhihui.trade.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.trade.mapper.AccTradeRecordMapper;
import com.huohuzhihui.trade.domain.AccTradeRecord;
import com.huohuzhihui.trade.service.IAccTradeRecordService;

/**
 * 交易记录Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-11-14
 */
@Service
public class AccTradeRecordServiceImpl implements IAccTradeRecordService 
{
    @Autowired
    private AccTradeRecordMapper accTradeRecordMapper;

    /**
     * 查询交易记录
     * 
     * @param id 交易记录ID
     * @return 交易记录
     */
    @Override
    public AccTradeRecord selectAccTradeRecordById(Long id)
    {
        return accTradeRecordMapper.selectAccTradeRecordById(id);
    }

    /**
     * 查询交易记录列表
     * 
     * @param accTradeRecord 交易记录
     * @return 交易记录
     */
    @Override
    public List<AccTradeRecord> selectAccTradeRecordList(AccTradeRecord accTradeRecord)
    {
        return accTradeRecordMapper.selectAccTradeRecordList(accTradeRecord);
    }

    /**
     * 新增交易记录
     * 
     * @param accTradeRecord 交易记录
     * @return 结果
     */
    @Override
    public int insertAccTradeRecord(AccTradeRecord accTradeRecord)
    {
        accTradeRecord.setCreateTime(DateUtils.getNowDate());
        return accTradeRecordMapper.insertAccTradeRecord(accTradeRecord);
    }

    /**
     * 修改交易记录
     * 
     * @param accTradeRecord 交易记录
     * @return 结果
     */
    @Override
    public int updateAccTradeRecord(AccTradeRecord accTradeRecord)
    {
        accTradeRecord.setUpdateTime(DateUtils.getNowDate());
        return accTradeRecordMapper.updateAccTradeRecord(accTradeRecord);
    }

    /**
     * 批量删除交易记录
     * 
     * @param ids 需要删除的交易记录ID
     * @return 结果
     */
    @Override
    public int deleteAccTradeRecordByIds(Long[] ids)
    {
        return accTradeRecordMapper.deleteAccTradeRecordByIds(ids);
    }

    /**
     * 删除交易记录信息
     * 
     * @param id 交易记录ID
     * @return 结果
     */
    @Override
    public int deleteAccTradeRecordById(Long id)
    {
        return accTradeRecordMapper.deleteAccTradeRecordById(id);
    }

    @Override
    public AccTradeRecord selectAccTradeRecordByAccountRechargeOrderId(Long rechargeOrderid) {
        AccTradeRecord AccTradeRecord = accTradeRecordMapper.selectAccTradeRecordByAccountRechargeOrderId(rechargeOrderid);
        return AccTradeRecord;
    }
}
