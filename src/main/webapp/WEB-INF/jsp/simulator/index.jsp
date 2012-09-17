<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Simulador</title>
        
        <link rel="stylesheet" href="<c:url value="/css/jquery/jquery.ui.all.css" />" />
        
        <script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.8.0.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery/jquery.ui.core.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery/jquery.ui.widget.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery/jquery.ui.mouse.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery/jquery.ui.slider.min.js" />"></script>
        
        <script type="text/javascript">
            $(function() {
                $( "#slider-horizontal" ).slider({
                    orientation: "horizontal",
                    range: "min",
                    min: 10,
                    max: 30,
                    step: 1,
                    value: 60,
                    slide: function( event, ui ) {
                        //BugFix - A lib traz o estado anterior do objeto. Vamos adivinhar pra onde ele vai.
                        var currentValue = parseInt($("#currentRange").html());
                        var newValue = parseInt(ui.value);
                        var newLeft = parseInt(ui.handle.style.left);
                        
                        if(currentValue > newValue){
                            newLeft -= 5;
                        } else {
                            newLeft += 5;
                        }
                        
                        $( "#amount" ).val( ui.value );
                        $("#currentRange").html( newValue );
                        $("#currentRange").css("left", newLeft+"%");
                    },
                    change: function( event, ui ) {
                        $("#currentRange").css("left", ui.handle.style.left);
                    }
                    
		});
		$( "#amount" ).val( $( "#slider-horizontal" ).slider( "value" ) );
            });
	</script>
    </head>
    <body>
        <h1 class="mainTitle">Regular temperatura</h1>
        
        ${errors}1
        ${message}2
        ${messages}3
        
        <form action="/" method="post" class="simulatorForm">
            <p>
                <label for="climate">temp. ambiente</label>
                <input type="text" id="climate" name="" value="10" />
                ºC
            </p>
            
            <p>
                <label for="timer"> tempo </label>
                <input type="text" id="timer" name="" value="10" />
                min
            </p>
            
            <p id="temperatureSlider">
                <label for="maxClimate">temp. m&aacute;xima</label>
                
                <span style="display: none;">
                    <input type="text" id="amount" style="border:0; color:#f6931f; font-weight:bold;" />
                </span>

                <span class="temperatureRage">10ºC</span>
                <span id="slider-horizontal">
                    
                    <span id="currentRange" style="left:100%;">30</span>
                </span>
                <span class="temperatureRage">30ºC</span>
            </p>
            
            <p class="submitArea">
                <button type="submit" class="simulatorSubmit">Simular</button>
                
                <label> custo </label>
                <span class="totalCost"> R$ 12,00 </span>
            </p>
        </form>
        
    </body>
</html>
