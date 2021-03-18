package com.huohuzhihui.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil {

    /**
     * 保留两位小数
     * @param num
     * @return
     */
    public static String format(BigDecimal num){
        DecimalFormat df =new DecimalFormat("#.00");
        String str =df.format(num);
        return str;
    }
}
