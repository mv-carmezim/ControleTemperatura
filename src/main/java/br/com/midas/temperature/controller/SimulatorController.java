package br.com.midas.temperature.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.environment.Environment;
import static br.com.caelum.vraptor.view.Results.*;
import br.com.midas.temperature.model.AirConditioner;
import br.com.midas.temperature.model.AirConditionerParams;
import br.com.midas.temperature.service.AirConditionerService;
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
    
    @Post("/simulator")
    public void simulator(Float currentTemperature, Float desiredTemperature, AirConditionerParams params){
        params.setFreezeCost( new BigDecimal(environment.get("freeze.cost")) );
        params.setStartCost( new BigDecimal(environment.get("start.cost")) );
        params.setTemperatureLatency( Float.parseFloat(environment.get("temperature.latency")) );
        params.setHumanTemperatureDiference( Float.parseFloat(environment.get("human.temperature.diference")) );
        
        AirConditionerService service = new AirConditionerService();
        AirConditioner air = service.freeze(currentTemperature, desiredTemperature, params);
        
        result.use(json()).withoutRoot().from(air).serialize();
    }
    
}
