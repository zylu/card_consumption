package com.huohuzhihui.framework.security.device;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * 设备号登陆鉴权 Provider，要求实现 AuthenticationProvider 接口
 *
 * @author xgj
 * @date : 2020-08-20 12:29
 */
@Configuration
@Slf4j
public class DeviceAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        DeviceAuthenticationToken authenticationToken = (DeviceAuthenticationToken) authentication;

        String deviceNo = (String) authenticationToken.getPrincipal();
        log.info("设备号{}开始认证......",deviceNo);
        UserDetails userDetails = userDetailsService.loadUserByUsername(deviceNo);

        if(userDetails == null){
            log.info("设备号{}无法根据手机号获取用户信息......",deviceNo);
            throw new InternalAuthenticationServiceException("无法根据设备号获取设备信息");
        }
        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        DeviceAuthenticationToken authenticationResult = new DeviceAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return DeviceAuthenticationToken.class.isAssignableFrom(authentication);
    }
    public UserDetailsService getUserDetailService() {
        return userDetailsService;
    }

    public void setUserDetailService(UserDetailsService userDetailService) {
        this.userDetailsService = userDetailService;
    }

}