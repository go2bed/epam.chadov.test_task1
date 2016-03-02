package com.epam.chadov.task1.database;

import com.epam.chadov.task1.database.exception.DaoException;
import com.epam.chadov.task1.model.News;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class NewsHibernateDao implements GenericDao<News> {

    private static final Logger logger = LoggerFactory.getLogger(NewsHibernateDao.class);
    private SessionFactory sessionFactory;
    private Session session;

    public NewsHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * This method will check session at the closing
     * or null and will return new, or current instance of
     * session
     */
    private Session getCurrentSession() {
        if (!sessionFactory.getCurrentSession().isOpen() || session == null) {
            logger.info("Session is closed, reopening session");
            return session = sessionFactory.openSession();
        }
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<News> getAllNews() {
        session = getCurrentSession();
        logger.info("Transaction starting");
        session.beginTransaction();
        Criteria criteria = session.createCriteria(News.class);
        criteria.add(Restrictions.isNotNull("id"));
        List<News> newsList = criteria.list();
        session.close();
        return newsList;
    }

    @Override
    public News getById(Integer id) {
        session = getCurrentSession();
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
        session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        logger.info("This news will be save " + news.toString());
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
    public boolean deleteNews(Integer id) {
        session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            News news = getById(id);
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
