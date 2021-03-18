package com.huohuzhihui.account.mapper;

import com.huohuzhihui.common.core.domain.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 账户Mapper接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface AccUserAccountMapper 
{
    /**
     * 查询账户
     * 
     * @param id 账户ID
     * @return 账户
     */
    public Account selectAccUserAccountById(Long id);

    /**
     * 查询账户列表
     * 
     * @param accUserAccount 账户
     * @return 账户集合
     */
    public List<Account> selectAccUserAccountList(Account accUserAccount);

    /**
     * 新增账户
     * 
     * @param accUserAccount 账户
     * @return 结果
     */
    public int insertAccUserAccount(Account accUserAccount);

    /**
     * 修改账户
     * 
     * @param accUserAccount 账户
     * @return 结果
     */
    public int updateAccUserAccount(Account accUserAccount);

    /**
     * 删除账户
     * 
     * @param id 账户ID
     * @return 结果
     */
    public int deleteAccUserAccountById(Long id);

    /**
     * 批量删除账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccUserAccountByIds(Long[] ids);

    /**
     * 充值
     * @param updateModel
     */
    int recharge(Account updateModel);

    /**
     * 批量修改账户状态
     * @param status 账户状态：0，正常，1冻结，2注销
     * @param updateTime 修改时间
     * @param updateBy 修改人
     * @param id 账户ID集合
     * @return
     */
    int updateStatusByIds( @Param("status")int status, @Param("updateTime")Date updateTime,@Param("updateBy") String updateBy,@Param("id")Long[] id);

    /**
     * 查询账户数量
     * @param account
     * @return
     */
    Long getUserAccountStatistics(Account account);

    BigDecimal getTotalUserBalance();

    /**
     * 根据用户ID查询账户
     * @param userId
     * @return
     */
    public Account selectAccUserAccountByUserId(Long userId);

    void updateAccUserAccountByUserId(Account accout);
}
