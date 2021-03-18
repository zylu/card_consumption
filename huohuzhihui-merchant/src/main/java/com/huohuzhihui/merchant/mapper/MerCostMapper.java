package com.huohuzhihui.merchant.mapper;



import com.huohuzhihui.merchant.domain.MerCost;
import com.huohuzhihui.merchant.domain.MerCostReport;

import java.util.List;

/**
 * app段配置项Mapper接口
 * 
 * @author Zhihe.Li
 * @date 2020-12-02
 */
public interface MerCostMapper
{

    /**
     * 查询消费列表
     * 
     * @param MerCost
     * @return 消费集合
     */
    public List<MerCost> selectMerCostList(MerCost MerCost);

    /**
     * 新增消费记录
     * 
     * @param MerCost
     * @return 结果
     */
    public int insertMerCost(MerCost MerCost);

    /**
     * 获取商户交易统计
     * @param merCost
     * @return
     */
    public List<MerCostReport> getMerCostStatistics(MerCost merCost);
}
