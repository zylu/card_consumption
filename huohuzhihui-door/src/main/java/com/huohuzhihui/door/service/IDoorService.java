package com.huohuzhihui.door.service;

import java.util.List;
import com.huohuzhihui.door.domain.Door;

/**
 * 门禁Service接口
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
public interface IDoorService 
{
    /**
     * 查询门禁
     * 
     * @param id 门禁ID
     * @return 门禁
     */
    public Door selectDoorById(String id);

    /**
     * 查询门禁列表
     * 
     * @param door 门禁
     * @return 门禁集合
     */
    public List<Door> selectDoorList(Door door);

    /**
     * 新增门禁
     * 
     * @param door 门禁
     * @return 结果
     */
    public int insertDoor(Door door);

    /**
     * 修改门禁
     * 
     * @param door 门禁
     * @return 结果
     */
    public int updateDoor(Door door);

    /**
     * 批量删除门禁
     * 
     * @param ids 需要删除的门禁ID
     * @return 结果
     */
    public int deleteDoorByIds(String[] ids);

    /**
     * 删除门禁信息
     * 
     * @param id 门禁ID
     * @return 结果
     */
    public int deleteDoorById(String id);
}
