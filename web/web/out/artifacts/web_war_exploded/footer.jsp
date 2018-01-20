<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<footer class= "footer-bottom  center">
<p class= "copy center "> &copy; <%=res.getString("copyright")%> </p>
</footer>