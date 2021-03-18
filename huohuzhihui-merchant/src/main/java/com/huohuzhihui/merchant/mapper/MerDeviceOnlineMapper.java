package com.huohuzhihui.system.mapper;

import java.util.List;
import com.huohuzhihui.system.domain.MerDeviceOnline;

/**
 * 商户设备在线离线Mapper接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface MerDeviceOnlineMapper 
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
     * 删除商户设备在线离线
     * 
     * @param id 商户设备在线离线ID
     * @return 结果
     */
    public int deleteMerDeviceOnlineById(Long id);

    /**
     * 批量删除商户设备在线离线
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerDeviceOnlineByIds(Long[] ids);
}
