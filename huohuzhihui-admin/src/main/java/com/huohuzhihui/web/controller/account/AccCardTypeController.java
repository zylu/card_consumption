package com.huohuzhihui.web.controller.account;

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
import com.huohuzhihui.account.domain.AccCardType;
import com.huohuzhihui.account.service.IAccCardTypeService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 卡类型Controller
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
@RestController
@RequestMapping("/account/cardtype")
public class AccCardTypeController extends BaseController
{
    @Autowired
    private IAccCardTypeService accCardTypeService;

    /**
     * 查询卡类型列表
     */
    @PreAuthorize("@ss.hasPermi('account:cardtype:list')")
    @GetMapping("/list")
    public TableDataInfo list(AccCardType accCardType)
    {
        startPage();
        List<AccCardType> list = accCardTypeService.selectAccCardTypeList(accCardType);
        return getDataTable(list);
    }

    /**
     * 导出卡类型列表
     */
    @PreAuthorize("@ss.hasPermi('account:cardtype:export')")
    @Log(title = "卡类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AccCardType accCardType)
    {
        List<AccCardType> list = accCardTypeService.selectAccCardTypeList(accCardType);
        ExcelUtil<AccCardType> util = new ExcelUtil<AccCardType>(AccCardType.class);
        return util.exportExcel(list, "cardtype");
    }

    /**
     * 获取卡类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:cardtype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(accCardTypeService.selectAccCardTypeById(id));
    }

    /**
     * 新增卡类型
     */
    @PreAuthorize("@ss.hasPermi('account:cardtype:add')")
    @Log(title = "卡类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AccCardType accCardType)
    {
        return toAjax(accCardTypeService.insertAccCardType(accCardType));
    }

    /**
     * 修改卡类型
     */
    @PreAuthorize("@ss.hasPermi('account:cardtype:edit')")
    @Log(title = "卡类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AccCardType accCardType)
    {
        return toAjax(accCardTypeService.updateAccCardType(accCardType));
    }

    /**
     * 删除卡类型
     */
    @PreAuthorize("@ss.hasPermi('account:cardtype:remove')")
    @Log(title = "卡类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(accCardTypeService.deleteAccCardTypeByIds(ids));
    }
}
