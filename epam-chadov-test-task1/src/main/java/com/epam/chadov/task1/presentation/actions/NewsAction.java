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
import org.springframework.web.struts.DispatchActionSupport;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Locale;

/**
 *
 */
public class NewsAction extends DispatchActionSupport {
    private static final Logger logger = LoggerFactory.getLogger(NewsAction.class);
    private NewsHibernateDao newsHibernateDao;

    public ActionForward listNews(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ShowNewsForm showNewsForm = (ShowNewsForm) form;
        newsHibernateDao = getWebApplicationContext().getBean(NewsHibernateDao.class);
        List<News> list = newsHibernateDao.getAllNews();
        logger.info("This is what we get from DB" + list.toString());
        showNewsForm.setNewsList(list);
        return mapping.findForward("success");
    }


    public ActionForward editNews(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        newsHibernateDao = getWebApplicationContext().getBean(NewsHibernateDao.class);
        Integer id = Integer.valueOf(request.getParameter("id"));
        logger.info(request.getParameter("id"));
        News news = newsHibernateDao.getById(id);
        request.setAttribute("news", news);
        return mapping.findForward("edit_list");
    }

    public ActionForward addNews(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("add_news");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        News news = newsFromForm((ShowNewsForm) form, request);
        if (news == null) {
            return mapping.findForward("failure");
        }
        newsHibernateDao = getWebApplicationContext().getBean(NewsHibernateDao.class);
        newsHibernateDao.editSaveNews(news);
        return mapping.findForward("success");
    }


    public ActionForward deleteList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ShowNewsForm showNewsForm = (ShowNewsForm) form;
        newsHibernateDao = getWebApplicationContext().getBean(NewsHibernateDao.class);
        List<News> newsList = showNewsForm.getNewsList();
        for (News news : newsList) {
            if (news.getDeleted().equals("true")) {
                newsHibernateDao.deleteNews(news);
            }
        }
        return mapping.findForward("success");
    }

    public ActionForward viewNews(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        newsHibernateDao = getWebApplicationContext().getBean(NewsHibernateDao.class);
        Integer id = Integer.valueOf(request.getParameter("id"));
        logger.info(request.getParameter("id"));
        News news = newsHibernateDao.getById(id);
        request.setAttribute("news", news);
        return mapping.findForward("view_list");
    }


    private News newsFromForm(ShowNewsForm form, HttpServletRequest request) {
        News news = new News();
        String id = request.getParameter("id");
        if (id != null) {
            news.setId(id);
        }
        news.setTitle(form.getTitle());
        news.setBrief(form.getBrief());
        news.setContent(form.getContent());
        String dateFromForm = form.getNewsDate();
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        try {
            Date date = format.parse(dateFromForm);
            java.sql.Date sqlDate = new java.sql.Date(date.getDate());
            news.setNewsDate(sqlDate);

        } catch (ParseException e) {
            logger.error("Can't parse data from newsForm", e);
            throw new ActionException("Can't parse data from newsForm", e);
        }
        logger.info(news.toString());
        return news;
    }
}
