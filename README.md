# CRUD Client Registration API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## Project Overview
This project was developed using the Java programming language and the Spring framework, focusing on client registration. It provides endpoints for CRUD (Create, Read, Update, Delete) operations, each with a minimum access hierarchy defined by users already registered in the database. These users can have `ADMIN` or `USER` roles. Data persistence is managed by the MySQL database.

## Architecture and Endpoints

### User Controller
- **Register**: *Admin-only endpoint*, allows the registration of new users.
- **Login**: Open to any user. Performs authentication and returns a JWT token for use in protected endpoints.
- **Delete**: *Admin-only endpoint*, allows the deletion of registered users.

### Client Controller
- **findAllClient**: Available to users with the `user` role or higher (`admin`), returns a list of all clients.
- **findClientById**: Allows users with the `user` role to retrieve data of a specific client.
- **saveClient**: *Admin-only endpoint*, allows the registration of new clients.
- **updateClient**: Endpoint for updating client data, available to admins only.
- **deleteClient**: *Admin-only endpoint*, allows the deletion of clients.

## Security and Authentication
The API's security is ensured by Spring Security, with JWT token authentication. The authentication flow occurs at the login endpoint, where the generated token is used to authenticate and authorize subsequent requests. The interactive documentation (Springdoc OpenAPI) integrates this functionality, allowing the JWT token to be inserted directly via the Autentication icon in the Swagger interface.

## Technologies and Libraries Used

- **Spring Boot Web**: Development of the REST API.
- **Spring Data JPA**: Integration and data manipulation in MySQL.
- **Spring Security**: Access control and authentication.
- **JWT Token**: Authentication management and validation.
- **Spring Validation**: Input data validation.
- **Springdoc OpenAPI**: Generation of interactive documentation (Swagger UI).
- **Lombok**: Reduces boilerplate code with automatic generation of methods and constructors.
- **Maven**: Dependency management and project build.
- **JDBC MySQL**: Configuration of connection and persistence with MySQL.
- **H2 Database**: Lightweight database used for unit and integration tests.
- **Spring Test (JUnit and Mockito)**: Unit testing and mock creation.
- **Docker Compose**: Container orchestration (including the application and MySQL database), facilitating deployment and environment configuration for both development and production.

## Testing and Quality
The project includes a comprehensive suite of tests:

- **JUnit**: Writing and executing unit tests.
- **Mockito**: Creating mocks to isolate components during tests.
- **H2 Database**: Lightweight environment for integration tests, simulating the behavior of the real database.

## Containerization with Docker
To simplify deployment and environment management, the project uses Docker. A `docker-compose.yml` file is configured to orchestrate the necessary containers, such as:

- **Application Container**: Runs the Spring Boot API.
- **MySQL Container**: Persistent database for the application.

This approach ensures that the environment is easily replicable and scalable, both for development and production, ensuring consistency in application execution.
