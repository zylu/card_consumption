package com.huohuzhihui.door.mapper;

import java.util.List;
import com.huohuzhihui.door.domain.DoorGroup;

/**
 * 门分组Mapper接口
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
public interface DoorGroupMapper 
{
    /**
     * 查询门分组
     * 
     * @param id 门分组ID
     * @return 门分组
     */
    public DoorGroup selectDoorGroupById(String id);

    /**
     * 查询门分组列表
     * 
     * @param doorGroup 门分组
     * @return 门分组集合
     */
    public List<DoorGroup> selectDoorGroupList(DoorGroup doorGroup);

    /**
     * 新增门分组
     * 
     * @param doorGroup 门分组
     * @return 结果
     */
    public int insertDoorGroup(DoorGroup doorGroup);

    /**
     * 修改门分组
     * 
     * @param doorGroup 门分组
     * @return 结果
     */
    public int updateDoorGroup(DoorGroup doorGroup);

    /**
     * 删除门分组
     * 
     * @param id 门分组ID
     * @return 结果
     */
    public int deleteDoorGroupById(String id);

    /**
     * 批量删除门分组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDoorGroupByIds(String[] ids);
}
