# fetch-receipt-processor
 fetch-receipt-processor challenge
 
Running Application in Docker
This guide explains how to run the application inside a Docker container using a Docker Compose file.

# Prerequisites
Make sure you have the following installed on your machine:

-> Docker: Follow the official Docker installation guide for your operating system.

# Setup
1. Clone the repository - git clone https://github.com/shamstabrez16/fetch-receipt-processor.git
2. Navigate to the project directory - fetch-receipt-processor., use command -> cmd - cd <project_directory>
3. Build the Docker image - **docker-compose up --build**

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

This command will stop and remove the running containers defined in the docker-
