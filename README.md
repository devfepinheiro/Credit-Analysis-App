# Aplicativo de Análise de Crédito

## Visão Geral

O Aplicativo de Análise de Crédito é um microsserviço Spring Boot projetado para avaliar propostas de crédito de forma assíncrona. O aplicativo recebe propostas de crédito através de uma fila RabbitMQ, as analisa com base em várias estratégias de pontuação e envia os resultados de volta através de outra exchange RabbitMQ.

## Sumário

- [Arquitetura](#arquitetura)
- [Principais Funcionalidades](#principais-funcionalidades)
- [Tecnologias](#tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Primeiros Passos](#getting-started)
  - [Pré-requisitos](#prerequisites)
  - [Instalação](#installation)
  - [Configuração](#configuration)
- [Executando o Aplicativo](#running-the-application)
- [Documentação da API](#api-documentation)
- [Testes](#testing)
- [Suporte a Docker](#docker-support)

## Arquitetura

O aplicativo segue um padrão de arquitetura de microsserviços, focando no domínio de análise de crédito. Ele segue os princípios SOLID e emprega uma abordagem de arquitetura limpa com:

- **Camada de Domínio**: Contém entidades de negócio centrais como `Proposal` e `User`
- **Camada de Serviço**: Contém a lógica de negócio com o `CreditAnalysisService` e implementações de estratégia
- **Camada de Infraestrutura**: Lida com a comunicação com o RabbitMQ através de listeners e notification services

O aplicativo usa o padrão Strategy para calcular pontuações de crédito, permitindo critérios de pontuação flexíveis que podem ser facilmente estendidos ou modificados.

## Principais Funcionalidades

- Processamento assíncrono de propostas de crédito via RabbitMQ
- Múltiplas estratégias de pontuação que analisam vários aspectos da capacidade de crédito
- Limiar de aprovação configurável
- Tratamento de erros com mecanismos de repetição
- Log abrangente
- Suporte a Docker para implantação conteinerizada

## Tecnologias

- Java 21
- Spring Boot
- Spring AMQP (RabbitMQ)
- Lombok
- Maven
- Docker
- JUnit e Mockito (para testes)

## Estrutura do Projeto

src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── leonardo/
│   │           └── creditanalysisapp/
│   │               ├── config/             # Configuration classes
│   │               ├── domain/             # Domain entities
│   │               ├── dto/                # Data Transfer Objects
│   │               ├── exception/          # Custom exceptions and handlers
│   │               ├── listener/           # RabbitMQ message listeners
│   │               ├── mapper/             # Object mappers
│   │               ├── service/            # Business logic services
│   │               │   └── strategy/       # Credit scoring strategies
│   │               └── statics/            # Constants and static messages
│   └── resources/
│       └── application.properties          # Application configuration
└── test/
    └── java/                               # Test classes

## Primeiros Passos

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Servidor RabbitMQ (executando localmente ou acessível remotamente)
- Docker (opcional, para implantação conteinerizada)

### Instalação

1. Clone o repositório:
   ```bash
   git clone [https://github.com/yourusername/credit-analysis-app.git](https://github.com/yourusername/credit-analysis-app.git)
   cd credit-analysis-app
Construa o aplicativo:
Bash

mvn clean package
Configuração
Configure o aplicativo através de application.properties:

Properties

# Nome da aplicação
spring.application.name=credit-analysis-app

# RabbitMQ Configuration
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# Queue and exchange names
rabbitmq.queue.pending.proposal=pending-proposal.ms-credit-analysis
rabbitmq.completed-exchange.exchange=completed-proposal.ex

# Retry configuration
spring.rabbitmq.listener.simple.retry.enabled=true
spring.rabbitmq.listener.simple.retry.max-attempts=3

# Credit analysis configuration
credit.analysis.approval.threshold=350
Executando o Aplicativo
Usando Maven
Bash

mvn spring-boot:run
Usando Java
Bash

java -jar target/credit-analysis-app.jar
Usando Docker
Bash

docker build -t credit-analysis-app .
docker run -p 8080:8080 credit-analysis-app
Alternativamente, você pode usar Docker Compose:

Bash

docker-compose up
Processo de Análise de Crédito
O aplicativo escuta por propostas de crédito na fila pending-proposal.ms-credit-analysis
Cada proposta é analisada usando múltiplas estratégias de pontuação:
Avaliação de compatibilidade de renda
Avaliação do prazo de pagamento
Pontuação baseada no nome
Verificação de registros negativos
Avaliação de empréstimos existentes
Os pontos de cada estratégia são somados e comparados ao limiar de aprovação
A proposta analisada com status de aprovação é enviada para a exchange completed-proposal.ex
Testes
Run unit tests:

Bash

mvn test
Run integration tests and verify the build:

Bash

mvn verify
Suporte a Docker
O aplicativo inclui um Dockerfile e docker-compose.yml para implantação conteinerizada:

Dockerfile: Define a imagem do contêiner para o aplicativo
docker-compose.yml: Orquestra o aplicativo e suas dependências (RabbitMQ)
Para construir e executar com Docker Compose:

Bash

docker-compose up --build
Contribuindo
Fork the repository
Create a feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a Pull Request
License
Este projeto está licenciado sob a Licença MIT.
