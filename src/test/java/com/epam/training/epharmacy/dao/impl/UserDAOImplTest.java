package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.ScriptRunner;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.connection.ConnectionCreator;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.SearchCriteria;
import com.epam.training.epharmacy.entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserDAOImplTest {

    private static final Properties properties = new Properties();
    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

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

    @Test
    public void getTotalRecordsTest() {
        Criteria<SearchCriteria.User> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.User.LOGIN, "sergeev_p");
        List<User> users = userDAO.findUserByCriteria(criteria);
        assertNotNull(users);
        assertEquals(1, users.size());
    }
}
