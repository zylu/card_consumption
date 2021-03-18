package com.huohuzhihui.web.controller.system;

import java.io.IOException;
import java.util.List;

import com.huohuzhihui.common.config.RuoYiConfig;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.common.utils.ServletUtils;
import com.huohuzhihui.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.huohuzhihui.common.annotation.Log;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.AjaxResult;
import com.huohuzhihui.common.core.page.TableDataInfo;
import com.huohuzhihui.common.enums.BusinessType;
import com.huohuzhihui.common.utils.SecurityUtils;
import com.huohuzhihui.system.domain.SysNotice;
import com.huohuzhihui.system.service.ISysNoticeService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公告 信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    @Autowired
    private ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId)
    {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestParam("mainImg") MultipartFile file,SysNotice notice)
            throws IOException {
        String imgUrl = "";
        if (!file.isEmpty())
        {
            imgUrl = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
        }
        notice.setMainimgUrl(imgUrl);
        notice.setCreateBy(SecurityUtils.getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestParam("mainImg") MultipartFile file, SysNotice notice)
            throws IOException{
        String imgUrl = "";
        if (!file.isEmpty())
        {
            imgUrl = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
        }
        notice.setMainimgUrl(imgUrl);
        notice.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
