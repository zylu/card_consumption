package com.huohuzhihui.merchant.service;



import com.huohuzhihui.merchant.domain.MerCost;
import com.huohuzhihui.merchant.domain.MerCostReport;
import com.huohuzhihui.merchant.vo.PosCost;
import com.huohuzhihui.merchant.vo.PosInfo;
import com.huohuzhihui.merchant.vo.PosUser;
import com.huohuzhihui.trade.domain.TradeOrderReport;

import java.util.List;

/**
 * 
 * 
 * @author mzy
 * @email 1992lcg@163.com
 * @date ${datetime}
 */
public interface IMerCostService {
    /**
     * 获取人员信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
	public List<PosUser> findCompanyMemberList(Integer pageIndex, Integer pageSize);

    /**
     * 消费
     * @param machineNumber 设备号
     * @param cardNumber
     * @param deductionType 10-固定模式40-菜品模式50-时段模式60-单价模式
     * @param money
     * @param consumptionType  10-刷脸消费20-刷卡消费30-二维码消费40-支付宝消费50-微信消费
     * @param outTradeNo 消费机上传的交易订单号
     * @return
     */
	public PosCost modifyBalance(String machineNumber, String cardNumber, int deductionType, String money, int consumptionType, String outTradeNo);

    /**
     * 获取心跳
     * @param machine_number 设备号
     * @return
     */
    public PosInfo reportDeviceStatus(String machine_number);

 /*   *//**
     * 根据卡号查询余额
     * @param cardNumber
     * @return
     *//*
    public PosUser getFullRyxxByCardNo(String cardNumber);

    *//**
     * 查询消费记录
     * @param cardNumber
     * @param pageIndex
     * @param pageSize
     * @return
     *//*
    public Map findConsumeRecordList(String cardNumber, int pageIndex, int pageSize);

    *//**
     * 消费统计
     * @return
     *//*
    public PosConsumptionStatistics getConsumptionStatistics(String mechineNumber);*/



    /**
     * 查询商户消费列表
     *
     * @param merCost 商户消费
     * @return 商户消费集合
     */
    public List<MerCost> selectMerCostList(MerCost merCost);

    /**
     * 新增商户消费
     *
     * @param merCost 商户消费
     * @return 结果
     */
    public int insertMerCost(MerCost merCost);


    public List<MerCostReport> getOrderStatisticsByMerchantId(Long merchantId, String beginDate, String endDate);
}
