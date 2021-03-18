package com.huohuzhihui.merchant.service;

import java.util.List;
import com.huohuzhihui.merchant.domain.MerMerchant;

/**
 * 商户Service接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface IMerMerchantService 
{
    /**
     * 查询商户
     * 
     * @param id 商户ID
     * @return 商户
     */
    public MerMerchant selectMerMerchantById(Long id);

    /**
     * 查询商户列表
     * 
     * @param merMerchant 商户
     * @return 商户集合
     */
    public List<MerMerchant> selectMerMerchantList(MerMerchant merMerchant);

    /**
     * 新增商户
     * 
     * @param merMerchant 商户
     * @return 结果
     */
    public int insertMerMerchant(MerMerchant merMerchant);

    /**
     * 修改商户
     * 
     * @param merMerchant 商户
     * @return 结果
     */
    public int updateMerMerchant(MerMerchant merMerchant);

    /**
     * 批量删除商户
     * 
     * @param ids 需要删除的商户ID
     * @return 结果
     */
    public int deleteMerMerchantByIds(Long[] ids);

    /**
     * 删除商户信息
     * 
     * @param id 商户ID
     * @return 结果
     */
    public int deleteMerMerchantById(Long id);
}
