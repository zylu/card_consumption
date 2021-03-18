package com.huohuzhihui.framework.security.mobile;

import com.huohuzhihui.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * 短信登陆鉴权 Provider，要求实现 AuthenticationProvider 接口
 *
 * @author xgj
 * @date : 2020-08-20 12:29
 */
@Configuration
@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();
        String password = (String)authenticationToken.getCredentials();
        log.info("手机号{}开始认证......",mobile);
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);

        if(userDetails == null){
            log.info("手机号{}无法根据手机号获取用户信息......",mobile);
            throw new InternalAuthenticationServiceException("无法根据手机号获取用户信息");
        }
        if( !SecurityUtils.matchesPassword( password,userDetails.getPassword())){
           throw new BadCredentialsException("密码不正确");
        }
        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        MobileAuthenticationToken authenticationResult = new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }
    public UserDetailsService getUserDetailService() {
        return userDetailsService;
    }

    public void setUserDetailService(UserDetailsService userDetailService) {
        this.userDetailsService = userDetailService;
    }

}