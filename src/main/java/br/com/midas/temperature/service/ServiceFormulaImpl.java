package br.com.midas.temperature.service;

import br.com.midas.temperature.model.AirConditioner;
import br.com.midas.temperature.model.AirConditionerParams;
import java.math.BigDecimal;
import org.apache.log4j.Logger;

public final class ServiceFormulaImpl implements AirConditionerService{

    Logger logger = Logger.getLogger("serviceFormula");

    public ServiceFormulaImpl() {
        logger.info("Conectando-se ao hardware do ar condicionado");
        this.hardwareConnect();
    }
    
    @Override
    public AirConditioner freeze(Float currentTemperature, Float desiredTemp, AirConditionerParams extraParams) {
        if(currentTemperature < desiredTemp  ||  (currentTemperature - desiredTemp) <= extraParams.getHumanTemperatureDiference()){
            logger.info("Não foi preciso ligar o ar condicionado. O ambiente ja esta de acordo com o desejado.");
            return new AirConditioner(desiredTemp, BigDecimal.ZERO);
        }
        
        Float minimum = getMinimumTemperature(desiredTemp, extraParams.getHumanTemperatureDiference());
        logger.info("Ligando o ar condicionado");
        AirConditioner air = new AirConditioner(currentTemperature, extraParams.getStartCost());
        
        logger.info("Resfriando temperatura inicial para o minimo possivel");
        float toMinimum = (float) Math.floor(air.getCurrentTemperature() - minimum);
        air.freeze(toMinimum, extraParams.getFreezeCost());
        
        logger.info("Calculando quantas vezes, no decorrer dos minutos, resfriaremos para o minimo possivel");
        Integer decreases = howManyDecreasesToMinimum(extraParams.getDuration(), extraParams.getTemperatureLatency(), extraParams.getHumanTemperatureDiference());
        logger.info("Calculando o custo de reduzir 'n' vezes para o minumo possivel");
        BigDecimal decreaseCost = getDecreaseCost(decreases * (extraParams.getHumanTemperatureDiference() *2), extraParams.getFreezeCost());

        logger.info("Somando custo de redução ao minimo possivel");
        air.addEnergyCost(decreaseCost);
        
        // no final, nao precisamos diminuir para o minimo, podemos economizar indo pra um pouco menos
        logger.info("Calculando quantas minutos sobrariam no final sem que precisemos resfriar para o minimo possivel");
        Integer leftIteratinos = counterEnoughIterations(extraParams.getDuration(), extraParams.getTemperatureLatency(), extraParams.getHumanTemperatureDiference()) -1;
        logger.info("Calculando custo da redução dos restantes dos minutos");
        BigDecimal leftIterationsCost = getDecreaseCost(
            howManyDecrease(leftIteratinos, extraParams.getTemperatureLatency()), 
            extraParams.getFreezeCost()
        );
        
        logger.info("Somando custo da redução ao necessário no final do processo");
        air.addEnergyCost(leftIterationsCost);
        
        // a temperatura final sera calculada baseada em quanto graus diminuimos no final
        Float maxTemperature = desiredTemp + extraParams.getHumanTemperatureDiference() + extraParams.getTemperatureLatency();
        Float howManyDecreased = howManyDecrease(leftIteratinos, extraParams.getTemperatureLatency());
        Float howManyWarmedUpAgain = leftIteratinos * extraParams.getTemperatureLatency();
        
        logger.info("Temperatura final sera a diferenca entre o maximo atingido e quanto aquecemos nos minutos finais");
        Float finalTemperature = maxTemperature - (howManyDecreased - howManyWarmedUpAgain);

        logger.info("Salvando temperatura final");
        air.setCurrentTemperature(finalTemperature);
        
        logger.info("Desconectando do hardware");
        this.hardwareDisconnect();
        
        logger.info("Resultado final do ar condicionado no final do periodo");
        logger.info(air);
        
        return air;
    }
    
    private BigDecimal getDecreaseCost(Float celsius, BigDecimal freezeCost){
        return freezeCost.multiply( new BigDecimal(celsius.floatValue()) );
    }
    
    private Float howManyDecrease(Integer counter, Float temperatureLatency) {
        if (counter % 2 == 0) {
            return counter / (1 / temperatureLatency) + 1;
        } else {
            return (float) Math.ceil(
                    (double) counter / (1 / temperatureLatency));
        }
    }
    
    private Integer counterEnoughIterations(Integer duration, Float temperatureLatency, Float humanTemperatureDiference){
        Float divisor = (humanTemperatureDiference * 2) / temperatureLatency;
        Integer rest = (int) (duration % divisor);
        
        return rest;
    }
    
    private Integer howManyDecreasesToMinimum(Integer duration, Float temperatureLatency, Float humanTemperatureDiference){
        Float divisor = (humanTemperatureDiference * 2) / temperatureLatency;
        Integer quociente = (int) (duration / divisor);
        
        return quociente -1; // o pirmeiro custo sera calculado separadamente
    }
    
    private Float getMinimumTemperature(Float desiredTemp, Float humanTemperatureDiference) {
        return desiredTemp - humanTemperatureDiference;
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
