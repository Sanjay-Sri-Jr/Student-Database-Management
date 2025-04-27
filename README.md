# 📚 Student Management System (Java Swing + MySQL)

A simple **Student Management System** built using **Java Swing** for GUI and **MySQL** for database storage.  
The application allows users to **add**, **view**, **search**, and **delete** student records with proper validations and a clean interface.

---

## ✨ Features

- Add new student details
- Search student by Register Number
- Delete student records
- View all saved students in a table
- Input validations (ID, Email, Contact, DOB, CGPA)
- Colorful and clean user interface
- Data persistence with MySQL database

---

## 📸 Screenshots


---

## 🛠️ Tech Stack

- **Java** (Swing Framework)
- **MySQL** (Database)
- **JDBC** (Java Database Connectivity)

---

## 📂 Project Structure

```bash
studentmanagement/
├── StudentManagementSystemJr.java
├── README.md
└── studentdb.sql
```

---

## ⚙️ Requirements

- Java JDK 8 or later
- MySQL Server
- MySQL JDBC Driver (Connector/J)

---

## 🔌 How to Run the Project

1. **Clone the repository**

```bash
git clone https://github.com/Sanjay-Sri-Jr/StudentManagementSystem.git
```

2. **Create Database and Table**

Open your MySQL and run:

```sql
CREATE DATABASE studentdb;

USE studentdb;

CREATE TABLE students (
    name VARCHAR(100),
    id VARCHAR(50) PRIMARY KEY,
    grade VARCHAR(10),
    dob DATE,
    email VARCHAR(100),
    contact VARCHAR(15),
    gender VARCHAR(10)
);
```

3. **Update Database Credentials**

In `StudentManagementSystemJr.java`, make sure the database connection details match your local setup:

```java
String url = "jdbc:mysql://localhost:3306/studentdb";
String user = "root";
String password = "your_mysql_password";
```

4. **Run the Project**

- Open the project in any IDE like IntelliJ IDEA / Eclipse / NetBeans.
- Run `StudentManagementSystemJr.java`.
- Start managing student records!

---

## ⚡ Functionalities Overview

- **Add Student**: Fill the form and save to database.
- **Reset Form**: Clear all fields at once.
- **Search Student**: Search any student by their Register Number.
- **Delete Student**: Select a record from table and delete permanently.

---

## 🚀 Future Improvements (Coming Soon)

- Update/Edit student records
- Export records to Excel/PDF
- Login/Authentication system
- Enhanced UI using JavaFX

---

## 🤝 Contributing

Contributions are welcome!  
If you have ideas or improvements, feel free to fork this repo and create a Pull Request. ❤️

---

## 📄 License

This project is licensed under the **MIT License**.  
Feel free to use and modify it.

---

## 📬 Contact

Created with ❤️ by **[Sanjay M]**  
- GitHub: [@Sanjay-Sri-Jr][](https://github.com/Sanjay-Sri-Jr)
- LinkedIn: [sanjay-sri-jr)](https://www.linkedin.com/in/sanjay-sri-jr/)

---
  
