package br.com.midas.temperature.model;

import br.com.caelum.vraptor.environment.Environment;
import java.math.BigDecimal;

public class AirConditionerParams {
    
    private Integer duration;
    
    private BigDecimal freezeCost;  // R$
    private Float temperatureLatency; // °C/min
    private Float humanTemperatureDiference;  // +-x
    
    private Environment environment;

    public AirConditionerParams(Environment environment) {
        this.environment = environment;
        freezeCost = new BigDecimal( environment.get("freeze.cost") );
        temperatureLatency = Float.parseFloat( environment.get("temperature.latency") );
        humanTemperatureDiference = Float.parseFloat( environment.get("human.temperature.diference") );
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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