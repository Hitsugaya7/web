package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.lang.String;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Item extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        HttpSession ss = request.getSession();
        Cookie[] cookies = request.getCookies();

        if(lang == null) {
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("lang".equals(c.getName()))
                        lang = c.getValue();

                    if ("user".equals(c.getName()))
                        ss.setAttribute("username", c.getValue());
                }
            }else{
                lang = getInitParameter("lang");
            }
        }
        ss.setAttribute("locale", lang);
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

        int parameter = -1;
        if(cookies != null) {
            for (Cookie c : cookies) {
                if ("choise".equals(c.getName()))
                    parameter = Integer.parseInt(c.getValue());
            }
        }

        if(parameter == -1)
            parameter = Integer.parseInt(getInitParameter("choise"));


        ResourceBundle bundle = ResourceBundle.getBundle("res", locale);
        String id = request.getParameter("id");
        int _id;
        try {
            _id = Integer.parseInt(id);
        } catch (Exception ex){
            _id = 0;
        }

        if(id == null || _id < 1 || _id > 4) {
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("id".equals(c.getName()))
                        id = c.getValue();
                }
            }else{
                id = "1";
            }
        }
        response.addCookie(new Cookie("id", id));


        String user = (String)ss.getAttribute("username");

        if(user != null && user != ""){
            log(new Date().toString()+": пользователь " + user + " смотрит товар под номером " + id);
        }else{
            log(new Date().toString()+":  неизвестный пользователь смотрит товар под номером " + id);
        }

        String description;


        switch (parameter){
            case 1: description = bundle.getString("nameOfDiscr") + bundle.getString("priceOfDiscr"+id); break;
            case 2: description = bundle.getString("nameOfDiscr") + bundle.getString("priceOfDiscr"+id)+bundle.getString("typeOfDiscr"+id); break;
            case 3: description = bundle.getString("reviewsOfDiscr"+id); break;
            default: description = bundle.getString("nameOfDiscr") + bundle.getString("priceOfDiscr"+id);
        }

        /*String description;
        String description;
        int parameter = Integer.parseInt(getInitParameter("choise"));

        switch (parameter){
            case 1: description = bundle.getString("nameOfDiscr") + bundle.getString("priceOfDiscr"+id); break;
            case 2: description = bundle.getString("nameOfDiscr") + bundle.getString("priceOfDiscr"+id)+bundle.getString("typeOfDiscr"+id); break;
            case 3: description = bundle.getString("reviewsOfDiscr"+id); break;
            default: description = bundle.getString("nameOfDiscr") + bundle.getString("priceOfDiscr"+id);
        }*/

        String userinfo = "<a href='./login'>" + bundle.getString("enter") + "</a>";

        if(user != null && user != "")
            userinfo = "<a href='./cabinet' id='lll'>" + bundle.getString("loginas") + " <b>" + user + "</b></a>";


        System.out.println("MyServlet.doGet()");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>"+
                    "<html>"+
                    "<head>"+
                        "<meta charset=\"utf-8 \">" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">" +
                        "<link href=\"https://fonts.googleapis.com/css?family=Lobster\" rel=\"stylesheet\">"+
                        "<link href=\"https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:700\" rel=\"stylesheet\">"+
                        "<script src=\"https://code.jquery.com/jquery-3.2.1.min.js\">"+"</script>"+
                        "<script src=\"js/cabinet_worker.js\">"+"</script>"+
                        "<script src=\"js/cart_worker.js\">"+"</script>"+
                        "<script src=\"js/login_worker.js\">"+"</script>"+
                        "<title>"+ bundle.getString("title") +"</title>"+
                    "</head>"+
                    "<body onLoad=\"changeText('" + description + "')\">\r\n"+
                        "<main>\r\n" +
                            "<div class=\"localisation center\">\r\n" +
                                "<ul class=\"locList\">\r\n" +
                                    "<li><a href=\"?lang=en\">EN</a></li>\r\n" +
                                    "<li><a href=\"?lang=ru\">RU</a></li>\r\n" +
                                    "<li><a href=\"?lang=tj\">TJ</a></li>\r\n" +
                                "</ul>\r\n" +
                            "</div>\r\n" +
                            "<div class=\"for-line\">\r\n" +
                                "<header class=\"center\">\r\n" +
                                    "<div class=\"logo-left-top\">\r\n" +
                                        "<a href=\"http://localhost:8080\"><img src=\"image/logo.png\" alt=\"\" ></a>\r\n" +
                                    "</div>\r\n" +
                                    "<menu class=\"menu-top \">\r\n" +
                                        "<ul>\r\n" +
                                            "<li><a href=\"#\">"+bundle.getString("man")+"</a></li>\r\n" +
                                            "<li><a href=\"#\">"+bundle.getString("woman")+"</a></li>\r\n" +
                                            "<li><a href=\"#\">"+bundle.getString("boy")+"</a></li>\r\n" +
                                            "<li><a href=\"#\">"+bundle.getString("girl")+"</a></li>\r\n" +
                                            "<li><a href=\"#\">"+bundle.getString("you_design")+"</a></li>\r\n" +
                                            "<li><a href=\"/cabinet\">"+ userinfo  +"</a></li>\r\n" +
                                            "<li><a href=\"/cart\">"+bundle.getString("cart")+"</a></li>\r\n" +
                                                "<div id=\"login-form\">"+
                                            "<h1>"+bundle.getString("need_auth")+"</h1>"+
                                            "<!--input type=\"text\" id=\"name\" placeholder=\"Name\"/>--!>"+
                                            "<a class=\"button10\" onclick=\"closeform()\">OK</a>"+
                                            "<!--iframe src=\"auth/auth.jsp\" scrolling=\"no\" width=\"100%\"/--!>"+
                                            "</div>"+
                                            /*"<li><a href=\"#\">"+bundle.getString("history")+"</a></li>\r\n" +*/
                                        "</ul>\r\n" +
                                    "</menu>\r\n" +
                                    "<!--div id='overlay' onclick='closeform()'></div>" +
                                        "<div id='login-form'>" +
                                        "<h1>" + bundle.getString("need_auth") + "</h1>" +
                                        "<a class='button10' onclick='closeform()'>OK</a>" +
                                        "</div>\r\n"+
                                    "</div--!>\r\n"+
                                "</header>\r\n" +
                            "</div>\r\n" +
                            "<div class=\"for-line\">\r\n" +
                					"<section class=\"center\">\r\n" +
                						"<div class=\"headerOfSlider\">\r\n" +
                			          		"<h3>ADIDAS ORIGINALS DESIGN BY KANYE WEST</h3>\r\n" +
                			          	"</div>\r\n" +
                						"<div class=\"gallery\">\r\n" +
                				            "<div class=\"gallery-main\" style=\"background-image: url('image/items/"+id+".jpg');\"></div>\r\n" +
                				            "<!--<ul class=\"gallery-list\">\r\n" +
                				              "<li class=\"gallery-item active\" style=\"background-image: url('image/1.jpg');\"></li>\r\n" +
                				              "<li class=\"gallery-item\" style=\"background-image: url('image/2.jpg');\"></li>\r\n" +
                				              "<li class=\"gallery-item\" style=\"background-image: url('image/1.jpg');\"></li>\r\n" +
                				              "<li class=\"gallery-item\" style=\"background-image: url('image/5.jpg');\"></li>\r\n" +
                				            "</ul>--!>\r\n" +
                			            "</div>\r\n" +
                                        "<div class =\"tabs\">\r\n"+
                                            "<div class=\"form\">\r\n" +
                                            "<a   onclick=\"to_cart(" + id + ")\">" +  bundle.getString("to_cart") + " | <span class='price'>" +  bundle.getString("cost" + id) + " $</span></a>"+
                                            "</div>\r\n"+
                                            "<div id=' '>" +
                                            "</div>\r\n"+
                                            "<script type=\"text/javascript\"> function changeText(value){ document.getElementById('TextDiv').innerHTML = value; } </script>\r\n" +
                                             "<div class=\"button-tabs\">\r\n"+
                                                "<input type='button' onclick=\"changeText('" + bundle.getString("nameOfDiscr") + bundle.getString("priceOfDiscr"+id)+ "')\" value="+bundle.getString("discr")+">\r\n"+
                                                "<input type='button' onclick=\"changeText('" + bundle.getString("nameOfDiscr") + bundle.getString("priceOfDiscr"+id)+bundle.getString("typeOfDiscr"+id)+ "')\" value="+bundle.getString("full_discr")+">\r\n"+
                                                "<input type='button' onclick=\"changeText('" + bundle.getString("reviewsOfDiscr")+ "')\" value="+bundle.getString("reviews")+">\r\n"+
                                             "</div>\r\n"+
                                            "<div id='TextDiv'></div> \r\n" +
                                         "</div>\r\n"+


                                "</section>\r\n"+
                            "</div>\r\n"+
                            "<footer class=\"footer-bottom \">\r\n" +
                                "<p class=\"copy center\">"+"&copy;"+bundle.getString("copyright") +"</p>\r\n" +
                            "</footer>"+
                        "</main>\r\n"+
                        "<div id=\"popup1\">"+
                        "<span class=\"info\" id=\"inf1\">Товар добавлен в корзину!<br><a href=\"/cart\">Оформить заказ</a></span>"+
                        "<span class=\"info\" id=\"inf2\" style=\"display: none; font-size: 1.2em; font-weight: bolder\">Заказ принят!</span>"+
                        "</div>"+
                    "<script src=\"for-slider.js\" type=\"text/javascript\"></script>	\r\n" +
                    "</body>\r\n"+
                    "</html>");
        out.close();
    }
}
