package com.huohuzhihui.merchant.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.merchant.mapper.MerMerchantMapper;
import com.huohuzhihui.merchant.domain.MerMerchant;
import com.huohuzhihui.merchant.service.IMerMerchantService;

/**
 * 商户Service业务层处理
 * 
 * @author zylu
 * @date 2020-11-14
 */
@Service
public class MerMerchantServiceImpl implements IMerMerchantService 
{
    @Autowired
    private MerMerchantMapper merMerchantMapper;

    /**
     * 查询商户
     * 
     * @param id 商户ID
     * @return 商户
     */
    @Override
    public MerMerchant selectMerMerchantById(Long id)
    {
        return merMerchantMapper.selectMerMerchantById(id);
    }

    /**
     * 查询商户列表
     * 
     * @param merMerchant 商户
     * @return 商户
     */
    @Override
    public List<MerMerchant> selectMerMerchantList(MerMerchant merMerchant)
    {
        return merMerchantMapper.selectMerMerchantList(merMerchant);
    }

    /**
     * 新增商户
     * 
     * @param merMerchant 商户
     * @return 结果
     */
    @Override
    public int insertMerMerchant(MerMerchant merMerchant)
    {
        merMerchant.setCreateTime(DateUtils.getNowDate());
        return merMerchantMapper.insertMerMerchant(merMerchant);
    }

    /**
     * 修改商户
     * 
     * @param merMerchant 商户
     * @return 结果
     */
    @Override
    public int updateMerMerchant(MerMerchant merMerchant)
    {
        merMerchant.setUpdateTime(DateUtils.getNowDate());
        return merMerchantMapper.updateMerMerchant(merMerchant);
    }

    /**
     * 批量删除商户
     * 
     * @param ids 需要删除的商户ID
     * @return 结果
     */
    @Override
    public int deleteMerMerchantByIds(Long[] ids)
    {
        return merMerchantMapper.deleteMerMerchantByIds(ids);
    }

    /**
     * 删除商户信息
     * 
     * @param id 商户ID
     * @return 结果
     */
    @Override
    public int deleteMerMerchantById(Long id)
    {
        return merMerchantMapper.deleteMerMerchantById(id);
    }
}
