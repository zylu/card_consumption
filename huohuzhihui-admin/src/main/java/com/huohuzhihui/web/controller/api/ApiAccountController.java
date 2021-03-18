package com.huohuzhihui.web.controller.api;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.huohuzhihui.account.constant.AccountConstants;
import com.huohuzhihui.account.domain.AccRecharge;
import com.huohuzhihui.account.domain.TradeVo;
import com.huohuzhihui.account.mapper.AccUserAccountMapper;
import com.huohuzhihui.account.service.IAccRechargeService;
import com.huohuzhihui.account.service.IAccUserAccountService;
import com.huohuzhihui.api.service.ApiAccountService;
import com.huohuzhihui.common.annotation.Log;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.AjaxResult;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.core.domain.entity.Card;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.common.core.page.TableDataInfo;
import com.huohuzhihui.common.enums.BusinessType;
import com.huohuzhihui.common.exception.CustomException;
import com.huohuzhihui.common.utils.SecurityUtils;
import com.huohuzhihui.common.utils.StringUtils;
import com.huohuzhihui.framework.web.service.TokenService;
import com.huohuzhihui.system.domain.CloudStorage;
import com.huohuzhihui.system.service.ICloudStorageService;
import com.huohuzhihui.system.service.ISysDictDataService;
import com.huohuzhihui.system.service.ISysUserService;
import com.huohuzhihui.system.util.QiNiuFileOptUtil;
import com.huohuzhihui.trade.domain.TradeOrder;
import com.huohuzhihui.trade.service.ITradeOrderService;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.IpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商户Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/api/account")
public class ApiAccountController extends BaseController
{
    @Autowired
    private ApiAccountService apiAccountService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IAccRechargeService rechargeService;
    @Autowired
    private ICloudStorageService cloudStorageService;
    @Autowired
    private ISysUserService userService;


    /**
     * 个人账户交易记录
     * @return
     */
    @PostMapping("/findTradeRecord")
    public AjaxResult findTradeRecord( HttpServletRequest request,String beginTime, String endTime){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();

        TradeVo tradeVo = new TradeVo();
        tradeVo.setUserId(sysUser.getUserId());
        tradeVo.setBeginTime(beginTime);
        tradeVo.setEndTime(endTime);
        startPage();

        List<TradeVo> list = apiAccountService.findTradeList(tradeVo);

        return AjaxResult.success("查询交易记录成功",getDataTable(list));
    }


    /**
     * 挂失卡
     * @param request
     * @return
     */
    @PostMapping("/lossCard")
    public AjaxResult lossCard(HttpServletRequest request){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();
        Account accUserAccount = apiAccountService.getAccountByUserId(sysUser.getUserId());
        Card card = apiAccountService.findCardByAccoutId(accUserAccount.getId());
        return new AjaxResult(200, "卡挂失成功", apiAccountService.lossCard(card.getId(),sysUser.getUserName()));
    }

    /**
     * 解挂卡
     * @param request
     * @return
     */
    @PostMapping("/unlossCard")
    public AjaxResult unlossCard(HttpServletRequest request){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();
        Account accUserAccount = apiAccountService.getAccountByUserId(sysUser.getUserId());
        Card card = apiAccountService.findCardByAccoutId(accUserAccount.getId());
        return new AjaxResult(200, "卡解挂成功", apiAccountService.unlossCard(card.getId(),sysUser.getUserName()));
    }

    /**
     * 获取用户余额
     * @param userId
     * @return
     */
    @GetMapping("/getBalance")
    public AjaxResult getBalance( Long userId){
        Account account = this.apiAccountService.getAccountByUserId(userId);
        return new AjaxResult(200, "获取用户余额成功", account.getBalance());
    }


    /**
     * 获取当前登录人信息
     * @param request
     * @return
     */
    @PostMapping("/getLoginUser")
    public AjaxResult getLoginUser(HttpServletRequest request){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();
        return new AjaxResult(200, "查询用户信息成功", sysUser);
    }


    /**
     * 修改密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/updatePwd")
    public AjaxResult updatePwd(HttpServletRequest request,String oldPassword,String newPassword){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();
        if(!SecurityUtils.matchesPassword(oldPassword,sysUser.getPassword())){
            return AjaxResult.error("修改密码失败，原密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, sysUser.getPassword()))
        {
            return AjaxResult.error("修改密码失败，新密码不能与旧密码相同");
        }
        return new AjaxResult(200, "密码修改成功", apiAccountService.resetPassword(sysUser.getUserName(),newPassword));
    }

    /**
     * 获取个人充值总金额
     * @param request
     * @return
     */
    @PostMapping("/getSumRecharge")
    public AjaxResult getSumRecharge(HttpServletRequest request,String beginTime,String endTime){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();
        TradeVo tradeVo = new TradeVo();
        tradeVo.setUserId(sysUser.getUserId());
        tradeVo.setBeginTime(beginTime);
        tradeVo.setEndTime(endTime);
        return new AjaxResult(200, "查询充值总金额成功", apiAccountService.getSumRecharge(tradeVo));
    }


    /**
     * 获取个人消费总金额
     * @param request
     * @return
     */
    @PostMapping("/getSumConsume")
    public AjaxResult getSumConsume(HttpServletRequest request,String beginTime,String endTime){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();
        TradeVo tradeVo = new TradeVo();
        tradeVo.setUserId(sysUser.getUserId());
        tradeVo.setBeginTime(beginTime);
        tradeVo.setEndTime(endTime);
        return new AjaxResult(200, "查询消费总金额成功", apiAccountService.getSumCost(tradeVo));
    }

    /**
     * 开通虚拟卡
     * @param request
     * @return
     */
    @PostMapping("/openVirtualCard")
    public AjaxResult openVirtualCard(HttpServletRequest request){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();
        Account accUserAccount = apiAccountService.getAccountByUserId(sysUser.getUserId());

        try{
            int count = apiAccountService.openVirtualCard(accUserAccount);
            return new AjaxResult(200, "开通虚拟卡成功",count );
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 查询虚拟卡字符串
     * @param request
     * @return
     */
    @PostMapping("/getQrCodeStr")
    public AjaxResult getQrCodeStr(HttpServletRequest request){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();
        try{
            if(sysUser.getAccountStatus()==null || sysUser.getAccountStatus()!=0){
                return AjaxResult.error("账户状态异常,无法开通虚拟卡");
            }
            if(sysUser.getCardNo()==null||"".equals(sysUser.getCardNo().trim())){
                return AjaxResult.error("未开通卡,请联系管理员开通");
            }
            if(sysUser.getCardStatus()==null||sysUser.getCardStatus()!=0){
                return AjaxResult.error("卡状态异常,无法开通虚拟卡");
            }
            String result = apiAccountService.getQrCodeStr(sysUser.getCardNo());
            return new AjaxResult(200, "查询虚拟卡成功", result);
        }catch (CustomException e){
            e.printStackTrace();
            return new AjaxResult(e.getCode(), e.getMessage(), null);
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResult(500, e.getMessage(), null);
        }
    }
    @PostMapping("/rechargeByWx")
    public AjaxResult rechargeByWx(HttpServletRequest request, BigDecimal amount, String source, String channelCode){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();

        Account accUserAccount = apiAccountService.getAccountByUserId(sysUser.getUserId());
        accUserAccount.setUserName(sysUser.getUserName());
        String ip = IpKit.getRealIp(request);
        if (StringUtils.isBlank(ip)) {
            ip = "127.0.0.1";
        }
        return new AjaxResult(200, "获取支付参数成功", apiAccountService.recharge(   accUserAccount, amount,  source, channelCode,sysUser.getOpenId(),ip));
    }
    /**
     * 异步通知
     */
    @RequestMapping(value = "/wxPayNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String wxPayNotify(HttpServletRequest request) {
        return  apiAccountService.wxPayNotify(request  );
    }


    /**
     * 公众号支付
     */
    @RequestMapping(value = "/webPay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult webPay(HttpServletRequest request,Long userId,String openId, String amount) {
        if (StrUtil.isEmpty(openId)) {
            return  AjaxResult.error("openId is null");
        }
        if (StrUtil.isEmpty(amount)) {
            return AjaxResult.error("请输入数字金额");
        }
        String ip = IpKit.getRealIp(request);
        if (StrUtil.isEmpty(ip)) {
            ip = "127.0.0.1";
        }

        return new AjaxResult(200, "获取支付参数成功",apiAccountService.webPay(userId, amount,  openId,ip));


    }

    /**
     * 保存充值订单
     * @param accRecharge
     * @return
     */
    @RequestMapping(value = "/addRechargeOrder", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addRechargeOrder(AccRecharge accRecharge) {
        rechargeService.insertAccRecharge(accRecharge);
        return new AjaxResult(200, "保存充值订单成功",accRecharge);
    }
    @RequestMapping(value = "/queryRechargeOrderByOrderId", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult queryRechargeOrderByOrderId(Long orderId) {
        return new AjaxResult(200, "查询充值订单成功",rechargeService.selectAccRechargeById(orderId));
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/chageAvatar")
    public AjaxResult chageAvatar(Long userId,@RequestParam("avatarfile") MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            CloudStorage cloudStorage = new CloudStorage();
            cloudStorage.setIsDefault(1L);
            //查询默认的云存储
            List<CloudStorage> cloudStorageList = cloudStorageService.selectCloudStorageList(cloudStorage);
            if(cloudStorageList!=null&&cloudStorageList.size()>0){
                cloudStorage = cloudStorageList.get(0);
            }
//            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            String avatar = cloudStorage.getStorageUrl() + QiNiuFileOptUtil.upload(file,cloudStorage).key;
            if (userService.updateUserAvatar(userId, avatar))
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }


}
