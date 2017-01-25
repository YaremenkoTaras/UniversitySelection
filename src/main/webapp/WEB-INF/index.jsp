<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<html>
<head>
    <title>JSTL fmt:message Tag</title>
</head>

<body>

<%@include file="/WEB-INF/languages_locale.jsp"%>
<br><br><br>
<fmt:message key="language.ru"/><br>
<fmt:message key="admin.header"/><br>
</body>
</html>