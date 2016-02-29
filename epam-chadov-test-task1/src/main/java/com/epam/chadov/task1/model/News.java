package com.epam.chadov.task1.model;


import java.sql.Date;

/**
 *
 */
public class News extends AbstractEntity {

    private String title;
    private Date newsDate;
    private String brief;
    private String content;
    private String deleted;

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", newsDate=" + newsDate  +
                '}';
    }
}
