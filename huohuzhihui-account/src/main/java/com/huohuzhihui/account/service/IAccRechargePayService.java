package com.huohuzhihui.account.service;

import java.util.List;
import com.huohuzhihui.account.domain.AccRechargePay;

/**
 * 订单充值支付流水Service接口
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
public interface IAccRechargePayService 
{
    /**
     * 查询订单充值支付流水
     * 
     * @param id 订单充值支付流水ID
     * @return 订单充值支付流水
     */
    public AccRechargePay selectAccRechargePayById(Long id);

    /**
     * 查询订单充值支付流水列表
     * 
     * @param accRechargePay 订单充值支付流水
     * @return 订单充值支付流水集合
     */
    public List<AccRechargePay> selectAccRechargePayList(AccRechargePay accRechargePay);

    /**
     * 新增订单充值支付流水
     * 
     * @param accRechargePay 订单充值支付流水
     * @return 结果
     */
    public int insertAccRechargePay(AccRechargePay accRechargePay);

    /**
     * 修改订单充值支付流水
     * 
     * @param accRechargePay 订单充值支付流水
     * @return 结果
     */
    public int updateAccRechargePay(AccRechargePay accRechargePay);

    /**
     * 批量删除订单充值支付流水
     * 
     * @param ids 需要删除的订单充值支付流水ID
     * @return 结果
     */
    public int deleteAccRechargePayByIds(Long[] ids);

    /**
     * 删除订单充值支付流水信息
     * 
     * @param id 订单充值支付流水ID
     * @return 结果
     */
    public int deleteAccRechargePayById(Long id);
}
