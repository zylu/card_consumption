package com.huohuzhihui.account.mapper;

import com.huohuzhihui.common.core.domain.entity.Card;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 账户下的卡Mapper接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface AccCardMapper 
{
    /**
     * 查询账户下的卡
     * 
     * @param id 账户下的卡ID
     * @return 账户下的卡
     */
    public Card selectAccCardById(Long id);

    /**
     * 查询账户下的卡列表
     * 
     * @param accCard 账户下的卡
     * @return 账户下的卡集合
     */
    public List<Card> selectAccCardList(Card accCard);

    /**
     * 新增账户下的卡
     * 
     * @param accCard 账户下的卡
     * @return 结果
     */
    public int insertAccCard(Card accCard);

    /**
     * 修改账户下的卡
     * 
     * @param accCard 账户下的卡
     * @return 结果
     */
    public int updateAccCard(Card accCard);

    /**
     * 删除账户下的卡
     * 
     * @param id 账户下的卡ID
     * @return 结果
     */
    public int deleteAccCardById(Long id);

    /**
     * 批量删除账户下的卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccCardByIds(Long[] ids);

    /**
     * 批量修改卡状态
     * @param status 卡状态：0，正常，1挂失，2注销
     * @param updateTime 修改时间
     * @param updateBy 修改人
     * @param id 卡ID集合
     * @return
     */
    public int updateStatusByIds(@Param("status")int status, @Param("updateTime")Date updateTime, @Param("updateBy") String updateBy, @Param("id")Long[] id);

    /**
     * 卡统计
     * @param accCard
     * @return
     */
    public Long getCardtStatistics(Card accCard);
    /*** 根据卡号查询卡
     * @param cardNo
     * @return
     */
    public Card selectAccCardByCardNo(String cardNo);

    /**
     * 判断卡号是否唯一
     * @param cardNo
     * @return
     */
    public int checkCardNoUnique(String cardNo);
}
