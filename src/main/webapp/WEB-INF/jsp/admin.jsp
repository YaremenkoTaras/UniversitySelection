<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 13.01.2017
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${content.getString("admin.head")} : ${user.name} ${content.getString("main.title")}</title>
</head>

<body>

Admin main page

<br>

<form action="/Controller" method="post">
    <input type="hidden" name="command" value="showFaculties">
    <button type="submit">Show faculties</button>
</form>

<br>

<form method="POST" action="Controller">
    <input type="hidden" name="command" value="changeUserInfo"/>
    <input type="hidden" name="additional" value="edit">
    <input type="submit" value="${content.getString("button.edit")}">
</form>

<form method="post" action="/Controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="${content.getString("button.logout")}">
</form>


</body>
</html>
