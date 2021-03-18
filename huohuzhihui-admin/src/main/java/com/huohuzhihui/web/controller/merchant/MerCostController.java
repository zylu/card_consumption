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
import com.huohuzhihui.merchant.domain.MerCost;
import com.huohuzhihui.merchant.service.IMerCostService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 商户消费Controller
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
@RestController
@RequestMapping("/trade/cost")
public class MerCostController extends BaseController
{
    @Autowired
    private IMerCostService merCostService;

    /**
     * 查询商户消费列表
     */
    @PreAuthorize("@ss.hasPermi('trade:cost:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerCost merCost)
    {
        startPage();
        List<MerCost> list = merCostService.selectMerCostList(merCost);
        return getDataTable(list);
    }

    /**
     * 导出商户消费列表
     */
    @PreAuthorize("@ss.hasPermi('trade:cost:export')")
    @Log(title = "商户消费", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MerCost merCost)
    {
        List<MerCost> list = merCostService.selectMerCostList(merCost);
        ExcelUtil<MerCost> util = new ExcelUtil<MerCost>(MerCost.class);
        return util.exportExcel(list, "cost");
    }


    /**
     * 新增商户消费
     */
    @PreAuthorize("@ss.hasPermi('trade:cost:add')")
    @Log(title = "商户消费", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerCost merCost)
    {
        return toAjax(merCostService.insertMerCost(merCost));
    }

}
