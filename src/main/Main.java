package main;

import java.sql.*;

public class Main {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL    = "jdbc:postgresql://localhost:5432/postgres";
    private static final String LOGIN  = "postgres";
    private static final String PASS   = "postgres";
    private static final String CREATE = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(50), pass VARCHAR(50))";
    private static final String INSERT = "INSERT INTO users VALUES (4, 'July', '1234')";
    private static final String UPDATE = "UPDATE users SET name = 'Steve' WHERE id = 3";
    private static final String DELETE = "DELETE FROM users WHERE id = 2";
    private static final String SELECT = "SELECT * FROM users";

    public static void main(String[] args) {
        //loading driver
        try {
            Class.forName(DRIVER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            //creating connection
            Connection c = null;
            c = DriverManager.getConnection(URL, LOGIN, PASS);
            //preparing statement
            PreparedStatement ps = c.prepareStatement(SELECT);
//            ps.executeUpdate();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString(2);
                String pass = rs.getString("pass");
                System.out.printf("%s \t\t %s \t\t %s \t\t", id, name, pass);
                System.out.println();
            }

            rs.close();
            ps.close();
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
