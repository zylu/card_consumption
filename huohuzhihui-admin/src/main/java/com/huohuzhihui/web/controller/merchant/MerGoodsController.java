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
import com.huohuzhihui.merchant.domain.MerGoods;
import com.huohuzhihui.merchant.service.IMerGoodsService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 商户商品Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/merchant/goods")
public class MerGoodsController extends BaseController
{
    @Autowired
    private IMerGoodsService merGoodsService;

    /**
     * 查询商户商品列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerGoods merGoods)
    {
        startPage();
        List<MerGoods> list = merGoodsService.selectMerGoodsList(merGoods);
        return getDataTable(list);
    }

    /**
     * 导出商户商品列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:goods:export')")
    @Log(title = "商户商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MerGoods merGoods)
    {
        List<MerGoods> list = merGoodsService.selectMerGoodsList(merGoods);
        ExcelUtil<MerGoods> util = new ExcelUtil<MerGoods>(MerGoods.class);
        return util.exportExcel(list, "goods");
    }

    /**
     * 获取商户商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:goods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(merGoodsService.selectMerGoodsById(id));
    }

    /**
     * 新增商户商品
     */
    @PreAuthorize("@ss.hasPermi('merchant:goods:add')")
    @Log(title = "商户商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerGoods merGoods)
    {
        return toAjax(merGoodsService.insertMerGoods(merGoods));
    }

    /**
     * 修改商户商品
     */
    @PreAuthorize("@ss.hasPermi('merchant:goods:edit')")
    @Log(title = "商户商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerGoods merGoods)
    {
        return toAjax(merGoodsService.updateMerGoods(merGoods));
    }

    /**
     * 删除商户商品
     */
    @PreAuthorize("@ss.hasPermi('merchant:goods:remove')")
    @Log(title = "商户商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merGoodsService.deleteMerGoodsByIds(ids));
    }
}
