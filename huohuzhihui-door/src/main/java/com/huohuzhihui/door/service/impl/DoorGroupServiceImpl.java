package com.huohuzhihui.door.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.door.mapper.DoorGroupMapper;
import com.huohuzhihui.door.domain.DoorGroup;
import com.huohuzhihui.door.service.IDoorGroupService;

/**
 * 门分组Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
@Service
public class DoorGroupServiceImpl implements IDoorGroupService 
{
    @Autowired
    private DoorGroupMapper doorGroupMapper;

    /**
     * 查询门分组
     * 
     * @param id 门分组ID
     * @return 门分组
     */
    @Override
    public DoorGroup selectDoorGroupById(String id)
    {
        return doorGroupMapper.selectDoorGroupById(id);
    }

    /**
     * 查询门分组列表
     * 
     * @param doorGroup 门分组
     * @return 门分组
     */
    @Override
    public List<DoorGroup> selectDoorGroupList(DoorGroup doorGroup)
    {
        return doorGroupMapper.selectDoorGroupList(doorGroup);
    }

    /**
     * 新增门分组
     * 
     * @param doorGroup 门分组
     * @return 结果
     */
    @Override
    public int insertDoorGroup(DoorGroup doorGroup)
    {
        doorGroup.setCreateTime(DateUtils.getNowDate());
        return doorGroupMapper.insertDoorGroup(doorGroup);
    }

    /**
     * 修改门分组
     * 
     * @param doorGroup 门分组
     * @return 结果
     */
    @Override
    public int updateDoorGroup(DoorGroup doorGroup)
    {
        doorGroup.setUpdateTime(DateUtils.getNowDate());
        return doorGroupMapper.updateDoorGroup(doorGroup);
    }

    /**
     * 批量删除门分组
     * 
     * @param ids 需要删除的门分组ID
     * @return 结果
     */
    @Override
    public int deleteDoorGroupByIds(String[] ids)
    {
        return doorGroupMapper.deleteDoorGroupByIds(ids);
    }

    /**
     * 删除门分组信息
     * 
     * @param id 门分组ID
     * @return 结果
     */
    @Override
    public int deleteDoorGroupById(String id)
    {
        return doorGroupMapper.deleteDoorGroupById(id);
    }
}
