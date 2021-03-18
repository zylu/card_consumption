package com.huohuzhihui.api.service.impl;

import com.huohuzhihui.account.constant.AccountConstants;
import com.huohuzhihui.account.mapper.AccCardMapper;
import com.huohuzhihui.account.mapper.AccUserAccountMapper;
import com.huohuzhihui.api.domain.TradeConstant;
import com.huohuzhihui.api.service.ApiMerchantService;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.core.domain.entity.Card;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.common.exception.CustomException;
import com.huohuzhihui.common.exception.user.UserPasswordNotMatchException;
import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.framework.security.device.DeviceAuthenticationToken;
import com.huohuzhihui.framework.web.service.TokenService;
import com.huohuzhihui.merchant.domain.MerDevice;
import com.huohuzhihui.merchant.mapper.MerDeviceMapper;
import com.huohuzhihui.system.service.ISysDictDataService;
import com.huohuzhihui.trade.domain.AccTradeRecord;
import com.huohuzhihui.trade.mapper.AccTradeRecordMapper;
import com.huohuzhihui.trade.mapper.TradeOrderMapper;
import com.huohuzhihui.trade.service.ITradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 订单Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
@Service
public class ApiMerchantServiceImpl implements ApiMerchantService {
    @Autowired
    private TradeOrderMapper tradeOrderMapper;
    @Autowired
    private AccTradeRecordMapper accTradeRecordMapper;
    @Autowired
    private MerDeviceMapper merDeviceMapper;
    @Autowired
    private AccUserAccountMapper accUserAccountMapper;
    @Autowired
    private AccCardMapper accCardMapper;
    @Autowired
    private ITradeOrderService iTradeOrderService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public int cost(String source,Long accoundId,String deviceNo,String payChannel, String cardNo,String amount,String goodsId,String tradeNo) {
        try{
            //验证此订单是否已经支付了
            AccTradeRecord accTradeRecord = accTradeRecordMapper.selectAccTradeRecordByTradeNo(tradeNo);
            if(accTradeRecord!=null){

            }


            //验证设备合法性
            MerDevice merDevice = merDeviceMapper.selectMerDeviceByNo(deviceNo);
            if(merDevice==null){
                return TradeConstant.DEVICE_NOT_FOUND.getCode();
            }
            //验证账户合法性
            Account accUserAccount = accUserAccountMapper.selectAccUserAccountById(accoundId);
            if(accUserAccount==null){
                return TradeConstant.ACCOUNT_NOT_FOUND.getCode();
            }
            if(accUserAccount.getStatus()!=0){
                return TradeConstant.ACCOUNT_STATUS_EXCEPTION.getCode();
            }
            //验证卡是否合法
            Card accCard = accCardMapper.selectAccCardByCardNo(cardNo);
            if(accCard==null){
                return TradeConstant.CARD_NOT_FOUND.getCode();
            }
            if(accCard.getStatus()!=0){
                return TradeConstant.CARD_STATUS_EXCEPTION.getCode();
            }
            //验证余额是否充足
            if(accUserAccount.getBalance().compareTo(new BigDecimal(amount))==-1){
                return TradeConstant.BALANCE_NOT_ENOUTH.getCode();
            }
            //验证商品库存是否充足

            //插入订单
            iTradeOrderService.insertTradeOrder(AccountConstants.OPER_CONSUME, accoundId, accUserAccount.getUserName(), new BigDecimal(amount), source, merDevice.getId(), payChannel, "系统",cardNo,tradeNo);
            //更新当前账户余额
            accUserAccount.setBalance(new BigDecimal(amount));//消费的金额
            accUserAccount.setUpdateBy(accUserAccount.getUserName());
            accUserAccount.setUpdateTime(DateUtils.getNowDate());
            accUserAccountMapper.updateAccUserAccount(accUserAccount);

            //更新交易记录状态
            int status = 1;
            iTradeOrderService.cardPayNotify(tradeNo,status);

            return TradeConstant.TRADE_SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            int status = 0;
            iTradeOrderService.cardPayNotify(tradeNo,status);
            return TradeConstant.TRADE_FAIL.getCode();
        }
    }

    @Override
    public String loginByDevice(String deviceNo) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new DeviceAuthenticationToken(deviceNo));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}

