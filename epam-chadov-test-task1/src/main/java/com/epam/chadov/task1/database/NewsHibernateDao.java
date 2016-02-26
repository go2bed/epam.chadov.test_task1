package com.epam.chadov.task1.database;

import com.epam.chadov.task1.database.exception.DaoException;
import com.epam.chadov.task1.model.News;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

/**
 *
 */

public class NewsHibernateDao implements GenericDao<News> {
    private static final Logger logger = LoggerFactory.getLogger(NewsHibernateDao.class);

    private Session getSession() {
        return getSessionFactory().openSession();
    }

    @Override
    @SuppressWarnings(value = "This is unchecked criterion")
    public List<News> getAllNews() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(News.class);
        criteria.add(Restrictions.isNotNull("id"));
        List<News> newsList = criteria.list();
        session.close();
        return newsList;
    }

    @Override
    public News getById(long id) {
        try {
            return (News) getSession().get(News.class, id);
        } catch (HibernateException | ClassCastException e) {
            logger.error("Can't get object by Id", e);
            throw new DaoException("Can't get object by Id", e);
        }
    }

    @Override
    public boolean editSaveNews(News news) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (news.getId() == null) {
                session.save(news);
            } else {
                session.update(news);
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can't save or update object in DB", e);
            throw new DaoException("Can't save or update object in DB", e);
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean deleteNews(News news) {
        return false;
    }


    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure("hibernate/hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }
}
