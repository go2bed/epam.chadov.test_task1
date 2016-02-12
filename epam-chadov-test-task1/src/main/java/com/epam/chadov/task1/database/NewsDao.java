package com.epam.chadov.task1.database;

import com.epam.chadov.task1.model.News;

import java.util.List;

/**
 *
 */
public class NewsDao implements GenericDao<News>{

    @Override
    public List<News> getAllNews() {
        return null;
    }

    @Override
    public boolean editNews() {
        return false;
    }

    @Override
    public boolean deleteNews() {
        return false;
    }

    @Override
    public News getById(long id) {
        return null;
    }
}
