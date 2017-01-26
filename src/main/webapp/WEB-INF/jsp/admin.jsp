<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 13.01.2017
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title><fmt:message key="admin.head"/> : ${user.name} <fmt:message key="main.title"/></title>
</head>

<body>

<h3><fmt:message key="admin.header"/> - ${user.name}</h3>

<br>

<form action="/Controller" method="post">
    <input type="hidden" name="command" value="showFaculties">
    <button type="submit">Show faculties</button>
</form>

<br>

<form method="POST" action="Controller">
    <input type="hidden" name="command" value="changeUserInfo"/>
    <input type="hidden" name="additional" value="edit">
    <input type="submit" value="<fmt:message key="button.edit"/>">
</form>

<br>

<form action="/Controller" method="post">
    <input type="hidden" name="command" value="register">
    <input type="hidden" name="additional" value="admin">
    <button type="submit"><fmt:message key="admin.button.create_admin"/></button>
</form>

<br>

<form method="post" action="/Controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="<fmt:message key="button.logout"/>">
</form>


</body>
</html>
