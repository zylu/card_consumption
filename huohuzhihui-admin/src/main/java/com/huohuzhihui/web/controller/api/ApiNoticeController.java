package com.huohuzhihui.web.controller.api;

import com.huohuzhihui.api.service.ApiAccountService;
import com.huohuzhihui.api.service.ApiNoticeService;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.AjaxResult;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.common.core.page.TableDataInfo;
import com.huohuzhihui.common.utils.SecurityUtils;
import com.huohuzhihui.framework.web.service.TokenService;
import com.huohuzhihui.system.domain.SysNotice;
import com.huohuzhihui.trade.domain.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 通知公告Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/api/notice")
public class ApiNoticeController extends BaseController
{
    @Autowired
    private ApiNoticeService apiNoticeService;

    @RequestMapping("/findNoticeList")
    @ResponseBody
    public TableDataInfo findNoticeList(SysNotice sysNotice){
        sysNotice.setStatus("0");

        startPage();
        List<SysNotice> list = apiNoticeService.findNoticeList(sysNotice);
        return getDataTable(list);
    }
    @GetMapping("/getNotice")
    @ResponseBody
    public AjaxResult getNotice(Long id){
        return new AjaxResult(200, "查询通知公告成功", apiNoticeService.getNoticeById(id));
    }



}
