<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 13.01.2017
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${content.getString("faculty")}: ${faculty.name}</title>
</head>
<header>
    <h3>${faculty.name}</h3>
    <h4>${content.getString("faculty.total")}: ${faculty.numberOfStudent}</h4>

</header>
<body>


<form>
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>${content.getString("faculty.subject")}</td>
            <td>${content.getString("faculty.min_mark")}</td>
        </tr>
        <c:forEach var="fsub" items="${facultysubjects}">
            <tr>
                <td>${fsub.subjectID}</td>
                <td>${fsub.minMark}</td>
            </tr>
        </c:forEach>
    </table>
</form>


<form>
    <c:if test="${user.roleID == 2 && userapp == null && missingmark == null && lowmark == null}">
        <div class="container">
            <form action="/Controller" method="post">
                <input type="hidden" name="command" value="addApplication"/>
                <input type="hidden" name="id" value="${faculty.id}"/>
                <input type="text" name="description" value="${description}" placeholder="Few words about yourself"
                       id="form1" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z\s]+$">
                <input type="submit" value="${content.getString("faculty.add_application")}">
            </form>
        </div>
    </c:if>

    <c:if test="${missingmark != null}">
        <h4>${content.getString("faculty.missmark")}</h4>
    </c:if>

    <c:if test="${lowmark != null}">
        <h4>${content.getString("faculty.lowmark")}</h4>
    </c:if>

    <c:if test="${user.roleID == 2 && userapp != null}">
        <h4>${content.getString("faculty.user_application")} ${userapp.date}</h4>
    </c:if>
</form>


<form>
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>${content.getString("application.applicant")}</td>
            <td>${content.getString("application.overall")}</td>
            <td>${content.getString("application.date")}</td>
            <td>${content.getString("application.status")}</td>
            <td>${content.getString("application.description")}</td>
        </tr>
        <c:forEach var="app" items="${applications}">
            <tr>
                <td>${app.userID}</td>
                <td>${app.overall}</td>
                <td>${app.date}</td>
                <td>${app.statusID}</td>
                <td>${app.description}</td>
            </tr>
        </c:forEach>
    </table>
</form>

<form name="back_faculties" action="/Controller" method="post">
    <input type="hidden" name="command" value="showFaculties">
    <button type="submit">${content.getString("faculty.button.back")}</button>
</form>
<form class="container" action="/Controller" method="post">
    <input type="hidden" name="command" value="showApplicant">
    <button type="submit">${content.getString("button.back_to_profile")}</button>
</form>

</body>
</html>
