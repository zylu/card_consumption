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
import com.huohuzhihui.merchant.domain.MerDevice;
import com.huohuzhihui.merchant.service.IMerDeviceService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 商户设备Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/merchant/device")
public class MerDeviceController extends BaseController
{
    @Autowired
    private IMerDeviceService merDeviceService;

    /**
     * 查询商户设备列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:device:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerDevice merDevice)
    {
        startPage();
        List<MerDevice> list = merDeviceService.selectMerDeviceList(merDevice);
        return getDataTable(list);
    }

    /**
     * 导出商户设备列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:device:export')")
    @Log(title = "商户设备", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MerDevice merDevice)
    {
        List<MerDevice> list = merDeviceService.selectMerDeviceList(merDevice);
        ExcelUtil<MerDevice> util = new ExcelUtil<MerDevice>(MerDevice.class);
        return util.exportExcel(list, "device");
    }

    /**
     * 获取商户设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:device:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(merDeviceService.selectMerDeviceById(id));
    }

    /**
     * 新增商户设备
     */
    @PreAuthorize("@ss.hasPermi('merchant:device:add')")
    @Log(title = "商户设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerDevice merDevice)
    {
        return toAjax(merDeviceService.insertMerDevice(merDevice));
    }

    /**
     * 修改商户设备
     */
    @PreAuthorize("@ss.hasPermi('merchant:device:edit')")
    @Log(title = "商户设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerDevice merDevice)
    {
        return toAjax(merDeviceService.updateMerDevice(merDevice));
    }

    /**
     * 删除商户设备
     */
    @PreAuthorize("@ss.hasPermi('merchant:device:remove')")
    @Log(title = "商户设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merDeviceService.deleteMerDeviceByIds(ids));
    }
}
