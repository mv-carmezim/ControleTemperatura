package br.com.midas.temperature.service;

import br.com.midas.temperature.model.AirConditioner;
import br.com.midas.temperature.model.AirConditionerParams;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.*;


public class ServiceFormulaImplTest {
    
    public ServiceFormulaImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testFreeze() {
        AirConditionerParams params = mockParams();
        ServiceFormulaImpl service = new ServiceFormulaImpl();
        
        Float currentTemp = 30f;
        Float desiredTemp = 20f;
        
        AirConditioner resultAir = service.freeze(currentTemp, desiredTemp, params);
        AirConditioner expectedAir = new AirConditioner(22f, new BigDecimal(19.3));

        assertEquals("Teste para caso sugerido", resultAir, expectedAir);
    }
    
    @Test
    public void testBothImplementations(){
        AirConditionerParams params = mockParams();
        ServiceFormulaImpl service1 = new ServiceFormulaImpl();
        ServiceMinuteByMinute service2 = new ServiceMinuteByMinute();
        
        Float currentTemp = 30f;
        Float desiredTemp = 20f;
        
        AirConditioner resultAir1 = service1.freeze(currentTemp, desiredTemp, params);
        AirConditioner resultAir2 = service1.freeze(currentTemp, desiredTemp, params);

        assertEquals("Testando igualdade das implementacoes do servico", resultAir1, resultAir2);
    }
    
    private AirConditionerParams mockParams(){
        AirConditionerParams params = new AirConditionerParams();
        params.setDuration(360);
        params.setFreezeCost(new BigDecimal(0.1));
        params.setHumanTemperatureDiference(2f);
        params.setStartCost(new BigDecimal(0.5));
        params.setTemperatureLatency(0.5f);
        
        return params;
    }
}
