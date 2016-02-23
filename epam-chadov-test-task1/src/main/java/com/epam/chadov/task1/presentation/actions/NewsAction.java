package com.epam.chadov.task1.presentation.actions;

import com.epam.chadov.task1.database.NewsDao;
import com.epam.chadov.task1.model.News;
import com.epam.chadov.task1.presentation.form.ShowNewsForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
public class NewsAction extends DispatchAction {
    private static final Logger logger = LoggerFactory.getLogger(NewsAction.class);
    NewsDao newsDao = new NewsDao();

    public ActionForward listNews(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ShowNewsForm showNewsForm = (ShowNewsForm) form;

        showNewsForm.setTitle("It's a hot news on today!");
        List<News> list = newsDao.getAllNews();
        logger.info(list.toString());
        showNewsForm.setNewsList(list);
        //  request.setAttribute("display", "This is a list of all news");
        return mapping.findForward("success");
    }


    public ActionForward editNews(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("display", "This form for editing some news");
        return mapping.findForward("edit_list");
    }

    public ActionForward addNews(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
         throws Exception {


        return mapping.findForward("add_news");
    }
}
