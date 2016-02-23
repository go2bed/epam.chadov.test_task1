package com.epam.chadov.task1.database;

import com.epam.chadov.task1.database.exception.DaoException;
import com.epam.chadov.task1.model.News;
import com.sun.xml.internal.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class NewsDao implements GenericDao<News> {
    private final static Logger logger = LoggerFactory.getLogger(NewsDao.class);
    private static Connection connection;
    private PreparedStatement preparedStatement;
    private static final String USER = "andrey";
    private static final String PASSWORD = "andrey";
    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String INSERT = "INSERT INTO ANDREY.NEWS (ID, TITLE, NEWS_DATE, BRIEF, CONTENT) VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM ANDREY.NEWS WHERE ID = ?";
    private static final String GET_ALL = "SELECT * FROM ANDREY.NEWS";
    private static final String DELETE = "DELETE FROM ANDREY.NEWS WHERE ID = ?";
    private static final String UPDATE_TITLE = " UPDATE ANDREY.NEWS SET TITLE = ? WHERE ID = ? ";



    @Override
    public List<News> getAllNews() {
        logger.info("Start getting all news from DB");
        List<News> list = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            list.addAll(parseResultSet(resultSet));
            logger.info(list.toString());
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error("Can't load data from DB", e);
            throw new DaoException("Can't load data from DB", e);
        }
        return list;
    }

    @Override
    public boolean editNews(News news) {
        boolean isUpdated;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_TITLE);
            preparedStatement = preparedStatementForUpdate(preparedStatement, news);
            int count = preparedStatement.executeUpdate();
            isUpdated = (count > 0);
            preparedStatement.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            logger.error("Couldn't update data of object in base", e);
            throw new DaoException("Couldn't update data of object in base", e);
        }
        return isUpdated;
    }

    @Override
    public News create(News news) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement = preparedStatementForInsert(preparedStatement, news);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            preparedStatement.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            logger.error("Can't create object in DB", e);
            throw new DaoException("Can't create object in DB", e);
        }
        return news;
    }

    @Override
    public boolean deleteNews(News news) {
        boolean isDelete;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement = preparedStatementForDelete(preparedStatement, news);
            int count = preparedStatement.executeUpdate();
            isDelete = (count > 0);
            preparedStatement.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            logger.error("Can't delete object from DB", e);
            throw new DaoException("Can't delete object from DB", e);
        }
        return isDelete;
    }

    @Override
    public News getById(long id) {
        List<News> list;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error("Can't get news by ID from DB,or no such data", e);
            throw new DaoException("Can't get news by ID from DB,or no such data", e);
        }
        return list.iterator().next();
    }


    private Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.error("Driver for SQL database wasn't found", e);
            throw new DaoException("Driver for SQL database wasn't found", e);
        } catch (SQLException e) {
            logger.error("Can,t connect to SQL Base, check connection url, or user parameters", e);
            throw new DaoException("Can,t connect to SQL Base, check connection url, or user parameters", e);
        }
        return connection;
    }

    private List<News> parseResultSet(ResultSet resultset) {
        List<News> newsList = new ArrayList<>();
        try {
            while (resultset.next()) {
                News news = new News();
                news.setId(resultset.getLong("id"));
                news.setTitle(resultset.getString("title"));
                news.setNewsDate(resultset.getDate("news_date"));
                news.setBrief(resultset.getString("brief"));
                news.setContent(resultset.getString("content"));
                newsList.add(news);
            }
        } catch (SQLException e) {
            logger.error("Can't parse result set from news base", e);
            throw new DaoException("Can't parse result set from news base", e);
        }

        return newsList;
    }

    public PreparedStatement preparedStatementForInsert(PreparedStatement preparedStatement, News news) {
        try {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setDate(2, news.getNewsDate());
            preparedStatement.setString(3, news.getBrief());
            preparedStatement.setString(4, news.getContent());
        } catch (SQLException e) {
            logger.error("Can't set id fields for create statement in news dao", e);
            throw new DaoException("Can't set id fields for create statement in news dao", e);
        }
        return preparedStatement;
    }

    public PreparedStatement preparedStatementForUpdate(PreparedStatement preparedStatement, News news) {
        try {
            preparedStatement.setString(1, news.getTitle());
        } catch (SQLException e) {
            logger.error("Can't set id fields for update statement in news dao", e);
            throw new DaoException("Can't set id fields for update statement in news dao", e);
        }
        return preparedStatement;
    }

    public PreparedStatement preparedStatementForDelete(PreparedStatement preparedStatement, News news) {
        try {
            preparedStatement.setLong(1, news.getId());
        } catch (SQLException e) {
            logger.error("Can't set id field for delete statement in news dao", e);
            throw new DaoException("Can't set id field for delete statement in news dao", e);
        }
        return preparedStatement;
    }

}
