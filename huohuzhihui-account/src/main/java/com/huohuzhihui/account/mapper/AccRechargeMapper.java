package com.huohuzhihui.account.mapper;

import java.util.List;
import com.huohuzhihui.account.domain.AccRecharge;

/**
 * 账户充值Mapper接口
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
public interface AccRechargeMapper 
{
    /**
     * 查询账户充值
     * 
     * @param id 账户充值ID
     * @return 账户充值
     */
    public AccRecharge selectAccRechargeById(Long id);

    /**
     * 查询账户充值列表
     * 
     * @param accRecharge 账户充值
     * @return 账户充值集合
     */
    public List<AccRecharge> selectAccRechargeList(AccRecharge accRecharge);

    /**
     * 新增账户充值
     * 
     * @param accRecharge 账户充值
     * @return 结果
     */
    public int insertAccRecharge(AccRecharge accRecharge);

    /**
     * 修改账户充值
     * 
     * @param accRecharge 账户充值
     * @return 结果
     */
    public int updateAccRecharge(AccRecharge accRecharge);

    /**
     * 删除账户充值
     * 
     * @param id 账户充值ID
     * @return 结果
     */
    public int deleteAccRechargeById(Long id);

    /**
     * 批量删除账户充值
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccRechargeByIds(Long[] ids);
}
