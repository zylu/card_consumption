package com.huohuzhihui.merchant.service;

import java.util.List;
import java.util.Map;

import com.huohuzhihui.merchant.domain.MerDevice;

/**
 * 商户设备Service接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface IMerDeviceService 
{
    /**
     * 查询商户设备
     * 
     * @param id 商户设备ID
     * @return 商户设备
     */
    public MerDevice selectMerDeviceById(Long id);


    /**
     * 查询商户设备
     *
     * @param no 商户设备编号
     * @return 商户设备
     */
    public MerDevice selectMerDeviceByNo(String no);

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
     * 批量删除商户设备
     * 
     * @param ids 需要删除的商户设备ID
     * @return 结果
     */
    public int deleteMerDeviceByIds(Long[] ids);

    /**
     * 删除商户设备信息
     * 
     * @param id 商户设备ID
     * @return 结果
     */
    public int deleteMerDeviceById(Long id);


    /**
     * 查询指定设备的消费总额和消费笔数
     * @param realPosNo 设备编号
     * @param createDate 消费日期
     * @return
     */
    Map<String,String> getSumAndCountByNo(String realPosNo,String createDate);
}
