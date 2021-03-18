package com.huohuzhihui.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.huohuzhihui.account.mapper.AccUserAccountMapper;
import com.huohuzhihui.account.service.IAccUserAccountService;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.account.mapper.AccSubsidyMapper;
import com.huohuzhihui.account.domain.AccSubsidy;
import com.huohuzhihui.account.service.IAccSubsidyService;

/**
 * 补贴Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
@Service
public class AccSubsidyServiceImpl implements IAccSubsidyService 
{
    @Autowired
    private AccSubsidyMapper accSubsidyMapper;

    @Autowired
    private AccUserAccountMapper accUserAccountMapper;
    /**
     * 查询补贴
     * 
     * @param id 补贴ID
     * @return 补贴
     */
    @Override
    public AccSubsidy selectAccSubsidyById(Long id)
    {
        return accSubsidyMapper.selectAccSubsidyById(id);
    }

    /**
     * 查询补贴列表
     * 
     * @param accSubsidy 补贴
     * @return 补贴
     */
    @Override
    public List<AccSubsidy> selectAccSubsidyList(AccSubsidy accSubsidy)
    {
        return accSubsidyMapper.selectAccSubsidyList(accSubsidy);
    }

    /**
     * 新增补贴
     * 
     * @param accSubsidy 补贴
     * @return 结果
     */
    @Override
    public int insertAccSubsidy(AccSubsidy accSubsidy)
    {
        Account Account = this.accUserAccountMapper.selectAccUserAccountByUserId(accSubsidy.getUserId());

        accSubsidy.setCreateTime(DateUtils.getNowDate());
        accSubsidyMapper.insertAccSubsidy(accSubsidy);

        //更新当前账户余额
        Account updateModel = new Account();
        updateModel.setId(Account.getId());
        updateModel.setVersion(Account.getVersion());
        updateModel.setBalance(accSubsidy.getAmount());//增加的金额
        updateModel.setUpdateBy(SecurityUtils.getUsername());
        updateModel.setUpdateTime(DateUtils.getNowDate());
        return accUserAccountMapper.recharge(updateModel);
    }

    /**
     * 修改补贴
     * 
     * @param accSubsidy 补贴
     * @return 结果
     */
    @Override
    public int updateAccSubsidy(AccSubsidy accSubsidy)
    {
        return accSubsidyMapper.updateAccSubsidy(accSubsidy);
    }

    /**
     * 批量删除补贴
     * 
     * @param ids 需要删除的补贴ID
     * @return 结果
     */
    @Override
    public int deleteAccSubsidyByIds(Long[] ids)
    {
        return accSubsidyMapper.deleteAccSubsidyByIds(ids);
    }

    /**
     * 删除补贴信息
     * 
     * @param id 补贴ID
     * @return 结果
     */
    @Override
    public int deleteAccSubsidyById(Long id)
    {
        return accSubsidyMapper.deleteAccSubsidyById(id);
    }
}
