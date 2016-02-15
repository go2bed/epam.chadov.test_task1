<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <meta charset="utf-8" content="application/http">
    <title>News Management</title>
</head>
<body>
<div class="wrapper">
    <div class="header">
        <tiles:insert attribute="header"/>
    </div>
    <div class="sidebar">
        <tiles:insert attribute="sidebar"/>
    </div>
    <div class="content">
        <div class="path">
            <tiles:insert attribute="content"/>
        </div>
    </div>
    <div class="footer">
        <tiles:insert attribute="footer"/>
    </div>
</div>
</body>
</html>
