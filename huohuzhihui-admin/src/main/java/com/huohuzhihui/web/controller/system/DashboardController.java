package com.huohuzhihui.web.controller.system;

import com.huohuzhihui.account.constant.AccountConstants;
import com.huohuzhihui.account.service.IAccCardService;
import com.huohuzhihui.account.service.IAccUserAccountService;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.core.domain.entity.Card;
import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.trade.domain.TradeOrder;
import com.huohuzhihui.trade.domain.TradeOrderChart;
import com.huohuzhihui.trade.service.ITradeOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 看板
 */
@RestController
@RequestMapping("/system/dashboard")
public class DashboardController extends BaseController {
    @Autowired
    private IAccUserAccountService accUserAccountService;

    @Autowired
    private IAccCardService accCardService;

    @Autowired
    private ITradeOrderService tradeOrderService;
    /**
     * 看板
     */
    @GetMapping("/init")
    public Map<String,Object> init()
    {
        Map<String,Object> result = new HashMap<>();
        Date today = DateUtils.initDateByDay();
        //获取账户信息
        Account account = new Account();
        account.setStatus(0);
        account.setCreateTime(today);
        //获取当日新增用户数
        Long addUserCount = accUserAccountService.getUserAccountStatistics(account);
        account.setStatus(2);
        account.setCreateTime(null);
        account.setUpdateTime(today);
        //获取当日注销用户数
        Long cancelUserCount = accUserAccountService.getUserAccountStatistics(account);
        //当日补卡人数
        Card accCard = new Card();
        accCard.setOperType(1);
        accCard.setCreateTime(today);
        Long replaceUserCount = accCardService.getCardtStatistics(accCard);
        //订单数据
        TradeOrder tradeOrder = new TradeOrder();
        //当日新增款数量
        tradeOrder.setType(AccountConstants.OPER_RECHARGE);
        tradeOrder.setCreateTime(today);
        tradeOrder.setStatus(1);
        BigDecimal addOrderAmount = tradeOrderService.getOrderStatistics(tradeOrder);
        //当日减款数量
        tradeOrder.setType(AccountConstants.OPER_CONSUME);
        tradeOrder.setCreateTime(today);
        tradeOrder.setStatus(1);
        BigDecimal reduceOrderAmount = tradeOrderService.getOrderStatistics(tradeOrder);
        addOrderAmount = addOrderAmount == null ? BigDecimal.ZERO : addOrderAmount;
        reduceOrderAmount = reduceOrderAmount == null ? BigDecimal.ZERO : reduceOrderAmount;
        //当日营业额
        BigDecimal totalOrderAmount = addOrderAmount.subtract(reduceOrderAmount);
        //当日消费人数
        Long userPayCount = tradeOrderService.getUserPayCount(today);
        //账户余额
        BigDecimal totalUserBalance = accUserAccountService.getTotalUserBalance();
        totalUserBalance = totalUserBalance == null ? BigDecimal.ZERO : totalUserBalance;
        result.put("addUserCount",addUserCount);
        result.put("cancelUserCount",cancelUserCount);
        result.put("replaceUserCount",replaceUserCount);
        result.put("addOrderAmount",addOrderAmount);
        result.put("reduceOrderAmount",reduceOrderAmount);
        result.put("totalOrderAmount",totalOrderAmount);
        result.put("userPayCount",userPayCount);
        result.put("totalUserBalance",totalUserBalance);
        return result;
    }

    /**
     * 当月消费统计折线图数据
     */
    @GetMapping("/getChart")
    public Map<String,Object> getChart()
    {
        Map<String,Object> result = new HashMap<>();
        //当月天数
        int days = DateUtils.getCurrentMonthDay();
        //获取当月充值订单
        List<TradeOrderChart> addOrderList = tradeOrderService.getCurrentMothOrder(0);
        //获取当月消费订单
        List<TradeOrderChart> reduceOrderList = tradeOrderService.getCurrentMothOrder(1);
        result.put("addOrderList",matchData(addOrderList,days));
        result.put("reduceOrderList",matchData(reduceOrderList,days));
        logger.info("充值金额"+result.get("addOrderList"));
        logger.info("消费金额"+result.get("reduceOrderList"));
        return result;
    }

    /**
     * 匹配数据
     * @param addOrderList
     * @param days
     */
    private List<BigDecimal> matchData(List<TradeOrderChart> addOrderList ,int days){
        if(CollectionUtils.isNotEmpty(addOrderList)){
            Map<Integer,TradeOrderChart> map = addOrderList.stream().collect(Collectors.toMap(TradeOrderChart::getDayName, orderChart->orderChart ));
            for (int i = 1; i <= days; i++) {
                //如果没有当天数据，手动插入为0的数据
                if(!map.containsKey(i)){
                    TradeOrderChart chart = new TradeOrderChart();
                    chart.setDayName(i);
                    chart.setAmount(BigDecimal.ZERO);
                    addOrderList.add(chart);
                }
            }
        }else {
            addOrderList = new ArrayList<>();
            for (int i = 1; i <= days; i++) {
                //如果没有当天数据，手动插入为0的数据
                TradeOrderChart chart = new TradeOrderChart();
                chart.setDayName(i);
                chart.setAmount(BigDecimal.ZERO);
                addOrderList.add(chart);
            }
        }
        return addOrderList.stream().sorted(Comparator.comparing(TradeOrderChart::getDayName)).map(e -> e.getAmount()).collect(Collectors.toList());

    }

}
