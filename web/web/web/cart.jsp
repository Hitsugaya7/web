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
    <link rel="stylesheet" href="style.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:700" rel="stylesheet">
    <script src="js/cabinet_worker.js"></script>
    <script src="js/cart_worker.js"></script>
    <script src="./js/login_worker.js"></script>


    <title><fmt:message key="title"/> | <fmt:message key="cart"/></title>
</head>

<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div id='container'>
            <c:if test="${empty sessionScope.username}">
                <c:redirect url="/login"/>
            </c:if>

            <c:if test="${not empty sessionScope.username}">
                <c:if test="${empty sessionScope.purchases.purchases}">
                    <p><fmt:message key="empty_cart"/></p>
                </c:if>

                <c:if test="${not empty sessionScope.purchases.purchases}">
                    <table id="cart_table">
                        <thead>
                            <td class="cart_img"></td><td class="about"><fmt:message key="full_discr"/></td><td class="count"><fmt:message key="count"/></td><td class="price"><fmt:message key="price"/></td><td width="30px"></td>
                        </thead>
                        <c:forEach var="purchase" items="${sessionScope.purchases.purchases}">
                            <tr>
                                    <td class="cart_img"><img src="${purchase.imgurl}"></td>
                                    <td class="about" style="text-align: left"><b>${purchase.name}</b> - ${purchase.discription}</td>
                                    <td class="count">${purchase.count}</td>
                                    <td class="price">${purchase.totalcost} $</td>
                                    <td class="del"><a onclick="del(${purchase.id})"><img style="width: 30px; margin: 5px" src="./image/x.png"></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <span id = "result"><fmt:message key="result"/>: ${sessionScope.purchases.totalCost} $</span>


                    <span class="discr order center">

                        <a href="./order"><fmt:message key="Order"/></a>

                    </span>
                </c:if>
        </c:if>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
