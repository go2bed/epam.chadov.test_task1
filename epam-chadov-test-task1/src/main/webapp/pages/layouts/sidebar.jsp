<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="menu">
    <h4 class="sidebar-header-layer" align="center"><bean:message key="side.bar.logo"/></h4>
    <ul class="actions">
        <html:link action="/newsAction.do?action=listNews">
            <li><bean:message key="side.bar.action.list"/></li>
        </html:link>
        <html:link action="/newsAction.do?action=addNews">
            <li><bean:message key="side.bar.action.add"/></li>
        </html:link>
    </ul>
</div>
