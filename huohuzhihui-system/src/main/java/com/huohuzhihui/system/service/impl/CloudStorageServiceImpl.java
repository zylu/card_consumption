package com.huohuzhihui.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.system.mapper.CloudStorageMapper;
import com.huohuzhihui.system.domain.CloudStorage;
import com.huohuzhihui.system.service.ICloudStorageService;

/**
 * 云存储Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
@Service
public class CloudStorageServiceImpl implements ICloudStorageService 
{
    @Autowired
    private CloudStorageMapper cloudStorageMapper;

    /**
     * 查询云存储
     * 
     * @param id 云存储ID
     * @return 云存储
     */
    @Override
    public CloudStorage selectCloudStorageById(Long id)
    {
        return cloudStorageMapper.selectCloudStorageById(id);
    }

    /**
     * 查询云存储列表
     * 
     * @param cloudStorage 云存储
     * @return 云存储
     */
    @Override
    public List<CloudStorage> selectCloudStorageList(CloudStorage cloudStorage)
    {
        return cloudStorageMapper.selectCloudStorageList(cloudStorage);
    }

    /**
     * 新增云存储
     * 
     * @param cloudStorage 云存储
     * @return 结果
     */
    @Override
    public int insertCloudStorage(CloudStorage cloudStorage)
    {
        return cloudStorageMapper.insertCloudStorage(cloudStorage);
    }

    /**
     * 修改云存储
     * 
     * @param cloudStorage 云存储
     * @return 结果
     */
    @Override
    public int updateCloudStorage(CloudStorage cloudStorage)
    {
        return cloudStorageMapper.updateCloudStorage(cloudStorage);
    }

    /**
     * 批量删除云存储
     * 
     * @param ids 需要删除的云存储ID
     * @return 结果
     */
    @Override
    public int deleteCloudStorageByIds(Long[] ids)
    {
        return cloudStorageMapper.deleteCloudStorageByIds(ids);
    }

    /**
     * 删除云存储信息
     * 
     * @param id 云存储ID
     * @return 结果
     */
    @Override
    public int deleteCloudStorageById(Long id)
    {
        return cloudStorageMapper.deleteCloudStorageById(id);
    }
}
