<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="login.u_selection"/></title>
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>

<div class="col-xs-6 ">
    <%@include file="/WEB-INF/jsp/languages_locale.jsp" %>
    <div class="container header_text">
        <h1><fmt:message key="login.u_selection"/></h1>
    </div>

    <form class="container" action="Controller" method="POST">
        <div class="text-info">
            <fmt:message key="login.sign_in_description"/>
        </div>

        <div class="md-form">
            <label for="form9"><fmt:message key="login.email"/>:</label>
            <input type="email" name="email" value="" id="form9" class="form-control validate"
                   required>
        </div>
        <div class="md-form">
            <label for="form10"><fmt:message key="login.password"/>:</label>
            <input type="password" name="password" value="" id="form10" class="form-control validate"
                   required>
        </div>
        <input type="hidden" name="command" value="login"/>
        <button name="signIn" class="btn"><fmt:message key="login.sign_in"/></button>

    </form>
    <br>
    <form class="container col-xs-4" method="POST" action="Controller">
        <input type="hidden" name="command" value="register"/>
        <input type="hidden" name="additional" value="user">
        <button class="btn"><fmt:message key="registration"/></button>
    </form>
</div>
</body>
</html>
