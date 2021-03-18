package com.huohuzhihui.web.controller.trade;

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
import com.huohuzhihui.trade.domain.TradeOrder;
import com.huohuzhihui.trade.service.ITradeOrderService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author huohuzhihui
 * @date 2020-11-18
 */
@RestController
@RequestMapping("/trade/order")
public class TradeOrderController extends BaseController
{
    @Autowired
    private ITradeOrderService tradeOrderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('trade:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(TradeOrder tradeOrder)
    {
        startPage();
        List<TradeOrder> list = tradeOrderService.selectTradeOrderList(tradeOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('trade:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TradeOrder tradeOrder)
    {
        List<TradeOrder> list = tradeOrderService.selectTradeOrderList(tradeOrder);
        ExcelUtil<TradeOrder> util = new ExcelUtil<TradeOrder>(TradeOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('trade:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tradeOrderService.selectTradeOrderById(id));
    }




}
