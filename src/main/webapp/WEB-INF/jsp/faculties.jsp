<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 13.01.2017
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All faculties</title>
</head>
<header>
    <div class="container">
        <h3>${content.getString("faculties.header")}</h3>
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
    <input type="hidden" name="command" value="showApplicant">
    <button type="submit">${content.getString("button.back_to_profile")}</button>
</form>
</body>
</html>
