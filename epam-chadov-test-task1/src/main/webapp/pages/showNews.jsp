<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<body>
<%--<bean:write name="display"/>--%>
<html:link action="/newsAction.do?action=editNews">
    Edit News
</html:link><br/>
<form action="${pageContext.request.contextPath}/newsAction.do?action=editNews">
    <input type="submit" value="show news 2"/>
</form>

<h1>It's a news world news today! And Hello from struts</h1>
</body>