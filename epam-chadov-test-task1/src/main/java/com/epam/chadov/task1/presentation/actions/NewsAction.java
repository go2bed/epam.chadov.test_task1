package com.epam.chadov.task1.presentation.actions;

import com.epam.chadov.task1.database.NewsDao;
import com.epam.chadov.task1.database.NewsHibernateDao;
import com.epam.chadov.task1.model.News;
import com.epam.chadov.task1.presentation.form.ShowNewsForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.struts.DispatchActionSupport;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        ShowNewsForm showNewsForm = (ShowNewsForm) form;
        return mapping.findForward("edit_list");
    }

    public ActionForward addNews(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ShowNewsForm showNewsForm = (ShowNewsForm) form;
        return mapping.findForward("add_news");
    }


    public ActionForward deleteList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
        throws Exception{
        ShowNewsForm showNewsForm = (ShowNewsForm) form;
        List<News> newsList = showNewsForm.getNewsList();
        for (News news: newsList){
            if(news.getDeleted().equals("true")){
                newsHibernateDao.deleteNews(news);
            }
        }
        return mapping.findForward("success");
    }
}
