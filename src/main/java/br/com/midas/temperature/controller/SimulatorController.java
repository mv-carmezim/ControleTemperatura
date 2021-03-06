package br.com.midas.temperature.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.environment.Environment;
import static br.com.caelum.vraptor.view.Results.json;
import br.com.midas.temperature.model.AirConditioner;
import br.com.midas.temperature.model.AirConditionerParams;
import br.com.midas.temperature.service.ServiceFormulaImpl;
import java.math.BigDecimal;

@Resource
public class SimulatorController {
    
    private final Validator validator;
    private final Result result;
    private final Environment environment;

    public SimulatorController(Validator validator, Result result, Environment environment) {
        this.validator = validator;
        this.result = result;
        this.environment = environment;
    }
    
    @Get("/")
    public void index(){
    }
    
    @Post("/simulator.json")
    public void newsimulator(Float currentTemperature, Float desiredTemperature, AirConditionerParams params){
        params.setFreezeCost( new BigDecimal(environment.get("freeze.cost")) );
        params.setStartCost( new BigDecimal(environment.get("start.cost")) );
        params.setTemperatureLatency( Float.parseFloat(environment.get("temperature.latency")) );
        params.setHumanTemperatureDiference( Float.parseFloat(environment.get("human.temperature.diference")) );
        
        ServiceFormulaImpl service = new ServiceFormulaImpl();
        AirConditioner air = service.freeze(currentTemperature, desiredTemperature, params);
        
        result.use(json()).withoutRoot().from(air).serialize();
    }
    
}
