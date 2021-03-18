package com.huohuzhihui.account.mapper;

import java.util.List;
import com.huohuzhihui.account.domain.AccSubsidy;

/**
 * 补贴Mapper接口
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
public interface AccSubsidyMapper 
{
    /**
     * 查询补贴
     * 
     * @param id 补贴ID
     * @return 补贴
     */
    public AccSubsidy selectAccSubsidyById(Long id);

    /**
     * 查询补贴列表
     * 
     * @param accSubsidy 补贴
     * @return 补贴集合
     */
    public List<AccSubsidy> selectAccSubsidyList(AccSubsidy accSubsidy);

    /**
     * 新增补贴
     * 
     * @param accSubsidy 补贴
     * @return 结果
     */
    public int insertAccSubsidy(AccSubsidy accSubsidy);

    /**
     * 修改补贴
     * 
     * @param accSubsidy 补贴
     * @return 结果
     */
    public int updateAccSubsidy(AccSubsidy accSubsidy);

    /**
     * 删除补贴
     * 
     * @param id 补贴ID
     * @return 结果
     */
    public int deleteAccSubsidyById(Long id);

    /**
     * 批量删除补贴
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccSubsidyByIds(Long[] ids);
}
