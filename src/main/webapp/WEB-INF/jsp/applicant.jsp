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
    <title><fmt:message key="applicant.applicant"/>: ${user.name} <fmt:message key="main.title"/></title>
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>
<div class="col-xs-8 ">

    <div class="container header_text">
        <h3><fmt:message key="applicant.profile"/>: ${user.name}</h3>
    </div>
    <div class="form-group">
        <form method="POST" action="Controller">
            <input type="hidden" name="command" value="changeUserInfo"/>
            <input type="hidden" name="additional" value="edit">
            <input type="submit" class="btn" value="<fmt:message key="button.edit"/>">
        </form>
    </div>

    <br>
    <div class="form-group col-xs-4">
        <label for="tableMarks">
            <h4><fmt:message key="applicant.marks"/></h4>
        </label>
        <table class="table table-info" border="2" cellspacing="0" cellpadding="1" id="tableMarks">
            <thead>
            <tr>
                <th><fmt:message key="applicant.subject"/></th>
                <th><fmt:message key="applicant.mark"/></th>
            </tr>
            </thead>
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
            <input type="submit" class="btn" value="<fmt:message key="applicant.button.add_mark"/>"/>
        </form>
    </div>
    <div class="clearfix"></div>
    <br>
    <div class="form-group">
        <form method="POST" action="Controller">
            <input type="hidden" name="command" value="showFaculties"/>
            <input type="submit" class="btn" value="<fmt:message key="applicant.button.add"/>">
        </form>
        <label for="tableApp"><h4><fmt:message key="applicant.applications_list"/></h4></label>
        <table class="table" border="2" cellspacing="1" cellpadding="4" id="tableApp">
            <thead>
            <tr>
                <th><fmt:message key="application.faculty"/></th>
                <th><fmt:message key="application.overall"/></th>
                <th><fmt:message key="application.date"/></th>
                <th><fmt:message key="application.status"/></th>
                <th><fmt:message key="application.description"/></th>
            </tr>
            </thead>
            <c:forEach var="app" items="${applications}">
                <tr>
                    <td>${app.faculty.name}</td>
                    <td>${app.overall}</td>
                    <td>${app.date}</td>
                    <td>${app.status}</td>
                    <td>${app.description}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="form-group">
        <form method="post" action="/Controller">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" class="btn" value="<fmt:message key="button.logout"/>">
        </form>
    </div>
</div>
</body>
</html>
