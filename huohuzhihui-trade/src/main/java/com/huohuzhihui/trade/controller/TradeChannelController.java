package com.huohuzhihui.trade.controller;

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
import com.huohuzhihui.trade.domain.TradeChannel;
import com.huohuzhihui.trade.service.ITradeChannelService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 交易渠道Controller
 * 
 * @author huohuzhihui
 * @date 2020-11-17
 */
@RestController
@RequestMapping("/trade/channel")
public class TradeChannelController extends BaseController
{
    @Autowired
    private ITradeChannelService tradeChannelService;

    /**
     * 查询交易渠道列表
     */
    @PreAuthorize("@ss.hasPermi('trade:channel:list')")
    @GetMapping("/list")
    public TableDataInfo list(TradeChannel tradeChannel)
    {
        startPage();
        List<TradeChannel> list = tradeChannelService.selectTradeChannelList(tradeChannel);
        return getDataTable(list);
    }

    /**
     * 导出交易渠道列表
     */
    @PreAuthorize("@ss.hasPermi('trade:channel:export')")
    @Log(title = "交易渠道", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TradeChannel tradeChannel)
    {
        List<TradeChannel> list = tradeChannelService.selectTradeChannelList(tradeChannel);
        ExcelUtil<TradeChannel> util = new ExcelUtil<TradeChannel>(TradeChannel.class);
        return util.exportExcel(list, "channel");
    }

    /**
     * 获取交易渠道详细信息
     */
    @PreAuthorize("@ss.hasPermi('trade:channel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tradeChannelService.selectTradeChannelById(id));
    }

    /**
     * 新增交易渠道
     */
    @PreAuthorize("@ss.hasPermi('trade:channel:add')")
    @Log(title = "交易渠道", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TradeChannel tradeChannel)
    {
        return toAjax(tradeChannelService.insertTradeChannel(tradeChannel));
    }

    /**
     * 修改交易渠道
     */
    @PreAuthorize("@ss.hasPermi('trade:channel:edit')")
    @Log(title = "交易渠道", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TradeChannel tradeChannel)
    {
        return toAjax(tradeChannelService.updateTradeChannel(tradeChannel));
    }

    /**
     * 删除交易渠道
     */
    @PreAuthorize("@ss.hasPermi('trade:channel:remove')")
    @Log(title = "交易渠道", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tradeChannelService.deleteTradeChannelByIds(ids));
    }
}
