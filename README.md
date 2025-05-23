# App de Análise de Crédito

## Visão Geral

Credit Analysis App é um microserviço Spring Boot projetado para avaliar propostas de crédito assincronamente. A aplicação recebe propostas de crédito através de uma fila RabbitMQ, as analisa com base em várias estratégias de pontuação e envia os resultados de volta através de outra exchange RabbitMQ.

## Sumário

- [Arquitetura](#arquitetura)
- [Principais Funcionalidades](#principais-funcionalidades)
- [Tecnologias](#tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Primeiros Passos](#primeiros-passos)
  - [Pré-requisitos](#pré-requisitos)
  - [Instalação](#instalação)
  - [Configuração](#configuração)
- [Executando a Aplicação](#executando-a-aplicação)
- [Documentação da API](#api-documentation)
- [Testes](#testing)
- [Suporte Docker](#docker-support)

## Arquitetura

A aplicação segue um padrão de arquitetura de microsserviços, focando no domínio de análise de crédito. Ela segue os princípios SOLID e emprega uma abordagem de arquitetura limpa com:

- **Camada de Domínio**: Contém entidades de negócio centrais como `Proposal` e `User`
- **Camada de Serviço**: Contém a lógica de negócio com o `CreditAnalysisService` e implementações de estratégia
- **Camada de Infraestrutura**: Lida com a comunicação com RabbitMQ através de listeners e serviços de notificação

A aplicação utiliza o Padrão Strategy para calcular pontuações de crédito, permitindo critérios de pontuação flexíveis que podem ser facilmente estendidos ou modificados.

## Principais Funcionalidades

- Processamento assíncrono de propostas de crédito via RabbitMQ
- Múltiplas estratégias de pontuação que analisam vários aspectos da capacidade de crédito
- Limite de aprovação configurável
- Tratamento de erros com mecanismos de retry
- Log abrangente
- Suporte Docker para implantação conteinerizada

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
│   │               ├── config/             # Classes de configuração
│   │               ├── domain/             # Entidades de domínio
│   │               ├── dto/                # Objetos de Transferência de Dados
│   │               ├── exception/          # Exceções personalizadas e handlers
│   │               ├── listener/           # Listeners de mensagem RabbitMQ
│   │               ├── mapper/             # Mapeadores de objeto
│   │               ├── service/            # Serviços de lógica de negócio
│   │               │   └── strategy/       # Estratégias de pontuação de crédito
│   │               └── statics/            # Constantes e mensagens estáticas
│   └── resources/
│       └── application.properties          # Configuração da aplicação
└── test/
└── java/                               # Classes de teste


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
Compile a aplicação:
Bash

mvn clean package
Configuração
Configure a aplicação através de application.properties:

Properties

# Nome da aplicação
spring.application.name=credit-analysis-app

# Configuração RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# Nomes da fila e da exchange
rabbitmq.queue.pending.proposal=pending-proposal.ms-credit-analysis
rabbitmq.completed-exchange.exchange=completed-proposal.ex

# Configuração de retry
spring.rabbitmq.listener.simple.retry.enabled=true
spring.rabbitmq.listener.simple.retry.max-attempts=3

# Configuração de análise de crédito
credit.analysis.approval.threshold=350
Executando a Aplicação
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
A aplicação escuta por propostas de crédito na fila pending-proposal.ms-credit-analysis
Cada proposta é analisada usando múltiplas estratégias de pontuação:
Avaliação de compatibilidade de renda
Avaliação de prazo de pagamento
Pontuação baseada em nome
Verificação de registros negativos
Avaliação de empréstimos existentes
Os pontos de cada estratégia são somados e comparados ao limite de aprovação
A proposta analisada com status de aprovação é enviada para a exchange completed-proposal.ex
Testes
Execute os testes de unidade:

Bash

mvn test
Execute os testes de integração e verifique a build:

Bash

mvn verify
Suporte Docker
A aplicação inclui um Dockerfile e docker-compose.yml para implantação conteinerizada:

Dockerfile: Define a imagem do contêiner para a aplicação
docker-compose.yml: Orquestra a aplicação e suas dependências (RabbitMQ)
Para construir e executar com Docker Compose:

Bash

docker-compose up --build
Contribuindo
Faça um fork do repositório
Crie uma branch de recurso (git checkout -b feature/amazing-feature)
Faça commit das suas alterações (git commit -m 'Add amazing feature')
Envie para a branch (git push origin feature/amazing-feature)
Abra um Pull Request
Licença
Este projeto está licenciado sob a Licença MIT.
