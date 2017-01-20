<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 13.01.2017
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${content.getString("change.user")} ${content.getString("main.title")}</title>
</head>
<body>

<div class="container">
    <form action="Controller" method="POST">
        <h3>${content.getString("change.user")} : ${user.name}</h3>

        <div class="row">

            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" type="text" placeholder="Address" value="${address}"
                       name="address" id="form3" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z\s0-9]+$">
                </input
                <label class="active" for="form3">${content.getString("registration.address")}</label>
            </div>

            <div class="md-form col-xs-4">
                <input class="col-xs-2 form-control validate" placeholder="+xx(xxx)xxx-xx-xx" type="text"
                       value="${phone}" name="phone" id="form7" required
                       pattern="^((8|\+7|\+38)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$">
                </input>
                <label class="active" for="form7">${content.getString("registration.phone")}</label>
            </div>

            <div class="md-form col-xs-4">
                <input class="col-xs-2 form-control validate" placeholder="user@email.com" type="email" value="${email}"
                       name="email" id="form8" required> </input>
                <label class="active" for="form8">${content.getString("registration.email")}</label>
                <label class="active" for="form8">${description}</label>

            </div>
        </div>

        <div class="row">
            <h4>${content.getString("change.disc")}</h4>
            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" type="password" value="${password_one}"
                       name="password_one" id="form9" required> </input>
                <label class="active" for="form9">${content.getString("change.old_password")}</label>
            </div>
            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" type="password" value="${password_two}"
                       name="password_two" id="form10" required> </input>
                <label class="active" for="form10">${content.getString("change.new_password")}</label>
            </div>
        </div>
        <input type="hidden" name="command" value="changeUserInfo"/>
        <input type="hidden" name="additional" value="submit">
        <button name="submit" id="reg_submit_btn"
                class="btn col-xs-4">${content.getString("change.button.confirm")}</button>
    </form>
</div>
<br>
<form class="container" action="/Controller" method="post">
    <c:if test="${user.roleID == 2}">
        <input type="hidden" name="command" value="showApplicant">
    </c:if>
    <c:if test="${user.roleID == 1}">
        <input type="hidden" name="command" value="showAdmin">
    </c:if>
    <button type="submit">${content.getString("button.back_to_profile")}</button>
</form>

</body>
</html>
