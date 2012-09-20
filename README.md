ControleTemperatura
===================

Projeto para simular o controle de temperatura de um ar condicionado

Sobre o Projeto
--------------------------------------

O desafio proposto era simples, por isso tentei maximizar o enredo dele para poder aplicar conhecimentos que adiquiri.

Acrescentei algumas idéias no desafio. A principal delas é tornar as variáveis do projeto configuráveis. Imaginei uma situação onde o ar condicionado seria colocado em outro cômodo.  Isso poderia fazer com que o valor que a temperaqtura aumenta por minuto aumentasse ou diminuisse.=, além de outras variáveis como a que referencia o quanto que os seres humandos conseguem disinguir.  Entao utilizei a idéia de "ambientação" do sistema. O conjunto de variáveis propostas configura um ambiente que chamei de "globoroom". Assim, se por exemplo o ar condicionado fosse mudado de sala ou de prédio, bastaria criar um novo properties para este novo ambiente sem ter que mudar a alpicação.

Outra idéia foi a de simular uma interface de comunicação com o hardware do ar condicionado. Assim, este mesmo sistema de controle de gastos poderia funcionar mudando o modelo do ar condicionado (para um mais ou menos potente por exemplo). Seria necessario somente implementar a interface entre o sistema e o novo hardware.

Aproveitando esta última idéia, fiz duas implementações para testar qual delas seria mais eficiente no controle dos gastos. Uma faz uma iteração simulando minuto a minuto o que acontece no ambiente. Outra utiliza uma fórmula que criei para controlar a temperatura.  No final, ambas as implementações deram o mesmo resultado, o que pode significar uma eficiência das duas formas.

Infelizmente não consegui fazer meu projeto gerar um makefile para ser instalado automaticamente conforme solicitado. A idéia era utilizar o maven com o glassfish embbedable, mas foi a primeira fez que tentei utilizar esta técnica e não funcionou a tempo.  Tentei suprir esta falha caprichando ao máximo no meu código, além disso meu projeeto utiliza o maven, então pode ser aberto em qualquer ide que baixará as dependencias automaticamente ao construir.

Tecnologias Utilizadas
--------------------------------------

O projeto foi arquitetado com as seguintes tecnologias

1. Java EE 6
2. Servidor GlassFish 3+
3. Maven
4. VRpator 
5. log4j 
6. SiteMesh 
7. JUnit
8. JQuery
9. JQueryUI
10. HTML5
11. CSS3
