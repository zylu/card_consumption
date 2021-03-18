package com.huohuzhihui.door.controller;

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
import com.huohuzhihui.door.domain.DoorRecord;
import com.huohuzhihui.door.service.IDoorRecordService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 门禁记录Controller
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
@RestController
@RequestMapping("/door/record")
public class DoorRecordController extends BaseController
{
    @Autowired
    private IDoorRecordService doorRecordService;

    /**
     * 查询门禁记录列表
     */
    @PreAuthorize("@ss.hasPermi('door:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(DoorRecord doorRecord)
    {
        startPage();
        List<DoorRecord> list = doorRecordService.selectDoorRecordList(doorRecord);
        return getDataTable(list);
    }

    /**
     * 导出门禁记录列表
     */
    @PreAuthorize("@ss.hasPermi('door:record:export')")
    @Log(title = "门禁记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DoorRecord doorRecord)
    {
        List<DoorRecord> list = doorRecordService.selectDoorRecordList(doorRecord);
        ExcelUtil<DoorRecord> util = new ExcelUtil<DoorRecord>(DoorRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取门禁记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('door:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(doorRecordService.selectDoorRecordById(id));
    }

    /**
     * 新增门禁记录
     */
    @PreAuthorize("@ss.hasPermi('door:record:add')")
    @Log(title = "门禁记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DoorRecord doorRecord)
    {
        return toAjax(doorRecordService.insertDoorRecord(doorRecord));
    }

    /**
     * 修改门禁记录
     */
    @PreAuthorize("@ss.hasPermi('door:record:edit')")
    @Log(title = "门禁记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DoorRecord doorRecord)
    {
        return toAjax(doorRecordService.updateDoorRecord(doorRecord));
    }

    /**
     * 删除门禁记录
     */
    @PreAuthorize("@ss.hasPermi('door:record:remove')")
    @Log(title = "门禁记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(doorRecordService.deleteDoorRecordByIds(ids));
    }
}
