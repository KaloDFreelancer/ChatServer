# Chat Server

Spring Boot Controller + MySQL + Docker + Liquibase

## Getting Started

These instructions will give you a copy of the project up and running on
your local machine for development and testing purposes.

### Prerequisites

- [Docker](https://www.docker.com/get-started)
- Java11

### Installing

    docker pull kaloyanmitev/chat-server:1.1

And after git clone run in the terminal(with Java11):
    
    ./gradlew clean build -x test

---

    docker-compose up
