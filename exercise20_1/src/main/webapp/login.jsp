<%@ page import="java.util.Enumeration" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!doctype html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8" />
    </head>
    <body>
        <h1>Login Page</h1>
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
            <p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
        </c:if>
        <form method="POST" action="<c:url value='/login' />" >
            User: <input name="username" value="<c:if test="${not empty LAST_USERNAME}">${sessionScope["LAST_USERNAME"]}</c:if>"/> <br/>
            Pass: <input type="password" name="password" /> <br/>
            <input type="submit"  value="Login" />
        </form>
    </body>
</html>