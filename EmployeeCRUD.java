package EmployeeJDBCApp;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class EmployeeCRUD {

    public static void main(String[] args) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(".env")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Failed to load .env file: " + e.getMessage());
            // Continue; url/user/password may be null and will cause a SQLException below
        }

        String url = props.getProperty("DB_URL");
        String user = props.getProperty("DB_USER");
        String password = props.getProperty("DB_PASSWORD");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            return;
        }

        try (Scanner sc = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, user, password)) {

            System.out.println("Connected to database!");

            while (true) {
                System.out.println("\n1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice;
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                } else {
                    sc.nextLine(); // consume invalid input
                    System.out.println("Invalid input!");
                    continue;
                }

                switch (choice) {
                    case 1 -> addEmployee(conn, sc);
                    case 2 -> viewEmployees(conn);
                    case 3 -> updateEmployee(conn, sc);
                    case 4 -> deleteEmployee(conn, sc);
                    case 5 -> { System.out.println("Exiting..."); return; }
                    default -> System.out.println("Invalid choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        sc.nextLine(); // consume leftover newline if any
        String name = sc.nextLine();
        System.out.print("Enter department: ");
        String dept = sc.nextLine();
        System.out.print("Enter salary: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter a valid number for salary:");
            sc.next();
        }
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employee(name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, dept);
        ps.setDouble(3, salary);
        ps.executeUpdate();
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employee";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        System.out.println("\nID\tName\tDepartment\tSalary");
        while (rs.next()) {
            System.out.printf("%d\t%s\t%s\t%.2f%n",
                    rs.getInt("id"), rs.getString("name"), rs.getString("department"), rs.getDouble("salary"));
        }
    }

    private static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to update: ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid integer ID:");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new department: ");
        String dept = sc.nextLine();
        System.out.print("Enter new salary: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter a valid number for salary:");
            sc.next();
        }
        double salary = sc.nextDouble();

        String sql = "UPDATE employee SET department=?, salary=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dept);
        ps.setDouble(2, salary);
        ps.setInt(3, id);
        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Employee updated successfully!" : "Employee not found!");
    }

    private static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to delete: ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid integer ID:");
            sc.next();
        }
        int id = sc.nextInt();

        String sql = "DELETE FROM employee WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Employee deleted successfully!" : "Employee not found!");
    }
}

