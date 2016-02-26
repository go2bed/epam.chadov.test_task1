package com.epam.chadov.task1.presentation.form;

import com.epam.chadov.task1.model.News;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;

import java.sql.Date;
import java.util.List;

/**
 *
 */
public class ShowNewsForm extends ValidatorForm {

    private long id;
    private String title;
    private String newsDate;
    private String brief;
    private String content;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
