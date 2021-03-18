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
import com.huohuzhihui.door.domain.DoorGroup;
import com.huohuzhihui.door.service.IDoorGroupService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 门分组Controller
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
@RestController
@RequestMapping("/door/group")
public class DoorGroupController extends BaseController
{
    @Autowired
    private IDoorGroupService doorGroupService;

    /**
     * 查询门分组列表
     */
    @PreAuthorize("@ss.hasPermi('door:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(DoorGroup doorGroup)
    {
        startPage();
        List<DoorGroup> list = doorGroupService.selectDoorGroupList(doorGroup);
        return getDataTable(list);
    }

    /**
     * 导出门分组列表
     */
    @PreAuthorize("@ss.hasPermi('door:group:export')")
    @Log(title = "门分组", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DoorGroup doorGroup)
    {
        List<DoorGroup> list = doorGroupService.selectDoorGroupList(doorGroup);
        ExcelUtil<DoorGroup> util = new ExcelUtil<DoorGroup>(DoorGroup.class);
        return util.exportExcel(list, "group");
    }

    /**
     * 获取门分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('door:group:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(doorGroupService.selectDoorGroupById(id));
    }

    /**
     * 新增门分组
     */
    @PreAuthorize("@ss.hasPermi('door:group:add')")
    @Log(title = "门分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DoorGroup doorGroup)
    {
        return toAjax(doorGroupService.insertDoorGroup(doorGroup));
    }

    /**
     * 修改门分组
     */
    @PreAuthorize("@ss.hasPermi('door:group:edit')")
    @Log(title = "门分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DoorGroup doorGroup)
    {
        return toAjax(doorGroupService.updateDoorGroup(doorGroup));
    }

    /**
     * 删除门分组
     */
    @PreAuthorize("@ss.hasPermi('door:group:remove')")
    @Log(title = "门分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(doorGroupService.deleteDoorGroupByIds(ids));
    }
}
