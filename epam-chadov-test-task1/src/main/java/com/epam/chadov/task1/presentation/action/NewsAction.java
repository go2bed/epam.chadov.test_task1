package com.epam.chadov.task1.presentation.action;

import com.epam.chadov.task1.presentation.form.ShowNewsForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class NewsAction extends DispatchAction {

    public ActionForward listNews (ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ShowNewsForm showNewsForm = (ShowNewsForm) form;
        showNewsForm.setTitle("It's a hot news on today!");
        request.setAttribute("display", "This is a list of all news");
         return mapping.findForward("success");
    }


    public ActionForward editNews (ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("display", "This form for editing some news");
        return mapping.findForward("success");
    }
}
