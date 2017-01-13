<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${content.getString("login.ad_commission")}</title>
</head>
<body>
<div class="container header_text">
    <h1>${content.getString("login.ad_commission")}</h1>
</div>

<div class="modal fade" id="login_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <p>${content.getString("login.sign_in_description")}</p>
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="login"/>
                    <div class="md-form">
                        <input type="email" name="email" value="" id="form9" class="form-control validate" name="email"
                               required>
                        <label for="form9">${content.getString("email")}:</label>
                    </div>
                    <div class="md-form">
                        <input type="password" name="password" value="" id="form10" class="form-control validate"
                               required>
                        <label for="form10">${content.getString("password")}:</label>
                    </div>
                    <button name="signIn" value="signIn" class="btn">${content.getString("login.sign_in")}</button>
                </form>
            </div>
        </div>
    </div>

    <
    <form method="LINK" action= /registration>
        <input type="submit" value=" Go to Registation  ">
    </form>

</div>

</body>
</html>