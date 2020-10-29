# Scrummers shop-service
Service to manage stores, products and sales.

## Built With

* 	[Maven] - Dependency Management
* 	[JDK] - Java™ Platform, Standard Edition Development Kit
Spring Applications
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control systemenvironments.
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.



## External Tools Used

* 	[Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
* 	[Postman Echo](https://docs.postman-echo.com/?version=latest) - A service that can be used to test your REST clients and make sample API calls. It provides endpoints for GET, POST, PUT, various auth mechanisms and other utility endpoints.




## Running the scrummers-shop-service locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.scrummers.shop.ScrummersShopServiceApplication` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin] like so:

```shell
mvn spring-boot:run
```

## Running the application via docker container



```shell
mvn clean install					   //clean, run all tests and build
```

|  Command |  Description |
|----------|--------------| 
|docker images                                       | take a look at the container images. |
|docker ps                                           | list all the running containers.     |
|docker ps -a                                        | list all the containers, including the ones that have finished executing.|
|docker run scrummers-shop-service:0.0.1-SNAPSHOT    | run the project's docker container   |
|docker stop [container_id]                          | stop a container                     |
|docker rm $(docker ps -aq)                          | stop and remove all containers       |


## Documentation

* 	[Postman Collection]() - offline
* 	[Swagger](http://localhost:8080/swagger-ui.html) - `http://localhost:8080/swagger-ui.html`- Documentation & Testing


## Files and Directories

The project has the next packages and directories:

```text
.
├── Spring Elements
├── src
│   └── main
│       └── java
│           ├── com.scrummers.shop
│           ├── com.scrummers.shop.config
│           ├── com.scrummers.shop.controller
|           ├── com.scrummers.shop.dto
│           ├── com.scrummers.shop.exception
│           ├── com.scrummers.shop.exception.error
│           ├── com.scrummers.shop.model
│           ├── com.scrummers.shop.repository
│           └── com.scrummers.shop.service
│           └── com.scrummers.shop.service.imp
├── src
│   └── main
│       └── resources
│           ├── application.properties
├── src
│   └── test
│       └── java
├── JRE System Library
├── Maven Dependencies
├── bin
├── src
├── target
│   └──scrummers-shop-service:0.0.1-SNAPSHOT
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```
## packages

* 	`models` — to hold our entities of domain;
* 	`repositories` — to communicate with the database, implements DAO pattern;
* 	`services` — to hold our business logic;
* 	`controllers` — to listen to the client REST;


* 	`pom.xml` - contains all the project dependencies
