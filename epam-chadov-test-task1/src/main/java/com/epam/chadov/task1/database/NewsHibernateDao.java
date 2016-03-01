package com.epam.chadov.task1.database;

import com.epam.chadov.task1.database.exception.DaoException;
import com.epam.chadov.task1.model.News;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.List;

/**
 *
 */
public class NewsHibernateDao implements GenericDao<News> {
    private static final Logger logger = LoggerFactory.getLogger(NewsHibernateDao.class);
    private SessionFactory sessionFactory;
    private Session session;

    @Autowired
    public NewsHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
         if (sessionFactory.getCurrentSession() == null) {
            return session = sessionFactory.openSession();
        }
        return this.sessionFactory.getCurrentSession();
    }


    @Override
    @SuppressWarnings(value = "unchecked")
    public List<News> getAllNews() {
        session = currentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(News.class);
        criteria.add(Restrictions.isNotNull("id"));
        return criteria.list();
    }

    @Override
    public News getById(Integer id) {
        session = currentSession();
        session.beginTransaction();
        try {
            return (News) session.get(News.class, id);
        } catch (HibernateException | ClassCastException e) {
            logger.error("Can't get object by Id", e);
            throw new DaoException("Can't get object by Id", e);
        }
    }

    @Override
    public boolean editSaveNews(News news) {
        session = currentSession();
        Transaction transaction = session.beginTransaction();
        logger.info("that news, which need save" + news.toString());
        try {
            if (news.getId() == null) {
                session.save(news);
            } else {
                session.update(news);
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can't save or update object in DB", e);
            transaction.rollback();
            return false;
        }
        return true;
    }


    @Override
    public boolean deleteNews(News news) {
        session = currentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(news);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can't save or update object in DB", e);
            transaction.rollback();
            return false;
        }
        return true;
    }
}
