<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><bean:write name="display"/></h1>
<h1><bean:write name="showNewsForm" property="title" /></h1>
<h1>This is a second news on today</h1>
</body>
</html>
