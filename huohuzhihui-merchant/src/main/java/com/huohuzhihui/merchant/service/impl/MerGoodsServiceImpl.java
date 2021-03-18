package com.huohuzhihui.merchant.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.merchant.mapper.MerGoodsMapper;
import com.huohuzhihui.merchant.domain.MerGoods;
import com.huohuzhihui.merchant.service.IMerGoodsService;

/**
 * 商户商品Service业务层处理
 * 
 * @author zylu
 * @date 2020-11-14
 */
@Service
public class MerGoodsServiceImpl implements IMerGoodsService 
{
    @Autowired
    private MerGoodsMapper merGoodsMapper;

    /**
     * 查询商户商品
     * 
     * @param id 商户商品ID
     * @return 商户商品
     */
    @Override
    public MerGoods selectMerGoodsById(Long id)
    {
        return merGoodsMapper.selectMerGoodsById(id);
    }

    /**
     * 查询商户商品列表
     * 
     * @param merGoods 商户商品
     * @return 商户商品
     */
    @Override
    public List<MerGoods> selectMerGoodsList(MerGoods merGoods)
    {
        return merGoodsMapper.selectMerGoodsList(merGoods);
    }

    /**
     * 新增商户商品
     * 
     * @param merGoods 商户商品
     * @return 结果
     */
    @Override
    public int insertMerGoods(MerGoods merGoods)
    {
        merGoods.setCreateTime(DateUtils.getNowDate());
        return merGoodsMapper.insertMerGoods(merGoods);
    }

    /**
     * 修改商户商品
     * 
     * @param merGoods 商户商品
     * @return 结果
     */
    @Override
    public int updateMerGoods(MerGoods merGoods)
    {
        merGoods.setUpdateTime(DateUtils.getNowDate());
        return merGoodsMapper.updateMerGoods(merGoods);
    }

    /**
     * 批量删除商户商品
     * 
     * @param ids 需要删除的商户商品ID
     * @return 结果
     */
    @Override
    public int deleteMerGoodsByIds(Long[] ids)
    {
        return merGoodsMapper.deleteMerGoodsByIds(ids);
    }

    /**
     * 删除商户商品信息
     * 
     * @param id 商户商品ID
     * @return 结果
     */
    @Override
    public int deleteMerGoodsById(Long id)
    {
        return merGoodsMapper.deleteMerGoodsById(id);
    }
}
