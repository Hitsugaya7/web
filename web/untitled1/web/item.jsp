<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="ru.unlimit.Catalog" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<head>
    <%

        String lang = request.getParameter("lang");
        Cookie[] cookies = request.getCookies();

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
    <jsp:useBean id="sneakersBean" class="Models.Sneakers" scope="session"/>
    <script src="./js/cart_worker.js"></script>
</head>
<%
    int id = sneakersBean.getId();
    sneakersBean.setName(res.getString("nameOfDiscr"));
    sneakersBean.setDescription(res.getString("priceOfDiscr" + Integer.toString(id)));
    sneakersBean.setPrice(Double.parseDouble(res.getString("cost" + Integer.toString(id))));
%>

<div id="i<%= id%>" class="box">
    <div class="image">
        <img src="<jsp:getProperty name="sneakersBean" property="imageUrl"/>">
    </div>
    <div   class="about-product">
        <h1><a href="./item?id=<%=Integer.toString(id)%>"><%=res.getString("nameOfDiscr")%></a></h1>
        <div class="cal"><%=res.getString("typeOfDiscr" + Integer.toString(id))%></div>
        <div class="form">
            <a onclick="to_cart(<%= id%>)"><%=res.getString("to_cart")%> | <span class="price"><jsp:getProperty name="sneakersBean" property="price"/> $</span></a>
        </div>
    </div>
</div>