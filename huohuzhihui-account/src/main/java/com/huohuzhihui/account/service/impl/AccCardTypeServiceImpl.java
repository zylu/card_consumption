package com.huohuzhihui.account.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.account.mapper.AccCardTypeMapper;
import com.huohuzhihui.account.domain.AccCardType;
import com.huohuzhihui.account.service.IAccCardTypeService;

/**
 * 卡类型Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
@Service
public class AccCardTypeServiceImpl implements IAccCardTypeService 
{
    @Autowired
    private AccCardTypeMapper accCardTypeMapper;

    /**
     * 查询卡类型
     * 
     * @param id 卡类型ID
     * @return 卡类型
     */
    @Override
    public AccCardType selectAccCardTypeById(Long id)
    {
        return accCardTypeMapper.selectAccCardTypeById(id);
    }

    /**
     * 查询卡类型列表
     * 
     * @param accCardType 卡类型
     * @return 卡类型
     */
    @Override
    public List<AccCardType> selectAccCardTypeList(AccCardType accCardType)
    {
        return accCardTypeMapper.selectAccCardTypeList(accCardType);
    }

    /**
     * 新增卡类型
     * 
     * @param accCardType 卡类型
     * @return 结果
     */
    @Override
    public int insertAccCardType(AccCardType accCardType)
    {
        accCardType.setCreateTime(DateUtils.getNowDate());
        return accCardTypeMapper.insertAccCardType(accCardType);
    }

    /**
     * 修改卡类型
     * 
     * @param accCardType 卡类型
     * @return 结果
     */
    @Override
    public int updateAccCardType(AccCardType accCardType)
    {
        accCardType.setUpdateTime(DateUtils.getNowDate());
        return accCardTypeMapper.updateAccCardType(accCardType);
    }

    /**
     * 批量删除卡类型
     * 
     * @param ids 需要删除的卡类型ID
     * @return 结果
     */
    @Override
    public int deleteAccCardTypeByIds(Long[] ids)
    {
        return accCardTypeMapper.deleteAccCardTypeByIds(ids);
    }

    /**
     * 删除卡类型信息
     * 
     * @param id 卡类型ID
     * @return 结果
     */
    @Override
    public int deleteAccCardTypeById(Long id)
    {
        return accCardTypeMapper.deleteAccCardTypeById(id);
    }
}
