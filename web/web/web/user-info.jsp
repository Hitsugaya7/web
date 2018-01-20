<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String lang = (String)request.getSession().getAttribute("locale");


    HttpSession ss = request.getSession();

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

    String user = (String)ss.getAttribute("username");
%>
<li><%
    if(user == "" || user == null){
%>
    <a href="/login"><%=res.getString("enter")%></a>
    <%}else{%>
    <a href="./cabinet" id="lll"><%=res.getString("loginas")%> <b><%= user%></b></a>
    <%
        }
    %></li>

<li><a href="/cart"><%= res.getString("cart")%></a></li>
<div id="overlay" onclick="closeform()"></div>
<div id="login-form">
    <h1><%=res.getString("need_auth")%></h1>
    <%--<input type="text" id="name" placeholder="Name"/>--%>
    <a class="button10" onclick="closeform()">OK</a>
    <%--<iframe src="auth/auth.jsp" scrolling="no" width="100%"/>--%>
</div>