package com.huohuzhihui.api.service;

import com.huohuzhihui.system.domain.SysNotice;

import java.util.List;

/**
 * 通知公告Service接口
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
public interface ApiNoticeService
{
    public List<SysNotice> findNoticeList(SysNotice sysNotice);

    public SysNotice getNoticeById(Long id);
}
