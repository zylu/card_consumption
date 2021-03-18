package com.huohuzhihui.api.service.impl;

import com.huohuzhihui.api.service.ApiNoticeService;
import com.huohuzhihui.merchant.domain.MerDevice;
import com.huohuzhihui.system.domain.SysNotice;
import com.huohuzhihui.system.mapper.SysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
@Service
public class ApiNoticeServiceImpl implements ApiNoticeService {
    @Autowired
    private  SysNoticeMapper sysNoticeMapper;

    @Override
    public List<SysNotice> findNoticeList(SysNotice sysNotice) {
        return sysNoticeMapper.selectNoticeList(sysNotice);
    }

    @Override
    public SysNotice getNoticeById(Long id) {
        return sysNoticeMapper.selectNoticeById(id);
    }
}

