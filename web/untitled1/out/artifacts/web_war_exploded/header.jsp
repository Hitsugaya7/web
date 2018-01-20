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


<div class="localisation center"> 
    <ul class= "locList ">
         <li><a href= "?lang=en ">EN</a></li>
         <li><a href= "?lang=ru ">RU</a></li>
         <li><a href= "?lang=tj ">TJ</a></li>
    </ul>
 </div>   
 <div class= "for-line ">   
     <header class= "center ">   
         <div class= "logo-left-top ">   
             <a href= "http://localhost:8080"><img src= "image/logo.png " alt= " " ></a>
         </div>
         <menu class= "menu-top  ">   
             <ul>
                 <li><a href= "#">  <%=res.getString("man")%>   </a></li>
                 <li><a href= "#">  <%=res.getString("woman")%>   </a></li>
                 <li><a href= "#">  <%=res.getString("boy")%>   </a></li>
                 <li><a href= "#">  <%=res.getString("girl")%>   </a></li>
                 <li><a href= "#">  <%=res.getString("you_design")%>   </a></li>
                 <jsp:include page="user-info.jsp"/>
             </ul>
         </menu>
     </header>
 </div>


