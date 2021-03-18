package com.huohuzhihui.account.service.impl;

import com.huohuzhihui.account.domain.AccRecharge;
import com.huohuzhihui.account.mapper.AccRechargeMapper;
import com.huohuzhihui.account.mapper.AccUserAccountMapper;
import com.huohuzhihui.account.service.IAccRechargeService;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户充值Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2021-03-16
 */
@Service
public class AccRechargeServiceImpl implements IAccRechargeService 
{
    @Autowired
    private AccRechargeMapper accRechargeMapper;
    @Autowired
    private AccUserAccountMapper accUserAccountMapper;
    /**
     * 查询账户充值
     * 
     * @param id 账户充值ID
     * @return 账户充值
     */
    @Override
    public AccRecharge selectAccRechargeById(Long id)
    {
        return accRechargeMapper.selectAccRechargeById(id);
    }

    /**
     * 查询账户充值列表
     * 
     * @param accRecharge 账户充值
     * @return 账户充值
     */
    @Override
    public List<AccRecharge> selectAccRechargeList(AccRecharge accRecharge)
    {
        return accRechargeMapper.selectAccRechargeList(accRecharge);
    }

    /**
     * 新增账户充值
     * 
     * @param accRecharge 账户充值
     * @return 结果
     */
    @Override
    public int insertAccRecharge(AccRecharge accRecharge)
    {
        Account account = this.accUserAccountMapper.selectAccUserAccountByUserId(accRecharge.getUserId());

        if("XJ".equals(accRecharge.getPayChannel())){
            accRecharge.setCreateBy(SecurityUtils.getUsername());
            accRecharge.setStatus(1L);//支付成功
            //更新当前账户余额
            Account updateModel = new Account();
            updateModel.setId(account.getId());
            updateModel.setVersion(account.getVersion());
            updateModel.setBalance(accRecharge.getAmount());//增加的金额
            updateModel.setUpdateBy(account.getUserName());
            updateModel.setUpdateTime(DateUtils.getNowDate());
            accUserAccountMapper.recharge(updateModel);
        }
        accRecharge.setCreateTime(DateUtils.getNowDate());
        return accRechargeMapper.insertAccRecharge(accRecharge);
    }

    /**
     * 修改账户充值
     * 
     * @param accRecharge 账户充值
     * @return 结果
     */
    @Override
    public int updateAccRecharge(AccRecharge accRecharge)
    {
        return accRechargeMapper.updateAccRecharge(accRecharge);
    }

    /**
     * 批量删除账户充值
     * 
     * @param ids 需要删除的账户充值ID
     * @return 结果
     */
    @Override
    public int deleteAccRechargeByIds(Long[] ids)
    {
        return accRechargeMapper.deleteAccRechargeByIds(ids);
    }

    /**
     * 删除账户充值信息
     * 
     * @param id 账户充值ID
     * @return 结果
     */
    @Override
    public int deleteAccRechargeById(Long id)
    {
        return accRechargeMapper.deleteAccRechargeById(id);
    }
}
