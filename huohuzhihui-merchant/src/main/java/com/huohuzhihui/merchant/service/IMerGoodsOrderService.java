package com.huohuzhihui.merchant.service;

import java.util.List;
import com.huohuzhihui.merchant.domain.MerGoodsOrder;

/**
 * 商户商品订单Service接口
 * 
 * @author zylu
 * @date 2020-11-14
 */
public interface IMerGoodsOrderService 
{
    /**
     * 查询商户商品订单
     * 
     * @param id 商户商品订单ID
     * @return 商户商品订单
     */
    public MerGoodsOrder selectMerGoodsOrderById(Long id);

    /**
     * 查询商户商品订单列表
     * 
     * @param merGoodsOrder 商户商品订单
     * @return 商户商品订单集合
     */
    public List<MerGoodsOrder> selectMerGoodsOrderList(MerGoodsOrder merGoodsOrder);

    /**
     * 新增商户商品订单
     * 
     * @param merGoodsOrder 商户商品订单
     * @return 结果
     */
    public int insertMerGoodsOrder(MerGoodsOrder merGoodsOrder);

    /**
     * 修改商户商品订单
     * 
     * @param merGoodsOrder 商户商品订单
     * @return 结果
     */
    public int updateMerGoodsOrder(MerGoodsOrder merGoodsOrder);

    /**
     * 批量删除商户商品订单
     * 
     * @param ids 需要删除的商户商品订单ID
     * @return 结果
     */
    public int deleteMerGoodsOrderByIds(Long[] ids);

    /**
     * 删除商户商品订单信息
     * 
     * @param id 商户商品订单ID
     * @return 结果
     */
    public int deleteMerGoodsOrderById(Long id);
}
