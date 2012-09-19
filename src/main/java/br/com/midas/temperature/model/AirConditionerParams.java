package br.com.midas.temperature.model;

import java.math.BigDecimal;

public class AirConditionerParams {
    
    private Integer duration;
    private BigDecimal startCost;  // R$
    private BigDecimal freezeCost;  // R$
    private Float temperatureLatency; // °C/min
    private Float humanTemperatureDiference;  // +-x

    public AirConditionerParams() {
    }
    
    public AirConditionerParams(Integer duration, BigDecimal startCost, BigDecimal freezeCost, Float temperatureLatency, Float humanTemperatureDiference) {
        this.duration = duration;
        this.startCost = startCost;
        this.freezeCost = freezeCost;
        this.temperatureLatency = temperatureLatency;
        this.humanTemperatureDiference = humanTemperatureDiference;
    }
    
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getStartCost() {
        return startCost;
    }

    public void setStartCost(BigDecimal startCost) {
        this.startCost = startCost;
    }

    public BigDecimal getFreezeCost() {
        return freezeCost;
    }

    public void setFreezeCost(BigDecimal freezeCost) {
        this.freezeCost = freezeCost;
    }

    public Float getTemperatureLatency() {
        return temperatureLatency;
    }

    public void setTemperatureLatency(Float temperatureLatency) {
        this.temperatureLatency = temperatureLatency;
    }

    public Float getHumanTemperatureDiference() {
        return humanTemperatureDiference;
    }

    public void setHumanTemperatureDiference(Float humanTemperatureDiference) {
        this.humanTemperatureDiference = humanTemperatureDiference;
    }
    
}