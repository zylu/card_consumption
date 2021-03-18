package com.huohuzhihui.web.controller.api;

import com.huohuzhihui.api.domain.TradeConstant;
import com.huohuzhihui.api.service.ApiMerchantService;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.AjaxResult;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.framework.web.service.TokenService;
import com.huohuzhihui.wxminiapp.domain.WxLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 商户Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/api/merchant")
public class ApiMerchantController extends BaseController
{
    @Autowired
    private ApiMerchantService apiMerchantService;

    @Autowired
    private TokenService tokenService;


    /**
     * 获取商户详细信息
     */

    @GetMapping(value = "/cost")
    public AjaxResult cost(String source,Long accoundId,String deviceNo,String payChannel, String cardNo,String amount,String goodsId,String tradeNo,HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if(loginUser == null || loginUser.getDevice() == null){
            return AjaxResult.error("无效的token");
        }
        int result = apiMerchantService.cost( source, accoundId, deviceNo, payChannel,  cardNo, amount, goodsId, tradeNo);
        String msg = "";
        for(TradeConstant tradeConstant : TradeConstant.values()){
            if(result == tradeConstant.getCode()){
                msg = tradeConstant.getDesc();
            }
        }
        return new AjaxResult(result, msg);
    }

    /**
     * 登录方法
     *
     * @param deviceNo 登录信息
     * @return 结果
     */
    @GetMapping("/loginByDevice")
    public AjaxResult loginByDevice(String deviceNo) {
        AjaxResult ajax = AjaxResult.success();
        String token = null;
        try {
            // 生成令牌
            token = apiMerchantService.loginByDevice(deviceNo);
            ajax.put("data", token);
        } catch (Exception e) {
            logger.error("设备登录失败",e);
            return AjaxResult.error(e.getMessage());
        }
        return ajax;
    }

}
