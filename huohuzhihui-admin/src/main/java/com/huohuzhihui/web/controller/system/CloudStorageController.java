package com.huohuzhihui.web.controller.system;

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
import com.huohuzhihui.system.domain.CloudStorage;
import com.huohuzhihui.system.service.ICloudStorageService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 云存储Controller
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
@RestController
@RequestMapping("/system/storage")
public class CloudStorageController extends BaseController
{
    @Autowired
    private ICloudStorageService cloudStorageService;

    /**
     * 查询云存储列表
     */
    @PreAuthorize("@ss.hasPermi('system:storage:list')")
    @GetMapping("/list")
    public TableDataInfo list(CloudStorage cloudStorage)
    {
        startPage();
        List<CloudStorage> list = cloudStorageService.selectCloudStorageList(cloudStorage);
        return getDataTable(list);
    }

    /**
     * 导出云存储列表
     */
    @PreAuthorize("@ss.hasPermi('system:storage:export')")
    @Log(title = "云存储", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CloudStorage cloudStorage)
    {
        List<CloudStorage> list = cloudStorageService.selectCloudStorageList(cloudStorage);
        ExcelUtil<CloudStorage> util = new ExcelUtil<CloudStorage>(CloudStorage.class);
        return util.exportExcel(list, "storage");
    }

    /**
     * 获取云存储详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:storage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cloudStorageService.selectCloudStorageById(id));
    }

    /**
     * 新增云存储
     */
    @PreAuthorize("@ss.hasPermi('system:storage:add')")
    @Log(title = "云存储", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CloudStorage cloudStorage)
    {
        return toAjax(cloudStorageService.insertCloudStorage(cloudStorage));
    }

    /**
     * 修改云存储
     */
    @PreAuthorize("@ss.hasPermi('system:storage:edit')")
    @Log(title = "云存储", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CloudStorage cloudStorage)
    {
        return toAjax(cloudStorageService.updateCloudStorage(cloudStorage));
    }

    /**
     * 删除云存储
     */
    @PreAuthorize("@ss.hasPermi('system:storage:remove')")
    @Log(title = "云存储", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cloudStorageService.deleteCloudStorageByIds(ids));
    }
}
