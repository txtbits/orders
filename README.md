# Import and sort orders from CSV files into REST API

Development of an API based on REST orders with Spring Boot from imported CSV files. 
These files can be processed and filtered and later downloaded through it. 

# Tools and frameworks

Tools needed to build, deploy and run the project:
- **Java JDK 8**. Recognized class-based, object-oriented programming language.
- **Gradle** (included in project with Gradle Wrapper). Open-source build automation tool that is designed to be flexible enough to build almost any type of software.

The development startup has been done using the utility [Spring Initializr](https://start.spring.io/) to initialize Spring Boot projects with some selected dependencies. The technologies used in the project are:
- **Spring Boot**: Open source Java-based framework used to create a micro Services.
- **Spring Web**: Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- **Spring Data JPA**: Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
- **Spring Configuration Processor**: Generate metadata for developers to offer contextual help and "code completion" when working with custom configuration keys (ex.application.properties/.yml files).
- **Swagger**: Toolset to design, build, document, and use RESTful web services. Includes automated documentation, code generation and test-case generation.
- **H2 Database**: Lightweight 100% Java SQL Database Engine for fast test for development deployment.
- **Lombok**: Java annotation library which helps to reduce boilerplate code.

# Build, deploy and run
To build the project you must use gradle. You must go into the directory of each module and execute:
```
gradle clean build -x test
```

After that, you can launch each application executable:
```
java -jar build/libs/orders-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

* Access to HTML web form for upload csv files: [http://localhost:8080](http://localhost:8080)
* Access to API Swagger Playground: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* Access to H2 Database console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  * Database: _ordersdb_
  * User Name: _sa_
  * Password: _password_

## REST methods available

* **GET /counts** - Summary with the count for each type of the fields: Region, Country, Item Type, Sales Channel, Order Priority.
* **GET /download/_{fileName}_** - Download the processed file ordered by orderId.
* **GET /order/_{id}_** - Get the data of an order by id.
* **GET /orders** - Get all orders data.
* **POST /upload** - Upload CSV files with the orders data.

# Nice-To-Have
Here are some improvements that could be made in the future:
* Improve the upload of CSV files asynchronously, mainly for large files.
* Improve exception handling
* Separate the file upload process from the rest api into different microservices
