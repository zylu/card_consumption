package com.huohuzhihui.wxminiapp.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.huohuzhihui.framework.security.mobile.MobileAuthenticationToken;
import com.huohuzhihui.wxminiapp.config.WxMaConfiguration;
import com.huohuzhihui.wxminiapp.domain.WxLoginRequest;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.common.core.redis.RedisCache;
import com.huohuzhihui.common.exception.CustomException;
import com.huohuzhihui.common.exception.user.UserPasswordNotMatchException;
import com.huohuzhihui.framework.web.service.TokenService;
import com.huohuzhihui.system.mapper.SysUserMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
@Data
@Slf4j
public class WxService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 微信登录认证
     * @param wxLoginRequest
     * @return
     */
    public String login(WxLoginRequest wxLoginRequest) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(wxLoginRequest.getUsername(), wxLoginRequest.getPassword()));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //更新openId
        SysUser updateUser = new SysUser();
        updateUser.setUserId(loginUser.getUser().getUserId());
        updateUser.setOpenId(wxLoginRequest.getOpenId());
        sysUserMapper.updateUser(updateUser);
        // 生成token
        return tokenService.createToken(loginUser);
    }
    /**
     * 微信手机号快速登录认证
     * @param phonenumber
     * @return
     */
    public String loginByPhonenumber(String phonenumber) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new MobileAuthenticationToken(phonenumber));
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
    public String loginByPhonenumber(String phonenumber,String password) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new MobileAuthenticationToken(phonenumber,password));
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
    public WxMaJscode2SessionResult getUserInfo(String code,String appid){
        if (StringUtils.isBlank(code)) {
            return null;
        }
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return session;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPhone( String openId, String sessionKey,String encryptedData, String iv,String appid) {

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        String phonenumber = phoneNoInfo.getPhoneNumber();
        //更新openId
        SysUser updateUser = sysUserMapper.selectUserByPhonenumber(phonenumber);
        updateUser.setOpenId(openId);
        sysUserMapper.updateUser(updateUser);

        return phoneNoInfo.getPhoneNumber();
    }

}
