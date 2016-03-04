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
import org.springframework.web.struts.DispatchActionSupport;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Locale;

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
        if (request.getParameter("id") == null ||request.getParameter("id").isEmpty()) {
            return mapping.findForward("edit_list");
        } else {
            Integer id = Integer.valueOf(request.getParameter("id"));
            News news = newsHibernateDao.getById(id);
            request.setAttribute("news", news);
            return mapping.findForward("edit_list");
        }
    }

    public ActionForward deleteList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        newsHibernateDao = getWebApplicationContext().getBean(NewsHibernateDao.class);
        String[] parameterValues = request.getParameterValues("checkbox");
        if (parameterValues != null) {
            for (String id : parameterValues) {
                newsHibernateDao.deleteNews(Integer.parseInt(id));
            }
        } else {
            String id = request.getParameter("id");
            newsHibernateDao.deleteNews(Integer.parseInt(id));
        }
        return mapping.findForward("list_news");
    }

    public ActionForward viewNews(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        newsHibernateDao = getWebApplicationContext().getBean(NewsHibernateDao.class);
        Integer id = Integer.valueOf(request.getParameter("id"));
        logger.info(request.getParameter("id"));
        News news = newsHibernateDao.getById(id);
        request.setAttribute("news", news);
        return mapping.findForward("view_news");
    }
}
