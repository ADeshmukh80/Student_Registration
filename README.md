# User Registration System

A Java Swing-based **User Registration System** with MySQL database integration.

## Requirements
- **JDK 8+**
- **MySQL Server**
- **MySQL Connector/J** (JDBC Driver)
- **Eclipse/IntelliJ IDEA** (Optional)

## MySQL Setup
```sql
CREATE DATABASE swing_demo;
USE swing_demo;
CREATE TABLE account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    user_name VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    email_id VARCHAR(100),
    mobile_number VARCHAR(10)
);
```

## Adding MySQL Connector/J
- **Eclipse:** Right-click project → **Build Path** → **Add External JARs** → Select JAR.
- **IntelliJ:** **File > Project Structure > Libraries** → Add JAR.

## Running the Project
```sh
git clone https://github.com/your-username/UserRegistrationSystem.git
```
- Open in IDE, ensure MySQL is running, and run `UserRegistration.java`.



