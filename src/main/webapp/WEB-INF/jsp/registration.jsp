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
        <c:if test="${register != null}">
            <h2>${register}</h2>
        </c:if>
    </div>
</header>
<body class="body">
<div class="container">
    <form action="Controller" method="POST">

        <h4><fmt:message key="registration.description"/></h4>
        <div class="row">
            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" placeholder="Doe" value="${lastName}" type="text"
                       name="lastName" id="form1" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$">

                </input>
                <label class="active" for="form1"><fmt:message key="registration.last_name"/></label>
            </div>
            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" type="text" placeholder="John" value="${firstName}"
                       name="firstName" id="form2" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$">

                </input>
                <label class="active" for="form2"><fmt:message key="registration.first_name"/></label>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" type="text" placeholder="Address" value="${address}"
                       name="address" id="form9" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z\s0-9]+$">
                </input>
                <label class="active" for="form9"><fmt:message key="registration.address"/></label>
            </div>
            <div class="md-form col-xs-4">
                <input class="col-xs-2 form-control validate" placeholder="+xx(xxx)xxx-xx-xx" type="text"
                       value="${phone}" name="phone" id="form5" required
                       pattern="^((8|\+7|\+38)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$">
                </input>
                <label class="active" for="form5"><fmt:message key="registration.phone"/></label>
            </div>
            <div class="col-xs-2 md-form">
                <div class="row">
                    <select class="c-select" size="1" name="sex" required id="form11">
                        <option selected value="M">Male</option>
                        <option value="F">Female</option>
                    </select>
                </div>
                <div class="row">
                    <label class="active" for="form11"><fmt:message key="registration.sex"/></label>
                </div>
            </div>
        </div>

        <div class="row">

            <h4><fmt:message key="registration.date_of_birth"/>: </h4>
        </div>


        <div class="col-xs-3">
            <div class="row">
                <input class="col-xs-1 form-control validate" placeholder="1999" type="number" min="1989" max="2016"
                       step="1" value="${year}" name="year" id="form3" required>
                </input>
                <label class="active" for="form3"><fmt:message key="registration.year"/></label>
            </div>

            <div class="row">
                <div class="col-xs-3">
                    <div class="row">
                        <select class="c-select" size="1" name="month" id="form10" required>
                            <option selected value="01">January</option>
                            <option value="02">February</option>
                            <option value="03">March</option>
                            <option value="04">April</option>
                            <option value="05">May</option>
                            <option value="06">June</option>
                            <option value="07">July</option>
                            <option value="08">August</option>
                            <option value="09">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                    </div>
                    <div class="row">
                        <label class="active" for="form10"><fmt:message key="registration.month"/></label>
                    </div>
                </div>

            </div>

            <div class="row">
                <input class="col-xs-2 form-control validate" placeholder="1" type="number" min="1" max="31"
                       value="${day}" name="day" id="form4" required>
                </input>
                <label class="active" for="form4"><fmt:message key="registration.day"/></label>
            </div>
        </div>

        <div class="col-xs-6">
            <div class="row">
                <div class="md-form col-xs-6">
                    <input class="col-xs-2 form-control validate" placeholder="user@email.com" type="email"
                           value="${email}"
                           name="email" id="form6" required> </input>
                    <label class="active" for="form6"><fmt:message key="registration.email"/></label>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6 md-form">
                    <input class="col-xs-2 form-control validate" type="password" value="${password_one}"
                           name="password_one" id="form7" required> </input>
                    <label class="active" for="form7"><fmt:message key="registration.password"/></label>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6 md-form">
                    <input class="col-xs-2 form-control validate" type="password" value="${password_two}"
                           name="password_two" id="form8" required> </input>
                    <label class="active" for="form8"><fmt:message key="registration.repeat_password"/></label>
                </div>
            </div>
        </div>
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="command" value="registration"/>
        <button name="submit" id="reg_submit_btn"
                class="btn col-xs-4"><fmt:message key="registration.submit_btn"/></button>

    </form>
</div>

</body>
</html>
