# Restaurant Management System - Spring Boot

## Introduction
This is a simple Restaurant Management System built using Spring Boot. It allows users to sign up and sign in to the system. Different types of users have different access levels: Admin users have added privileges to manage users and food items, normal users can order food items, and visitors can view food items without signing in. The system also implements basic CRUD operations for managing users, food items, and orders.

## Technologies Used
- Java-17
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL Database (for demonstration purposes; can be changed to other databases)
- Maven (for project build)
##  Data Flow

* Controller -
  it Will receive request from client and send request to service layer.
* Service -
  it Will receive request from controller and this will manipulate the operation and send request to DAO layer
* Repository -
  it Will receive request from service and it will communicate with the Database.
* DataBase Design -
  For Database i am using MySQL and created a pogo for user management where i performing CRUD
  operation with user basic information.

##  Data Structure used in project

* DataBase Design -
  In this project mostly used ArrayList & along with java 8 feature like optional class and
  lambda expression.


## Setup and Installation
1. Clone the repository to your local machine using `git clone`.
2. Ensure you have Java JDK and Maven installed on your system.
3. Open the project in your favorite IDE (IntelliJ, Eclipse, etc.).
4. Build the project using Maven: `mvn clean install`.
5. Run the Spring Boot application: `mvn spring-boot:run`.

## API Endpoints

### User Endpoints:
- `POST /api/users/signup`: Sign up a new user with a JSON payload containing username, password, and email.
- `POST /api/users/signin`: Sign in an existing user with a JSON payload containing username and password.
- `GET /api/users/{id}`: Get user details by ID (requires admin access).
- `GET /api/users`: Get a list of all users (requires admin access).

### Food Item Endpoints:
- `POST /api/foodItems`: Create a new food item with a JSON payload containing title, description, price, and dummyImageUrl (requires admin access).
- `GET /api/foodItems/{id}`: Get food item details by ID (requires admin access).
- `GET /api/foodItems`: Get a list of all food items (public access).

### Order Endpoints:
- `POST /api/orders`: Place a new order with a JSON payload containing foodItemId and quantity (requires user access).
- `GET /api/orders/{id}`: Get order details by ID (requires user access).
- `GET /api/orders`: Get a list of all orders for the currently logged-in user (requires user access).

## Role-Based Access Control (RBAC)
- Admins have the role "ADMIN" and have additional privileges to manage users and food items.
- Users have the role "USER" and can place orders.
- Visitors have read-only access to view food items without signing in.

## Database
The application uses an in-memory MySQl database for demonstration purposes. It is pre-populated with some sample data for testing. Upon application restart, the data will be reset.

## Security
The application uses Spring Security for authentication and authorization. Passwords are hashed using BCrypt before storing in the database.

## Known Issues / Future Improvements
- The application currently uses an in-memory MySQL database. For production use, consider switching to a more robust database like MySQL or PostgreSQL.
- Add error handling and validation for user inputs and API responses.
- Implement pagination for listing endpoints that can potentially return large datasets.
- Improve error responses with clear error messages and HTTP status codes.


## Author
Your Name (@PappuLaL)