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
</head>
<body>

<%@include file="/WEB-INF/languages_locale.jsp"%>

<div class="container header_text">
    <h1><fmt:message key="login.u_selection"/></h1>
</div>

<div class="modal fade" id="login_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <p><fmt:message key="login.sign_in_description"/></p>
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="login"/>
                    <div class="md-form">
                        <input type="email" name="email" value="" id="form9" class="form-control validate"
                               required>
                        <label for="form9"><fmt:message key="login.email"/>:</label>
                    </div>
                    <div class="md-form">
                        <input type="password" name="password" value="" id="form10" class="form-control validate"
                               required>
                        <label for="form10"><fmt:message key="login.password"/>:</label>
                    </div>
                    <button name="signIn" value="signIn" class="btn"><fmt:message key="login.sign_in"/></button>
                </form>
            </div>
        </div>
    </div>

    <br>
    <form method="POST" action="Controller">
        <input type="hidden" name="command" value="register"/>
        <input type="hidden" name="additional" value="user">
        <input type="submit" value="Go to Registation">
    </form>

</div>

</body>
</html>
