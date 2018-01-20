<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@page isELIgnored="false"%>

<c:if test="${empty sessionScope.locale}">
    <fmt:setLocale value="en"/>
</c:if>

<c:if test="${sessionScope.locale eq 'ru'}">
    <fmt:setLocale value="ru"/>
</c:if>

<c:if test="${sessionScope.locale eq 'en'}">
    <fmt:setLocale value="en"/>
</c:if>

<c:if test="${sessionScope.locale eq 'tj'}">
    <fmt:setLocale value="tj"/>
</c:if>
<fmt:setBundle basename="/res"/>

<html>
<head>
    <link href="../style.css" type="text/css" rel = "stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:700" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="./js/login_worker.js"></script>
    <script src="./js/cabinet_worker.js"></script>
    <script src="./js/cart_worker.js"></script>
    <title><fmt:message key="title"/></title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id='container' class = "center">
        <div id="boxForm">
            <h2 id="title"><fmt:message key="need_login"/></h2>
            <form action="." method="post" name="loginForm" class="form-signin">
                <input type="text" name="j_username" placeholder="Name" class="text" size="20"/><br>
                <input type="password" name="j_password" placeholder="Passoword" class="text" size="20"/><br>
                <button class="button" type="submit">OK</button>
            </form>
        </div>
    </div>
    <jsp:include page="../footer.jsp"/>
</div>
</body>
</html>