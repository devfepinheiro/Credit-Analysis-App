# Credit Analysis App

## Overview

Credit Analysis App is a Spring Boot microservice designed to evaluate credit proposals asynchronously. The application receives credit proposals through a RabbitMQ queue, analyzes them based on various scoring strategies, and sends the results back through another RabbitMQ exchange.

## Table of Contents

- [Architecture](#architecture)
- [Key Features](#key-features)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Docker Support](#docker-support)

## Architecture

The application follows a microservice architecture pattern, focusing on the credit analysis domain. It follows SOLID principles and employs a clean architecture approach with:

- **Domain Layer**: Contains core business entities like `Proposal` and `User`
- **Service Layer**: Contains business logic with the `CreditAnalysisService` and strategy implementations
- **Infrastructure Layer**: Handles communication with RabbitMQ through listeners and notification services

The application uses the Strategy Pattern for calculating credit scores, allowing for flexible scoring criteria that can be easily extended or modified.

## Key Features

- Asynchronous credit proposal processing via RabbitMQ
- Multiple scoring strategies that analyze various aspects of credit worthiness
- Configurable approval threshold
- Error handling with retry mechanisms
- Comprehensive logging
- Docker support for containerized deployment

## Technologies

- Java 21
- Spring Boot
- Spring AMQP (RabbitMQ)
- Lombok
- Maven
- Docker
- JUnit and Mockito (for testing)

## Project Structure

```
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
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- RabbitMQ server (running locally or accessible remotely)
- Docker (optional, for containerized deployment)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/credit-analysis-app.git
   cd credit-analysis-app
   ```

2. Build the application:
   ```bash
   mvn clean package
   ```

### Configuration

Configure the application through `application.properties`:

```properties
# Application name
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
```

## Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

### Using Java

```bash
java -jar target/credit-analysis-app.jar
```

### Using Docker

```bash
docker build -t credit-analysis-app .
docker run -p 8080:8080 credit-analysis-app
```

Alternatively, you can use Docker Compose:

```bash
docker-compose up
```

## Credit Analysis Process

1. The application listens for credit proposals on the `pending-proposal.ms-credit-analysis` queue
2. Each proposal is analyzed using multiple scoring strategies:
   - Income compatibility assessment
   - Payment term evaluation
   - Name-based scoring
   - Negative record checks
   - Existing loan evaluation
3. Points from each strategy are summed and compared to the approval threshold
4. The analyzed proposal with approval status is sent to the `completed-proposal.ex` exchange

## Testing

Run unit tests:

```bash
mvn test
```

Run integration tests and verify the build:

```bash
mvn verify
```

## Docker Support

The application includes a `Dockerfile` and `docker-compose.yml` for containerized deployment:

- `Dockerfile`: Defines the container image for the application
- `docker-compose.yml`: Orchestrates the application and its dependencies (RabbitMQ)

To build and run with Docker Compose:

```bash
docker-compose up --build
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the [MIT License](LICENSE).