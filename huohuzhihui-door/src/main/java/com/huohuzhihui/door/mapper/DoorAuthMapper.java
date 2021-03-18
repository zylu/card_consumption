package com.huohuzhihui.door.mapper;

import java.util.List;
import com.huohuzhihui.door.domain.DoorAuth;

/**
 * 门禁授权Mapper接口
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
public interface DoorAuthMapper 
{
    /**
     * 查询门禁授权
     * 
     * @param id 门禁授权ID
     * @return 门禁授权
     */
    public DoorAuth selectDoorAuthById(String id);

    /**
     * 查询门禁授权列表
     * 
     * @param doorAuth 门禁授权
     * @return 门禁授权集合
     */
    public List<DoorAuth> selectDoorAuthList(DoorAuth doorAuth);

    /**
     * 新增门禁授权
     * 
     * @param doorAuth 门禁授权
     * @return 结果
     */
    public int insertDoorAuth(DoorAuth doorAuth);

    /**
     * 修改门禁授权
     * 
     * @param doorAuth 门禁授权
     * @return 结果
     */
    public int updateDoorAuth(DoorAuth doorAuth);

    /**
     * 删除门禁授权
     * 
     * @param id 门禁授权ID
     * @return 结果
     */
    public int deleteDoorAuthById(String id);

    /**
     * 批量删除门禁授权
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDoorAuthByIds(String[] ids);
}
