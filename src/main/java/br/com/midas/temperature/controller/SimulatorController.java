package br.com.midas.temperature.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import static br.com.caelum.vraptor.view.Results.*;
import br.com.midas.temperature.model.AirConditioner;
import br.com.midas.temperature.model.AirConditionerParams;
import br.com.midas.temperature.service.AirConditionerService;

@Resource
public class SimulatorController {
    
    private final Validator validator;
    private final Result result;
    private AirConditionerParams params;

    public SimulatorController(Validator validator, Result result, AirConditionerParams params) {
        this.validator = validator;
        this.result = result;
        this.params = params;
    }
    
    @Get("/")
    public void index(){
    }
    
    @Post("/simulator")
    public void simulator(Float currentTemperature, Float desiredTemperature, Integer duration){
        params.setDuration(duration);
        
        AirConditionerService service = new AirConditionerService();
        AirConditioner air = service.freeze(currentTemperature, desiredTemperature, params);
        
        result.use(json()).withoutRoot().from(air);
    }
    
}
