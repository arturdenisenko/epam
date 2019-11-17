package com.epam;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Main {
    private static final String PROPS = "/periodicals.properties";

    public static void main(String[] args) throws ClassNotFoundException {
        String userName = "";
        String password = "";
        String connectionUrl = "";
        try (InputStream is = Main.class.getResourceAsStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            userName = props.getProperty("db.user");
            password = props.getProperty("db.password");
            connectionUrl = props.getProperty("db.url");
            Class.forName("org.postgresql.Driver");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {
            System.out.println("We're connected");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.user_type");
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("id"), resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
