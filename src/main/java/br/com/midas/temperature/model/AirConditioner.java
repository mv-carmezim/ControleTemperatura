package br.com.midas.temperature.model;

import java.math.BigDecimal;
import java.util.Objects;

public class AirConditioner {
    
    private Float currentTemperature;
    private BigDecimal energyCost;

    public AirConditioner(Float currentTemperature, BigDecimal startCost) {
        this.currentTemperature = currentTemperature;
        this.energyCost = startCost;
    }
    
    public void freeze(Float celsius, BigDecimal freezeCost){
        this.currentTemperature -= celsius;
        energyCost = energyCost.add(freezeCost.multiply(new BigDecimal(celsius.floatValue())));
    }
    
    public void roomWarmedUp(Float celsius){
        this.currentTemperature += celsius;
    }

    public Float getCurrentTemperature() {
        return currentTemperature;
    }

    public BigDecimal getEnergyCost() {
        return energyCost;
    }

    public void setCurrentTemperature(Float currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setEnergyCost(BigDecimal energyCost) {
        this.energyCost = energyCost;
    }
    
    public void addEnergyCost(BigDecimal plus){
        this.energyCost = this.energyCost.add(plus);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AirConditioner other = (AirConditioner) obj;
        if (!Objects.equals(this.currentTemperature, other.currentTemperature)) {
            return false;
        }
        if (!Objects.equals(this.energyCost.floatValue(), other.energyCost.floatValue())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.currentTemperature);
        hash = 97 * hash + Objects.hashCode(this.energyCost);
        return hash;
    }
    
}
