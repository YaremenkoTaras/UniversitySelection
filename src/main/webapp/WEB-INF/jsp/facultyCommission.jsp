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
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>

<body>

<div class="col-xs-8 container">
    <div class="form-group">
        <h3>${faculty.name}</h3>
        <h4><fmt:message key="faculty.total"/>: ${faculty.numberOfStudent}</h4>
    </div>


    <div class="form-group">
        <h4 class="text-info"><fmt:message key="application.accepted"/></h4>
        <table class="table" border="2" cellspacing="0" cellpadding="2">
            <thead>
            <tr>
                <th><fmt:message key="application.applicant"/></th>
                <th><fmt:message key="application.overall"/></th>
                <th><fmt:message key="application.date"/></th>
                <th><fmt:message key="application.description"/></th>
            </tr>
            </thead>
            <c:forEach var="app" items="${applications}">
                <c:if test="${app.isAccept()}">
                    <tr>
                        <td>${app.user.name}</td>
                        <td>${app.overall}</td>
                        <td>${app.date}</td>
                        <td>${app.description}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>

    <div class="form-group">
        <form name="form_process_applications">
            <h4 class="text-danger"><fmt:message key="application.process"/></h4>
            <table class="table" border="2" cellspacing="0" cellpadding="2">
                <thead>
                <tr>
                    <th><fmt:message key="application.applicant"/></th>
                    <th><fmt:message key="application.overall"/></th>
                    <th><fmt:message key="application.date"/></th>
                    <th><fmt:message key="application.description"/></th>
                    <th><fmt:message key="faculty.commission.watch"/></th>
                </tr>
                </thead>
                <c:forEach var="app" items="${applications}">
                    <c:if test="${app.isProcess()}">
                        <tr>
                            <td>${app.user.name}</td>
                            <td>${app.overall}</td>
                            <td>${app.date}</td>
                            <td>${app.description}</td>
                            <td>
                                <form action="/Controller" method="post">
                                    <input type="hidden" name="command" value="showApplication">
                                    <input type="hidden" name="additional" value="${app.id}">
                                    <input type="submit" class="btn"
                                           value="<fmt:message key="faculty.commission.show_app"/>">
                                </form>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </form>
    </div>

    <div class="form-group">
        <form name="back_faculties" action="/Controller" method="post">
            <input type="hidden" name="command" value="showFaculties">
            <button class="btn" type="submit"><fmt:message key="faculty.button.back"/></button>
        </form>
        <form class="container" action="/Controller" method="post">
            <input type="hidden" name="command" value="showAdmin">
            <button class="btn" type="submit"><fmt:message key="button.back_to_profile"/></button>
        </form>
    </div>

</div>
</body>
</html>

