package com.huohuzhihui.account.service.impl;

import com.huohuzhihui.account.constant.AccountConstants;
import com.huohuzhihui.account.mapper.AccCardMapper;
import com.huohuzhihui.account.mapper.AccUserAccountMapper;
import com.huohuzhihui.account.service.IAccUserAccountService;
import com.huohuzhihui.account.vo.SubsidyVo;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.core.domain.entity.Card;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.common.utils.SecurityUtils;
import com.huohuzhihui.common.utils.uuid.IdUtils;
import com.huohuzhihui.trade.constant.TradeConstants;
import com.huohuzhihui.trade.domain.AccTradeRecord;
import com.huohuzhihui.trade.domain.TradeOrder;
import com.huohuzhihui.trade.service.ITradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户Service业务层处理
 *
 * @author zylu
 * @date 2020-11-14
 */
@Service
public class AccUserAccountServiceImpl implements IAccUserAccountService
{
    @Autowired
    private AccUserAccountMapper accUserAccountMapper;
    @Autowired
    private ITradeOrderService iTradeOrderService;
    @Autowired
    private AccCardMapper accCardMapper;


    /**
     * 查询账户
     *
     * @param id 账户ID
     * @return 账户
     */
    @Override
    public Account selectAccUserAccountById(Long id)
    {
        return accUserAccountMapper.selectAccUserAccountById(id);
    }

    /**
     * 查询账户列表
     *
     * @param accUserAccount 账户
     * @return 账户
     */
    @Override
    public List<Account> selectAccUserAccountList(Account accUserAccount)
    {
        return accUserAccountMapper.selectAccUserAccountList(accUserAccount);
    }

    /**
     * 新增账户
     *
     * @param accUserAccount 账户
     * @return 结果
     */
    @Override
    public int insertAccUserAccount(Account accUserAccount)
    {
        accUserAccount.setCreateTime(DateUtils.getNowDate());
        return accUserAccountMapper.insertAccUserAccount(accUserAccount);
    }

    /**
     * 修改账户
     *
     * @param accUserAccount 账户
     * @return 结果
     */
    @Override
    public int updateAccUserAccount(Account accUserAccount)
    {
        accUserAccount.setUpdateTime(DateUtils.getNowDate());
        return accUserAccountMapper.updateAccUserAccount(accUserAccount);
    }

    /**
     * 批量删除账户
     *
     * @param ids 需要删除的账户ID
     * @return 结果
     */
    @Override
    public int deleteAccUserAccountByIds(Long[] ids)
    {
        return accUserAccountMapper.deleteAccUserAccountByIds(ids);
    }

    /**
     * 删除账户信息
     *
     * @param id 账户ID
     * @return 结果
     */
    @Override
    public int deleteAccUserAccountById(Long id)
    {
        return accUserAccountMapper.deleteAccUserAccountById(id);
    }

    /**
     * 冻结账户
     * @param ids 需要冻结账户的ID
     * @return
     */
    @Override
    public int freezeUserAccountByIds(Long[] ids) {

        return accUserAccountMapper.updateStatusByIds(1,DateUtils.getNowDate(),SecurityUtils.getUsername(),ids);
    }

    /**
     * 解冻账户
     * @param ids
     * @return
     */
    @Override
    public int unfreezeUserAccountByIds(Long[] ids) {
        return accUserAccountMapper.updateStatusByIds(0,DateUtils.getNowDate(),SecurityUtils.getUsername(),ids);
    }

    @Override
    public int cancelUserAccountByIds(Long[] ids) {
        return accUserAccountMapper.updateStatusByIds(2,DateUtils.getNowDate(),SecurityUtils.getUsername(),ids);
    }

    @Override
    public int recharge( Long userId,  BigDecimal amount, String source,  String channelCode , String channelName,String createBy) {
        Account accUserAccount = accUserAccountMapper.selectAccUserAccountByUserId(userId);
        if(accUserAccount != null) {
            String userName = accUserAccount.getUserName();
            Long deviceId = null;
            String cardNo = "";
            String tradeNo = IdUtils.fastSimpleUUID();
            iTradeOrderService.insertTradeOrder(AccountConstants.OPER_RECHARGE, userId, userName, amount, source, deviceId, channelCode, createBy,cardNo,tradeNo);

            //更新当前账户余额
            Account updateModel = new Account();
            updateModel.setId(accUserAccount.getId());
            updateModel.setVersion(accUserAccount.getVersion());
            updateModel.setBalance(amount);//增加的金额
            updateModel.setUpdateBy(SecurityUtils.getUsername());
            updateModel.setUpdateTime(DateUtils.getNowDate());
            return accUserAccountMapper.recharge(updateModel);
        }
        return 0;
    }

    @Override
    public Long getUserAccountStatistics(Account account) {
        return accUserAccountMapper.getUserAccountStatistics(account);
    }

    @Override
    public BigDecimal getTotalUserBalance() {
        return accUserAccountMapper.getTotalUserBalance();
    }

    @Override
    public Account selectAccUserAccountByUserId(Long userId) {
        return accUserAccountMapper.selectAccUserAccountByUserId(userId);
    }

    @Override
    public int subsidy(Long userId, BigDecimal amount,String source) {
        Account accUserAccount = accUserAccountMapper.selectAccUserAccountByUserId(userId);
        String accountName = accUserAccount.getUserName();
        Long deviceId = null;
        String cardNo = "";
        String tradeNo = IdUtils.fastSimpleUUID();
        iTradeOrderService.insertTradeOrder(AccountConstants.OPER_SUBSIDY, userId, accountName, amount, source, deviceId, "BUTIE", SecurityUtils.getUsername(),cardNo,tradeNo);

        //更新当前账户余额
        Account updateModel = new Account();
        updateModel.setId(accUserAccount.getId());
        updateModel.setVersion(accUserAccount.getVersion());
        updateModel.setBalance(amount);//增加的金额
        updateModel.setUpdateBy(SecurityUtils.getUsername());
        updateModel.setUpdateTime(DateUtils.getNowDate());
        return accUserAccountMapper.recharge(updateModel);
    }

    @Override
    public List<TradeOrder> findSubsidyList(SubsidyVo subsidyVo) {
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setBeginTime(subsidyVo.getBeginTime());
        tradeOrder.setEndTime(subsidyVo.getEndTime());
        tradeOrder.setMobile(subsidyVo.getMobile());
        tradeOrder.setType(AccountConstants.OPER_SUBSIDY);
        tradeOrder.setStatus(1);
        List<TradeOrder> tradeOrderList = iTradeOrderService.selectTradeOrderList(tradeOrder);

        return tradeOrderList;

    }

    @Override
    public void updateAccUserAccountByUserId(Account accout) {
        this.accUserAccountMapper.updateAccUserAccountByUserId(accout);
    }

   /* @Override
    public int cost(String source, Long accoundId, String deviceNo, String payChannel, String cardNo, String amount, String goodsId, String tradeNo) {
        try{
            //验证设备合法性
            MerDevice merDevice = merDeviceMapper.selectMerDeviceByNo(deviceNo);
            if(merDevice==null){
                return TradeConstants.DEVICE_NOT_FOUND.getCode();
            }

            //验证此订单是否已经支付了
            AccTradeRecord accTradeRecord = iTradeOrderService.selectAccTradeRecordByTradeNo(tradeNo);
            if(accTradeRecord!=null){
                if(accTradeRecord.getStatus()==1){
                    return TradeConstants.TRADE_SUCCESS.getCode();
                }
            }
            //验证账户合法性
            Account accUserAccount = accUserAccountMapper.selectAccUserAccountById(accoundId);
            if(accUserAccount==null){
                return TradeConstants.ACCOUNT_NOT_FOUND.getCode();
            }
            if(accUserAccount.getStatus()!=0){
                return TradeConstants.ACCOUNT_STATUS_EXCEPTION.getCode();
            }
            //验证卡是否合法
            Card accCard = accCardMapper.selectAccCardByCardNo(cardNo);
            if(accCard==null){
                return TradeConstants.CARD_NOT_FOUND.getCode();
            }
            if(accCard.getStatus()!=0){
                return TradeConstants.CARD_STATUS_EXCEPTION.getCode();
            }
            //验证余额是否充足
            if(accUserAccount.getBalance().compareTo(new BigDecimal(amount))==-1){
                return TradeConstants.BALANCE_NOT_ENOUTH.getCode();
            }
            //验证商品库存是否充足
            //扣费
            if(accTradeRecord!=null){
                //更新当前账户余额
                accUserAccount.setBalance(new BigDecimal(amount));//消费的金额
                accUserAccount.setUpdateBy(accUserAccount.getUserName());
                accUserAccount.setUpdateTime(DateUtils.getNowDate());
                accUserAccountMapper.updateAccUserAccount(accUserAccount);

                this.iTradeOrderService.tradeSuccess(tradeNo);
            }else{
                //插入订单
                iTradeOrderService.insertTradeOrder(AccountConstants.OPER_CONSUME, accoundId, accUserAccount.getUserName(), new BigDecimal(amount), source, merDevice.getId(), payChannel, "系统",cardNo,tradeNo);
                //更新当前账户余额
                accUserAccount.setBalance(new BigDecimal(amount));//消费的金额
                accUserAccount.setUpdateBy(accUserAccount.getUserName());
                accUserAccount.setUpdateTime(DateUtils.getNowDate());
                accUserAccountMapper.updateAccUserAccount(accUserAccount);

                this.iTradeOrderService.tradeSuccess(tradeNo);
            }
            return TradeConstants.TRADE_SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            return TradeConstants.TRADE_FAIL.getCode();
        }
    }*/
}
