package com.huohuzhihui.system.service;

import java.util.List;
import com.huohuzhihui.system.domain.TCloudStorage;

/**
 * 云存储Service接口
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
public interface ITCloudStorageService 
{
    /**
     * 查询云存储
     * 
     * @param id 云存储ID
     * @return 云存储
     */
    public TCloudStorage selectTCloudStorageById(Long id);

    /**
     * 查询云存储列表
     * 
     * @param tCloudStorage 云存储
     * @return 云存储集合
     */
    public List<TCloudStorage> selectTCloudStorageList(TCloudStorage tCloudStorage);

    /**
     * 新增云存储
     * 
     * @param tCloudStorage 云存储
     * @return 结果
     */
    public int insertTCloudStorage(TCloudStorage tCloudStorage);

    /**
     * 修改云存储
     * 
     * @param tCloudStorage 云存储
     * @return 结果
     */
    public int updateTCloudStorage(TCloudStorage tCloudStorage);

    /**
     * 批量删除云存储
     * 
     * @param ids 需要删除的云存储ID
     * @return 结果
     */
    public int deleteTCloudStorageByIds(Long[] ids);

    /**
     * 删除云存储信息
     * 
     * @param id 云存储ID
     * @return 结果
     */
    public int deleteTCloudStorageById(Long id);
}
