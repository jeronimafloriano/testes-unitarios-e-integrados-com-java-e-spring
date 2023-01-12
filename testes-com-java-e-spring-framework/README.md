[![CircleCI](https://dl.circleci.com/status-badge/img/gh/jeronimafloriano/tests-java-e-spring/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/jeronimafloriano/tests-java-e-spring/tree/master)

# Introduction to JUnit 5 with Maven

Esse repositório contém exemplos de testes unitários e integrados no Java utilizando Mockito.
Também foi realizada a implementação de um pipeline de testes com CircleCI.

## Setup
### Requerimentos
* Java 11 ou superior
* Maven 3.8.6 ou superior

## Recursos estudados:

Mockito
* Indicar o retorno de um método com when(): Quando um método X for chamado o retorno deverá ser Y (thenReturn, thenThrow etc…);
* Verificar se um método foi chamado com verify();
* Usar Argument Matchers para verificar se um determinado método chamou ou retornou algo (any(), anyDouble(),anyLong(),anyInt() etc);
* Utilizar o Given-when-then do BDDMockito para analisar comportamentos;
* Teste com Lambdas utilizando argThat() para verificar uma correspondência em uma propriedade específica;
* Capturar um valor ao chamar um método com ArgumentCaptor;
* Mockito Answers com willAnswer() para configurar uma resposta;
* Verificar a ordem das chamadas com Mockito - InOrder;
* Verificar o tempo de execução adicionando o timeout() ao método then().sould();
* Verificar se um mock nunca foi chamado ou se não foi mais chamado após uma execução;
* Mockito Spies para acessar o objeto subjacente da real implementação, simulando como um mock;
* Utilizar um método @AfterEach para resetar, "limpar", os testes(reset()).

Mockito e Spring
* Configurar execução de testes com Spring e Junit4 e 5;
* Configurar e escanear beans através de classes internas;
* Usar profiles para configurar os testes;
* Spring Test Properties - Definir valores para campos através de arquivos de propriedades;

Mockito e Spring MVC 
* Configurações para testar um único controlador ou vários controladores;
* Configurar Spring MVC utilizando XML;
* Passar parâmetros ao método da API;
* Capturar parâmetros do método com ArgumentCaptor.

