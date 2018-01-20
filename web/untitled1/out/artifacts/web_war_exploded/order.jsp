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
    <link rel="stylesheet" href="./style.css" type="text/css">
    <link rel="stylesheet" href="./login_form.css" type="text/css">

    <script src="./js/map_worker.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:700" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

    <title><fmt:message key="title"/> | <fmt:message key="cart"/></title>
</head>

<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div id='container' style="text-align: center">
        <form method="post" action="./order" class="order_form">
            <div id="order-settings">
                <fmt:message key="DeliveryType" />
                <span><div style="display: inline-block">
                    <input id="type-choice" name="type-choice" type="checkbox" onchange="changeType()">
                    <label for="type-choice">Toggle</label>
                </div></span>

                <div id="shop">
                    <fmt:message key="ShopChoice" /><br>
                    <select name="shop-choice" id="shop-choice" onchange="changeShop()">
                        <option value="1">Первая, ул.Торжковская 15</option>
                        <option value="2" selected>Вторая , Усадьба Ланских</option>
                        <option value="3">Третья , Новочеркасская 52</option>
                        <%--<option value="4">Четвертая , ул.Савушкина 1</option>--%>
                    </select>
                </div>
                <div id="courier" style="display:none">
                    <fmt:message key="Addresse" /><br>
                    <input class="a_input" name="addressee" id="addressee" type="text">
                </div>
                <input class="button" type="submit" value="<fmt:message key="HandleOrder" />">
            </div>
        </form>
        <div id="map"></div>
        <script src="https://api-maps.yandex.ru/2.1/?load=package.full&lang=ru_RU&onload=initMap"></script>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
