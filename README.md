---

# 🧑‍💼 Employee JDBC Management System (Java + MySQL)

## 📖 Overview

The **Employee JDBC Management System** is a simple **Java console-based application** that demonstrates how to perform **CRUD (Create, Read, Update, Delete)** operations on a MySQL database using **JDBC (Java Database Connectivity)**.

This project helps beginners understand how Java interacts with relational databases and provides a foundation for building larger enterprise-level systems.

---

## ⚙️ Features

* ➕ **Add Employee** – Insert new employee records into the database
* 📋 **View Employees** – Retrieve and display all employee details
* ✏️ **Update Employee** – Modify existing employee information
* ❌ **Delete Employee** – Remove employee records from the database
* 🔐 **Secure Credentials** – Database credentials stored in `.env` file (ignored by Git)

---

## 🧰 Tech Stack

| Component          | Technology                   |
| ------------------ | ---------------------------- |
| **Language**       | Java                         |
| **Database**       | MySQL                        |
| **Connector**      | MySQL JDBC Driver            |
| **IDE (Optional)** | VS Code                      |
| **Build Tool**     | javac & java (CLI)           |

---

## 🧩 Project Structure

```
EmployeeJDBCApp/
│
├── lib/
│   └── mysql-connector-j-9.5.0.jar
│
├── EmployeeCRUD.java
├── .env                 # Contains DB credentials (not uploaded to GitHub)
├── .gitignore
└── README.md
```

---

## 🗄️ Database Setup

1. **Open MySQL** and create a new database:

   ```sql
   CREATE DATABASE employee_db;
   USE employee_db;
   ```

2. **Create the employee table:**

   ```sql
   CREATE TABLE employee (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       department VARCHAR(100),
       salary DECIMAL(10,2)
   );
   ```

3. **Add your credentials** in a `.env` file:

   ```
   DB_URL=jdbc:mysql://localhost:3306/employee_db
   DB_USER=root
   DB_PASSWORD=yourpassword
   ```

---

## 🚀 Run the Project

### 1️⃣ Compile

```bash
javac -cp ".:lib/mysql-connector-j-9.5.0.jar" EmployeeJDBCApp/EmployeeCRUD.java
```

### 2️⃣ Run

```bash
java -cp ".:lib/mysql-connector-j-9.5.0.jar" EmployeeJDBCApp.EmployeeCRUD
```

---

## 📸 Sample Output

```
Connected to database!

1. Add Employee
2. View Employees
3. Update Employee
4. Delete Employee
5. Exit
Enter choice: 1
Enter name: Khushi
Enter department: IT
Enter salary: 100000
Employee added successfully!
```

---

## 🔒 Security Note

* The `.env` file is **excluded from GitHub** using `.gitignore` to protect your credentials.
* Ensure you **never commit your password** or any sensitive data.

---

## ✨ Future Enhancements

* Add GUI using **Java AWT / Swing**
* Integrate user authentication
* Use **Prepared Statements** for secure queries
* Implement logging and error handling

---

## 👩‍💻 Author
**Khushi Gupta**
🔗 [GitHub Profile](https://github.com/Ks-Gupta)

---
