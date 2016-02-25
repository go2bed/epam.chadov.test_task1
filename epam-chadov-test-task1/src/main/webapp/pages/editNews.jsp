<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<div class="news-box">
    <div class="edit-form">
        <html:form action="/newsAction" method="post">
            <div class="field">
                <label for="news_title"><bean:message key="news.edit.input.title"/> </label>
                <html:text styleId="news_title" property="title" value=""/>
            </div>
            <div class="field">
                <label for="news_date">News Date</label>
                <html:text styleId="news_date" property="title" value=""/>
            </div>
            <div class="field">
                <label for="news_brief">Brief</label>
                <html:textarea cols="80" rows="7" styleId="news_brief" property="title" value=""/>
            </div>

            <div class="field">
                <label for="news_content">Content</label>
                <html:textarea cols="80" rows="13" styleId="news_content" property="title" value=""/>
            </div>
        </html:form>
        <bean:write name="showNewsForm" property="title"/>
    </div>
</div>
