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
import com.huohuzhihui.door.domain.Door;
import com.huohuzhihui.door.service.IDoorService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 门禁Controller
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
@RestController
@RequestMapping("/door/door")
public class DoorController extends BaseController
{
    @Autowired
    private IDoorService doorService;

    /**
     * 查询门禁列表
     */
    @PreAuthorize("@ss.hasPermi('door:door:list')")
    @GetMapping("/list")
    public TableDataInfo list(Door door)
    {
        startPage();
        List<Door> list = doorService.selectDoorList(door);
        return getDataTable(list);
    }

    /**
     * 导出门禁列表
     */
    @PreAuthorize("@ss.hasPermi('door:door:export')")
    @Log(title = "门禁", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Door door)
    {
        List<Door> list = doorService.selectDoorList(door);
        ExcelUtil<Door> util = new ExcelUtil<Door>(Door.class);
        return util.exportExcel(list, "door");
    }

    /**
     * 获取门禁详细信息
     */
    @PreAuthorize("@ss.hasPermi('door:door:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(doorService.selectDoorById(id));
    }

    /**
     * 新增门禁
     */
    @PreAuthorize("@ss.hasPermi('door:door:add')")
    @Log(title = "门禁", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Door door)
    {
        return toAjax(doorService.insertDoor(door));
    }

    /**
     * 修改门禁
     */
    @PreAuthorize("@ss.hasPermi('door:door:edit')")
    @Log(title = "门禁", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Door door)
    {
        return toAjax(doorService.updateDoor(door));
    }

    /**
     * 删除门禁
     */
    @PreAuthorize("@ss.hasPermi('door:door:remove')")
    @Log(title = "门禁", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(doorService.deleteDoorByIds(ids));
    }
}
