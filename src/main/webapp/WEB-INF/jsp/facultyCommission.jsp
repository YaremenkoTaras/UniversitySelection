<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 20.01.2017
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${content.getString("faculty")}: ${faculty.name} ${content.getString("main.title")}</title>
</head>
<header>
    <h3>${faculty.name}</h3>
    <h4>${content.getString("faculty.total")}: ${faculty.numberOfStudent}</h4>
</header>

<form name="form_proces_applications">

    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>${content.getString("application.applicant")}</td>
            <td>${content.getString("application.overall")}</td>
            <td>${content.getString("application.date")}</td>
            <td>${content.getString("application.status")}</td>
            <td>${content.getString("application.description")}</td>
        </tr>
        <c:forEach var="app" items="${applications}">
            <c:if test="${app.statusID == 1}">
                <tr>
                    <td>${app.userID}</td>
                    <td>${app.overall}</td>
                    <td>${app.date}</td>
                    <td>${app.statusID}</td>
                    <td>${app.description}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</form>

<form name="form_proces_applications">
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>${content.getString("application.applicant")}</td>
            <td>${content.getString("application.overall")}</td>
            <td>${content.getString("application.date")}</td>
            <td>${content.getString("application.status")}</td>
            <td>${content.getString("application.description")}</td>
            <td></td>
        </tr>
        <c:forEach var="app" items="${applications}">
            <c:if test="${app.statusID == 2}">
                <tr>
                    <td>${app.userID}</td>
                    <td>${app.overall}</td>
                    <td>${app.date}</td>
                    <td>${app.statusID}</td>
                    <td>${app.description}</td>
                    <td>
                        <form action="/Controller" method="post">
                            <input type="hidden" name="command" value="showApplication">
                            <input type="submit" value="${content.getString("faculty.commission.show_app")}">
                        </form>
                    </td>
                </tr>
            </c:if>
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

