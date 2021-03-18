package com.huohuzhihui.door.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.door.mapper.DoorRecordMapper;
import com.huohuzhihui.door.domain.DoorRecord;
import com.huohuzhihui.door.service.IDoorRecordService;

/**
 * 门禁记录Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
@Service
public class DoorRecordServiceImpl implements IDoorRecordService 
{
    @Autowired
    private DoorRecordMapper doorRecordMapper;

    /**
     * 查询门禁记录
     * 
     * @param id 门禁记录ID
     * @return 门禁记录
     */
    @Override
    public DoorRecord selectDoorRecordById(String id)
    {
        return doorRecordMapper.selectDoorRecordById(id);
    }

    /**
     * 查询门禁记录列表
     * 
     * @param doorRecord 门禁记录
     * @return 门禁记录
     */
    @Override
    public List<DoorRecord> selectDoorRecordList(DoorRecord doorRecord)
    {
        return doorRecordMapper.selectDoorRecordList(doorRecord);
    }

    /**
     * 新增门禁记录
     * 
     * @param doorRecord 门禁记录
     * @return 结果
     */
    @Override
    public int insertDoorRecord(DoorRecord doorRecord)
    {
        doorRecord.setCreateTime(DateUtils.getNowDate());
        return doorRecordMapper.insertDoorRecord(doorRecord);
    }

    /**
     * 修改门禁记录
     * 
     * @param doorRecord 门禁记录
     * @return 结果
     */
    @Override
    public int updateDoorRecord(DoorRecord doorRecord)
    {
        return doorRecordMapper.updateDoorRecord(doorRecord);
    }

    /**
     * 批量删除门禁记录
     * 
     * @param ids 需要删除的门禁记录ID
     * @return 结果
     */
    @Override
    public int deleteDoorRecordByIds(String[] ids)
    {
        return doorRecordMapper.deleteDoorRecordByIds(ids);
    }

    /**
     * 删除门禁记录信息
     * 
     * @param id 门禁记录ID
     * @return 结果
     */
    @Override
    public int deleteDoorRecordById(String id)
    {
        return doorRecordMapper.deleteDoorRecordById(id);
    }
}
