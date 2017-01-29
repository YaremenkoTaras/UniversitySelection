<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 13.01.2017
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title><fmt:message key="admin.head"/> : ${user.name} <fmt:message key="main.title"/></title>
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>

<body>
<div class="col-xs-8 container center-block">
    <div class="form-group">
        <h3><fmt:message key="admin.header"/> - ${user.name}</h3>
    </div>

    <div class="form-group col-xs-4">
        <form method="POST" action="Controller">
            <input type="hidden" name="command" value="changeUserInfo"/>
            <input type="hidden" name="additional" value="edit">
            <input type="submit" class="btn"  value="<fmt:message key="button.edit"/>">
        </form>
    </div>
    <div class="form-group col-xs-4">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="register">
            <input type="hidden" name="additional" value="admin">
            <button  class="btn" type="submit"><fmt:message key="admin.button.create_admin"/></button>
        </form>
    </div>
    <div class="clearfix"></div>

    <br><br><br><br>

    <div class="form-group">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="showFaculties">
            <button type="submit" class="btn" ><fmt:message key="admin.button.admissions"/></button>
        </form>
    </div>
    <br>
    <br>
    <div class="form-group">
        <form method="post" action="/Controller">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" class="btn" value="<fmt:message key="button.logout"/>">
        </form>
    </div>
</div>
</body>
</html>
