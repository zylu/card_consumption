package com.huohuzhihui.account.service;

import java.util.List;
import com.huohuzhihui.common.core.domain.entity.Card;

/**
 * 账户下的卡Service接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface IAccCardService 
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
     * 开卡
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
     * 批量删除账户下的卡
     * 
     * @param ids 需要删除的账户下的卡ID
     * @return 结果
     */
    public int deleteAccCardByIds(Long[] ids);

    /**
     * 删除账户下的卡信息
     * 
     * @param id 账户下的卡ID
     * @return 结果
     */
    public int deleteAccCardById(Long id);

    /**
     * 补卡
     * @param accCard
     * @return
     */
    public int replaceAccCard(Card accCard);

    /**
     * 批量挂失卡
     * @param ids 卡ID
     * @return
     */
    public int lossAccCardByIds(Long[] ids);

    /**
     * 批量解挂卡
     * @param ids 卡ID
     * @return
     */
    public int unlossAccCardByIds(Long[] ids);

    /**
     * 批量注销卡
     * @param ids 卡ID
     * @return
     */
    public int cancelAccCardByIds(Long[] ids);

    /**
     * 卡统计
     * @param accCard
     * @return
     */
    Long getCardtStatistics(Card accCard);

    String checkCardNoUnique(String cardNo);
}
