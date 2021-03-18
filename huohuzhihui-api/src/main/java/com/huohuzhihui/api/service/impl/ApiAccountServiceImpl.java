package com.huohuzhihui.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.huohuzhihui.account.constant.AccountConstants;
import com.huohuzhihui.account.domain.TradeVo;
import com.huohuzhihui.account.mapper.AccCardMapper;
import com.huohuzhihui.account.mapper.AccTradeMapper;
import com.huohuzhihui.account.mapper.AccUserAccountMapper;
import com.huohuzhihui.api.service.ApiAccountService;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.core.domain.entity.Card;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.exception.CustomException;
import com.huohuzhihui.common.utils.AESUtils;
import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.common.utils.SecurityUtils;
import com.huohuzhihui.common.utils.SnowFlakeUtil;
import com.huohuzhihui.common.utils.uuid.IdUtils;
import com.huohuzhihui.system.mapper.SysUserMapper;
import com.huohuzhihui.system.service.ISysConfigService;
import com.huohuzhihui.trade.domain.AccTradeRecord;
import com.huohuzhihui.trade.domain.TradeOrder;
import com.huohuzhihui.trade.mapper.AccTradeRecordMapper;
import com.huohuzhihui.trade.mapper.TradeOrderMapper;
import com.huohuzhihui.trade.service.ITradeOrderService;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
@Service
public class ApiAccountServiceImpl implements ApiAccountService {
    @Autowired
    private AccUserAccountMapper accUserAccountMapper;
    @Autowired
    private AccTradeMapper accTradeMapper;
    @Autowired
    private AccCardMapper accCardMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TradeOrderMapper tradeOrderMapper;
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private ITradeOrderService iTradeOrderService;
    @Autowired
    private AccTradeRecordMapper accTradeRecordMapper;
    @Value("${wxpay.appId}")
    private String appId;

    @Value("${wxpay.appSecret}")
    private String appSecret;

    @Value("${wxpay.certPath}")
    private String certPath;

    @Value("${wxpay.domain}")
    private String domain;

    @Value("${wxpay.mchId}")
    private String mchId;

    @Value("${wxpay.partnerKey}")
    private String partnerKey;
    @Value("${wxpay.notifyUrl}")
    private String notifyUrl;
    @Value("${wxpay.refundNotifyUrl}")
    private String refundNotifyUrl;

    private WxPayApiConfig wxPayApiConfig;
    @PostConstruct
    public WxPayApiConfig getApiConfig() {
        wxPayApiConfig = WxPayApiConfig.builder()
                    .appId(appId)
                    .mchId(mchId)
                    .partnerKey(partnerKey)
                    .certPath(certPath)
                    .domain(domain)
                    .build();
        return wxPayApiConfig;
    }
    @Override
    public Account getAccountByPhonenumber(String phonenumber) {
        SysUser sysUser = sysUserMapper.selectUserByPhonenumber(phonenumber);
        if(sysUser==null){
            return null;
        }
        return accUserAccountMapper.selectAccUserAccountByUserId(sysUser.getUserId());
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accUserAccountMapper.selectAccUserAccountById(accountId);
    }

    @Override
    public Account getAccountByUserId(Long userId) {
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        if(sysUser==null){
            return null;
        }
        return accUserAccountMapper.selectAccUserAccountByUserId(sysUser.getUserId());
    }

    @Override
    public SysUser getUserByPhonenumber(String phonenumber) {
        return sysUserMapper.selectUserByPhonenumber(phonenumber);
    }

    @Override
    public SysUser getUserById(Long userId) {
        return sysUserMapper.selectUserById(userId);
    }

    @Override
    public Card findCardByAccoutId(Long accountId) {
        Account accUserAccount = accUserAccountMapper.selectAccUserAccountById(accountId);

        Card accCard = new Card();
        accCard.setUserId(accUserAccount.getId());
        List<Card> cardList = accCardMapper.selectAccCardList(accCard);
        if(cardList!=null && cardList.size()>0){
            for(int i = 0 ;i<cardList.size();i++){
                Card card = cardList.get(i);
                if(card.getStatus() != 2 ){
                    return card;
                }
            }
        }
        return null;
    }

    @Override
    public int lossCard(Long cardId,String updateBy) {
        Date now = DateUtils.getNowDate();
        return accCardMapper.updateStatusByIds(1,now,updateBy,new Long[]{cardId});
    }

    @Override
    public int unlossCard(Long cardId,String updateBy) {
        Date now = DateUtils.getNowDate();
        return accCardMapper.updateStatusByIds(0,now,updateBy,new Long[]{cardId});
    }

    @Override
    public int resetPassword(String userName, String newPassword) {
        return sysUserMapper.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword));
    }

    @Override
    public BigDecimal getSumTrade(TradeOrder tradeOrder) {
        return tradeOrderMapper.getOrderStatistics(tradeOrder);
    }

    @Override
    public List<TradeOrder> findAccountTradeRecordList(TradeOrder tradeOrder) {
        return tradeOrderMapper.selectTradeOrderList(tradeOrder);
    }

    @Override
    public int openVirtualCard(Account accUserAccount){
        if(accUserAccount.getStatus()!=0){
            throw new CustomException("账户状态异常，无法开通虚拟卡");
        }
        Card queryCard = new Card();
        queryCard.setUserId(accUserAccount.getId());
        queryCard.setVirtualcard(1);
        List<Card> cardList = this.accCardMapper.selectAccCardList(queryCard);
        if(cardList!=null&& cardList.size()>0){//说明已经开通过，那么就不再开通了
            return 1;
        }
        Card card = new Card();
        card.setVirtualcard(1);
        card.setUserId(accUserAccount.getId());
        card.setStatus(0);
        card.setCreateTime(new Date());
        card.setCreateBy(accUserAccount.getUserName());
        card.setUpdateTime(new Date());
        card.setUpdateBy(accUserAccount.getUserName());
        card.setCardNo(SnowFlakeUtil.getId()+"");
        return accCardMapper.insertAccCard(card);
    }

    public String getQrCodeStr(String rawCardNo) {
        //生成虚拟卡二维码
        String password = sysConfigService.selectConfigByKey("viturlcard_password");;//虚拟卡秘钥
        String cardNo = rawCardNo + DateUtils.parseDateToStr("yyyyMMDDHHmmss",new Date());//虚拟卡每30S刷新一次
        String qrCodeStr = AESUtils.parseByte2HexStr(AESUtils.encrypt(cardNo, password));
        return qrCodeStr;
    }

    @Override
    public String recharge(Account accUserAccount, BigDecimal amount, String source, String channelCode,String openId,String ip) {
        if(accUserAccount != null) {
            String type = "0";//充值
            Long deviceId = null;
            String cardNo = "";
            String tradeNo = IdUtils.fastSimpleUUID();

            iTradeOrderService.insertTradeOrder(AccountConstants.OPER_RECHARGE, accUserAccount.getId(), accUserAccount.getUserName(), amount, source, deviceId, channelCode, accUserAccount.getUserName(),cardNo,tradeNo);

            String subject = "一卡通充值";
            String body = "一卡通充值";

            Map<String, String> params = UnifiedOrderModel
                    .builder()
                    .appid(wxPayApiConfig.getAppId())
                    .mch_id(wxPayApiConfig.getMchId())
                    .nonce_str(WxPayKit.generateStr())
                    .body(body)
                    .attach(subject)
                    .out_trade_no(tradeNo)
                    .total_fee(amount.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString())
                    .spbill_create_ip(ip)
                    .notify_url(notifyUrl)
                    .trade_type(TradeType.JSAPI.getTradeType())
                    .openid(openId)
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

            String xmlResult = WxPayApi.pushOrder(false, params);

            Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

            String returnCode = result.get("return_code");
            String returnMsg = result.get("return_msg");
            if (!WxPayKit.codeIsOk(returnCode)) {
                throw new CustomException(returnMsg,500);
            }
            String resultCode = result.get("result_code");
            if (!WxPayKit.codeIsOk(resultCode)) {
                throw new CustomException(returnMsg,500);
            }
            // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
            String prepayId = result.get("prepay_id");
            Map<String, String> packageParams = WxPayKit.miniAppPrepayIdCreateSign(wxPayApiConfig.getAppId(), prepayId,
                    wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);
            String jsonStr = JSON.toJSONString(packageParams);
            System.out.println("支付结果------------------"+jsonStr);
            return jsonStr;
        }
        return null;
    }

    @Override
    public String wxPayNotify(HttpServletRequest request) {
        String xmlMsg = HttpKit.readData(request);
        Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);
        String returnCode = params.get("return_code");

        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 注意此处签名方式需与统一下单的签名类型一致
        if (WxPayKit.verifyNotify(params, partnerKey, SignType.HMACSHA256)) {
            if (WxPayKit.codeIsOk(returnCode)) {
                String tradeNo = params.get("out_trade_no");
                // 更新订单信息
                AccTradeRecord accTradeRecord = this.accTradeRecordMapper.selectAccTradeRecordByTradeNo(tradeNo);
                if(accTradeRecord.getStatus()==2){
                    accTradeRecord.setStatus(1);
                    accTradeRecord.setUpdateTime(DateUtils.getNowDate());
                    accTradeRecord.setUpdateBy("系统");
                    accTradeRecordMapper.updateAccTradeRecord(accTradeRecord);

                    //更新订单状态
                    long orderId = accTradeRecord.getTradeOrderId();
                    TradeOrder tradeOrder = this.tradeOrderMapper.selectTradeOrderById(orderId);
                    tradeOrder.setStatus(1);
                    tradeOrder.setUpdateTime(DateUtils.getNowDate());
                    tradeOrder.setUpdateBy("系统");
                    tradeOrderMapper.updateTradeOrder(tradeOrder);

                    //更新余额
                    //更新当前账户余额
                    Account queryAccount = this.accUserAccountMapper.selectAccUserAccountById(tradeOrder.getAccountId());
                    queryAccount.setVersion(queryAccount.getVersion());
                    queryAccount.setBalance(tradeOrder.getAmount());//增加的金额
                    queryAccount.setUpdateBy("系统");
                    queryAccount.setUpdateTime(DateUtils.getNowDate());
                    accUserAccountMapper.recharge(queryAccount);


                    // 发送通知等
                    Map<String, String> xml = new HashMap<String, String>(2);
                    xml.put("return_code", "SUCCESS");
                    xml.put("return_msg", "OK");
                    xml.put("accountId", accTradeRecord.getAccountId()+"");
                    xml.put("amount", accTradeRecord.getAmount().toString());
                    return WxPayKit.toXml(xml);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override
    public String webPay(Long userId, String amount,  String openId, String ip) {
        // openId，采用 网页授权获取 access_token API：SnsAccessTokenApi获取
        if (openId == null) {
            openId = "11111111";
        }
        String tradeNo = IdUtils.fastSimpleUUID();

        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        Map<String, String> params = UnifiedOrderModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body("IJPay 让支付触手可及-公众号支付")
                .attach("Node.js 版:https://gitee.com/javen205/TNWX")
                .out_trade_no(tradeNo)
                .total_fee(new BigDecimal(amount).multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString())
                .spbill_create_ip(ip)
                .notify_url(notifyUrl)
                .trade_type(TradeType.JSAPI.getTradeType())
                .openid(openId)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);


        Map<String, String> resultMap = WxPayKit.xmlToMap(xmlResult);
        String returnCode = resultMap.get("return_code");
        String returnMsg = resultMap.get("return_msg");
        if (!WxPayKit.codeIsOk(returnCode)) {
             throw new CustomException(resultMap.get("err_code_des"),500);
        }
        String resultCode = resultMap.get("result_code");
        if (!WxPayKit.codeIsOk(resultCode)) {
            throw new CustomException(resultMap.get("err_code_des"),500);
        }
        // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回

        String prepayId = resultMap.get("prepay_id");

        Map<String, String> packageParams = WxPayKit.prepayIdCreateSign(prepayId, wxPayApiConfig.getAppId(),
                wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

        String jsonStr = JSON.toJSONString(packageParams);
        return jsonStr;
    }

    @Override
    public List<TradeVo> findTradeList(TradeVo tradeVo) {
        return accTradeMapper.selectAccTradeList(tradeVo);
    }

    @Override
    public java.math.BigDecimal getSumRecharge(TradeVo tradeVo) {
        return accTradeMapper.getSumRecharge(tradeVo);
    }

    @Override
    public java.math.BigDecimal getSumCost(TradeVo tradeVo) {
        return accTradeMapper.getSumCost(tradeVo);
    }


}

