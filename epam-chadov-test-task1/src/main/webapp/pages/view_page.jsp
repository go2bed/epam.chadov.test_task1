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
        <html:form action="/newsAction" method="post">
            <div class="field">
                <label><bean:message key="news.edit.input.title"/> </label>
                <c:out value="${news.title}"/>
            </div>
            <div class="field">
                <label><bean:message key="news.edit.input.date"/> </label>
                <c:out value="${news.newsDate}"/>
            </div>
            <div class="field">
                <label><bean:message key="news.edit.input.brief"/></label>
                <c:out value="${news.brief}"/>
            </div>

            <div class="field">
                <label><bean:message key="news.edit.input.content"/></label>
                <c:out value="${news.content}"/>
            </div>
            <div class="button-area">
                <input type="hidden" name="id" value="${news.id}">
                <button type="submit" value="editNews" name="action"><bean:message key="news.button.edit"/></button>
                <button type="submit" value="deleteList" name="action"><bean:message
                        key="news.button.delete"/></button>
                <input type="hidden" name="id" value="${news.id}">

            </div>
        </html:form>
    </div>
</div>
