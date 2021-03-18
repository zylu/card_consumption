package com.huohuzhihui.door.mapper;

import java.util.List;
import com.huohuzhihui.door.domain.DoorRecord;

/**
 * 门禁记录Mapper接口
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
public interface DoorRecordMapper 
{
    /**
     * 查询门禁记录
     * 
     * @param id 门禁记录ID
     * @return 门禁记录
     */
    public DoorRecord selectDoorRecordById(String id);

    /**
     * 查询门禁记录列表
     * 
     * @param doorRecord 门禁记录
     * @return 门禁记录集合
     */
    public List<DoorRecord> selectDoorRecordList(DoorRecord doorRecord);

    /**
     * 新增门禁记录
     * 
     * @param doorRecord 门禁记录
     * @return 结果
     */
    public int insertDoorRecord(DoorRecord doorRecord);

    /**
     * 修改门禁记录
     * 
     * @param doorRecord 门禁记录
     * @return 结果
     */
    public int updateDoorRecord(DoorRecord doorRecord);

    /**
     * 删除门禁记录
     * 
     * @param id 门禁记录ID
     * @return 结果
     */
    public int deleteDoorRecordById(String id);

    /**
     * 批量删除门禁记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDoorRecordByIds(String[] ids);
}
