package br.com.midas.temperature.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import static br.com.caelum.vraptor.view.Results.*;
import br.com.midas.temperature.model.Simulator;


@Resource
public class SimulatorController {
    
    private final Validator validator;
    private final Result result;

    public SimulatorController(Validator validator, Result result) {
        this.validator = validator;
        this.result = result;
    }
    
    @Get("/")
    public void index(){
    }
    
    @Post("/simulator")
    public void simulator(Simulator simulator){
        validator.validate(simulator);
        validator.onErrorUse(logic()).forwardTo(getClass()).index();
        
        result.include("mensagem", "simulator.success");
        result.redirectTo(getClass()).index();
    }
    
}
