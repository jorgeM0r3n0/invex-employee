# invex-employee
Servicio backend resiliente seguro y escalable que gestione operaciones sobre una entidad de "Empleado",

## Employee API – REST Controller
This module provides a REST API for managing employees within the system.
It allows creating, retrieving, updating, deleting, and searching employees.
The main entry point is the EmployeeController, which exposes the endpoints under:
```
/api/employees
```
## Features
* Retrieve all employees
* Get employee by ID
* Create one or multiple employees
* Update an employee
* Delete an employee
* Search employees by full or partial name
* Fully documented with OpenAPI / Swagger

## Technologies Used
* Java 17+
* Spring Boot 2.7.18
* Spring Web
* Hibernate / JPA
* Jakarta Validation
* Swagger / springdoc-openapi
* ModelMapper

## Architecture Overview
Controller → Service → Repository → Database  
* Controller: Exposes REST endpoints
* Service: Contains business logic
* Repository: Manages persistence using JPA
* MapperUtil / EmployeeMapper: Handles DTO ↔ Entity conversions
* GlobalExceptionHandler: Returns structured error responses

## Running the Project
* Build
    ```
    mvn clean install
    ```
* Run
    ```
    mvn spring-boot:run
    ```
* Open Swagger UI
    ```
    http://localhost:9080/swagger-ui/index.html
    ```
* Testing  
  ![Descripción](img.png)
  * The project includes:
  * Unit tests using JUnit 5
  * Mockito for mocking services and repositories
  * Code coverage via JaCoCo
    ```
    mvn clean test
    mvn jacoco:report
    
    file:/invex-employee/target/site/jacoco/index.html
    ```
* Docker
    ```
    docker build -t invex-employee:1.0 .
  
    docker images
  
    docker run -p 9080:9080 invex-employee:1.0
    ```


## API Endpoints
### 1. List all employees
* GET /api/employees  
  Returns the complete list of employees.
 ```
 curl --location 'http://localhost:9080/api/employees'
 ```
* Response
  200 OK – List of Employee objects.  
 ```json
 [
     {
         "idEmployee": "123c2dd1-c102-11f0-9884-ae32cdf1c2ae",
         "firstName": "a",
         "middleName": "a",
         "paternalSurname": "a",
         "maternalSurname": "a",
         "sex": {
             "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
             "code": "M",
             "description": "Male"
         },
         "jobPosition": {
             "idJobPosition": "84d8a183-c0fd-11f0-9884-ae32cdf1c2ae",
             "code": "HRM",
             "description": "Human Resources Manager"
         },
         "birthDay": "23/04/1971",
         "age": 54,
         "status": true,
         "ts": "13/11/2025 20:31:48"
     },
     {
         "idEmployee": "262815c2-7898-4c9d-abd9-ce394b7d6256",
         "firstName": "Juan",
         "middleName": "Carlos",
         "paternalSurname": "Moreno",
         "maternalSurname": "García",
         "sex": {
             "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
             "code": "M",
             "description": "Male"
         },
         "jobPosition": {
             "idJobPosition": "84d89fe9-c0fd-11f0-9884-ae32cdf1c2ae",
             "code": "CTO",
             "description": "Chief Technology Officer"
         },
         "birthDay": "23/04/1971",
         "age": 54,
         "status": false,
         "ts": "14/11/2025 09:00:19"
     }
 ]
 ```

### 2. Get employee by ID
* GET /api/employees/{id}  
  Fetch an employee using its unique identifier.
 ```
 curl --location 'http://localhost:9080/api/employees/123c2dd1-c102-11f0-9884-ae32cdf1c2ae'
 ```
* Response  
  200 OK – List of Employee objects.  
  404 Not Found – Employee does not exist
 ```json
{
  "idEmployee": "123c2dd1-c102-11f0-9884-ae32cdf1c2ae",
  "firstName": "a",
  "middleName": "a",
  "paternalSurname": "a",
  "maternalSurname": "a",
  "sex": {
    "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
    "code": "M",
    "description": "Male"
  },
  "jobPosition": {
    "idJobPosition": "84d8a183-c0fd-11f0-9884-ae32cdf1c2ae",
    "code": "HRM",
    "description": "Human Resources Manager"
  },
  "birthDay": "23/04/1971",
  "age": 54,
  "status": true,
  "ts": "13/11/2025 20:31:48"
}
 ```

### 3. Create employees
* POST /api/employees  
  Fetch an employee using its unique identifier.
 ```
curl --location 'http://localhost:9080/api/employees' \
--header 'Content-Type: application/json' \
--data '{
    "employees": [
        {
            "firstName": "Otro",
            "middleName": "Carlos",
            "paternalSurname": "Moreno",
            "maternalSurname": "García",
            "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
            "idJobPosition": "84d89fe9-c0fd-11f0-9884-ae32cdf1c2ae",
            "birthDay": "23/04/1971",
            "status": true
        }
    ]
}
'
 ```
* Request  
 ```json
{
  "employees": [
    {
      "firstName": "Otro",
      "middleName": "Carlos",
      "paternalSurname": "Moreno",
      "maternalSurname": "García",
      "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
      "idJobPosition": "84d89fe9-c0fd-11f0-9884-ae32cdf1c2ae",
      "birthDay": "23/04/1971",
      "status": 0
    }
  ]
}

```

* Response  
  201 Created – List of created employees.  
 ```json
[
    {
        "idEmployee": "e79ce531-0bac-4b7c-9c4d-a3d027618b6c",
        "firstName": "Otro",
        "middleName": "Carlos",
        "paternalSurname": "Moreno",
        "maternalSurname": "García",
        "sex": {
            "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
            "code": "M",
            "description": "Male"
        },
        "jobPosition": {
            "idJobPosition": "84d89fe9-c0fd-11f0-9884-ae32cdf1c2ae",
            "code": "CTO",
            "description": "Chief Technology Officer"
        },
        "birthDay": "23/04/1971",
        "age": 54,
        "status": true,
        "ts": "18/11/2025 10:53:44"
    }
]
 ```

### 4. Update employee
* PUT /api/employees/{id}  
  Updates the fields of the employee with the given ID.
 ```
curl --location --request PUT 'http://localhost:9080/api/employees/e79ce531-0bac-4b7c-9c4d-a3d027618b6c' \
--header 'Content-Type: application/json' \
--data '{
    "firstName": "Otro",
    "middleName": "Carlos",
    "paternalSurname": "Moreno",
    "maternalSurname": "García",
    "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
    "idJobPosition": "84d89fe9-c0fd-11f0-9884-ae32cdf1c2ae",
    "birthDay": "24/04/1971",
    "status": 1
}'
 ```
* Request
 ```json
{
  "firstName": "Otro",
  "middleName": "Carlos",
  "paternalSurname": "Moreno",
  "maternalSurname": "García",
  "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
  "idJobPosition": "84d89fe9-c0fd-11f0-9884-ae32cdf1c2ae",
  "birthDay": "24/04/1971",
  "status": 1
}
```

* Response  
  200 OK – Updated employee object.
 ```json
{
  "idEmployee": "e79ce531-0bac-4b7c-9c4d-a3d027618b6c",
  "firstName": "Otro",
  "middleName": "Carlos",
  "paternalSurname": "Moreno",
  "maternalSurname": "García",
  "sex": {
    "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
    "code": "M",
    "description": "Male"
  },
  "jobPosition": {
    "idJobPosition": "84d89fe9-c0fd-11f0-9884-ae32cdf1c2ae",
    "code": "CTO",
    "description": "Chief Technology Officer"
  },
  "birthDay": "24/04/1971",
  "age": 54,
  "status": true,
  "ts": "18/11/2025 10:53:44"
}
 ```

### 5. Delete employee
* DELETE /api/employees/{id}  
  Deletes the employee with the given ID.
 ```
curl --location --request DELETE 'http://localhost:9080/api/employees/9645c85c-5908-4d19-bb4a-734c92e4890d'
 ```

* Response  
  204 No Content – Employee deleted.
  404 Not Found – Employee not found.

### 6. Search employees by name
* GET /api/employees/search?name={name}  
  Returns employees whose full name contains the provided text.
 ```
curl --location 'http://localhost:9080/api/employees/search?name=ore' \
--data ''
 ```

* Response  
  200 OK – List of matching employees.
 ```json
[
  {
    "idEmployee": "00689cb5-c39a-4f55-a74e-c71ef800a19a",
    "firstName": "Otro",
    "middleName": "Carlos",
    "paternalSurname": "Moreno",
    "maternalSurname": "García",
    "sex": {
      "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
      "code": "M",
      "description": "Male"
    },
    "jobPosition": {
      "idJobPosition": "84d89fe9-c0fd-11f0-9884-ae32cdf1c2ae",
      "code": "CTO",
      "description": "Chief Technology Officer"
    },
    "birthDay": "23/04/1971",
    "age": 54,
    "status": true,
    "ts": null
  },
  {
    "idEmployee": "262815c2-7898-4c9d-abd9-ce394b7d6256",
    "firstName": "Juan",
    "middleName": "Carlos",
    "paternalSurname": "Moreno",
    "maternalSurname": "García",
    "sex": {
      "idSex": "50284c0c-c0fd-11f0-9884-ae32cdf1c2ae",
      "code": "M",
      "description": "Male"
    },
    "jobPosition": {
      "idJobPosition": "84d89fe9-c0fd-11f0-9884-ae32cdf1c2ae",
      "code": "CTO",
      "description": "Chief Technology Officer"
    },
    "birthDay": "23/04/1971",
    "age": 54,
    "status": false,
    "ts": "14/11/2025 09:00:19"
  }
]
 ```
