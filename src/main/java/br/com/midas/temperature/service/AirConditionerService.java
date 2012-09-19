package br.com.midas.temperature.service;

import br.com.midas.temperature.model.AirConditioner;
import br.com.midas.temperature.model.AirConditionerParams;
import java.math.BigDecimal;

public class AirConditionerService {

    public AirConditionerService() {
        // Possivel conexao com hardware do ar condicionado
    }

    public AirConditioner freeze(Float currentTemperature, Float desiredTemp, AirConditionerParams extraParams) {
        /*if(extraParams.getDuration() < 7){
            return new AirConditioner(currentTemperature, BigDecimal.ZERO);
        }*/
        
        Float minimum = getMinimumTemperature(desiredTemp, extraParams.getHumanTemperatureDiference());
        AirConditioner air = new AirConditioner(currentTemperature, extraParams.getStartCost());
        
        float toMinimum = (float) Math.floor(air.getCurrentTemperature() - minimum);
        air.freeze(toMinimum, extraParams.getFreezeCost());
        
        for (int i = 1, duration = extraParams.getDuration(); i <= duration; i++) {
            air.roomWarmedUp(extraParams.getTemperatureLatency());
            
            if (canHumansFeel(air.getCurrentTemperature(), desiredTemp, extraParams.getHumanTemperatureDiference())) {

                if (hasSufficientIterations(i, duration)) {
                    toMinimum = (float) Math.floor(air.getCurrentTemperature() - minimum);
                    air.freeze(toMinimum, extraParams.getFreezeCost());
                } else {
                    float celsiusLeft = howManyDecrease(duration - i, extraParams.getTemperatureLatency());
                    air.freeze(celsiusLeft, extraParams.getFreezeCost());
                }

            }

        }

        return air;
    }

    private Float getMinimumTemperature(Float desiredTemp, Float humanTemperatureDiference) {
        return desiredTemp - humanTemperatureDiference;
    }

    private Float howManyDecrease(Integer counter, Float temperatureLatency) {
        if (counter % 2 == 0) {
            return counter / (1 / temperatureLatency) + 1;
        } else {
            return (float) Math.ceil(
                    (double) counter / (1 / temperatureLatency));
        }
    }

    private boolean canHumansFeel(Float currentTemp, Float airTemperature, Float humanTemperatureDiference) {
        if (Math.abs(currentTemp - airTemperature) > humanTemperatureDiference) {
            return true;
        }
        return false;
    }

    private boolean hasSufficientIterations(Integer counter, Integer duration) {
        if (duration - counter >= 7) {
            return true;
        }
        return false;
    }
}
