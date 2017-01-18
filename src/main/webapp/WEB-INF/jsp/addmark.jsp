<%--
  Created by IntelliJ IDEA.
  User: Tapac
  Date: 18.01.2017
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${content.getString("addmark.title")}</title>
</head>
<body>
<h3>${user.name} ${content.getString("addmark.head")} :</h3>
<form>
    ${content.getString("applicant.marks")}
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>Subject</td>
            <td>Mark</td>
        </tr>
        <c:forEach var="value" items="${marks}">
            <tr>
                <td>${value.subjectID}</td>
                <td>${value.mark}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<br>
<form>
    <select name="subject" size="1" required>
        <c:forEach var="subj" items="${subjects}">
            <option value="${subj.id}">${subj.name}</option>
        </c:forEach>
    </select>

    <input class="col-xs-2 form-control validate" placeholder="100" type="number" min="1" max="200"
           value="" name="mark" id="form2" required pattern="[0-9]{3}">
    </input>
    <input type="hidden" name="command" value="addMark"/>
    <input type="hidden" name="add" value="true"/>
    <button name="submit" id="add_submit_btn" class="btn col-xs-4">${content.getString("addmark.add")}</button>
</form>
<br>
<form method="POST" action="Controller">
    <input type="hidden" name="command" value="showApplicant"/>
    <button name="submit">${content.getString("applicant.button.back")}</button>
</form>

</body>
</html>
