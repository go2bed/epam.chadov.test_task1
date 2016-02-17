<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="logo">
    <a>News Management</a>
</div>
<br/>
<br/>
<div align="right" class="lang-panel">
    <a href="${pageContext.request.contextPath}
       /Locale.do?action=english"><bean:message key="main.page.en.language"/></a>
    <a href="${pageContext.request.contextPath}
       /Locale.do?action=russian"><bean:message key="main.page.ru.language"/></a>
</div>