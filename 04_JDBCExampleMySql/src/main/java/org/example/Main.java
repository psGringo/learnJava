package org.example;


import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.printf("Hello and welcome!");

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307", "root", "root"
        )) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from library.author");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                System.out.printf("ID: %d, Name: %s\n", id, name);
            }
        }
    }
}