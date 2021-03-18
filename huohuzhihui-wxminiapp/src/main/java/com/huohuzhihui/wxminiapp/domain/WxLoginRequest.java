package com.huohuzhihui.wxminiapp.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 微信登录请求对象
 */
@Data
public class WxLoginRequest implements Serializable {
    /**
     * openId
     */
    @NotNull(message = "openId不能为空")
    private String openId;

    /**
     * 用户名
     */
    @NotNull(message = "password不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotNull(message = "password不能为空")
    private String password;

    private String sessionKey;
    private String encryptedData;
    private String iv;


    /**
     * 用户密码
     */
    private String mobile;
}
