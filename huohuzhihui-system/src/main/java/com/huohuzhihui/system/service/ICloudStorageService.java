package com.huohuzhihui.system.service;

import java.util.List;
import com.huohuzhihui.system.domain.CloudStorage;

/**
 * 云存储Service接口
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
public interface ICloudStorageService 
{
    /**
     * 查询云存储
     * 
     * @param id 云存储ID
     * @return 云存储
     */
    public CloudStorage selectCloudStorageById(Long id);

    /**
     * 查询云存储列表
     * 
     * @param cloudStorage 云存储
     * @return 云存储集合
     */
    public List<CloudStorage> selectCloudStorageList(CloudStorage cloudStorage);

    /**
     * 新增云存储
     * 
     * @param cloudStorage 云存储
     * @return 结果
     */
    public int insertCloudStorage(CloudStorage cloudStorage);

    /**
     * 修改云存储
     * 
     * @param cloudStorage 云存储
     * @return 结果
     */
    public int updateCloudStorage(CloudStorage cloudStorage);

    /**
     * 批量删除云存储
     * 
     * @param ids 需要删除的云存储ID
     * @return 结果
     */
    public int deleteCloudStorageByIds(Long[] ids);

    /**
     * 删除云存储信息
     * 
     * @param id 云存储ID
     * @return 结果
     */
    public int deleteCloudStorageById(Long id);
}
