<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${content.getString("registration")}</title>

</head>
<header>
    <div class="container">
        <h3>${content.getString("registration")}</h3>
    </div>
</header>
<body class="body">
<div class="container">
    <form action="Controller" method="POST">

        <h4>${content.getString("registration.description")}</h4>
        <div class="row">
            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" placeholder="Doe" value="${lastName}" type="text"
                       name="lastName" id="form1" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$">

                </input>
                <label class="active" for="form1">${content.getString("registration.last_name")}</label>
            </div>
            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" type="text" placeholder="John" value="${firstName}"
                       name="firstName" id="form2" required pattern="^[а-яА-ЯёЁіІїЇєЄa-zA-Z]+$">

                </input>
                <label class="active" for="form2">${content.getString("registration.frist_name")}</label>
            </div>
        </div>


        <h4>${content.getString("registration.date_of_birth")}: </h4>
        <div class="row">
            <div class="col-xs-4 md-form">
                <input class="col-xs-1 form-control validate" placeholder="1999" type="number" min="1900" max="2016"
                       step="1" value="${year}" name="year" id="form3" required>

                </input>
                <label class="active" for="form3">${content.getString("registration.year")}</label>
            </div>

            <div class="col-xs-4 descripted_select">
                <p class="description">${content.getString("registration.month")}</p>
                <div>
                    <select size="12"  name="month" required>
                        <option selected value="january">January</option>
                        <option value="feb">feb</option>
                        <option value="mart">mart</option>
                        <option value="april">april</option>
                        <option value="may">may</option>
                        <option value="june">june</option>
                        <option value="july">july</option>
                        <option value="august">august</option>
                        <option value="sep">sep</option>
                        <option value="oct">oct</option>
                        <option value="nov">nov</option>
                        <option value="dec">dec</option>
                    </select>
                </div>
            </div>

            <div class="col-xs-4  md-form">
                <input class="col-xs-2 form-control validate" placeholder="1" type="number" min="1" max="31"
                       value="${day}" name="day" id="form4" required>

                </input>
                <label class="active" for="form4">${content.getString("registration.day")}</label>
            </div>
        </div>

        <div class="col-xs-4 descripted_select">
            <p class="description">${content.getString("registration.sex")}</p>
            <div>
                <select size="2"  name="sex" required>
                    <option selected value="M">Male</option>
                    <option value="F">Female</option>>
                </select>
            </div>
        </div>

        <<br>

        <div class="row">
            <div class="md-form col-xs-4">
                <input class="col-xs-2 form-control validate" placeholder="+xx(xxx)xxx-xx-xx" type="text"
                       value="${phone}" name="phone" id="form5" required pattern="^((8|\+7|\+38)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$">
                </input>
                <label class="active" for="form5">${content.getString("registration.phone")}</label>
            </div>
            <div class="md-form col-xs-4">
                <input class="col-xs-2 form-control validate" placeholder="user@email.com" type="email" value="${email}" name="email" id="form6" required> </input>
                <label class="active" for="form6">${content.getString("registration.email")}</label>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" type="password" value="${password_one}" name="password_one" id="form7" required> </input>
                <label class="active" for="form7">${content.getString("registration.password")}</label>
            </div>
            <div class="col-xs-6 md-form">
                <input class="col-xs-2 form-control validate" type="password" value="${password_two}" name="password_two" id="form8" required> </input>
                <label class="active" for="form8">${content.getString("registration.repeat_password")}</label>
            </div>
        </div>

        <input type="hidden" name="command" value="register"/>
        <input type="submit" value="Create account">

    </form>
</div>

</body>
</html>