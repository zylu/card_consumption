package com.huohuzhihui.api.service;

import com.huohuzhihui.account.domain.TradeVo;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.core.domain.entity.Card;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.trade.domain.TradeOrder;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单Service接口
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
public interface ApiAccountService
{

    public Account getAccountByPhonenumber(String phonenumber);

    public Account getAccountById(Long accountId);

    public Account getAccountByUserId(Long userId);

    public SysUser getUserByPhonenumber(String phonenumber);

    public SysUser getUserById(Long userId);

    public Card findCardByAccoutId(Long accountId);

    public int lossCard(Long cardId,String updateBy);

    public int unlossCard(Long cardId,String updateBy);

    public int  resetPassword(String userName, String newPassword);


    public BigDecimal getSumTrade(TradeOrder tradeOrder);

    public List<TradeOrder> findAccountTradeRecordList(TradeOrder tradeOrder);

    public int openVirtualCard(Account accUserAccount);

    public String getQrCodeStr(String cardNo);

    public String recharge(Account accUserAccount, BigDecimal amount, String source, String channelCode, String openId, String ip);

    public String wxPayNotify(HttpServletRequest request);

    public String webPay(Long userId, String amount,  String openId, String ip);

    /**
     * 查询交易记录
     * @param tradeVo
     * @return
     */
    public List<TradeVo> findTradeList(TradeVo tradeVo);

    /**
     * 总收入金额
     * @param tradeVo
     * @return
     */
    public java.math.BigDecimal getSumRecharge(TradeVo tradeVo);

    /**
     * 总支出金额
     * @param tradeVo
     * @return
     */
    public java.math.BigDecimal getSumCost(TradeVo tradeVo);
}
