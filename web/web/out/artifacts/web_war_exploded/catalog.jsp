<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<html>
<head>

    <%
        String lang = request.getParameter("lang");
        Cookie[] cookies = request.getCookies();
        String filter_param = "0";

       if (cookies != null) {
            for (Cookie c : cookies) {
                if ("filter".equals(c.getName())) {
                    filter_param = c.getValue();
                }
            }
        }

        if(lang == null) {
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("lang".equals(c.getName()))
                        lang = c.getValue();
                }
            }else{
                lang = getServletConfig().getInitParameter("lang");
            }
        }
        response.addCookie(new Cookie("lang",lang));

        Locale locale;
        if ("en".equals(lang)) {
            locale = new Locale("en", "GB");
        } else if ("tj".equals(lang)) {
            locale = new Locale("tj", "TJ");
        } else if ("ru".equals(lang)) {
            locale = new Locale("ru", "RU");
        }else {
            locale = Locale.getDefault();
        }
        ResourceBundle res = ResourceBundle.getBundle("res", locale);
    %>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:700" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <jsp:useBean id="sneakersBean" class="Models.Sneakers" scope="session"/>
    <script src="for_sort.js"></script>
    <script src="./js/login_worker.js"></script>
    <script src="./js/cabinet_worker.js"></script>
    <script src="./js/cart_worker.js"></script>
    <title>my design</title>
</head>
<body onload="sort(<%=filter_param%>)">
    <jsp:include page="header.jsp"/>
    <div id='container' class="center for-line">
        <span id="sort">
            <a onclick="sort(0)" id="a0">*</a> | <a onclick="sort(1)" id="a1"><%=res.getString("man")%></a> | <a onclick="sort(2)" id="a2"><%=res.getString("woman")%></a> | <a onclick="sort(3)" id="a3"><%=res.getString("boy")%></a>| <a onclick="sort(4)" id="a4"><%=res.getString("girl")%></a>
        </span>

        <%
            String img;
            for(int i = 1; i < 5; i++){
                img = "./image/items/" + Integer.toString(i) + ".jpg";
        %>
        <jsp:setProperty name="sneakersBean" property="id" value="<%= i%>"/>
        <jsp:setProperty name="sneakersBean" property="imageUrl" value="<%=img%>"/>
        <jsp:include page="item.jsp"/>
        <%
            }
        %>
    </div>
    <jsp:include page="footer.jsp"/>
    <div id="popup1">
        <span class="info" id="inf1">Товар добавлен в корзину!<br><a href="/cart">Оформить заказ</a></span>
        <span class="info" id="inf2" style="display: none; font-size: 1.2em; font-weight: bolder">Заказ принят!</span>
    </div>
</body>
</html>
