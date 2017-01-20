<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 12.01.2017
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${content.getString("applicant.applicant")} : ${user.name} ${content.getString("main.title")}</title>
</head>
<body>
<form>
    <h4>${content.getString("applicant.profile")}: ${user.name}</h4>
    <br>
    ${content.getString("applicant.marks")}
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>Subject</td>
            <td>Mark</td>
        </tr>

        <c:forEach var="value" items="${marks}">
            <tr>
                <td>${value.subjectID}</td>
                <td>${value.mark}</td>
            </tr>
        </c:forEach>
    </table>
    <form method="POST" action="Controller">
        <input type="hidden" name="command" value="addMark"/>
        <input type="hidden" name="additional" value="show">
        <input type="submit" value="${content.getString("applicant.button.add_mark")}">
    </form>
    <br>
    <form method="POST" action="Controller">
        <input type="hidden" name="command" value="changeApplicantInfo"/>
        <input type="hidden" name="additional" value="edit">
        <input type="submit" value="${content.getString("applicant.button.edit")}">
    </form>

</form>
<br>
<form>
    <h4>${content.getString("applicant.applications_list")}</h4>
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>${content.getString("application.applicant")}</td>
            <td>${content.getString("application.overall")}</td>
            <td>${content.getString("application.date")}</td>
            <td>${content.getString("application.status")}</td>
            <td>${content.getString("application.description")}</td>
        </tr>
        <c:forEach  var="app" items="${applications}">
            <tr>
                <td>${app.facultyID}</td>
                <td>${app.overall}</td>
                <td>${app.date}</td>
                <td>${app.statusID}</td>
                <td>${app.description}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form method="POST" action="Controller">
        <input type="hidden" name="command" value="showFaculties"/>
        <input type="submit" value="${content.getString("applicant.button.add")}">
    </form>

    <form method="post" action="/Controller">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" value="${content.getString("applicant.button.logout")}">
    </form>

</form>
</body>
</html>
