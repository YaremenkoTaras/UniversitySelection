<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="registration"/> <fmt:message key="main.title"/></title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>
<header>
    <div class="container">
        <h3><fmt:message key="registration"/></h3>
    </div>
</header>
<body>

<br>
<form class="col-xs-8 container" action="Controller" method="POST">

    <c:if test="${register != null}">
        <h2><fmt:message key="registration.register.message"/> </h2>
    </c:if>

    <h4><fmt:message key="registration.description"/></h4>

    <div class="form-group row">
        <div class="col-md-1">
            <label for="form1"><fmt:message key="registration.last_name"/></label>
        </div>
        <div class="col-md-6">
            <input class="form-control validate" placeholder="Doe" value="${lastName}" type="text"
                   name="lastName" id="form1" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$">
            </input>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-md-1">
            <label for="form2"><fmt:message key="registration.first_name"/></label>
        </div>
        <div class="col-md-6">
            <input class="form-control validate" type="text" placeholder="John" value="${firstName}"
                   name="firstName" id="form2" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$">
            </input>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-md-1">
            <label for="form9"><fmt:message key="registration.address"/></label>
        </div>
        <div class="col-md-6">
            <textarea onkeydown="if(event.keyCode == 13){{ return false;}}" class="form-control validate" type="text"
                      placeholder="Address"name="address"
                      id="form9" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z\s0-9]+$">${address}</textarea>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-md-1">
            <label class="active" for="form5"><fmt:message key="registration.phone"/></label>
        </div>
        <div class="col-md-6">
            <input class="form-control validate" placeholder="+xx(xxx)xxx-xx-xx" type="text"
                   value="${phone}" name="phone" id="form5" required
                   pattern="^((8|\+7|\+38)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$">
            </input>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-md-1">
            <label class="active" for="form11"><fmt:message key="registration.sex"/></label>
        </div>
        <div class="col-md-4">
            <select class="c-select" size="1" name="sex" required id="form11">
                <c:if test="${sex eq 'M'}">
                    <option selected value="${sex}"><fmt:message key="registration.sex.male"/></option>
                </c:if>
                <c:if test="${sex eq 'F'}">
                    <option selected value="${sex}"><fmt:message key="registration.sex.female"/></option>
                </c:if>
                <option value="M"><fmt:message key="registration.sex.male"/></option>
                <option value="F"><fmt:message key="registration.sex.female"/></option>
            </select>
        </div>
    </div>


    <h5><fmt:message key="registration.date_of_birth"/></h5>
    <div class="form-group row">
        <div class="col-md-1">
            <label class="active" for="form3"><fmt:message key="registration.year"/></label>
        </div>
        <div class="col-md-2">
            <input class="form-control validate" placeholder="1999" type="number" min="1989" max="2016"
                   step="1" value="${year}" name="year" id="form3" required>
            </input>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-1">
            <label class="active" for="form10"><fmt:message key="registration.month"/></label>
        </div>
        <div class="col-md-2">
            <select class="c-select" size="1" name="month" id="form10" required>
                <option value="01"><fmt:message key="registration.month.jan"/></option>
                <option value="02"><fmt:message key="registration.month.feb"/></option>
                <option value="03"><fmt:message key="registration.month.mar"/></option>
                <option value="04"><fmt:message key="registration.month.apr"/></option>
                <option value="05"><fmt:message key="registration.month.may"/></option>
                <option value="06"><fmt:message key="registration.month.jun"/></option>
                <option value="07"><fmt:message key="registration.month.jul"/></option>
                <option value="08"><fmt:message key="registration.month.aug"/></option>
                <option value="09"><fmt:message key="registration.month.sep"/></option>
                <option value="10"><fmt:message key="registration.month.oct"/></option>
                <option value="11"><fmt:message key="registration.month.nov"/></option>
                <option value="12"><fmt:message key="registration.month.dec"/></option>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-md-1">
            <label class="active" for="form4"><fmt:message key="registration.day"/></label>
        </div>
        <div class="col-md-2">
            <input class="form-control validate" placeholder="1" type="number" min="1" max="31"
                   value="${day}" name="day" id="form4" required>
            </input>
        </div>
    </div>

    <div class="clearfix"></div>


    <div class="form-group row">
        <div class="col-md-1">
            <label class="active" for="form6"><fmt:message key="registration.email"/></label>
        </div>
        <div class="col-md-6">
            <input class="form-control validate" placeholder="user@email.com" type="email"
                   value="${email}"
                   name="email" id="form6" required> </input>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-1">
            <label class="active" for="form7"><fmt:message key="registration.password"/></label>
        </div>
        <div class="col-md-6">
            <input class="form-control validate" type="password" value="${password_one}"
                   name="password_one" id="form7" required> </input>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-1">
            <label class="active" for="form8"><fmt:message key="registration.repeat_password"/></label>
        </div>
        <div class="col-md-6">
            <input class="form-control validate" type="password" value="${password_two}"
                   name="password_two" id="form8" required> </input>
        </div>
    </div>


    <div class="form-group">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="command" value="registration"/>
        <button name="submit" id="reg_submit_btn"
                class="btn col-xs-4"><fmt:message key="registration.submit_btn"/></button>
    </div>

</form>
<br><br>
</body>
</html>
