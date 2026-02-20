package main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {
    public static void main(String[] args) {
        try {
            String url = "jdbc:postgresql://localhost:5432/project_scheduler";
            String user = "postgres";
            String password = "";  // <-- change this

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println(" Connected to PostgreSQL successfully!");
            conn.close();
        } catch (Exception e) {
            System.out.println(" Connection failed!");
            e.printStackTrace();
        }
    }
}


