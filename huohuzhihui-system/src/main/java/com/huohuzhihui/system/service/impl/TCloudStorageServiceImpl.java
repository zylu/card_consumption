package com.huohuzhihui.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.system.mapper.TCloudStorageMapper;
import com.huohuzhihui.system.domain.TCloudStorage;
import com.huohuzhihui.system.service.ITCloudStorageService;

/**
 * 云存储Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
@Service
public class TCloudStorageServiceImpl implements ITCloudStorageService 
{
    @Autowired
    private TCloudStorageMapper tCloudStorageMapper;

    /**
     * 查询云存储
     * 
     * @param id 云存储ID
     * @return 云存储
     */
    @Override
    public TCloudStorage selectTCloudStorageById(Long id)
    {
        return tCloudStorageMapper.selectTCloudStorageById(id);
    }

    /**
     * 查询云存储列表
     * 
     * @param tCloudStorage 云存储
     * @return 云存储
     */
    @Override
    public List<TCloudStorage> selectTCloudStorageList(TCloudStorage tCloudStorage)
    {
        return tCloudStorageMapper.selectTCloudStorageList(tCloudStorage);
    }

    /**
     * 新增云存储
     * 
     * @param tCloudStorage 云存储
     * @return 结果
     */
    @Override
    public int insertTCloudStorage(TCloudStorage tCloudStorage)
    {
        return tCloudStorageMapper.insertTCloudStorage(tCloudStorage);
    }

    /**
     * 修改云存储
     * 
     * @param tCloudStorage 云存储
     * @return 结果
     */
    @Override
    public int updateTCloudStorage(TCloudStorage tCloudStorage)
    {
        return tCloudStorageMapper.updateTCloudStorage(tCloudStorage);
    }

    /**
     * 批量删除云存储
     * 
     * @param ids 需要删除的云存储ID
     * @return 结果
     */
    @Override
    public int deleteTCloudStorageByIds(Long[] ids)
    {
        return tCloudStorageMapper.deleteTCloudStorageByIds(ids);
    }

    /**
     * 删除云存储信息
     * 
     * @param id 云存储ID
     * @return 结果
     */
    @Override
    public int deleteTCloudStorageById(Long id)
    {
        return tCloudStorageMapper.deleteTCloudStorageById(id);
    }
}
