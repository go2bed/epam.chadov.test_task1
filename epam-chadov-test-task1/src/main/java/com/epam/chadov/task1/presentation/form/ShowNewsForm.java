package com.epam.chadov.task1.presentation.form;

import org.apache.struts.action.ActionForm;

/**
 *
 */
public class ShowNewsForm extends ActionForm {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
