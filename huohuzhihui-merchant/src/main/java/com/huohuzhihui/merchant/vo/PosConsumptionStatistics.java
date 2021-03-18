package com.huohuzhihui.merchant.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 消费记录
 */
public class PosConsumptionStatistics implements Serializable{

    @JsonProperty("consumption_Mone")
    private Double consumptionMone;

    @JsonProperty("counts")
    private Integer counts;

    @JsonProperty("machine_Number")
    private String machineNumber;

    public Double getConsumptionMone() {
        return consumptionMone;
    }

    public void setConsumptionMone(Double consumptionMone) {
        this.consumptionMone = consumptionMone;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }
}
