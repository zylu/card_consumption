package com.huohuzhihui.account.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.account.mapper.AccRechargePayMapper;
import com.huohuzhihui.account.domain.AccRechargePay;
import com.huohuzhihui.account.service.IAccRechargePayService;

/**
 * 订单充值支付流水Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
@Service
public class AccRechargePayServiceImpl implements IAccRechargePayService 
{
    @Autowired
    private AccRechargePayMapper accRechargePayMapper;

    /**
     * 查询订单充值支付流水
     * 
     * @param id 订单充值支付流水ID
     * @return 订单充值支付流水
     */
    @Override
    public AccRechargePay selectAccRechargePayById(Long id)
    {
        return accRechargePayMapper.selectAccRechargePayById(id);
    }

    /**
     * 查询订单充值支付流水列表
     * 
     * @param accRechargePay 订单充值支付流水
     * @return 订单充值支付流水
     */
    @Override
    public List<AccRechargePay> selectAccRechargePayList(AccRechargePay accRechargePay)
    {
        return accRechargePayMapper.selectAccRechargePayList(accRechargePay);
    }

    /**
     * 新增订单充值支付流水
     * 
     * @param accRechargePay 订单充值支付流水
     * @return 结果
     */
    @Override
    public int insertAccRechargePay(AccRechargePay accRechargePay)
    {
        accRechargePay.setCreateTime(DateUtils.getNowDate());
        return accRechargePayMapper.insertAccRechargePay(accRechargePay);
    }

    /**
     * 修改订单充值支付流水
     * 
     * @param accRechargePay 订单充值支付流水
     * @return 结果
     */
    @Override
    public int updateAccRechargePay(AccRechargePay accRechargePay)
    {
        accRechargePay.setUpdateTime(DateUtils.getNowDate());
        return accRechargePayMapper.updateAccRechargePay(accRechargePay);
    }

    /**
     * 批量删除订单充值支付流水
     * 
     * @param ids 需要删除的订单充值支付流水ID
     * @return 结果
     */
    @Override
    public int deleteAccRechargePayByIds(Long[] ids)
    {
        return accRechargePayMapper.deleteAccRechargePayByIds(ids);
    }

    /**
     * 删除订单充值支付流水信息
     * 
     * @param id 订单充值支付流水ID
     * @return 结果
     */
    @Override
    public int deleteAccRechargePayById(Long id)
    {
        return accRechargePayMapper.deleteAccRechargePayById(id);
    }
}
