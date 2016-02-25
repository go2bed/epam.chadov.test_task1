<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<div class="news-box">
    <logic:present name="showNewsForm">
        <logic:notEmpty name="showNewsForm" property="newsList">
            <html:form method="post" action="/newsAction">
                <logic:iterate id="newsList" name="showNewsForm" property="newsList" indexId="id">
                    <input type="hidden" name="<bean:write name="newsList" property="id"/> ">
                    <div class="clear-area">
                        <div class="news-text">
                            <h3>News title <bean:write name="newsList" property="title"/></h3> </br>
                            <bean:write name="newsList" property="brief"/>
                        </div>

                        <div class="news-date">
                            <bean:write name="newsList" property="newsDate"/>
                        </div>
                    </div>
                    <div class="actions-in-news-block">
                        <html:link action="/newsAction.do?action=viewNews">
                            view
                        </html:link>
                        <html:link action="/newsAction.do?action=editNews">
                            edit
                        </html:link>
                        <input type="checkbox" name="checkbox" value="newsList.id" title="checkbox"/>
                    </div>

                </logic:iterate>
                <div class="button-area">
                    <button type="submit" value="deleteList" name="action"><bean:message
                            key="news.button.delete"/></button>
                </div>
            </html:form>
        </logic:notEmpty>
    </logic:present>

    <logic:empty name="showNewsForm" property="newsList">
        <h2><bean:message key="news.empty"/></h2>
    </logic:empty>
</div>

