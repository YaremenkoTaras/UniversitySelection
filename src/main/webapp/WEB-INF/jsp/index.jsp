<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<c:if test="${user == null}">
    <jsp:forward page="/WEB-INF/jsp/login.jsp"></jsp:forward>
</c:if>
<c:if test="${user.roleID == 1  }">
    <jsp:forward page="/WEB-INF/jsp/admin.jsp"></jsp:forward>
</c:if>
<c:if test="${user.roleID == 2  }">
    <jsp:forward page="/WEB-INF/jsp/applicant.jsp"></jsp:forward>
</c:if>

</body>
</html>
