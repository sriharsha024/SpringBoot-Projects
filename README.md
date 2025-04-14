# SpringBoot-Projects

Spring Boot Projects Collection

About
This repository contains a collection of Spring Boot projects that demonstrate various features and best practices in backend development. The projects include both monolithic and microservices architectures and cover the following systems:

Quiz Application (both microservices and monolith versions)

Airline Reservation System

Event Management System

Weather Application

Job Portal

Library Management System

Each project integrates technologies such as REST APIs, JPA for database interactions, and Spring's best practices for building scalable and maintainable applications.

Projects Overview

1. Quiz Application
This project includes two versions of a quiz application:

Monolithic Version: A single service that handles all quiz functionalities.

Microservices Version: A distributed architecture using Spring Cloud, Eureka for service discovery, and Spring Cloud Gateway for dynamic routing. The QuizService and QuestionService communicate using Feign Clients and are backed by a MySQL database.

Key Features:

Quiz Creation and Submission

Dynamic Question Retrieval

Score Calculation

Error Handling and Validation

2. Airline Reservation System
   
A complete system for managing airline reservations, customer details, and flight information.

Key Features:

CRUD Operations for managing flights, customers, and reservations

Dynamic Seat Management functionality

JPA Relationships for entities like Customer, Flight, and Reservation

3. Event Management System
   
A system for managing events, attendees, and venues. It includes full CRUD functionality with JPA relationships.

Key Features:

Event Planning and Attendee Management

Venue Assignments and Category Management

API Endpoints to handle event-related requests

4. Weather Application
   
An application that fetches real-time weather data from the OpenWeatherMap API and displays it using Thymeleaf for the frontend.

Key Features:

Real-Time Weather Data fetching using RestTemplate

Dynamic UI Updates with Thymeleaf

JavaScript for interactive weather displays

5. Job Portal
   
A job portal system where users can view job listings and companies.

Key Features:

Job Listings management with CRUD operations

Company Profile management

RESTful APIs to manage job applications and company data

6. Library Management System
   
A library management system for books, authors, publishers, and categories.

Key Features:

CRUD Operations for managing books, authors, and publishers

JPA Relationships to model entities such as Book, Author, Publisher, and Category

Efficient Database Management using Spring Data JPA



Technologies Used

Spring Boot: Framework for building backend services and RESTful APIs

Java: Primary programming language for backend development

JPA (Java Persistence API): ORM framework for managing database interactions

Spring Data JPA: Simplifies database operations and repository layer implementation

Hibernate: ORM tool for managing relationships and entities in the database

Spring Cloud: For microservices architecture (Eureka, Feign, Spring Cloud Gateway)

Thymeleaf: For rendering dynamic HTML content in web applications

MySQL: Relational database for storing application data

JavaScript: For dynamic, interactive features on the frontend

