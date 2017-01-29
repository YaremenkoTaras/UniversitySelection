<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 18.01.2017
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title><fmt:message key="addmark.title"/> <fmt:message key="main.title"/></title>
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>
<div class="container col-xs-8">

    <div class="form-group">
        <h4><fmt:message key="applicant.marks"/></h4>
    </div>

    <div class="form-group col-xs-4">
        <table class="table-info table" border="2" cellspacing="0" cellpadding="2">
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
    </div>
    <div class="clearfix"></div>
    <div class="form-group">
        <h4>${user.name} <fmt:message key="addmark.head"/> :</h4>
    </div>

    <br>
    <div class="form-group">
        <form action="/Controller" method="post">
            <div class="col-xs-2">
                <select class="c-select" name="subject" size="1" required>
                    <c:forEach var="subj" items="${subjects}">
                        <option value="${subj.id}">${subj.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-xs-2">
                <input class="form-control validate" placeholder="100" type="number" min="1" max="200"
                       value="" name="mark" id="form2" required pattern="[0-9]{3}">
                </input>
            </div>
            <div class="col-xs-4">
                <input type="hidden" name="command" value="addMark"/>
                <input type="hidden" name="additional" value="add"/>
                <button name="submit" id="add_submit_btn" class="btn"><fmt:message key="addmark.add"/></button>

            </div>
        </form>
    </div>

    <div class="clearfix"></div>
    <br><br>
    <div class="form-group">
        <form method="POST" action="Controller">
            <input type="hidden" name="command" value="showApplicant"/>
            <button class="btn" name="submit"><fmt:message key="button.back_to_profile"/></button>
        </form>
    </div>
</div>

</body>
</html>
