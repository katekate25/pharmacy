package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.ScriptRunner;
import com.epam.training.epharmacy.dao.connection.ConnectionCreator;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

public abstract class AbstractDAOTest {

    protected static final Properties properties = new Properties();

    @BeforeClass
    public static void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        try (InputStream dbPropertiesIS = ConnectionCreator.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(dbPropertiesIS);
        }
        initDatabase();
    }

    @AfterClass
    public static void destroy() throws SQLException, ClassNotFoundException, IOException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.executeUpdate("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK;");
            connection.commit();
        }
    }

    private static void initDatabase() throws SQLException, IOException {
        try (Connection connection = getConnection();
             InputStream sqlScriptIS = UserDAOImplTest.class.getClassLoader().getResourceAsStream("pharmacy.sql")) {
            ScriptRunner runner = new ScriptRunner(connection, true, true);
            runner.runScript(new InputStreamReader(Objects.requireNonNull(sqlScriptIS)));
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("user"),
                properties.getProperty("password"));
    }
}
