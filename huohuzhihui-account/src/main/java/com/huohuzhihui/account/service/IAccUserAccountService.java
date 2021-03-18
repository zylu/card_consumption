package com.huohuzhihui.account.service;

import com.huohuzhihui.account.vo.SubsidyVo;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.trade.domain.TradeOrder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户Service接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface IAccUserAccountService 
{
    /**
     * 查询账户
     * 
     * @param id 账户ID
     * @return 账户
     */
    public Account selectAccUserAccountById(Long id);

    /**
     * 查询账户列表
     * 
     * @param accUserAccount 账户
     * @return 账户集合
     */
    public List<Account> selectAccUserAccountList(Account accUserAccount);

    /**
     * 新增账户
     * 
     * @param accUserAccount 账户
     * @return 结果
     */
    public int insertAccUserAccount(Account accUserAccount);

    /**
     * 修改账户
     * 
     * @param accUserAccount 账户
     * @return 结果
     */
    public int updateAccUserAccount(Account accUserAccount);

    /**
     * 批量删除账户
     * 
     * @param ids 需要删除的账户ID
     * @return 结果
     */
    public int deleteAccUserAccountByIds(Long[] ids);

    /**
     * 删除账户信息
     * 
     * @param id 账户ID
     * @return 结果
     */
    public int deleteAccUserAccountById(Long id);

    /**
     * 冻结账户
     * @param ids 需要冻结账户的ID
     * @return 结果
     */
    public int freezeUserAccountByIds(Long[] ids);

    /**
     * 解冻账户
     * @param ids
     * @return
     */
    int unfreezeUserAccountByIds(Long[] ids);

    /**
     * 注销账户
     * @param ids
     * @return
     */
    int cancelUserAccountByIds(Long[] ids);


    /**
     * 账户充值
     * @param accountId 账户ID
     * @param amount 金额
     * @param source 来源
     * @param channelCode 支付渠道编码
     * @param createBy 创建人
     * @return
     */
    int recharge(Long accountId,  BigDecimal amount, String source,  String channelCode , String channelName ,String createBy);

    /**
     * 账户统计
     * @param account
     * @return
     */
    Long getUserAccountStatistics(Account account);

    /**
     * 账户总余额
     * @return
     */
    BigDecimal getTotalUserBalance();

    public Account selectAccUserAccountByUserId(Long userId);

    /**
     * 账户补贴
     * @param id
     * @param amount
     * @return
     */
    public int subsidy(Long id, BigDecimal amount,String source);

    /**
     * 查询补贴记录
     * @param subsidyVo
     * @return
     */
    public List<TradeOrder> findSubsidyList(SubsidyVo subsidyVo);

    void updateAccUserAccountByUserId(Account accout);

    /**
     *
     * @param source 订单来源
     * @param accoundId 消费账户ID
     * @param deviceNo 消费设备编号
     * @param payChannel 支付渠道
     * @param cardNo 卡号
     * @param amount 消费金额
     * @param goodsId 购买商品ID
     * @param tradeNo 交易流水号
     * @return 消费结果
     */
    /*public int cost(String source,Long accoundId,String deviceNo,String payChannel, String cardNo,String amount,String goodsId,String tradeNo);*/



}
