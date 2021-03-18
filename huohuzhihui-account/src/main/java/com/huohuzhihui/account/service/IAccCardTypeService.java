package com.huohuzhihui.account.service;

import java.util.List;
import com.huohuzhihui.account.domain.AccCardType;

/**
 * 卡类型Service接口
 * 
 * @author huohuzhihui
 * @date 2020-11-15
 */
public interface IAccCardTypeService 
{
    /**
     * 查询卡类型
     * 
     * @param id 卡类型ID
     * @return 卡类型
     */
    public AccCardType selectAccCardTypeById(Long id);

    /**
     * 查询卡类型列表
     * 
     * @param accCardType 卡类型
     * @return 卡类型集合
     */
    public List<AccCardType> selectAccCardTypeList(AccCardType accCardType);

    /**
     * 新增卡类型
     * 
     * @param accCardType 卡类型
     * @return 结果
     */
    public int insertAccCardType(AccCardType accCardType);

    /**
     * 修改卡类型
     * 
     * @param accCardType 卡类型
     * @return 结果
     */
    public int updateAccCardType(AccCardType accCardType);

    /**
     * 批量删除卡类型
     * 
     * @param ids 需要删除的卡类型ID
     * @return 结果
     */
    public int deleteAccCardTypeByIds(Long[] ids);

    /**
     * 删除卡类型信息
     * 
     * @param id 卡类型ID
     * @return 结果
     */
    public int deleteAccCardTypeById(Long id);
}
