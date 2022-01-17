package com.epam.training.epharmacy.dao.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {

    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        try (InputStream dbPropertiesIS = ConnectionCreator.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(dbPropertiesIS);
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (IOException | ClassNotFoundException e) {
            // TODO throw custom exception
            throw new RuntimeException("Error during creating connection", e);
        }
        DATABASE_URL = (String) properties.get("db.url");
    }

    private ConnectionCreator() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}
