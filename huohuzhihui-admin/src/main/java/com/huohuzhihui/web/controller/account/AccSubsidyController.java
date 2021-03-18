package com.huohuzhihui.web.controller.account;

import java.util.List;

import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.utils.SecurityUtils;
import com.huohuzhihui.system.service.ISysUserService;
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
import com.huohuzhihui.account.domain.AccSubsidy;
import com.huohuzhihui.account.service.IAccSubsidyService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 补贴Controller
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
@RestController
@RequestMapping("/account/subsidy")
public class AccSubsidyController extends BaseController
{
    @Autowired
    private IAccSubsidyService accSubsidyService;
    @Autowired
    private ISysUserService sysUserService;
    /**
     * 查询补贴列表
     */
    @PreAuthorize("@ss.hasPermi('account:subsidy:list')")
    @GetMapping("/list")
    public TableDataInfo list(AccSubsidy accSubsidy)
    {
        startPage();
        List<AccSubsidy> list = accSubsidyService.selectAccSubsidyList(accSubsidy);
        return getDataTable(list);
    }

    /**
     * 导出补贴列表
     */
    @PreAuthorize("@ss.hasPermi('account:subsidy:export')")
    @Log(title = "补贴", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AccSubsidy accSubsidy)
    {
        List<AccSubsidy> list = accSubsidyService.selectAccSubsidyList(accSubsidy);
        ExcelUtil<AccSubsidy> util = new ExcelUtil<AccSubsidy>(AccSubsidy.class);
        return util.exportExcel(list, "subsidy");
    }

    /**
     * 获取补贴详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:subsidy:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(accSubsidyService.selectAccSubsidyById(id));
    }

    /**
     * 新增补贴
     */
    @PreAuthorize("@ss.hasPermi('account:subsidy:add')")
    @Log(title = "补贴", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AccSubsidy accSubsidy)
    {
        SysUser sysUser = this.sysUserService.selectUserById(accSubsidy.getUserId());
        if(!"0".equals(sysUser.getStatus()) || sysUser.getAccountStatus()!=0){
            return AjaxResult.error("账户状态异常，无法补贴");
        }
        accSubsidy.setCreateBy(SecurityUtils.getUsername());
        return toAjax(accSubsidyService.insertAccSubsidy(accSubsidy));
    }

}
