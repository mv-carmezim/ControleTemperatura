package br.com.midas.temperature.model;

import java.math.BigDecimal;

public class AirConditioner {
    
    private Float currentTemperature;
    private BigDecimal energyCost;

    public AirConditioner(Float currentTemperature) {
        this.currentTemperature = currentTemperature;
        this.energyCost = new BigDecimal(0.5);
    }
    
    public void freeze(Float celsius, BigDecimal freezeCost){
        this.currentTemperature -= celsius;
        energyCost.add(freezeCost);
    }
    
    public void warmUp(Float celsius){
        this.currentTemperature += celsius;
    }

    public Float getCurrentTemperature() {
        return currentTemperature;
    }

    public BigDecimal getEnergyCost() {
        return energyCost;
    }
    
}
