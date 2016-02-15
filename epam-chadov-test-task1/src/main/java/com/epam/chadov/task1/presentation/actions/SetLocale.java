package com.epam.chadov.task1.presentation.actions;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 *
 */
public class SetLocale extends DispatchAction {

    public ActionForward english(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.US);
        return mapping.findForward("success");
    }

    public ActionForward russian(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        Locale ruLocale = new Locale("ru");
        request.getSession().setAttribute(Globals.LOCALE_KEY,
                ruLocale);
        return mapping.findForward("success");
    }
}
