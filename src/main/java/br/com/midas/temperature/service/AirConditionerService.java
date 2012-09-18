package br.com.midas.temperature.service;

import br.com.midas.temperature.model.AirConditioner;
import br.com.midas.temperature.model.AirConditionerParams;

public class AirConditionerService {

    public AirConditionerService() {
        // Possivel conexao com hardware do ar condicionado
    }
    
    public AirConditioner freeze(Float currentTemp, Float DesiredTemp, AirConditionerParams extraParams){
        AirConditioner air = new AirConditioner(currentTemp);
        
        for(int i = 1; i<extraParams.getDuration(); i++){
            air.warmUp( extraParams.getTemperatureLatency() );
        }
        
        return air;
    }
}
