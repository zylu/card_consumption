package com.huohuzhihui.web.controller.trade;

import java.util.List;

import com.huohuzhihui.account.constant.AccountConstants;
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
import com.huohuzhihui.trade.domain.AccTradeRecord;
import com.huohuzhihui.trade.service.IAccTradeRecordService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 交易记录Controller
 * 
 * @author huohuzhihui
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/trade/record")
public class AccTradeRecordController extends BaseController
{
    @Autowired
    private IAccTradeRecordService accTradeRecordService;




    /**
     * 查询充值记录列表
     */
    @PreAuthorize("@ss.hasPermi('trade:record:rechargeList')")
    @GetMapping("/rechargeList")
    public TableDataInfo rechargeList(AccTradeRecord accTradeRecord)
    {
        startPage();
        accTradeRecord.setType(AccountConstants.OPER_RECHARGE);
        List<AccTradeRecord> list = accTradeRecordService.selectAccTradeRecordList(accTradeRecord);
        return getDataTable(list);
    }
    /**
     * 查询消费记录列表
     */
    @PreAuthorize("@ss.hasPermi('trade:record:consumeList')")
    @GetMapping("/consumeList")
    public TableDataInfo consumeList(AccTradeRecord accTradeRecord)
    {
        startPage();
        accTradeRecord.setType(AccountConstants.OPER_CONSUME);
        List<AccTradeRecord> list = accTradeRecordService.selectAccTradeRecordList(accTradeRecord);
        return getDataTable(list);
    }

    /**
     * 查询交易记录列表
     */
    @PreAuthorize("@ss.hasPermi('trade:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(AccTradeRecord accTradeRecord)
    {
        startPage();
        List<AccTradeRecord> list = accTradeRecordService.selectAccTradeRecordList(accTradeRecord);
        return getDataTable(list);
    }

    /**
     * 导出交易记录列表
     */
    @PreAuthorize("@ss.hasPermi('trade:record:export')")
    @Log(title = "交易记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AccTradeRecord accTradeRecord)
    {
        List<AccTradeRecord> list = accTradeRecordService.selectAccTradeRecordList(accTradeRecord);
        ExcelUtil<AccTradeRecord> util = new ExcelUtil<AccTradeRecord>(AccTradeRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取交易记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('trade:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(accTradeRecordService.selectAccTradeRecordById(id));
    }

    /**
     * 新增交易记录
     */
    @PreAuthorize("@ss.hasPermi('trade:record:add')")
    @Log(title = "交易记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AccTradeRecord accTradeRecord)
    {
        return toAjax(accTradeRecordService.insertAccTradeRecord(accTradeRecord));
    }

    /**
     * 修改交易记录
     */
    @PreAuthorize("@ss.hasPermi('trade:record:edit')")
    @Log(title = "交易记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AccTradeRecord accTradeRecord)
    {
        return toAjax(accTradeRecordService.updateAccTradeRecord(accTradeRecord));
    }

    /**
     * 删除交易记录
     */
    @PreAuthorize("@ss.hasPermi('trade:record:remove')")
    @Log(title = "交易记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(accTradeRecordService.deleteAccTradeRecordByIds(ids));
    }
}
