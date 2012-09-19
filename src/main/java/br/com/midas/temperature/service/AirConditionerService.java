package br.com.midas.temperature.service;

import br.com.midas.temperature.model.AirConditioner;
import br.com.midas.temperature.model.AirConditionerParams;

public interface AirConditionerService {

    public void hardwareConnect();
    public void hardwareDecreaseOneCelsius();
    public void hardwareDisconnect();
    
    public AirConditioner freeze(Float currentTemperature, Float desiredTemp, AirConditionerParams extraParams);
    
}
