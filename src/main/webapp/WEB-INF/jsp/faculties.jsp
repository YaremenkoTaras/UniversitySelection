<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 13.01.2017
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>All faculties <fmt:message key="main.title"/></title>
</head>
<header>
    <div class="container">
        <h3><fmt:message key="faculties.header"/></h3>
    </div>
</header>
<body>
<div>
    <form class="container">
        <c:forEach var="faculty" items="${faculties}">
            <a href="Controller?command=showFaculty&id=${faculty.id}">
                <div class="facultyvalue"> ${faculty.name} </div>
            </a>
        </c:forEach>
    </form>
</div>
<br>
<form class="container" action="/Controller" method="post">
    <c:if test="${user.roleID == 2}">
        <input type="hidden" name="command" value="showApplicant">
    </c:if>
    <c:if test="${user.roleID == 1}">
        <input type="hidden" name="command" value="showAdmin">
    </c:if>
    <button type="submit"><fmt:message key="button.back_to_profile"/></button>
</form>
</body>
</html>
