package com.huohuzhihui.merchant.service.impl;

import java.util.List;
import com.huohuzhihui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huohuzhihui.merchant.mapper.MerGoodsOrderMapper;
import com.huohuzhihui.merchant.domain.MerGoodsOrder;
import com.huohuzhihui.merchant.service.IMerGoodsOrderService;

/**
 * 商户商品订单Service业务层处理
 * 
 * @author zylu
 * @date 2020-11-14
 */
@Service
public class MerGoodsOrderServiceImpl implements IMerGoodsOrderService 
{
    @Autowired
    private MerGoodsOrderMapper merGoodsOrderMapper;

    /**
     * 查询商户商品订单
     * 
     * @param id 商户商品订单ID
     * @return 商户商品订单
     */
    @Override
    public MerGoodsOrder selectMerGoodsOrderById(Long id)
    {
        return merGoodsOrderMapper.selectMerGoodsOrderById(id);
    }

    /**
     * 查询商户商品订单列表
     * 
     * @param merGoodsOrder 商户商品订单
     * @return 商户商品订单
     */
    @Override
    public List<MerGoodsOrder> selectMerGoodsOrderList(MerGoodsOrder merGoodsOrder)
    {
        return merGoodsOrderMapper.selectMerGoodsOrderList(merGoodsOrder);
    }

    /**
     * 新增商户商品订单
     * 
     * @param merGoodsOrder 商户商品订单
     * @return 结果
     */
    @Override
    public int insertMerGoodsOrder(MerGoodsOrder merGoodsOrder)
    {
        merGoodsOrder.setCreateTime(DateUtils.getNowDate());
        return merGoodsOrderMapper.insertMerGoodsOrder(merGoodsOrder);
    }

    /**
     * 修改商户商品订单
     * 
     * @param merGoodsOrder 商户商品订单
     * @return 结果
     */
    @Override
    public int updateMerGoodsOrder(MerGoodsOrder merGoodsOrder)
    {
        merGoodsOrder.setUpdateTime(DateUtils.getNowDate());
        return merGoodsOrderMapper.updateMerGoodsOrder(merGoodsOrder);
    }

    /**
     * 批量删除商户商品订单
     * 
     * @param ids 需要删除的商户商品订单ID
     * @return 结果
     */
    @Override
    public int deleteMerGoodsOrderByIds(Long[] ids)
    {
        return merGoodsOrderMapper.deleteMerGoodsOrderByIds(ids);
    }

    /**
     * 删除商户商品订单信息
     * 
     * @param id 商户商品订单ID
     * @return 结果
     */
    @Override
    public int deleteMerGoodsOrderById(Long id)
    {
        return merGoodsOrderMapper.deleteMerGoodsOrderById(id);
    }
}
