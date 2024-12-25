
# Tax Management System

## Overview

The **Tax Management System** is a Java-based application designed for users to calculate taxes based on their taxable income and income amount. The system also supports user registration and role-based authentication, allowing users to interact with the application and save their data in a PostgreSQL database.

This system calculates tax according to predefined tax rates (7% for taxable income and 5% for the amount of income). It provides a simple graphical user interface (GUI) built using JavaFX, making it easy for users to input data and view results.

The system supports user management features, allowing users to sign up with a username, password, and role. The data entered by users (username, password, taxable income, and amount of income) are stored in a PostgreSQL database, ensuring secure and persistent storage.

## Features

- **Tax Calculation**: The system calculates tax based on taxable income and income amount. The calculation formula is:
  - `taxable_income * 0.07 + amount_income * 0.05`
- **User Registration**: Users can register by entering a username, password, and role. If the username already exists, the system will alert the user.
- **Database Integration**: All user and tax data are stored in a PostgreSQL database. This includes users' login credentials, tax calculations, and income data.
- **Role-Based Access**: Different roles can be assigned to users, ensuring controlled access based on the user’s role.
- **Graphical User Interface**: Built using JavaFX and FXML, the application provides a clean and easy-to-use interface for users.
- **Tax Payment Tracking**: The system tracks tax payments, including the amount paid and the payment date.

## Technologies Used

- **Java**: The core programming language used for implementing business logic and database interaction.
- **JavaFX**: The framework used for building the graphical user interface.
- **PostgreSQL**: A relational database system used for storing user and tax-related data.
- **JDBC (Java Database Connectivity)**: Used to interact with the PostgreSQL database.
- **FXML**: A declarative XML-based markup language used for designing the UI layout.
- **Maven/Gradle**: (Optional) Can be used for managing dependencies and building the project.

## Database Schema

### Users Table
Stores user information for authentication and role management.
- `user_id`: INT (Primary Key, Auto Increment) — Unique identifier for each user.
- `username`: VARCHAR (Unique) — The username chosen by the user.
- `password`: VARCHAR — The password for the user (to be encrypted before storing).
- `role`: VARCHAR — The role assigned to the user (e.g., Admin, User).

### Tax Payments Table
Stores information about tax payments made by users.
- `payment_id`: INT (Primary Key, Auto Increment) — Unique identifier for each payment.
- `user_id`: INT (Foreign Key referencing Users) — The user who made the payment.
- `amount_paid`: DOUBLE — The amount of tax paid.
- `payment_date`: DATE — The date of payment.

### Income Table
Stores income details provided by users for tax calculation.
- `income_id`: INT (Primary Key, Auto Increment) — Unique identifier for each income record.
- `user_id`: INT (Foreign Key referencing Users) — The user who earned the income.
- `amount`: DOUBLE — The income amount.
- `source`: VARCHAR — The source of income (e.g., Salary, Business).
- `date`: DATE — The date the income was received.

## How to Run the Application

### Prerequisites

Before running the application, ensure that the following prerequisites are met:

- **Java 17** or later installed on your machine.
- **PostgreSQL** installed and running. Create a database named `taxmanagementsystem` to store user and tax data.
- **JDBC driver** for PostgreSQL added to your project classpath. If you're using Maven, you can include the dependency as shown below:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.5</version>
</dependency>
```

- An IDE like **IntelliJ IDEA** or **Eclipse** with JavaFX configured for GUI development.

### Steps to Run

1. **Clone the repository** to your local machine using:
   ```bash
   git clone https://github.com/yourusername/tax-management-system.git
   ```
2. **Set up the PostgreSQL database**:
   - Create a database named `taxmanagementsystem`.
   - Set the necessary credentials (username, password) in the `DatabaseConnection` class.
   - You can use the following SQL commands to create the tables:
     ```sql
     CREATE TABLE users (
         user_id SERIAL PRIMARY KEY,
         username VARCHAR(255) UNIQUE NOT NULL,
         password VARCHAR(255) NOT NULL,
         role VARCHAR(50) NOT NULL
     );

     CREATE TABLE income (
         income_id SERIAL PRIMARY KEY,
         user_id INT REFERENCES users(user_id),
         amount DOUBLE NOT NULL,
         source VARCHAR(255) NOT NULL,
         date DATE NOT NULL
     );

     CREATE TABLE taxpaymants (
         payment_id SERIAL PRIMARY KEY,
         user_id INT REFERENCES users(user_id),
         amount_paid DOUBLE NOT NULL,
         payment_date DATE NOT NULL
     );
     ```
3. **Configure the `DatabaseConnection`**:
   - In the `DatabaseConnection.java` file, update the `url`, `username`, and `password` with your PostgreSQL credentials.
4. **Build and Run**:
   - Compile and run the application using your IDE or a build tool like Maven or Gradle.
   - The application will open a JavaFX window for the user interface.

5. **Use the application**:
   - Enter a username, password, role, taxable income, and amount of income in the respective fields.
   - Click the **Calculate Tax** button to get the calculated tax.
   - The result will be displayed in the result field.
   - The application will save the data in the database.

## Code Structure

### Core Classes

- **`DatabaseConnection`**: Manages the connection to the PostgreSQL database. Provides a method to establish a connection to the database.
- **`HelloApplication`**: The main entry point of the application, which launches the JavaFX GUI.
- **`HelloController`**: The controller that handles user inputs and actions, such as calculating the tax and saving data to the database.
- **`User`**: Represents a user entity. Contains fields for user ID, username, password, and role.
- **`UserDAO`**: A Data Access Object (DAO) responsible for interacting with the `users` table in the database. Handles operations like adding and fetching users.
- **`Income`**: Represents an income entity. Contains fields for income ID, user ID, amount, source, and date.
- **`IncomeDAO`**: A DAO responsible for managing income records in the database.
- **`TaxPayment`**: Represents a tax payment entity. Contains fields for payment ID, user ID, amount paid, and payment date.
- **`TaxPaymentDAO`**: A DAO responsible for managing tax payment records in the database.

### User Interface

- **`hello-view.fxml`**: The FXML file used to define the layout of the JavaFX window. Contains input fields for username, password, taxable income, amount of income, and tax result, as well as a button to trigger the tax calculation.

## How the Tax Calculation Works

1. **User Input**: The user enters their username, password, role, taxable income, and amount of income into the respective fields.
2. **Tax Calculation**: When the "Calculate Tax" button is clicked, the application calculates the tax:
   - Taxable income is multiplied by 7%.
   - Amount of income is multiplied by 5%.
   - The results are summed up to give the total tax.
3. **Data Storage**: The application then saves the user's data and tax calculation in the PostgreSQL database:
   - The user’s details (username, password, role) are stored in the `users` table.
   - Income and tax calculation results are stored in the `income` and `taxpaymants` tables.

## Future Enhancements

- **Password Hashing**: Implement password hashing for better security (e.g., using BCrypt).
- **User Roles and Permissions**: Add role-based authorization to restrict access to certain features based on the user's role (e.g., Admin, User).
- **Tax Calculation Enhancements**: Implement more complex tax calculation logic with different tax brackets and deduction options.
- **Error Handling**: Improve error handling with more specific error messages and validation on the inputs.
- **Internationalization**: Support for multiple languages to allow non-English users to interact with the system.
