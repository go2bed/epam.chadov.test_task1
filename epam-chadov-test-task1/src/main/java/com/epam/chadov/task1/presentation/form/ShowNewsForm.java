package com.epam.chadov.task1.presentation.form;

import com.epam.chadov.task1.model.News;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 *
 */
public class ShowNewsForm extends ActionForm {
    private String title;
    private List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
