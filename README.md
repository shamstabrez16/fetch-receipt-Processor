# fetch-receipt-processor
fetch-receipt-processor challenge

Index  of Contents
    Technical Details
    Technologies Used
    Instructions for Running and Building the Project


**Technical Details:**

Project Name: fetch-receipt-processor
Project Version: 0.0.1-SNAPSHOT
Framework: Spring Boot
Database: H2 (in-memory database)
Testing: JUnit 5, Pact JVM
Description: https://github.com/fetch-rewards/receipt-processor-challenge
Java Version: 17

**Technologies Used:**

**Spring Boot:** It is the main framework used for developing the application.
**Spring Boot Starter Parent:** It provides the parent configuration for the Spring Boot project.
**Spring Boot Starter Data JPA:** It enables the use of Java Persistence API (JPA) for data access and object-relational mapping.
**Spring Boot Starter Web:** It provides the necessary dependencies to build web applications with Spring MVC.
**Spring Boot DevTools:** It offers development-time features like automatic application restart and live reload.
**H2 Database:** It is an in-memory database used for development and testing purposes.
**Project Lombok:** It is a library that simplifies the development process by automatically generating boilerplate code such as getters, setters, constructors, etc.
**Spring Boot Starter Test:** It provides testing dependencies and utilities for writing unit and integration tests.
**Pact JVM Provider JUnit 5 Spring:** It is a library for creating consumer-driven contracts and testing interactions between providers and consumers in distributed systems.


**Instructions for Running and Building the Project**
 
**Running Application in Docker**

This guide explains how to run the application inside a Docker container using a Docker Compose file.

# Prerequisites
Make sure you have the following installed on your machine:

-> Docker: Follow the official Docker installation guide for your operating system.

# Setup
1. Clone the repository - git clone https://github.com/shamstabrez16/fetch-receipt-processor.git
2. Navigate to the project directory - fetch-receipt-processor., use command -> cmd - cd <project_directory>
3. Make sure you have Docker applictaion running
4. using the command **./mvnw.cmd clean package** to build the jar file
5. Build the Docker image - **docker-compose up --build**

   NOTE:_ Please make sure No service is running on port 8080. _

# Running the Application
1. Start the application using Docker Compose
   **docker-compose up**
This command will start the application container along with any necessary dependencies defined in the docker-compose.yml file.

2. Access the application

Open your Postman and visit 

POST -  http://localhost:8080/receipt/process

GET - http://localhost:8080/receipt/{id}/points

stop the application and the associated containers, use the following command
  **docker-compose down**

