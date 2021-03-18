package com.huohuzhihui.web.controller.merchant;

import java.util.Date;
import java.util.List;

import com.huohuzhihui.merchant.domain.MerCostReport;
import com.huohuzhihui.merchant.service.IMerCostService;
import com.huohuzhihui.trade.domain.TradeOrderReport;
import com.huohuzhihui.trade.service.ITradeOrderService;
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
import com.huohuzhihui.merchant.domain.MerGoodsOrder;
import com.huohuzhihui.merchant.service.IMerGoodsOrderService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 商户商品订单Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/merchant/order")
public class MerGoodsOrderController extends BaseController
{
    @Autowired
    private IMerGoodsOrderService merGoodsOrderService;
    @Autowired
    private ITradeOrderService iTradeOrderService;
    @Autowired
    private IMerCostService merCostService;


    /**
     * 查询商户商品订单列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerGoodsOrder merGoodsOrder)
    {
        startPage();
        List<MerGoodsOrder> list = merGoodsOrderService.selectMerGoodsOrderList(merGoodsOrder);
        return getDataTable(list);
    }

    /**
     * 导出商户商品订单列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:order:export')")
    @Log(title = "商户商品订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Long merchantId, String beginDate, String endDate)
    {
        //List<MerGoodsOrder> list = merGoodsOrderService.selectMerGoodsOrderList(merGoodsOrder);
        List<TradeOrderReport> list = iTradeOrderService.getOrderStatisticsByMerchantId( merchantId,  beginDate,  endDate);
        ExcelUtil<TradeOrderReport> util = new ExcelUtil<TradeOrderReport>(TradeOrderReport.class);
        return util.exportExcel(list, "营收统计");
    }

    /**
     * 获取商户商品订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(merGoodsOrderService.selectMerGoodsOrderById(id));
    }

    /**
     * 新增商户商品订单
     */
    @PreAuthorize("@ss.hasPermi('merchant:order:add')")
    @Log(title = "商户商品订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerGoodsOrder merGoodsOrder)
    {
        return toAjax(merGoodsOrderService.insertMerGoodsOrder(merGoodsOrder));
    }

    /**
     * 修改商户商品订单
     */
    @PreAuthorize("@ss.hasPermi('merchant:order:edit')")
    @Log(title = "商户商品订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerGoodsOrder merGoodsOrder)
    {
        return toAjax(merGoodsOrderService.updateMerGoodsOrder(merGoodsOrder));
    }

    /**
     * 删除商户商品订单
     */
    @PreAuthorize("@ss.hasPermi('merchant:order:remove')")
    @Log(title = "商户商品订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merGoodsOrderService.deleteMerGoodsOrderByIds(ids));
    }

    /**
     * 查询商户商品订单列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:order:report')")
    @GetMapping("/report")
    public TableDataInfo report(Long merchantId, String beginDate, String endDate)
    {
        startPage();
        List<MerCostReport> list = merCostService.getOrderStatisticsByMerchantId( merchantId,  beginDate,  endDate);
        return getDataTable(list);
    }
}
