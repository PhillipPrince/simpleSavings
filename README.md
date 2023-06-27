# Simple Savings API

This repository contains the source code for the Simple Savings API, which provides a RESTful API for managing customer data, transactions, and products.


## Introduction

The Simple Savings API is a Java-based application built with Spring Boot. It allows users to perform CRUD operations on customer data, transactions, and products. The API provides endpoints for creating customers, updating customer details, retrieving customer information, managing transactions, and managing products.

## Features

- Create a new customer with their bio data
- Update customer details
- Retrieve customer information by ID
- Delete a customer
- Get transactions for a customer
- Get all customers
- Create a new product
- Update product details
- Retrieve product information by ID
- Delete a product
- Create a new transaction
- Get total savings amount for a customer
- Get total savings account

## Technologies

The Simple Savings API is built using the following technologies:

- Java
- Spring Boot
- Spring Data JPA
- SQLite (Database)
- Swagger (API documentation)

## Getting Started

To get started with the Simple Savings API, follow these steps:

1. Clone the repository:

2. Navigate to the project directory:

3. Configure the database connection in the `application.properties` file. Set the following properties:

spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.url=jdbc:sqlite:/path/to/your/database.db
spring.datasource.username=
spring.datasource.password=

Make sure to replace `/path/to/your/database.db` with the actual path to your SQLite database file.

4. Build the project:
./mvnw clean install


5. Run the application:

./mvnw spring-boot:run


6. The API will be accessible at `http://localhost:8080`.

## API Endpoints

The following endpoints are available:

- Customers
- `POST /customers/createCustomer`: Create a new customer with their bio data.
- `GET /customers/getcustomer/{customerId}`: Get a customer by their ID.
- `PATCH /customers/update/{customerId}`: Update a customer by their ID.
- `DELETE /customers/delete/{customerId}`: Delete a customer by their ID.
- `GET /customers/{customerId}/transactions`: Get transactions for a customer by their ID.
- `GET /customers/getAllCustomers`: Get all customers.

- Products
- `POST /api/products/createProduct`: Create a new product.
- `GET /api/products/getProduct/{productId}`: Get a product by its ID.
- `PATCH /api/products/update/{productId}`: Update a product by its ID.
- `DELETE /api/products/delete/{productId}`: Delete a product by its ID.

- Transactions
- `POST /api/customers/transactions/{customerId}/create`: Create a new transaction for a customer.
- `GET /api/customers/transactions/{customerId}/total-savings`: Get the total savings amount for a customer.
- `GET /api/customers/transactions/total-savings`: Get the total savings account.

## API Documentation

The Simple Savings API is documented using Swagger. The API documentation provides detailed information about the available endpoints, request and response models, and allows you to interact with the API.

To access the API documentation:

1. Start the application.

2. Open a web browser and navigate to `http://localhost:8080/swagger-ui.html`.

3. The Swagger UI will be displayed, showing the available endpoints and allowing you to make requests and view responses.

