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
    <title>${content.getString("applicant.applicant")} : ${user.name}</title>
</head>
<body>
    <form>
        <h4>${content.getString("applicant.account")}: ${user.name}</h4>
        <br>
        ${content.getString("applicant.marks")}
        <table border="1" cellspacing="0" cellpadding="2">
            <tr>
                <td>Subject</td>
                <td>Mark</td>
            </tr>

            <c:forEach  var="value" items="${marks}">
                <tr>
                    <td>${value.subjectID}</td>
                    <td>${value.mark}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <h4>${content.getString("applicant.applications_list")}</h4>
        <table border="1" cellspacing="0" cellpadding="2">
            <tr>
                <td>${content.getString("applicant.faculty")}</td>
                <td>${content.getString("applicant.overall")}</td>
                <td>${content.getString("applicant.date")}</td>
                <td>${content.getString("applicant.status")}</td>
                <td>${content.getString("applicant.description")}</td>
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

    </form>
</body>
</html>
