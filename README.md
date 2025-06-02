App de Análise de Crédito

Visão Geral
O App de Análise de Crédito é um microsserviço Spring Boot projetado para avaliar propostas de crédito de forma assíncrona. A aplicação recebe propostas de crédito por meio de uma fila RabbitMQ, as analisa com base em várias estratégias de pontuação e envia os resultados de volta por meio de outro exchange RabbitMQ.

Sumário
Arquitetura
Principais Recursos
Tecnologias
Estrutura do Projeto
Introdução
Pré-requisitos
Instalação
Configuração
Executando a Aplicação
Documentação da API
Testes
Suporte Docker
Arquitetura
A aplicação segue um padrão de arquitetura de microsserviços, focando no domínio de análise de crédito. Ela adere aos princípios SOLID e emprega uma abordagem de arquitetura limpa com:

Camada de Domínio: Contém entidades de negócio essenciais como Proposal e User.
Camada de Serviço: Contém a lógica de negócio com o CreditAnalysisService e implementações de estratégia.
Camada de Infraestrutura: Gerencia a comunicação com o RabbitMQ por meio de listeners e serviços de notificação.
A aplicação utiliza o Strategy Pattern para calcular pontuações de crédito, permitindo critérios de pontuação flexíveis que podem ser facilmente estendidos ou modificados.

Principais Recursos
Processamento assíncrono de propostas de crédito via RabbitMQ.
Múltiplas estratégias de pontuação que analisam vários aspectos da capacidade de crédito.
Limiar de aprovação configurável.
Tratamento de erros com mecanismos de retry.
Registro abrangente (logging).
Suporte a Docker para deploy containerizado.
Tecnologias
Java 21
Spring Boot
Spring AMQP (RabbitMQ)
Lombok
Maven
Docker
JUnit e Mockito (para testes)
Estrutura do Projeto
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── leonardo/
│   │           └── creditanalysisapp/
│   │               ├── config/             # Classes de configuração
│   │               ├── domain/             # Entidades de domínio
│   │               ├── dto/                # Objetos de Transferência de Dados (DTOs)
│   │               ├── exception/          # Exceções personalizadas e handlers
│   │               ├── listener/           # Listeners de mensagens RabbitMQ
│   │               ├── mapper/             # Mapeadores de objetos
│   │               ├── service/            # Serviços de lógica de negócio
│   │               │   └── strategy/       # Estratégias de pontuação de crédito
│   │               └── statics/            # Constantes e mensagens estáticas
│   └── resources/
│       └── application.properties          # Configuração da aplicação
└── test/
    └── java/                               # Classes de teste
Introdução
Pré-requisitos
Java 17 ou superior
Maven 3.6+
Servidor RabbitMQ (executando localmente ou acessível remotamente)
Docker (opcional, para deploy containerizado)
Instalação
Clone o repositório:

Bash

git clone https://github.com/yourusername/credit-analysis-app.git
cd credit-analysis-app
Construa a aplicação:

Bash

mvn clean package
Configuração
Configure a aplicação através do application.properties:

Properties

# Nome da aplicação
spring.application.name=credit-analysis-app

# Configurações do RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# Nomes da fila e do exchange
rabbitmq.queue.pending.proposal=pending-proposal.ms-credit-analysis
rabbitmq.completed-exchange.exchange=completed-proposal.ex

# Configurações de retry
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
A aplicação escuta por propostas de crédito na fila pending-proposal.ms-credit-analysis.
Cada proposta é analisada usando múltiplas estratégias de pontuação:
Avaliação de compatibilidade de renda.
Avaliação de prazo de pagamento.
Pontuação baseada no nome.
Verificação de registros negativos.
Avaliação de empréstimos existentes.
Os pontos de cada estratégia são somados e comparados com o limiar de aprovação.
A proposta analisada com o status de aprovação é enviada para o exchange completed-proposal.ex.
Testes
Execute os testes de unidade:

Bash

mvn test
Execute os testes de integração e verifique o build:

Bash

mvn verify
Suporte Docker
A aplicação inclui um Dockerfile e docker-compose.yml para deploy containerizado:

Dockerfile: Define a imagem do contêiner para a aplicação.
docker-compose.yml: Orquestra a aplicação e suas dependências (RabbitMQ).
Para construir e executar com Docker Compose:

Bash

docker-compose up --build
Contribuição
Faça um fork do repositório.
Crie uma branch de recurso (git checkout -b feature/amazing-feature).
Faça commit das suas alterações (git commit -m 'Add amazing feature').
Envie para a branch (git push origin feature/amazing-feature).
Abra um Pull Request.
Licença
Este projeto está licenciado sob a Licença MIT.
