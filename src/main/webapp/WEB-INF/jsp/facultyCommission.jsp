<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 20.01.2017
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title><fmt:message key="faculty"/>: ${faculty.name} <fmt:message key="main.title"/></title>
</head>
<header>
    <h3>${faculty.name}</h3>
    <h4><fmt:message key="faculty.total"/>: ${faculty.numberOfStudent}</h4>
</header>

<form name="form_accepted_applications">
    <h4><fmt:message key="application.accepted"/></h4>
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td><fmt:message key="application.applicant"/></td>
            <td><fmt:message key="application.overall"/></td>
            <td><fmt:message key="application.date"/></td>
            <td><fmt:message key="application.status"/></td>
            <td><fmt:message key="application.description"/></td>
        </tr>
        <c:forEach var="app" items="${applications}">
            <c:if test="${app.isAccept()}">
                <tr>
                    <td>${app.user.name}</td>
                    <td>${app.overall}</td>
                    <td>${app.date}</td>
                    <td>${app.status}</td>
                    <td>${app.description}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</form>

<form name="form_process_applications">
    <h4><fmt:message key="application.process"/></h4>
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td><fmt:message key="application.applicant"/></td>
            <td><fmt:message key="application.overall"/></td>
            <td><fmt:message key="application.date"/></td>
            <td><fmt:message key="application.status"/></td>
            <td><fmt:message key="application.description"/></td>
            <td><fmt:message key="faculty.commission.watch"/></td>
        </tr>
        <c:forEach var="app" items="${applications}">
            <c:if test="${app.isProcess()}">
                <tr>
                    <td>${app.user.name}</td>
                    <td>${app.overall}</td>
                    <td>${app.date}</td>
                    <td>${app.status}</td>
                    <td>${app.description}</td>
                    <td>
                        <form action="/Controller" method="post">
                            <input type="hidden" name="command" value="showApplication">
                            <input type="hidden" name="additional" value="${app.id}">
                            <input type="submit" value="<fmt:message key="faculty.commission.show_app"/>">
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</form>

<form name="back_faculties" action="/Controller" method="post">
    <input type="hidden" name="command" value="showFaculties">
    <button type="submit"><fmt:message key="faculty.button.back"/></button>
</form>
<form class="container" action="/Controller" method="post">
    <input type="hidden" name="command" value="showAdmin">
    <button type="submit"><fmt:message key="button.back_to_profile"/></button>
</form>

</body>
</html>

