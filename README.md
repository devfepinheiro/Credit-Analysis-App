# Aplicativo de Análise de Crédito

## Visão Geral

O Aplicativo de Análise de Crédito é um microsserviço Spring Boot projetado para avaliar propostas de crédito de forma assíncrona. A aplicação recebe propostas de crédito por meio de uma fila RabbitMQ, as analisa com base em várias estratégias de pontuação e envia os resultados de volta por meio de outro exchange do RabbitMQ.

## Índice

- [Arquitetura](#arquitetura)
- [Principais Funcionalidades](#principais-funcionalidades)
- [Tecnologias](#tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Primeiros Passos](#primeiros-passos)
  - [Pré-requisitos](#pré-requisitos)
  - [Instalação](#instalação)
  - [Configuração](#configuração)
- [Executando a Aplicação](#executando-a-aplicação)
- [Documentação da API](#documentação-da-api)
- [Testes](#testes)
- [Suporte a Docker](#suporte-a-docker)

## Arquitetura

A aplicação segue o padrão de arquitetura de microsserviços, com foco no domínio de análise de crédito. Ela segue os princípios SOLID e emprega uma abordagem de arquitetura limpa com:

- **Camada de Domínio**: Contém as entidades de negócio principais como `Proposal` e `User`
- **Camada de Serviço**: Contém a lógica de negócio com `CreditAnalysisService` e implementações de estratégias
- **Camada de Infraestrutura**: Lida com a comunicação com RabbitMQ por meio de ouvintes e serviços de notificação

A aplicação usa o Padrão Strategy para cálculo de pontuação de crédito, permitindo critérios de pontuação flexíveis que podem ser facilmente estendidos ou modificados.

## Principais Funcionalidades

- Processamento assíncrono de propostas de crédito via RabbitMQ
- Múltiplas estratégias de pontuação que analisam vários aspectos da capacidade de crédito
- Limite de aprovação configurável
- Tratamento de erros com mecanismos de retentativa
- Log abrangente
- Suporte a Docker para implantação em contêineres

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
│ ├── java/
│ │ └── com/
│ │ └── leonardo/
│ │ └── creditanalysisapp/
│ │ ├── config/ # Classes de configuração
│ │ ├── domain/ # Entidades de domínio
│ │ ├── dto/ # Objetos de Transferência de Dados
│ │ ├── exception/ # Exceções personalizadas e manipuladores
│ │ ├── listener/ # Ouvintes de mensagens do RabbitMQ
│ │ ├── mapper/ # Mapeadores de objetos
│ │ ├── service/ # Serviços de lógica de negócio
│ │ │ └── strategy/ # Estratégias de pontuação de crédito
│ │ └── statics/ # Constantes e mensagens estáticas
│ └── resources/
│ └── application.properties # Configuração da aplicação
└── test/
└── java/ # Classes de teste

## Primeiros Passos

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Servidor RabbitMQ (executando localmente ou acessível remotamente)
- Docker (opcional, para implantação em contêineres)

### Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/yourusername/credit-analysis-app.git
   cd credit-analysis-app
Compile a aplicação:

bash
Sempre exibir os detalhes

Copiar
mvn clean package
Configuração
Configure a aplicação no application.properties:

properties
Sempre exibir os detalhes

Copiar
# Nome da aplicação
spring.application.name=credit-analysis-app

# Configuração do RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# Nomes das filas e exchanges
rabbitmq.queue.pending.proposal=pending-proposal.ms-credit-analysis
rabbitmq.completed-exchange.exchange=completed-proposal.ex

# Configuração de retentativa
spring.rabbitmq.listener.simple.retry.enabled=true
spring.rabbitmq.listener.simple.retry.max-attempts=3

# Configuração de análise de crédito
credit.analysis.approval.threshold=350
Executando a Aplicação
Usando Maven
bash
Sempre exibir os detalhes

Copiar
mvn spring-boot:run
Usando Java
bash
Sempre exibir os detalhes

Copiar
java -jar target/credit-analysis-app.jar
Usando Docker
bash
Sempre exibir os detalhes

Copiar
docker build -t credit-analysis-app .
docker run -p 8080:8080 credit-analysis-app
Alternativamente, você pode usar Docker Compose:

bash
Sempre exibir os detalhes

Copiar
docker-compose up
Processo de Análise de Crédito
A aplicação escuta propostas de crédito na fila pending-proposal.ms-credit-analysis

Cada proposta é analisada utilizando múltiplas estratégias de pontuação:

Avaliação de compatibilidade de renda

Avaliação do prazo de pagamento

Pontuação baseada no nome

Verificações de registros negativos

Avaliação de empréstimos existentes

Os pontos de cada estratégia são somados e comparados ao limite de aprovação

A proposta analisada com o status de aprovação é enviada para o exchange completed-proposal.ex

Testes
Executar testes unitários:

bash
Sempre exibir os detalhes

Copiar
mvn test
Executar testes de integração e verificar o build:

bash
Sempre exibir os detalhes

Copiar
mvn verify
Suporte a Docker
A aplicação inclui um Dockerfile e um docker-compose.yml para implantação em contêineres:

Dockerfile: Define a imagem do contêiner para a aplicação

docker-compose.yml: Orquestra a aplicação e suas dependências (RabbitMQ)

Para compilar e executar com Docker Compose:

bash
Sempre exibir os detalhes

Copiar
docker-compose up --build
Contribuindo
Faça um fork do repositório

Crie uma branch de funcionalidade (git checkout -b feature/funcionalidade-incrível)

Faça commit das suas alterações (git commit -m 'Adiciona funcionalidade incrível')

Faça push para a branch (git push origin feature/funcionalidade-incrível)

Abra um Pull Request

Licença
Este projeto está licenciado sob a Licença MIT.
