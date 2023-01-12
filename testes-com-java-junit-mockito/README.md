[![CircleCI](https://dl.circleci.com/status-badge/img/gh/jeronimafloriano/tests-java-e-spring/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/jeronimafloriano/tests-java-e-spring/tree/master)

# Testes no Java com JUnit 5 e Maven

Esse repositório contém exemplos de testes unitários e integrados no Java utilizando JUnit 5.
Também foi realizada a implementação de um pipeline de testes com CircleCI.

## Setup
### Requerimentos
* Java 11 ou superior
* Maven 3.8.6 ou superior

## Recursos estudados:

* Anotações básicas do Junit (@BeforeAll, @BeforeEach...)
* Implementação de tags de execução dos testes
* Desabilitação de métodos de teste
* Testes de TimeOut 
* Configuração de Repetição de testes
* Classes de teste aninhadas
* Apresentação de informações do teste com TestInfo e RepetitionInfo 
* Configurações através de interfaces de teste
* Configuração de condições de execução do teste com @Enabled
* Parametrizações com @ParameterizedTest
* Testes de Asserções, Suposições e Exceções
* Suite de testes
