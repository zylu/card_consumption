package com.huohuzhihui.system.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.system.mapper.MerDeviceOnlineMapper;
import com.huohuzhihui.system.domain.MerDeviceOnline;
import com.huohuzhihui.system.service.IMerDeviceOnlineService;

/**
 * 商户设备在线离线Service业务层处理
 * 
 * @author zylu
 * @date 2020-11-14
 */
@Service
public class MerDeviceOnlineServiceImpl implements IMerDeviceOnlineService 
{
    @Autowired
    private MerDeviceOnlineMapper merDeviceOnlineMapper;

    /**
     * 查询商户设备在线离线
     * 
     * @param id 商户设备在线离线ID
     * @return 商户设备在线离线
     */
    @Override
    public MerDeviceOnline selectMerDeviceOnlineById(Long id)
    {
        return merDeviceOnlineMapper.selectMerDeviceOnlineById(id);
    }

    /**
     * 查询商户设备在线离线列表
     * 
     * @param merDeviceOnline 商户设备在线离线
     * @return 商户设备在线离线
     */
    @Override
    public List<MerDeviceOnline> selectMerDeviceOnlineList(MerDeviceOnline merDeviceOnline)
    {
        return merDeviceOnlineMapper.selectMerDeviceOnlineList(merDeviceOnline);
    }

    /**
     * 新增商户设备在线离线
     * 
     * @param merDeviceOnline 商户设备在线离线
     * @return 结果
     */
    @Override
    public int insertMerDeviceOnline(MerDeviceOnline merDeviceOnline)
    {
        merDeviceOnline.setCreateTime(DateUtils.getNowDate());
        return merDeviceOnlineMapper.insertMerDeviceOnline(merDeviceOnline);
    }

    /**
     * 修改商户设备在线离线
     * 
     * @param merDeviceOnline 商户设备在线离线
     * @return 结果
     */
    @Override
    public int updateMerDeviceOnline(MerDeviceOnline merDeviceOnline)
    {
        merDeviceOnline.setUpdateTime(DateUtils.getNowDate());
        return merDeviceOnlineMapper.updateMerDeviceOnline(merDeviceOnline);
    }

    /**
     * 批量删除商户设备在线离线
     * 
     * @param ids 需要删除的商户设备在线离线ID
     * @return 结果
     */
    @Override
    public int deleteMerDeviceOnlineByIds(Long[] ids)
    {
        return merDeviceOnlineMapper.deleteMerDeviceOnlineByIds(ids);
    }

    /**
     * 删除商户设备在线离线信息
     * 
     * @param id 商户设备在线离线ID
     * @return 结果
     */
    @Override
    public int deleteMerDeviceOnlineById(Long id)
    {
        return merDeviceOnlineMapper.deleteMerDeviceOnlineById(id);
    }
}
