# 🧪 REST Assured Project – Restful Booker

A simple Java-based automation project using **REST Assured** to perform **CRUD operations** on a hotel booking system.  
This project interacts with a publicly available demo API that simulates a real-world booking service.

---

## 📖 Overview

This project demonstrates how to automate RESTful API testing using the REST Assured library.  
It covers the full suite of **Create, Read, Update, and Delete** (CRUD) operations on hotel bookings.

The tests are executed using **Maven**, and the results can be viewed in an interactive **Allure report**.

You can view the API documentation [here](https://restful-booker.herokuapp.com/apidoc/index.html).

---

## 🔧 Prerequisites

- Java 11 or above  
- Maven  
- IntelliJ IDEA / Eclipse (or any IDE)  
- Internet connection (to access the public API)

---

## 🚀 Getting Started

### 1. Clone the Repository

Open your terminal and run:

```bash
git clone https://github.com/your-username/restassured-restful-booker.git
cd restassured-restful-booker
```

### 2. Run the test
```bash
mvn clean test
```

### 3. View the Reports
```bash
mvn allure:serve
```


### 📌 Notes
- ⚠️ This project uses a public API meant for testing only. Data is not persistent.
- 🔄 Since the API is shared, booking IDs may change frequently or be overwritten by other users.
-  Faker library is being used to create fake Booking data, where you can create your own data or import it form excelsutility class.
