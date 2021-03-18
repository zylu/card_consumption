package com.huohuzhihui.framework.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huohuzhihui.common.enums.LoginType;
import com.huohuzhihui.framework.security.device.DeviceAuthenticationToken;
import com.huohuzhihui.framework.security.mobile.MobileAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.common.utils.SecurityUtils;
import com.huohuzhihui.common.utils.StringUtils;
import com.huohuzhihui.framework.web.service.TokenService;

/**
 * token过滤器 验证token有效性
 * 
 * @author ruoyi
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        log.info("开始验证token有效性");
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
        {
            tokenService.verifyToken(loginUser);
            if(loginUser.getLoginType() == LoginType.WEB.getCode()){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            if(loginUser.getLoginType() == LoginType.WX.getCode()){
                MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(loginUser.getUser().getPhonenumber(), loginUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            if(loginUser.getLoginType() == LoginType.DEVICE.getCode()){
                DeviceAuthenticationToken authenticationToken = new DeviceAuthenticationToken(loginUser.getDevice().getNo(), loginUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
