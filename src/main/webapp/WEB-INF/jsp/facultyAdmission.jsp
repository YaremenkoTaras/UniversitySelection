<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 13.01.2017
  Time: 12:49
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
    </div>

    <div class="form-group">
        <h4 class="text-info"><fmt:message key="faculty.total"/>: ${faculty.numberOfStudent}</h4>
    </div>


    <div class="form-group col-xs-4" name="div_faculty_subjects">
        <table class="table table-info" border="2" cellspacing="0" cellpadding="0">
            <thead>
            <tr>
                <th><fmt:message key="faculty.subject"/></th>
                <th><fmt:message key="faculty.min_mark"/></th>
            </tr>
            </thead>
            <c:forEach var="fsub" items="${facultysubjects}">
                <tr>
                    <td>${fsub.subject.name}</td>
                    <td>${fsub.minMark}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="clearfix"></div>

    <div class="form-group">
        <form name="form_add_application">
            <c:if test="${userapp == null && missingmark == null && lowmark == null}">
                <div class="container">
                    <form action="/Controller" method="post">
                        <input type="hidden" name="command" value="addApplication"/>
                        <input type="hidden" name="id" value="${faculty.id}"/>
                        <input type="text" name="description" value="${description}"
                               placeholder="Few words about yourself"
                               id="form1" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z\s]+$">
                        <input type="submit" class="btn" value="<fmt:message key="faculty.add_application"/>">
                    </form>
                </div>
            </c:if>

            <c:if test="${missingmark != null}">
                <h4 class="text-danger"><fmt:message key="faculty.missmark"/></h4>
            </c:if>

            <c:if test="${lowmark != null}">
                <h4 class="text-danger"><fmt:message key="faculty.lowmark"/></h4>
            </c:if>

            <c:if test="${userapp != null}">
                <h3><fmt:message key="faculty.user_application"/> ${userapp.date}</h3>
            </c:if>
        </form>
    </div>
    <div class="form-group" name="form_accepted_applications">
        <label for="acceptApp">
            <h4 class="text-info"><fmt:message key="application.accepted"/></h4>
        </label>
        <table class="table" id="acceptApp" border="2" cellspacing="0" cellpadding="2">
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

    <div class="form-group" name="div_process_applications">
        <label for="processApp">
            <h4 class="text-danger"><fmt:message key="application.process"/></h4>
        </label>
        <table class="table" border="2" cellspacing="0" cellpadding="2" id="processApp">
            <thead>
            <tr>
                <th><fmt:message key="application.applicant"/></th>
                <th><fmt:message key="application.overall"/></th>
                <th><fmt:message key="application.date"/></th>
                <th><fmt:message key="application.description"/></th>
            </tr>
            </thead>
            <c:forEach var="app" items="${applications}">
                <c:if test="${app.isProcess()}">
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
        <form name="back_faculties" action="/Controller" method="post">
            <input type="hidden" name="command" value="showFaculties">
            <button class="btn" type="submit"><fmt:message key="faculty.button.back"/></button>
        </form>
    </div>
    <div class="form-group">
        <form class="container" action="/Controller" method="post">
            <input type="hidden" name="command" value="showApplicant">
            <button class="btn" type="submit"><fmt:message key="button.back_to_profile"/></button>
        </form>
    </div>
</div>
</body>
</html>
