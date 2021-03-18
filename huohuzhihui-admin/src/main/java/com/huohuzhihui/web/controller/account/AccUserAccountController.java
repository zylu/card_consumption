package com.huohuzhihui.web.controller.account;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.huohuzhihui.account.service.IAccUserAccountService;
import com.huohuzhihui.common.annotation.Log;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.AjaxResult;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.core.page.TableDataInfo;
import com.huohuzhihui.common.enums.BusinessType;
import com.huohuzhihui.common.utils.SecurityUtils;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.system.service.ISysDictDataService;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.IpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 账户Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/account/account")
public class AccUserAccountController extends BaseController
{
    @Autowired
    private IAccUserAccountService accUserAccountService;
    @Autowired
    private ISysDictDataService iSysDictDataService;


    /**
     * 查询账户列表
     */
    @PreAuthorize("@ss.hasPermi('account:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(Account accUserAccount)
    {
        startPage();
        List<Account> list = accUserAccountService.selectAccUserAccountList(accUserAccount);
        return getDataTable(list);
    }

    /**
     * 导出账户列表
     */
    @PreAuthorize("@ss.hasPermi('account:account:export')")
    @Log(title = "账户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Account accUserAccount)
    {
        List<Account> list = accUserAccountService.selectAccUserAccountList(accUserAccount);
        ExcelUtil<Account> util = new ExcelUtil<Account>(Account.class);
        return util.exportExcel(list, "account");
    }

    /**
     * 获取账户详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:account:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(accUserAccountService.selectAccUserAccountByUserId(userId));
    }

    /**
     * 新增账户
     */
    @PreAuthorize("@ss.hasPermi('account:account:add')")
    @Log(title = "账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Account accUserAccount)
    {
        accUserAccount.setCreateBy(SecurityUtils.getUsername());
        return toAjax(accUserAccountService.insertAccUserAccount(accUserAccount));
    }

    /**
     * 修改账户
     */
    @PreAuthorize("@ss.hasPermi('account:account:edit')")
    @Log(title = "账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Account accUserAccount)
    {
        accUserAccount.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(accUserAccountService.updateAccUserAccount(accUserAccount));
    }

    /**
     * 删除账户
     */
    @PreAuthorize("@ss.hasPermi('account:account:remove')")
    @Log(title = "账户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(accUserAccountService.deleteAccUserAccountByIds(ids));
    }


    /**
     * 冻结账户
     */
    @PreAuthorize("@ss.hasPermi('account:account:remove')")
    @Log(title = "账户", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/freeze")
    @ResponseBody
    public AjaxResult freeze( Long[] ids)
    {
        return toAjax(accUserAccountService.freezeUserAccountByIds(ids));
    }

    /**
     * 冻结账户
     */
    @PreAuthorize("@ss.hasPermi('account:account:remove')")
    @Log(title = "账户", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/unfreeze")
    @ResponseBody
    public AjaxResult unfreeze(Long[] ids)
    {
        return toAjax(accUserAccountService.unfreezeUserAccountByIds(ids));
    }

    /**
     * 注销账户
     */
    @PreAuthorize("@ss.hasPermi('account:account:remove')")
    @Log(title = "账户", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/cancel")
    @ResponseBody
    public AjaxResult cancel(Long[] ids)
    {
        return toAjax(accUserAccountService.cancelUserAccountByIds(ids));
    }


    /**
     * 充值
     */
    @PreAuthorize("@ss.hasPermi('account:account:recharge')")
    @Log(title = "账户充值", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/recharge")
    public AjaxResult recharge(Long id, BigDecimal amount,String source,String channelCode)
    {
        return toAjax(accUserAccountService.recharge(   id,   amount,  source, channelCode, iSysDictDataService.selectDictLabel("pay_channel",channelCode),SecurityUtils.getUsername()));
    }




}
