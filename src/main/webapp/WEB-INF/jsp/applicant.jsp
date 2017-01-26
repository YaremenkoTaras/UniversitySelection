<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 12.01.2017
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title><fmt:message key="applicant.applicant"/>: ${user.name} <fmt:message key="main.title"/> </title>
</head>
<body>
<form>
    <h4><fmt:message key="applicant.profile"/>: ${user.name}</h4>
    <br>
    <fmt:message key="applicant.marks"/>
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>Subject</td>
            <td>Mark</td>
        </tr>

        <c:forEach var="value" items="${marks}">
            <tr>
                <td>${value.subject.name}</td>
                <td>${value.mark}</td>
            </tr>
        </c:forEach>
    </table>
    <form method="POST" action="Controller">
        <input type="hidden" name="command" value="addMark"/>
        <input type="hidden" name="additional" value="show">
        <input type="submit" value="<fmt:message key="applicant.button.add_mark"/>">
    </form>
    <br>
    <form method="POST" action="Controller">
        <input type="hidden" name="command" value="changeUserInfo"/>
        <input type="hidden" name="additional" value="edit">
        <input type="submit" value="<fmt:message key="button.edit"/>">
    </form>

</form>
<br>
<form>
    <h4><fmt:message key="applicant.applications_list"/></h4>
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td><fmt:message key="application.applicant"/></td>
            <td><fmt:message key="application.overall"/></td>
            <td><fmt:message key="application.date"/></td>
            <td><fmt:message key="application.status"/></td>
            <td><fmt:message key="application.description"/></td>
        </tr>
        <c:forEach  var="app" items="${applications}">
            <tr>
                <td>${app.faculty.name}</td>
                <td>${app.overall}</td>
                <td>${app.date}</td>
                <td>${app.status}</td>
                <td>${app.description}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form method="POST" action="Controller">
        <input type="hidden" name="command" value="showFaculties"/>
        <input type="submit" value="<fmt:message key="applicant.button.add"/>">
    </form>

    <form method="post" action="/Controller">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" value="<fmt:message key="button.logout"/>">
    </form>

</form>
</body>
</html>
