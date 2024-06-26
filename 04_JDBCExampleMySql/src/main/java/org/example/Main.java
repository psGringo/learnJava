package org.example;


import java.sql.*;

public class Main {

    private void anotherMethod() {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test",
                        "root",
                        "root"
                )
        ) {
            Statement stm = conn.createStatement();
            conn.setAutoCommit(false);
            stm.execute("update employee set salary = salary + 2000 where name = 'Diego'");
            stm.execute("update employee set salary = salary + 5000 where name = 'Amigo'");
            conn.commit();
            stm.close();
        }
        catch (Exception e) {

        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.printf("Hello and welcome!");

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307", "root", "root"
        )) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("selectselect * from library.author");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                if (resultSet.wasNull()) {
                    System.out.println("id is null");
                }
                String name = resultSet.getString("first_name");
                if (resultSet.wasNull()) {
                    System.out.println("name is null");
                }
                System.out.printf("ID: %d, Name: %s\n", id, name);
            }

            jdbcMetaDataExample(resultSet);

        }
    }

    private static void jdbcMetaDataExample(ResultSet resultSet) throws SQLException {
        var metaData = resultSet.getMetaData();
        for (int i = 1; i < metaData.getColumnCount(); i++) {
            System.out.println(metaData.getColumnName(i));
        }
    }
}