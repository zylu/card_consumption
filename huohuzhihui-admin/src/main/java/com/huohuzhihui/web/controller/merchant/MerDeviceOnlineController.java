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
import com.huohuzhihui.system.domain.MerDeviceOnline;
import com.huohuzhihui.system.service.IMerDeviceOnlineService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 商户设备在线离线Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/merchant/online")
public class MerDeviceOnlineController extends BaseController
{
    @Autowired
    private IMerDeviceOnlineService merDeviceOnlineService;

    /**
     * 查询商户设备在线离线列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:online:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerDeviceOnline merDeviceOnline)
    {
        startPage();
        List<MerDeviceOnline> list = merDeviceOnlineService.selectMerDeviceOnlineList(merDeviceOnline);
        return getDataTable(list);
    }

    /**
     * 导出商户设备在线离线列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:online:export')")
    @Log(title = "商户设备在线离线", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MerDeviceOnline merDeviceOnline)
    {
        List<MerDeviceOnline> list = merDeviceOnlineService.selectMerDeviceOnlineList(merDeviceOnline);
        ExcelUtil<MerDeviceOnline> util = new ExcelUtil<MerDeviceOnline>(MerDeviceOnline.class);
        return util.exportExcel(list, "online");
    }

    /**
     * 获取商户设备在线离线详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:online:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(merDeviceOnlineService.selectMerDeviceOnlineById(id));
    }

    /**
     * 新增商户设备在线离线
     */
    @PreAuthorize("@ss.hasPermi('merchant:online:add')")
    @Log(title = "商户设备在线离线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerDeviceOnline merDeviceOnline)
    {
        return toAjax(merDeviceOnlineService.insertMerDeviceOnline(merDeviceOnline));
    }

    /**
     * 修改商户设备在线离线
     */
    @PreAuthorize("@ss.hasPermi('merchant:online:edit')")
    @Log(title = "商户设备在线离线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerDeviceOnline merDeviceOnline)
    {
        return toAjax(merDeviceOnlineService.updateMerDeviceOnline(merDeviceOnline));
    }

    /**
     * 删除商户设备在线离线
     */
    @PreAuthorize("@ss.hasPermi('merchant:online:remove')")
    @Log(title = "商户设备在线离线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merDeviceOnlineService.deleteMerDeviceOnlineByIds(ids));
    }
}
