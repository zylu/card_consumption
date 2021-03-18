package com.huohuzhihui.account.service.impl;

import com.huohuzhihui.account.mapper.AccCardMapper;
import com.huohuzhihui.account.service.IAccCardService;
import com.huohuzhihui.common.constant.CardConstants;
import com.huohuzhihui.common.constant.UserConstants;
import com.huohuzhihui.common.core.domain.entity.Card;
import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 账户下的卡Service业务层处理
 * 
 * @author zylu
 * @date 2020-11-14
 */
@Service
public class AccCardServiceImpl implements IAccCardService 
{
    @Autowired
    private AccCardMapper accCardMapper;

    /**
     * 查询账户下的卡
     * 
     * @param id 账户下的卡ID
     * @return 账户下的卡
     */
    @Override
    public Card selectAccCardById(Long id)
    {
        return accCardMapper.selectAccCardById(id);
    }

    /**
     * 查询账户下的卡列表
     * 
     * @param accCard 账户下的卡
     * @return 账户下的卡
     */
    @Override
    public List<Card> selectAccCardList(Card accCard)
    {
        return accCardMapper.selectAccCardList(accCard);
    }

    /**
     * 新增账户下的卡
     * 
     * @param accCard 账户下的卡
     * @return 结果
     */
    @Override
    public int insertAccCard(Card accCard)
    {
        accCard.setCreateTime(DateUtils.getNowDate());
        accCard.setOperType(0);
        return accCardMapper.insertAccCard(accCard);
    }

    /**
     * 修改账户下的卡
     * 
     * @param accCard 账户下的卡
     * @return 结果
     */
    @Override
    public int updateAccCard(Card accCard)
    {
        accCard.setUpdateTime(DateUtils.getNowDate());
        return accCardMapper.updateAccCard(accCard);
    }

    /**
     * 批量删除账户下的卡
     * 
     * @param ids 需要删除的账户下的卡ID
     * @return 结果
     */
    @Override
    public int deleteAccCardByIds(Long[] ids)
    {
        return accCardMapper.deleteAccCardByIds(ids);
    }

    /**
     * 删除账户下的卡信息
     * 
     * @param id 账户下的卡ID
     * @return 结果
     */
    @Override
    public int deleteAccCardById(Long id)
    {
        return accCardMapper.deleteAccCardById(id);
    }

    @Override
    public int replaceAccCard(Card accCard) {
        //注销旧卡
        String updateBy = accCard.getUpdateBy();
        List<Card> cardList =  accCardMapper.selectAccCardList(accCard);
        if(cardList!=null && cardList.size()>0){
            Long[] ids = new Long[cardList.size()];
            for(int i = 0 ; i < cardList.size(); i++){
                ids[i] = cardList.get(i).getId();
            }
            accCardMapper.updateStatusByIds(2,new Date(),updateBy,ids);
        }
        //插入新卡
        accCard.setOperType(1);
        accCard.setCreateBy(updateBy);
        accCard.setCreateTime(new Date());
        accCard.setUpdateTime(new Date());
        return accCardMapper.insertAccCard(accCard);
    }

    @Override
    public int lossAccCardByIds(Long[] ids) {
        return accCardMapper.updateStatusByIds(1,DateUtils.getNowDate(), SecurityUtils.getUsername(),ids);
    }

    @Override
    public int unlossAccCardByIds(Long[] ids) {
        return accCardMapper.updateStatusByIds(0,DateUtils.getNowDate(), SecurityUtils.getUsername(),ids);
    }

    @Override
    public int cancelAccCardByIds(Long[] ids) {
        return accCardMapper.updateStatusByIds(2,DateUtils.getNowDate(), SecurityUtils.getUsername(),ids);
    }

    @Override
    public Long getCardtStatistics(Card accCard) {
        return accCardMapper.getCardtStatistics(accCard);
    }

    @Override
    public String checkCardNoUnique(String cardNo) {
        int count = accCardMapper.checkCardNoUnique(cardNo);
        if (count > 0)
        {
            return CardConstants.NOT_UNIQUE;
        }
        return CardConstants.UNIQUE;
    }
}
