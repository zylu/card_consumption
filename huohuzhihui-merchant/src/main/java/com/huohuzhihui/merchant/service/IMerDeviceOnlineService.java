package com.huohuzhihui.system.service;

import java.util.List;
import com.huohuzhihui.system.domain.MerDeviceOnline;

/**
 * 商户设备在线离线Service接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface IMerDeviceOnlineService 
{
    /**
     * 查询商户设备在线离线
     * 
     * @param id 商户设备在线离线ID
     * @return 商户设备在线离线
     */
    public MerDeviceOnline selectMerDeviceOnlineById(Long id);

    /**
     * 查询商户设备在线离线列表
     * 
     * @param merDeviceOnline 商户设备在线离线
     * @return 商户设备在线离线集合
     */
    public List<MerDeviceOnline> selectMerDeviceOnlineList(MerDeviceOnline merDeviceOnline);

    /**
     * 新增商户设备在线离线
     * 
     * @param merDeviceOnline 商户设备在线离线
     * @return 结果
     */
    public int insertMerDeviceOnline(MerDeviceOnline merDeviceOnline);

    /**
     * 修改商户设备在线离线
     * 
     * @param merDeviceOnline 商户设备在线离线
     * @return 结果
     */
    public int updateMerDeviceOnline(MerDeviceOnline merDeviceOnline);

    /**
     * 批量删除商户设备在线离线
     * 
     * @param ids 需要删除的商户设备在线离线ID
     * @return 结果
     */
    public int deleteMerDeviceOnlineByIds(Long[] ids);

    /**
     * 删除商户设备在线离线信息
     * 
     * @param id 商户设备在线离线ID
     * @return 结果
     */
    public int deleteMerDeviceOnlineById(Long id);
}
