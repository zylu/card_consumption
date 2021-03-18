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
import com.huohuzhihui.door.domain.DoorAuth;
import com.huohuzhihui.door.service.IDoorAuthService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 门禁授权Controller
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
@RestController
@RequestMapping("/door/auth")
public class DoorAuthController extends BaseController
{
    @Autowired
    private IDoorAuthService doorAuthService;

    /**
     * 查询门禁授权列表
     */
    @PreAuthorize("@ss.hasPermi('door:auth:list')")
    @GetMapping("/list")
    public TableDataInfo list(DoorAuth doorAuth)
    {
        startPage();
        List<DoorAuth> list = doorAuthService.selectDoorAuthList(doorAuth);
        return getDataTable(list);
    }

    /**
     * 导出门禁授权列表
     */
    @PreAuthorize("@ss.hasPermi('door:auth:export')")
    @Log(title = "门禁授权", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DoorAuth doorAuth)
    {
        List<DoorAuth> list = doorAuthService.selectDoorAuthList(doorAuth);
        ExcelUtil<DoorAuth> util = new ExcelUtil<DoorAuth>(DoorAuth.class);
        return util.exportExcel(list, "auth");
    }

    /**
     * 获取门禁授权详细信息
     */
    @PreAuthorize("@ss.hasPermi('door:auth:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(doorAuthService.selectDoorAuthById(id));
    }

    /**
     * 新增门禁授权
     */
    @PreAuthorize("@ss.hasPermi('door:auth:add')")
    @Log(title = "门禁授权", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DoorAuth doorAuth)
    {
        return toAjax(doorAuthService.insertDoorAuth(doorAuth));
    }

    /**
     * 修改门禁授权
     */
    @PreAuthorize("@ss.hasPermi('door:auth:edit')")
    @Log(title = "门禁授权", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DoorAuth doorAuth)
    {
        return toAjax(doorAuthService.updateDoorAuth(doorAuth));
    }

    /**
     * 删除门禁授权
     */
    @PreAuthorize("@ss.hasPermi('door:auth:remove')")
    @Log(title = "门禁授权", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(doorAuthService.deleteDoorAuthByIds(ids));
    }
}
