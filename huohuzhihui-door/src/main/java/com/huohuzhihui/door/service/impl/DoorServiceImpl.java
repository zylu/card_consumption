package com.huohuzhihui.door.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.door.mapper.DoorMapper;
import com.huohuzhihui.door.domain.Door;
import com.huohuzhihui.door.service.IDoorService;

/**
 * 门禁Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
@Service
public class DoorServiceImpl implements IDoorService 
{
    @Autowired
    private DoorMapper doorMapper;

    /**
     * 查询门禁
     * 
     * @param id 门禁ID
     * @return 门禁
     */
    @Override
    public Door selectDoorById(String id)
    {
        return doorMapper.selectDoorById(id);
    }

    /**
     * 查询门禁列表
     * 
     * @param door 门禁
     * @return 门禁
     */
    @Override
    public List<Door> selectDoorList(Door door)
    {
        return doorMapper.selectDoorList(door);
    }

    /**
     * 新增门禁
     * 
     * @param door 门禁
     * @return 结果
     */
    @Override
    public int insertDoor(Door door)
    {
        door.setCreateTime(DateUtils.getNowDate());
        return doorMapper.insertDoor(door);
    }

    /**
     * 修改门禁
     * 
     * @param door 门禁
     * @return 结果
     */
    @Override
    public int updateDoor(Door door)
    {
        door.setUpdateTime(DateUtils.getNowDate());
        return doorMapper.updateDoor(door);
    }

    /**
     * 批量删除门禁
     * 
     * @param ids 需要删除的门禁ID
     * @return 结果
     */
    @Override
    public int deleteDoorByIds(String[] ids)
    {
        return doorMapper.deleteDoorByIds(ids);
    }

    /**
     * 删除门禁信息
     * 
     * @param id 门禁ID
     * @return 结果
     */
    @Override
    public int deleteDoorById(String id)
    {
        return doorMapper.deleteDoorById(id);
    }
}
