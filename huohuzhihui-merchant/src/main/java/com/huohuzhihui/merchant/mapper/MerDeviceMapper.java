package com.huohuzhihui.merchant.mapper;

import java.util.List;
import java.util.Map;

import com.huohuzhihui.merchant.domain.MerDevice;

/**
 * 商户设备Mapper接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface MerDeviceMapper 
{
    /**
     * 查询商户设备
     * 
     * @param id 商户设备ID
     * @return 商户设备
     */
    public MerDevice selectMerDeviceById(Long id);

    /**
     * 查询商户设备列表
     * 
     * @param merDevice 商户设备
     * @return 商户设备集合
     */
    public List<MerDevice> selectMerDeviceList(MerDevice merDevice);

    /**
     * 新增商户设备
     * 
     * @param merDevice 商户设备
     * @return 结果
     */
    public int insertMerDevice(MerDevice merDevice);

    /**
     * 修改商户设备
     * 
     * @param merDevice 商户设备
     * @return 结果
     */
    public int updateMerDevice(MerDevice merDevice);

    /**
     * 删除商户设备
     * 
     * @param id 商户设备ID
     * @return 结果
     */
    public int deleteMerDeviceById(Long id);

    /**
     * 批量删除商户设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerDeviceByIds(Long[] ids);

    /**
     * 根据设备编号查询设备
     * @param deviceNo 设备编号
     * @return 设备
     */
    public MerDevice selectMerDeviceByNo(String deviceNo);

    /**
     * 查询指定设备的消费金额和消费笔数
     * deviceNo 设备编号
     * createDate 消费日期
     * @return
     */
    public Map<String,String> getSumAndCountByNo(String deviceNo,String createDate);
}
