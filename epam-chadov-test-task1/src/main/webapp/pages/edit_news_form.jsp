<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="news" type="com.epam.chadov.task1.model.News"--%>
<c:if test="${not empty news}">
    <jsp:useBean id="news" scope="request" type="com.epam.chadov.task1.model.News"/>
</c:if>

<div class="news-box">
    <div class="edit-form">
        <html:form action="/saveNews" method="post">
            <html:errors/>
            <div class="field">
                <label for="news_title"><bean:message key="news.edit.input.title"/> </label>
                  <html:text styleId="news_title" property="title" value="${news.title}"/>
            </div>
            <div class="field">
                <label for="news_date"><bean:message key="news.edit.input.date"/> </label>
                   <html:text styleId="news_date" property="newsDate" value="${news.newsDate}"/>
            </div>
            <div class="field">
                <label for="news_brief"><bean:message key="news.edit.input.brief"/></label>
                <html:textarea cols="80" rows="7" styleId="news_brief" property="brief" value="${news.brief}"/>
            </div>

            <div class="field">
                <label for="news_content"><bean:message key="news.edit.input.content"/></label>
                <html:textarea cols="80" rows="13" styleId="news_content" property="content" value="${news.content}"/>
            </div>
            <div class="button-area">
                <input type="hidden" name="id" value="${news.id}">
                <button type="submit" value="save" onclick="window.location = '/'" name="action"><bean:message key="news.button.save"/></button>
                <button type="button" onclick="window.location = '/'"><bean:message key="news.button.cancel"/></button>
            </div>
        </html:form>
    </div>
</div>
