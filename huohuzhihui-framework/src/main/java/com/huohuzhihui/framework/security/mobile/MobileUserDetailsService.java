package com.huohuzhihui.framework.security.mobile;

import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.common.enums.LoginType;
import com.huohuzhihui.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * blog
 *
 * @author : xgj
 * @description : 基于短信验证的userdetails
 * @date : 2020-08-17 09:24
 **/
@Service("mobileUserDetailsService")
@Slf4j
public class MobileUserDetailsService implements UserDetailsService {
    @Autowired
    private ISysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String mobile) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser user = userService.selectUserByPhonenumber(mobile);
        // 判断用户是否存在
        if (user==null) {
            log.info("手机号{}不存在",mobile);
            throw new UsernameNotFoundException("手机号不存在");
        }
        // 返回UserDetails实现类
        LoginUser loginUser = new LoginUser(user, null);
        loginUser.setLoginType(LoginType.WX.getCode());
        return loginUser;
    }
}
