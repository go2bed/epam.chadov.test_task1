package com.epam.chadov.task1.database;


import com.epam.chadov.task1.model.AbstractEntity;

import java.util.List;

/**
 *
 */
public interface GenericDao<T extends AbstractEntity> {

    List<T> getAllNews();

    boolean editNews(T object);

    T create(T object);

    boolean deleteNews(T object);

    T getById(long id);
}
