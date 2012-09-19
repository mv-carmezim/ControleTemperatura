package br.com.midas.temperature.service;

import br.com.midas.temperature.model.AirConditioner;
import br.com.midas.temperature.model.AirConditionerParams;
import java.math.BigDecimal;
import org.apache.log4j.Logger;

public final class ServiceMinuteByMinute implements AirConditionerService{

    Logger logger = Logger.getLogger("serviceMinuteByMinute");
    
    public ServiceMinuteByMinute() {
        logger.info("Conectando-se ao hardware do ar condicionado");
        this.hardwareConnect();
    }

    @Override
    public AirConditioner freeze(Float currentTemperature, Float desiredTemp, AirConditionerParams extraParams) {
        if(currentTemperature < desiredTemp  ||  (currentTemperature - desiredTemp) <= extraParams.getHumanTemperatureDiference()){
            logger.info("Não foi preciso ligar o ar condicionado. O ambiente ja esta de acordo com o desejado.");
            return new AirConditioner(desiredTemp, BigDecimal.ZERO);
        }
        
        logger.info("Ligando ar condicionado");
        Float minimum = getMinimumTemperature(desiredTemp, extraParams.getHumanTemperatureDiference());
        AirConditioner air = new AirConditioner(currentTemperature, extraParams.getStartCost());
        
        logger.info("Resfriando inicialmente para o mínimo possivel");
        float toMinimum = (float) Math.floor(air.getCurrentTemperature() - minimum);
        air.freeze(toMinimum, extraParams.getFreezeCost());
        
        logger.info("Para cada minuto decorrido:");
        for (int i = 1, duration = extraParams.getDuration(); i <= duration; i++) {
            logger.info("  minuto " + i);
            
            air.roomWarmedUp(extraParams.getTemperatureLatency());
            logger.info("  Ambiente aqueceu " + extraParams.getTemperatureLatency());
            
            if (canHumansFeel(air.getCurrentTemperature(), desiredTemp, extraParams.getHumanTemperatureDiference())) {
                logger.info("  A diferença já pode ser sentida. Vamos resfriar.");
                
                if (hasSufficientIterations(i, duration)) {
                    logger.info("Resfriando somente o necessário, pois falatm poucos minutos");
                    
                    toMinimum = (float) Math.floor(air.getCurrentTemperature() - minimum);
                    air.freeze(toMinimum, extraParams.getFreezeCost());
                } else {
                    logger.info("Resfriando para o mínimo possível");
                    
                    float celsiusLeft = howManyDecrease(duration - i, extraParams.getTemperatureLatency());
                    air.freeze(celsiusLeft, extraParams.getFreezeCost());
                }

            }
            
            logger.info("  Ar condicionado no minuto " + i);
            logger.info(air);
        }

        logger.info("Desconectando do hardware");
        this.hardwareDisconnect();
        
        logger.info("Resultado final do ar condicionado no final do periodo");
        logger.info(air);
        
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

    @Override
    public void hardwareConnect() {
        logger.info("Conectando com hardware do ar condicionado");
    }

    @Override
    public void hardwareDecreaseOneCelsius() {
        logger.info("Diminuindo um grau com o hardware do ar condicionado");
    }

    @Override
    public void hardwareDisconnect() {
        logger.info("Desconectando com hardware do ar condicionado");
    }
}
