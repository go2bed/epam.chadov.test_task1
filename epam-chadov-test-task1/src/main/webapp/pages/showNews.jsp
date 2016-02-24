<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<body>
<html:link action="/newsAction.do?action=editNews">
    Edit News
</html:link><br/>
<form action="${pageContext.request.contextPath}/newsAction.do?action=editNews">
    <input type="submit" value="show news 2"/>
</form>

<h1>It's a news world news today! And Hello from struts</h1>

<h1><bean:write name="showNewsForm" property="title"/></h1>

<div class="news-box">
    <logic:present name="showNewsForm">
        <logic:notEmpty name="showNewsForm" property="newsList">
            <table border="1">
                <tr>
                    <td>News title</td>
                    <td>Data</td>
                    <td>Brief</td>
                    <td>Content</td>
                </tr>

                <logic:iterate id="news" name="showNewsForm" property="newsList" indexId="id">
                    <input type="hidden" name="<bean:write name="newsList" property="id"/> ">
                    <tr>
                        <td><bean:write name="news" property="title"/></td>
                        <td><bean:write name="news" property="newsDate"/></td>
                        <td><bean:write name="news" property="brief"/></td>
                        <td><bean:write name="news" property="content"/></td>
                    </tr>
                </logic:iterate>
            </table>
        </logic:notEmpty>
    </logic:present>

    <logic:empty name="showNewsForm" property="newsList">
        <h2><bean:message key="news.empty"/></h2>
    </logic:empty>
</div>

</body>