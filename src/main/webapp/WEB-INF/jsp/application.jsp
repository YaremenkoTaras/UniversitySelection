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
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>
<div class="container col-xs-8">
    <div class="form-group">
        <h3><fmt:message key="application.title"/> : ${applicant.name}</h3>
    </div>


    <div class="form-group col-xs-6">
        <table id="tableApp" class="table" border="2" cellspacing="0" cellpadding="2">
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

    <div class="clearfix"></div>

    <div class="form-group row">

        <div class="col-xs-4">
            <form action="/Controller" method="post">
                <input type="hidden" name="command" value="processApplication">
                <input type="hidden" name="additional" value="accept">
                <input type="hidden" name="userapp" value="${userapp.id}">
                <input type="hidden" name="id" value="${userapp.faculty.id}">
                <button class="btn" type="submit"><fmt:message key="application.button.accept"/></button>
            </form>
        </div>
        <div class="col-xs-4">
            <form action="/Controller" method="post">
                <input type="hidden" name="command" value="processApplication">
                <input type="hidden" name="additional" value="decline">
                <input type="hidden" name="id" value="${userapp.faculty.id}">
                <input type="hidden" name="userapp" value="${userapp.id}">
                <button class="btn" type="submit"><fmt:message key="application.button.decline"/></button>
            </form>
        </div>
    </div>

    <div class="clearfix"></div>

    <div class="form-group">
        <h4><fmt:message key="application.all_applicant_app"/></h4>
        <table class="table" border="2" cellspacing="0" cellpadding="2">
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
    </div>
    <br>
    <div class="form-group">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="showFaculty">
            <input type="hidden" name="id" value="${userapp.faculty.id}">
            <button class="btn" type="submit"><fmt:message key="application.button.back"/></button>
        </form>
    </div>

</div>
</body>
</html>
