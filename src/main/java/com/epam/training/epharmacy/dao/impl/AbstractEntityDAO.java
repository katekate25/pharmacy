package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.entity.Criteria;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;


public abstract class AbstractEntityDAO {

    protected static final String SPACE = " ";
    protected static final String WHERE = "WHERE";
    protected static final String AND = "AND";
    public static final String EQUALS_PARAM = " = ?";
    public static final String LIKE_PARAM = " LIKE '%@@@%'";
    public static final String PLACEHOLDER = "@@@";

    protected PreparedStatement createStatement(Connection connection, Criteria<?> criteria) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(buildSearchQuery(criteria));
        int index = 1;
        for (Object value : criteria.getParametersMap().values()) {
            if (value instanceof Date) {
                statement.setDate(index++, (Date) value);
            } else if (value instanceof Integer) {
                statement.setInt(index++, (Integer) value);
            } else if (value instanceof Double) {
                statement.setDouble(index++, (Double) value);
            } else {
                statement.setString(index++, value.toString());
            }
        }
        return statement;
    }

    protected PreparedStatement createLikeStatement(Connection connection, Criteria<?> criteria) throws SQLException {
        return connection.prepareStatement(buildSearchLikeQuery(criteria));
    }

    private String buildSearchQuery(Criteria<?> criteria) {
        boolean isFirstAttribute = true;
        StringBuilder query = new StringBuilder(getInitialQuery());
        for (Object searchCriteria : criteria.getParametersMap().keySet()) {
            if (isFirstAttribute)
            {
                query.append(SPACE).append(WHERE);
                isFirstAttribute = false;
            }
            else
            {
                query.append(SPACE).append(AND);
            }
            query.append(SPACE).append(searchCriteria.toString().toLowerCase()).append(EQUALS_PARAM);
        }
        return query.toString();
    }

    private String buildSearchLikeQuery(Criteria<?> criteria) {
        boolean isFirstAttribute = true;
        StringBuilder query = new StringBuilder(getInitialQuery());
        for (Map.Entry<?, Object> searchCriteria : criteria.getParametersMap().entrySet()) {
            if (isFirstAttribute)
            {
                query.append(SPACE).append(WHERE);
                isFirstAttribute = false;
            }
            else
            {
                query.append(SPACE).append(AND);
            }
            query.append(SPACE).append(searchCriteria.getKey().toString().toLowerCase())
                    .append(LIKE_PARAM.replace(PLACEHOLDER, searchCriteria.getValue().toString()));
        }
        return query.toString();
    }

    protected abstract String getInitialQuery();

}
