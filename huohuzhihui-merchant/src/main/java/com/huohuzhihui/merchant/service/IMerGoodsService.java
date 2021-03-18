package com.huohuzhihui.merchant.service;

import java.util.List;
import com.huohuzhihui.merchant.domain.MerGoods;

/**
 * 商户商品Service接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface IMerGoodsService 
{
    /**
     * 查询商户商品
     * 
     * @param id 商户商品ID
     * @return 商户商品
     */
    public MerGoods selectMerGoodsById(Long id);

    /**
     * 查询商户商品列表
     * 
     * @param merGoods 商户商品
     * @return 商户商品集合
     */
    public List<MerGoods> selectMerGoodsList(MerGoods merGoods);

    /**
     * 新增商户商品
     * 
     * @param merGoods 商户商品
     * @return 结果
     */
    public int insertMerGoods(MerGoods merGoods);

    /**
     * 修改商户商品
     * 
     * @param merGoods 商户商品
     * @return 结果
     */
    public int updateMerGoods(MerGoods merGoods);

    /**
     * 批量删除商户商品
     * 
     * @param ids 需要删除的商户商品ID
     * @return 结果
     */
    public int deleteMerGoodsByIds(Long[] ids);

    /**
     * 删除商户商品信息
     * 
     * @param id 商户商品ID
     * @return 结果
     */
    public int deleteMerGoodsById(Long id);
}
