<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
<head>
    <title>Show News</title>
</head>
<body>

<h1><bean:write name="display"/></h1>
<h1>THis hello from edit news</h1>
<form action="${pageContext.request.contextPath}/newsAction.do">
    <input type="submit" value="show news 2"/>
</form>
</body>
</html>
