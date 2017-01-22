<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 20.01.2017
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${content.getString("application.title")}: ${applicant.name} ${content.getString("main.title")}</title>
</head>
<body>
<h3>${content.getString("application.title")} : ${applicant.name}</h3>
<form name="form_application">
    <div class="container">
        <table border="1" cellspacing="0" cellpadding="2">
            <tr>
                <td>${content.getString("application.applicant")}</td>
                <td>${applicant.name}</td>
            </tr>
            <tr>
                <td>${content.getString("registration.date_of_birth")}</td>
                <td>${applicant.birth}</td>
            </tr>
            <tr>
                <td>${content.getString("registration.sex")}</td>
                <td>${applicant.sex}</td>
            </tr>
            <tr>
                <td>${content.getString("registration.phone")}</td>
                <td>${applicant.phone}</td>
            </tr>
            <tr>
                <td>${content.getString("registration.address")}</td>
                <td>${applicant.address}</td>
            </tr>
            <tr>
                <td>${content.getString("application.description")}</td>
                <td>${userapp.description}</td>
            </tr>
            <tr>
                <td>${content.getString("application.overall")}</td>
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
            <input type="hidden" name="id" value="${userapp.facultyID}">
            <button type="submit">${content.getString("application.button.accept")}</button>
        </form>
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="processApplication">
            <input type="hidden" name="additional" value="decline">
            <input type="hidden" name="id" value="${userapp.facultyID}">
            <input type="hidden" name="userapp" value="${userapp.id}">
            <button type="submit">${content.getString("application.button.decline")}</button>
        </form>
    </div>
</form>

<form name="form_all_applications">
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>${content.getString("application.faculty")}</td>
            <td>${content.getString("application.overall")}</td>
            <td>${content.getString("application.date")}</td>
            <td>${content.getString("application.status")}</td>
            <td>${content.getString("application.description")}</td>
        </tr>
        <c:forEach var="app" items="${applications}">
            <tr>
                <td>${app.facultyID}</td>
                <td>${app.overall}</td>
                <td>${app.date}</td>
                <td>${app.statusID}</td>
                <td>${app.description}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<br>
<form action="/Controller" method="post">
    <input type="hidden" name="command" value="showFaculty">
    <input type="hidden" name="id" value="${userapp.facultyID}">
    <button type="submit">${content.getString("application.button.back")}</button>
</form>

</body>
</html>
