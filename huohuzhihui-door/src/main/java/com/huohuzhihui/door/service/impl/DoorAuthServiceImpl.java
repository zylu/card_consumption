package com.huohuzhihui.door.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.door.mapper.DoorAuthMapper;
import com.huohuzhihui.door.domain.DoorAuth;
import com.huohuzhihui.door.service.IDoorAuthService;

/**
 * 门禁授权Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-12-22
 */
@Service
public class DoorAuthServiceImpl implements IDoorAuthService 
{
    @Autowired
    private DoorAuthMapper doorAuthMapper;

    /**
     * 查询门禁授权
     * 
     * @param id 门禁授权ID
     * @return 门禁授权
     */
    @Override
    public DoorAuth selectDoorAuthById(String id)
    {
        return doorAuthMapper.selectDoorAuthById(id);
    }

    /**
     * 查询门禁授权列表
     * 
     * @param doorAuth 门禁授权
     * @return 门禁授权
     */
    @Override
    public List<DoorAuth> selectDoorAuthList(DoorAuth doorAuth)
    {
        return doorAuthMapper.selectDoorAuthList(doorAuth);
    }

    /**
     * 新增门禁授权
     * 
     * @param doorAuth 门禁授权
     * @return 结果
     */
    @Override
    public int insertDoorAuth(DoorAuth doorAuth)
    {
        doorAuth.setCreateTime(DateUtils.getNowDate());
        return doorAuthMapper.insertDoorAuth(doorAuth);
    }

    /**
     * 修改门禁授权
     * 
     * @param doorAuth 门禁授权
     * @return 结果
     */
    @Override
    public int updateDoorAuth(DoorAuth doorAuth)
    {
        doorAuth.setUpdateTime(DateUtils.getNowDate());
        return doorAuthMapper.updateDoorAuth(doorAuth);
    }

    /**
     * 批量删除门禁授权
     * 
     * @param ids 需要删除的门禁授权ID
     * @return 结果
     */
    @Override
    public int deleteDoorAuthByIds(String[] ids)
    {
        return doorAuthMapper.deleteDoorAuthByIds(ids);
    }

    /**
     * 删除门禁授权信息
     * 
     * @param id 门禁授权ID
     * @return 结果
     */
    @Override
    public int deleteDoorAuthById(String id)
    {
        return doorAuthMapper.deleteDoorAuthById(id);
    }
}
