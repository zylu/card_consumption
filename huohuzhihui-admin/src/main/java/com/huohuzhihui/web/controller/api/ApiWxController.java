package com.huohuzhihui.web.controller.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.huohuzhihui.account.service.IAccUserAccountService;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.AjaxResult;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.framework.web.service.TokenService;
import com.huohuzhihui.wxminiapp.domain.WxLoginRequest;
import com.huohuzhihui.wxminiapp.service.WxService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *微信api
 * 
 * @author lizhihe
 * @date 2020-11-22
 */
@RestController
@RequestMapping("/api/wx")
public class ApiWxController extends BaseController
{

    @Autowired
    private WxService wxService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IAccUserAccountService iAccUserAccountService;

    @Value("${wxpay.appId}")
    private String appId;
    /**
     * 登录方法
     *
     * @param request 登录信息
     * @return 结果
     */
    @PostMapping("/loginByMobile")
    public AjaxResult loginByMobile(@RequestBody WxLoginRequest request) {
        AjaxResult ajax = AjaxResult.success();
        String token = null;
        try {
            // 生成令牌
            token = wxService.loginByPhonenumber(request.getMobile(),request.getPassword());
            ajax.put("data", token);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return ajax;
    }

    @RequestMapping("/getUserInfo")
    public AjaxResult getUserInfo(@RequestParam(name = "code") String code) throws Exception {
        if (StringUtils.isBlank(code)) {
            AjaxResult ajax = AjaxResult.error("code不能为空！");
            return ajax;
        }
        WxMaJscode2SessionResult wxMaJscode2SessionResult = wxService.getUserInfo(code,appId);
        if(wxMaJscode2SessionResult==null){
            AjaxResult ajax = AjaxResult.error("获取微信用户session错误，请确认微信相关配置参数是否正确！");
            return ajax;
        }
        return new AjaxResult(200, "获取微信用户session成功", wxMaJscode2SessionResult);
    }
    @PostMapping("/getUserAccount")
    public AjaxResult getUserAccount(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser sysUser = loginUser.getUser();
        Account accUserAccount = iAccUserAccountService.selectAccUserAccountByUserId(sysUser.getUserId());
        AjaxResult ajax = AjaxResult.success(accUserAccount);
        return  ajax;
    }


    @PostMapping("/getPhoneNumber")
    public AjaxResult getPhoneNumber(@RequestBody WxLoginRequest wxLoginRequest) {
        String phonenumber = wxService.getPhone( wxLoginRequest.getOpenId(),wxLoginRequest.getSessionKey(),wxLoginRequest.getEncryptedData(),wxLoginRequest.getIv(),appId);
        return new AjaxResult(200, "获取微信绑定手机号成功", phonenumber);
    }





}
