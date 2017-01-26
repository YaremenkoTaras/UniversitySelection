<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 20.01.2017
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title><fmt:message key="application.title"/>: ${applicant.name} <fmt:message key="main.title"/></title>
</head>
<body>
<h3><fmt:message key="application.title"/> : ${applicant.name}</h3>
<form name="form_application">
    <div class="container">
        <table border="1" cellspacing="0" cellpadding="2">
            <tr>
                <td><fmt:message key="application.applicant"/></td>
                <td>${applicant.name}</td>
            </tr>
            <tr>
                <td><fmt:message key="registration.date_of_birth"/></td>
                <td>${applicant.birth}</td>
            </tr>
            <tr>
                <td><fmt:message key="registration.sex"/></td>
                <td>${applicant.sex}</td>
            </tr>
            <tr>
                <td><fmt:message key="registration.phone"/></td>
                <td>${applicant.phone}</td>
            </tr>
            <tr>
                <td><fmt:message key="registration.address"/></td>
                <td>${applicant.address}</td>
            </tr>
            <tr>
                <td><fmt:message key="application.description"/></td>
                <td>${userapp.description}</td>
            </tr>
            <tr>
                <td><fmt:message key="application.overall"/></td>
                <td>${userapp.overall}</td>
            </tr>
        </table>
    </div>
</form>

<form name="form_button">
    <div class="container">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="processApplication">
            <input type="hidden" name="additional" value="accept">
            <input type="hidden" name="userapp" value="${userapp.id}">
            <input type="hidden" name="id" value="${userapp.faculty.id}">
            <button type="submit"><fmt:message key="application.button.accept"/></button>
        </form>
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="processApplication">
            <input type="hidden" name="additional" value="decline">
            <input type="hidden" name="id" value="${userapp.faculty.id}">
            <input type="hidden" name="userapp" value="${userapp.id}">
            <button type="submit"><fmt:message key="application.button.decline"/></button>
        </form>
    </div>
</form>

<form name="form_all_applications">
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td><fmt:message key="application.faculty"/></td>
            <td><fmt:message key="application.overall"/></td>
            <td><fmt:message key="application.date"/></td>
            <td><fmt:message key="application.status"/></td>
            <td><fmt:message key="application.description"/></td>
        </tr>
        <c:forEach var="app" items="${applications}">
            <tr>
                <td>${app.faculty.shortName}</td>
                <td>${app.overall}</td>
                <td>${app.date}</td>
                <td>${app.status}</td>
                <td>${app.description}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<br>
<form action="/Controller" method="post">
    <input type="hidden" name="command" value="showFaculty">
    <input type="hidden" name="id" value="${userapp.faculty.id}">
    <button type="submit"><fmt:message key="application.button.back"/></button>
</form>

</body>
</html>
