ControleTemperatura
===================

Projeto para simular o controle de temperatura de um ar condicionado

Sobre o Projeto
--------------------------------------

O desafio proposto era simples, por isso tentei maximizar o enredo dele para poder aplicar conhecimentos que adiquiri.

Acrescentei algumas id�ias no desafio. A principal delas � tornar as vari�veis do projeto configur�veis. Imaginei uma situa��o onde o ar condicionado seria colocado em outro c�modo.  Isso poderia fazer com que o valor que a temperaqtura aumenta por minuto aumentasse ou diminuisse.=, al�m de outras vari�veis como a que referencia o quanto que os seres humandos conseguem disinguir.  Entao utilizei a id�ia de "ambienta��o" do sistema. O conjunto de vari�veis propostas configura um ambiente que chamei de "globoroom". Assim, se por exemplo o ar condicionado fosse mudado de sala ou de pr�dio, bastaria criar um novo properties para este novo ambiente sem ter que mudar a alpica��o.

Outra id�ia foi a de simular uma interface de comunica��o com o hardware do ar condicionado. Assim, este mesmo sistema de controle de gastos poderia funcionar mudando o modelo do ar condicionado (para um mais ou menos potente por exemplo). Seria necessario somente implementar a interface entre o sistema e o novo hardware.

Aproveitando esta �ltima id�ia, fiz duas implementa��es para testar qual delas seria mais eficiente no controle dos gastos. Uma faz uma itera��o simulando minuto a minuto o que acontece no ambiente. Outra utiliza uma f�rmula que criei para controlar a temperatura.  No final, ambas as implementa��es deram o mesmo resultado, o que pode significar uma efici�ncia das duas formas.

Infelizmente n�o consegui fazer meu projeto gerar um makefile para ser instalado automaticamente conforme solicitado. A id�ia era utilizar o maven com o glassfish embbedable, mas foi a primeira fez que tentei utilizar esta t�cnica e n�o funcionou a tempo.  Tentei suprir esta falha caprichando ao m�ximo no meu c�digo, al�m disso meu projeeto utiliza o maven, ent�o pode ser aberto em qualquer ide que baixar� as dependencias automaticamente ao construir.

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
