# E-Wallet Savings Application

> Final Project - DSF 4.0 Program Back End by Dibimbing.id

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?style=flat-square&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red?style=flat-square&logo=apache-maven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)](LICENSE)
[![Digital Skola](https://img.shields.io/badge/Program-Digital%20Skola%20Backend%204.0-purple?style=flat-square)](https://dibimbing.id/)

A comprehensive banking application built with Spring Boot and vanilla JavaScript that enables users to manage accounts, perform transfers with real-time validation, and track transaction history.

**Mentored by:** Dibimbing.id - Digital Skola

---

## Table of Contents

- [About This Project](#about-this-project)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Learning Objectives](#learning-objectives)
- [Database Schema](#database-schema)
- [API Documentation](#api-documentation)
- [Sample Data](#sample-data)
- [Security Notes](#security-notes)
- [Contributing](#contributing)
- [Resources](#resources)
- [Author](#author)

---

## About This Project

This project is the **final assignment** for the **Digital Skola Backend 4.0** mentorship program. It demonstrates the implementation of a complete RESTful API backend with Spring Boot, JPA, and MySQL integration, along with a clean frontend interface.

The application simulates a real-world e-wallet system with core banking features including account management, fund transfers, and transaction tracking.

---

## Features

### Core Banking Features
- **Customer Management** - Create, read, update, and delete customer records
- **Account Management** - Manage savings accounts with multiple bank support
- **Real-time Transfer Validation** - Instant account verification before transfer
- **Transaction History** - Complete audit trail of all transactions
- **Multi-bank Support** - BCA, Mandiri, BNI, BRI, CIMB Niaga, Permata, Danamon, BTN

### Key Highlights
- Real-time account validation with bank details
- Automatic balance updates
- Transaction history tracking
- Clean and responsive UI
- RESTful API architecture

---

## Tech Stack

**Backend:**
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Web
- MySQL 8.0
- Maven

**Frontend:**
- HTML5
- CSS3 (Bootstrap 5.3.2)
- Vanilla JavaScript
- Font Awesome Icons

---

## Prerequisites

- Java JDK 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher
- Modern web browser (Chrome, Firefox, Safari, Edge)
- IDE (IntelliJ IDEA / Eclipse / VS Code recommended)

---

## Getting Started

### Step 1: Create Spring Boot Template from Spring Initializr

Before cloning this repository, understand the project structure by creating a base template:

1. Go to [Spring Initializr](https://start.spring.io/)
2. Configure your project:
   ```
   Project: Maven
   Language: Java
   Spring Boot: 3.2.0 or latest stable
   Group: com.example
   Artifact: saving
   Name: saving
   Package name: com.example.saving
   Packaging: Jar
   Java: 17
   ```

3. Add Dependencies:
   - **Spring Web** - For building REST APIs
   - **Spring Data JPA** - For database operations
   - **MySQL Driver** - For MySQL database connection
   - **Lombok** (optional) - For reducing boilerplate code

4. Click **GENERATE** to download the project template
5. Extract the ZIP file and open in your IDE

### Step 2: Clone This Repository

```bash
git clone https://github.com/yourusername/savings-app.git
cd savings-app
```

**Important:** Compare the generated template structure with this repository to understand the project architecture and file organization.

### Step 3: Database Setup

#### Option 1: Using SQL Script
```bash
mysql -u root -p < database/saving_db.sql
```

#### Option 2: Manual Setup
```sql
CREATE DATABASE saving_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE saving_db;

-- Run the table creation scripts from saving_db.sql
```

### Step 4: Configure Application

Edit `src/main/resources/application.properties`:

```properties
# Application Name
spring.application.name=saving

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/saving_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Server Configuration
server.port=8080
```

### Step 5: Build and Run Application

```bash
# Clean and build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Step 6: Access Frontend

Open your browser and navigate to:
```
http://localhost:8080/index.html
```

Or open `src/main/resources/static/index.html` directly in your browser.

---

## Project Structure

```
saving-app/
├── src/
│   ├── main/
│   │   ├── java/com/example/saving/
│   │   │   ├── controller/              # REST API Controllers
│   │   │   │   ├── CustomerController.java
│   │   │   │   ├── SavingController.java
│   │   │   │   └── TransferController.java
│   │   │   ├── entity/                  # JPA Entities
│   │   │   │   ├── Customer.java
│   │   │   │   ├── Saving.java
│   │   │   │   └── TransactionHistory.java
│   │   │   ├── repository/              # JPA Repositories
│   │   │   │   ├── CustomerRepository.java
│   │   │   │   ├── SavingRepository.java
│   │   │   │   └── TransactionHistoryRepository.java
│   │   │   ├── services/                # Business Logic Layer
│   │   │   │   ├── CustomerServices.java
│   │   │   │   └── SavingServices.java
│   │   │   ├── dto/                     # Data Transfer Objects
│   │   │   │   ├── TransferDTO.java
│   │   │   │   ├── TransferResponseDTO.java
│   │   │   │   └── AccountValidationDTO.java
│   │   │   └── SavingApplication.java   # Main Application Class
│   │   └── resources/
│   │       ├── static/                  # Frontend Assets
│   │       │   └── index.html
│   │       └── application.properties   # Application Configuration
│   └── test/                            # Unit and Integration Tests
├── database/
│   └── saving_db.sql                   # Database Schema and Sample Data
├── pom.xml                             # Maven Dependencies
├── README.md                           # Project Documentation
└── .gitignore                          # Git Ignore Rules
```

---

## Learning Objectives

This project demonstrates key concepts from the Digital Skola Backend 4.0 program:

### Spring Boot Fundamentals
- Project structure and organization
- Dependency injection and IoC container
- Auto-configuration mechanism
- Application properties management

### RESTful API Development
- CRUD operations implementation
- HTTP methods (GET, POST, PUT, DELETE)
- Request and response handling
- JSON serialization and deserialization
- Status code management

### Database Integration
- JPA and Hibernate ORM
- Entity relationships (@OneToMany, @ManyToOne)
- Repository pattern implementation
- Custom queries with @Query annotation
- Transaction management

### Business Logic Implementation
- Service layer architecture
- Transaction management with @Transactional
- Data validation and error handling
- Complex business rules implementation

### Advanced Features
- Real-time data validation
- Complex SQL queries
- Transaction history tracking
- Multi-entity operations
- API endpoint design

---

## Database Schema

### Tables

#### customer
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(255) | NOT NULL |
| government_id | VARCHAR(255) | NOT NULL, UNIQUE |
| email | VARCHAR(255) | NOT NULL, UNIQUE |
| phone | VARCHAR(50) | NOT NULL |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |
| updated_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP ON UPDATE |

#### saving
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(255) | NOT NULL, UNIQUE |
| account_number | VARCHAR(50) | NOT NULL, UNIQUE |
| balance | DOUBLE | NOT NULL, DEFAULT 0.0 |
| bank_name | VARCHAR(100) | NOT NULL |
| bank_logo | VARCHAR(255) | NULL |
| customer_id | BIGINT | FOREIGN KEY (customer.id) |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |
| updated_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP ON UPDATE |

#### transaction_history
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| transaction_type | VARCHAR(50) | NOT NULL |
| amount | DOUBLE | NOT NULL |
| sender_account_number | VARCHAR(50) | NOT NULL |
| sender_name | VARCHAR(255) | NOT NULL |
| sender_bank | VARCHAR(100) | NOT NULL |
| receiver_account_number | VARCHAR(50) | NOT NULL |
| receiver_name | VARCHAR(255) | NOT NULL |
| receiver_bank | VARCHAR(100) | NOT NULL |
| description | VARCHAR(500) | NULL |
| status | VARCHAR(50) | NOT NULL |
| transaction_date | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |

### Relationships
```
Customer (1) -----> (Many) Saving
Saving (1) -----> (Many) TransactionHistory
```

---

## API Documentation

### Customer Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/customer` | Get all customers |
| GET | `/api/v1/customer/{id}` | Get customer by ID |
| POST | `/api/v1/customer` | Create new customer |
| PUT | `/api/v1/customer/{id}` | Update customer |
| DELETE | `/api/v1/customer/{id}` | Delete customer |

### Saving (Account) Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/saving` | Get all accounts |
| GET | `/api/v1/saving/{id}` | Get account by ID |
| GET | `/api/v1/saving/name/{name}` | Get account by name |
| GET | `/api/v1/saving/account/{number}` | Get account by number |
| GET | `/api/v1/saving/customer/{customerId}` | Get accounts by customer |
| POST | `/api/v1/saving` | Create new account |
| PUT | `/api/v1/saving/{id}` | Update account |
| DELETE | `/api/v1/saving/{id}` | Delete account |

### Transfer & Validation Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/validate-account/{accountNumber}` | Validate account number |
| POST | `/api/v1/transfer` | Process money transfer |

### Transaction History Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/transaction-history` | Get all transactions |
| GET | `/api/v1/transaction-history/account/{accountNumber}` | Get transactions by account |
| GET | `/api/v1/transaction-history/recent` | Get 10 most recent transactions |

### Request/Response Examples

#### Validate Account
**Request:**
```http
GET /api/v1/validate-account/1234567890
```

**Response:**
```json
{
  "valid": true,
  "accountNumber": "1234567890",
  "accountName": "John Doe",
  "bankName": "BCA",
  "bankLogo": "🏦",
  "message": "Rekening ditemukan"
}
```

#### Process Transfer
**Request:**
```http
POST /api/v1/transfer
Content-Type: application/json

{
  "nomorRekeningPengirim": "1234567890",
  "nomorRekeningPenerima": "1234567892",
  "nominal": 500000,
  "description": "Payment for services"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Transfer berhasil",
  "balancePengirim": 4500000.0,
  "balancePenerima": 10500000.0
}
```

---

## Sample Data

The database includes sample data for testing:

### Customers
- John Doe (ID: 3201234567890001)
- Jane Smith (ID: 3201234567890002)
- Bob Johnson (ID: 3201234567890003)
- Alice Wonder (ID: 3201234567890004)

### Accounts
| Account Number | Owner | Bank | Initial Balance |
|----------------|-------|------|-----------------|
| 1234567890 | John Doe | BCA | Rp 5,000,000 |
| 1234567891 | John Doe | Mandiri | Rp 3,000,000 |
| 1234567892 | Jane Smith | BNI | Rp 10,000,000 |
| 1234567893 | Bob Johnson | BRI | Rp 50,000,000 |
| 1234567894 | Alice Wonder | CIMB Niaga | Rp 15,000,000 |

### Test Transfer Example
```
From: 1234567890 (John Doe - BCA)
To: 1234567892 (Jane Smith - BNI)
Amount: 500,000
Description: Test transfer
```

---

## Security Notes

**This is a demonstration project for learning purposes.**

For production deployment, implement:
- Authentication and Authorization (JWT/OAuth2)
- Input validation and sanitization
- CORS configuration
- Rate limiting
- Password encryption (BCrypt)
- SQL injection prevention
- XSS protection
- HTTPS/TLS encryption
- API versioning
- Request logging and monitoring

---

## Contributing

This is a learning project from the Digital Skola Backend 4.0 program. Contributions and improvements are welcome.

### How to Contribute

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/NewFeature`)
3. Commit your changes (`git commit -m 'Add NewFeature'`)
4. Push to the branch (`git push origin feature/NewFeature`)
5. Open a Pull Request

### Contribution Guidelines

- Follow Java coding conventions
- Write clear commit messages
- Add comments for complex logic
- Update documentation as needed
- Test your changes thoroughly

---

## Resources

### Official Documentation
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/guides/gs/accessing-data-jpa/)
- [Spring Web Guide](https://spring.io/guides/gs/rest-service/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

### Learning Materials
- [REST API Best Practices](https://restfulapi.net/)
- [JPA and Hibernate Tutorial](https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa)
- [Maven Getting Started](https://maven.apache.org/guides/getting-started/)

### Program Resources
- [Digital Skola - Dibimbing.id](https://dibimbing.id/)
- [Spring Initializr](https://start.spring.io/)

---

## Assignment Requirements

This project fulfills the following Digital Skola Backend 4.0 requirements:

- [x] RESTful API implementation with proper HTTP methods
- [x] Database integration using Spring Data JPA
- [x] CRUD operations for multiple entities
- [x] Complex business logic (transfer with validation)
- [x] Transaction history tracking
- [x] Proper project structure and organization
- [x] Clean code principles and best practices
- [x] Comprehensive API documentation
- [x] Sample data and testing scenarios
- [x] Version control with Git

---


## Acknowledgments

- **Digital Skill fair & Dibimbing.id** - For providing excellent mentorship and learning resources
- **Spring Boot Team** - For creating an amazing framework
- **MySQL Community** - For the reliable open-source database
- **Bootstrap Team** - For the responsive UI framework
- **Font Awesome** - For the comprehensive icon library

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

### Note for Fellow Students

If you are also taking the Digital Skola Backend 4.0 program:

**Learning Tips:**
1. Do not just copy code - understand each line and its purpose
2. Experiment with the code - try to break and fix things
3. Ask questions - use the community forum and mentorship sessions
4. Build your own version - use this as reference, not as a complete solution
5. Share knowledge - help other students when you understand concepts

**Best Practices:**
- Start with Spring Initializr to understand the base structure
- Read the official Spring Boot documentation
- Practice writing clean and maintainable code
- Write meaningful commit messages
- Test your endpoints using Postman or similar tools
- Keep learning and building projects

---

**Star this repository if it helps your learning journey.**

**Follow for more Spring Boot projects and learning resources.**
