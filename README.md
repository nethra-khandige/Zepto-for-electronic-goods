# Zepto-for-electronic-goods 💽🖨️

Zepto is a web application for buying electronic goods built using Java, MySQL, HTML, and CSS. The application follows MVC architecture, utilizes the Repository Pattern and Service Pattern, and includes different roles such as Admin, Delivery Driver, and Store. The platform also supports login and cart management, and integrates Google Authentication for user authentication.

## Features 📑📑
Roles: Admin, Delivery Driver, and Store, each with different access privileges.

Product Management: Users can browse and purchase electronic products.

Cart Management: Add, update, and manage products in the shopping cart.

Google Authentication: Allows secure login via Google account.

Shop Management: Stores can manage their electronic goods.

Order Delivery: Delivery drivers can view and manage delivery tasks.

Admin Panel: Admins have access to manage users, products, and orders.

## Technologies Used 🖥️💻
Java: Backend development using MVC architecture.

MySQL: Database management for storing user and product data.

HTML/CSS: Frontend design and layout.

Google Authentication: For user login and authentication.

Repository Pattern: To manage the data access logic separately from business logic.

Service Pattern: To handle business logic independently from controllers.

## Project Structure 🏗️🏢
The project follows the MVC architecture to separate concerns:

Model: Handles the data layer and communicates with the database using MySQL.

View: HTML pages provide the user interface.

Controller: Java classes handle user input, processes it, and returns the output.

Service: Contains business logic and interacts with repositories.

Repository: Manages data access and communicates with the database.

## Roles and Functionality 🧑🏽‍🚒
Admin: 
  Manage users, products, orders, and stores.
  
  View sales reports and customer data.

Delivery Driver:
  View assigned deliveries.
  
  Update delivery status.

Store:
  Add new products.

  Manage inventory and product pricing.

Users:
  Browse products, manage their cart, and place orders.

  Authenticate using Google to log in and access their account.
