package com.epam.chadov.task1.database;


import com.epam.chadov.task1.model.AbstractEntity;
import com.epam.chadov.task1.model.News;

import java.util.List;

/**
 *
 */
public interface GenericDao<T extends AbstractEntity> {

    List<T> getAllNews();

    boolean editSaveNews(News news);

    boolean deleteNews(News news);

    T getById(long id);
}
