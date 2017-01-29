<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 13.01.2017
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>All faculties <fmt:message key="main.title"/></title>
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>
<header>
    <div class="container">
        <h3><fmt:message key="faculties.header"/></h3>
    </div>
</header>
<body>
<div class="container col-xs-6">
    <div class="form-group">
        <table class="table">
            <c:forEach var="faculty" items="${faculties}">
                <tr>
                    <td>
                        <a class="btn-link" href="Controller?command=showFaculty&id=${faculty.id}">
                            <h4> ${faculty.name} </h4>
                        </a>
                    </td>
                </tr>
            </c:forEach>


        </table>

    </div>

    <br>
    <div class="form-group">
        <form action="/Controller" method="post">
            <c:if test="${user.roleID == 2}">
                <input type="hidden" name="command" value="showApplicant">
            </c:if>
            <c:if test="${user.roleID == 1}">
                <input type="hidden" name="command" value="showAdmin">
            </c:if>
            <button class="btn" type="submit"><fmt:message key="button.back_to_profile"/></button>
        </form>
    </div>

</div>

</body>
</html>
