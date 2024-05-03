# TODO List RESTFUL API

Welcome to the TODO List REST API built with Spring Boot! This 
application provides endpoints to manage your TODO tasks efficiently.

## What is this app?
This Spring Boot application serves as a backend service 
for managing TODO lists. It provides RESTful endpoints to 
perform CRUD (Create, Read, Update, Delete) operations on tasks.

## What can this API do?
This API allows users to register and then start adding TODO items
depending on what tasks they want to complete. After successfully
adding a task user can see all their tasks and after completion they
can remove them from the list. Also if they decide that they want to 
change something in the task they can also do that

### Endpoints:
- **POST /api/v1/users**: Create new user 
- **POST /api/v1/todos/{username}**: Create new TODO item
- **GET /api/v1/todos/{username}**: Retrieve all TODO items of a user
- **PUT /api/v1/todos/{username}/{title}**: Update specific task of a user
- **DELETE /api/v1/todos/{username}/{title}**: Delete specific task of a user

### Todo model:
- **id**: A unique identifier of a task
- **title**: A name of a task
- **description**: A description of a task user wants to complete
- **user**: A user this task belongs to

### User model:
- **id**: A unique identifier of a user
- **username**: A username of a user
- **firstName**: A first name of a user
- **lastName**: A last name of a user
- **todos**: A list of todo items user has created

## How to run this application:

### Prerequisites:
- Java 17 or higher installed.
- Maven installed.
- MySQL installed and running (you can use a local instance or a cloud-based service).

### Steps
1. Clone this repository:

```shell
git clone git@github.com:David-Mais/ToDo-REST-API.git
```
2. Navigate to the project directory:

```sh
cd ToDo-REST-API
```
3. Open `src/main/resources/application.yml` and configure MySQL connection properties:

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/todo
    username: root
    password: rootroot
```

4. Build the project:

```sh
mvn clean install
```

5. Run the application:

```sh
java -jar target/ToDo-REST-API-1.0.jar
```

The application will start running at `http://localhost:8080`.

## How to send requests:

You can use tools like cURL, Postman, or any HTTP client 
library to send requests to the API endpoints.

## Experience while Developing/Learning with ChatGPT:
- It was pretty easy competing this task with AI since it had all the
specific answers whet i was looking for I could provide snippets
and AI would provide tailored solutions for my project
- This task took me about 4 hours in total to complete since it
didn't have much complexity it was simple CRUD application
- Code was not always ready to run since there were times when
GPT provided DTOs that didn't exist in the project or was not relevant
for my specific use cases. But overall about 90%-95% of code was 
already ready to be used.
- There was a challenge in sketching out the project what would be
the relevant information to return on each http request and which
DTOs to use where.
- One type of prompt that I used the most `Help me do specific task
within this method """Code snippet""""`