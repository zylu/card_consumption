package com.huohuzhihui.framework.security.device;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设备号登录的鉴权过滤器，模仿 UsernamePasswordAuthenticationFilter 实现
 *
 * @author xgj
 * @date : 2020-08-20 12:29
 */
public class DeviceAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String parameter = "device";
    /**
     * 是否仅 POST 方式
     */
    private boolean postOnly = true;

    public DeviceAuthenticationFilter() {
        super(new AntPathRequestMatcher("/loginByDevice", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String deviceNo = checkDeviceNo(request);

        if (deviceNo == null) {
            deviceNo = "";
        }

        deviceNo = deviceNo.trim();

        DeviceAuthenticationToken authRequest = new DeviceAuthenticationToken(deviceNo);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String checkDeviceNo(HttpServletRequest request) {
        return request.getParameter(parameter);
    }

    protected void setDetails(HttpServletRequest request, DeviceAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        Assert.hasText(parameter, "Device parameter must not be empty or null");
        this.parameter = parameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}