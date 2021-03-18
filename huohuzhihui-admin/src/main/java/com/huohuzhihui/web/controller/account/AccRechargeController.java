package com.huohuzhihui.web.controller.account;

import java.util.List;

import com.huohuzhihui.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.huohuzhihui.common.annotation.Log;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.AjaxResult;
import com.huohuzhihui.common.enums.BusinessType;
import com.huohuzhihui.account.domain.AccRecharge;
import com.huohuzhihui.account.service.IAccRechargeService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 账户充值Controller
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
@RestController
@RequestMapping("/account/recharge")
public class AccRechargeController extends BaseController
{
    @Autowired
    private IAccRechargeService accRechargeService;

    /**
     * 查询账户充值列表
     */
    @PreAuthorize("@ss.hasPermi('account:recharge:list')")
    @GetMapping("/list")
    public TableDataInfo list(AccRecharge accRecharge)
    {
        startPage();
        List<AccRecharge> list = accRechargeService.selectAccRechargeList(accRecharge);
        return getDataTable(list);
    }

    /**
     * 导出账户充值列表
     */
    @PreAuthorize("@ss.hasPermi('account:recharge:export')")
    @Log(title = "账户充值", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AccRecharge accRecharge)
    {
        List<AccRecharge> list = accRechargeService.selectAccRechargeList(accRecharge);
        ExcelUtil<AccRecharge> util = new ExcelUtil<AccRecharge>(AccRecharge.class);
        return util.exportExcel(list, "recharge");
    }

    /**
     * 获取账户充值详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:recharge:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(accRechargeService.selectAccRechargeById(id));
    }

    /**
     * 新增账户充值
     */
    @PreAuthorize("@ss.hasPermi('account:recharge:add')")
    @Log(title = "账户充值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add( AccRecharge accRecharge)
    {

        return toAjax(accRechargeService.insertAccRecharge(accRecharge));
    }

    /**
     * 修改账户充值
     */
    @PreAuthorize("@ss.hasPermi('account:recharge:edit')")
    @Log(title = "账户充值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AccRecharge accRecharge)
    {
        return toAjax(accRechargeService.updateAccRecharge(accRecharge));
    }

    /**
     * 删除账户充值
     */
    @PreAuthorize("@ss.hasPermi('account:recharge:remove')")
    @Log(title = "账户充值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(accRechargeService.deleteAccRechargeByIds(ids));
    }
}
