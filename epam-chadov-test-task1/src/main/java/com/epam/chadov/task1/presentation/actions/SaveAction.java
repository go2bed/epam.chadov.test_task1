package com.epam.chadov.task1.presentation.actions;

import com.epam.chadov.task1.database.NewsHibernateDao;
import com.epam.chadov.task1.model.News;
import com.epam.chadov.task1.presentation.actions.exception.ActionException;
import com.epam.chadov.task1.presentation.form.ShowNewsForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.struts.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveAction extends ActionSupport {

    private static final Logger logger = LoggerFactory.getLogger(SaveAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        News news = newsFromForm((ShowNewsForm) form, request);
        if (news == null) {
            return mapping.findForward("failure");
        }
        NewsHibernateDao newsHibernateDao = getWebApplicationContext().getBean(NewsHibernateDao.class);
        newsHibernateDao.editSaveNews(news);
        return mapping.findForward("success");
    }

    private News newsFromForm(ShowNewsForm form, HttpServletRequest request) {
        News news = new News();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            news.setId(id);
        }
        news.setTitle(form.getTitle());
        news.setBrief(form.getBrief());
        news.setContent(form.getContent());
        String dateFromForm = form.getNewsDate();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateFromForm);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            news.setNewsDate(sqlDate);
        } catch (ParseException e) {
            logger.error("Can't parse date from newsForm", e);
            throw new ActionException("Can't parse date from newsForm", e);
        }
        return news;
    }
}
