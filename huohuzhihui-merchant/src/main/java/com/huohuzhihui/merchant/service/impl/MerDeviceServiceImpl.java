package com.huohuzhihui.merchant.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.merchant.mapper.MerDeviceMapper;
import com.huohuzhihui.merchant.domain.MerDevice;
import com.huohuzhihui.merchant.service.IMerDeviceService;

/**
 * 商户设备Service业务层处理
 * 
 * @author zylu
 * @date 2020-11-14
 */
@Service
public class MerDeviceServiceImpl implements IMerDeviceService 
{
    @Autowired
    private MerDeviceMapper merDeviceMapper;
    /**
     * 查询商户设备
     * 
     * @param id 商户设备ID
     * @return 商户设备
     */
    @Override
    public MerDevice selectMerDeviceById(Long id)
    {
        return merDeviceMapper.selectMerDeviceById(id);
    }

    @Override
    public MerDevice selectMerDeviceByNo(String no) {
        return merDeviceMapper.selectMerDeviceByNo(no);
    }

    /**
     * 查询商户设备列表
     * 
     * @param merDevice 商户设备
     * @return 商户设备
     */
    @Override
    public List<MerDevice> selectMerDeviceList(MerDevice merDevice)
    {
        return merDeviceMapper.selectMerDeviceList(merDevice);
    }

    /**
     * 新增商户设备
     * 
     * @param merDevice 商户设备
     * @return 结果
     */
    @Override
    public int insertMerDevice(MerDevice merDevice)
    {
        String loginUser = SecurityUtils.getLoginUser().getUsername();
        merDevice.setCreateBy(loginUser);
        merDevice.setCreateTime(DateUtils.getNowDate());
        merDevice.setUpdateBy(loginUser);
        merDevice.setUpdateTime(DateUtils.getNowDate());
        return merDeviceMapper.insertMerDevice(merDevice);
    }

    /**
     * 修改商户设备
     * 
     * @param merDevice 商户设备
     * @return 结果
     */
    @Override
    public int updateMerDevice(MerDevice merDevice)
    {
        merDevice.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        merDevice.setUpdateTime(DateUtils.getNowDate());
        return merDeviceMapper.updateMerDevice(merDevice);
    }

    /**
     * 批量删除商户设备
     * 
     * @param ids 需要删除的商户设备ID
     * @return 结果
     */
    @Override
    public int deleteMerDeviceByIds(Long[] ids)
    {
        return merDeviceMapper.deleteMerDeviceByIds(ids);
    }

    /**
     * 删除商户设备信息
     * 
     * @param id 商户设备ID
     * @return 结果
     */
    @Override
    public int deleteMerDeviceById(Long id)
    {
        return merDeviceMapper.deleteMerDeviceById(id);
    }

    @Override
    public Map<String, String> getSumAndCountByNo(String realPosNo,String createDate) {
        return merDeviceMapper.getSumAndCountByNo(realPosNo,createDate);
    }

}
