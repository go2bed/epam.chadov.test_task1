package com.epam.chadov.task1.model;


import java.sql.Date;

/**
 *
 */
public class News extends AbstractEntity {

    private String title;
    private Date newsDate;
    private String content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", newsDate=" + newsDate +
                ", content='" + content + '\'' +
                '}';
    }
}
