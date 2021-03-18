package com.huohuzhihui.web.controller.merchant;

import java.util.List;
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
import com.huohuzhihui.merchant.domain.MerMerchant;
import com.huohuzhihui.merchant.service.IMerMerchantService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 商户Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/merchant/merchant")
public class MerMerchantController extends BaseController
{
    @Autowired
    private IMerMerchantService merMerchantService;

    /**
     * 查询商户列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerMerchant merMerchant)
    {
        startPage();
        List<MerMerchant> list = merMerchantService.selectMerMerchantList(merMerchant);
        return getDataTable(list);
    }

    /**
     * 导出商户列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:export')")
    @Log(title = "商户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MerMerchant merMerchant)
    {
        List<MerMerchant> list = merMerchantService.selectMerMerchantList(merMerchant);
        ExcelUtil<MerMerchant> util = new ExcelUtil<MerMerchant>(MerMerchant.class);
        return util.exportExcel(list, "merchant");
    }

    /**
     * 获取商户详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(merMerchantService.selectMerMerchantById(id));
    }

    /**
     * 新增商户
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:add')")
    @Log(title = "商户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerMerchant merMerchant)
    {
        return toAjax(merMerchantService.insertMerMerchant(merMerchant));
    }

    /**
     * 修改商户
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:edit')")
    @Log(title = "商户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerMerchant merMerchant)
    {
        return toAjax(merMerchantService.updateMerMerchant(merMerchant));
    }

    /**
     * 删除商户
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:remove')")
    @Log(title = "商户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merMerchantService.deleteMerMerchantByIds(ids));
    }
}
