package ru.unlimit;


import dbTools.OrderService;
import dbTools.OrdersEntity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.*;
public class Cabinet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choise = request.getParameter("choise");
        response.addCookie(new Cookie("choise", choise));
        request.getSession().setAttribute("choise", choise);
        log(new Date().toString()+": пользователь " + request.getSession().getAttribute("username") + " изменил фильтр по умолчанию");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Cookie[] cookies = request.getCookies();
            String lang = request.getParameter("lang");
            String choise = (String)request.getSession().getAttribute("choise");
            HttpSession ss = request.getSession();
            if(lang == null) {
                for (Cookie c : cookies) {
                    if ("lang".equals(c.getName()))
                        lang = c.getValue();
                }
            }
            request.getSession().setAttribute("locale", lang);
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



            ResourceBundle bundle = ResourceBundle.getBundle("res", locale);
           //String user = (String)ss.getAttribute("username");

            String user = (String)request.getSession().getAttribute("username");

            StringBuilder sb = new StringBuilder();
            sb.append("<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
                    "    <link href=\"https://fonts.googleapis.com/css?family=Lobster\" rel=\"stylesheet\">\n" +
                    "    <link href=\"https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:700\" rel=\"stylesheet\">\n" +
                    "    <script src=\"https://code.jquery.com/jquery-3.2.1.min.js\"></script>\n" +
                    "    <jsp:useBean id=\"sneakersBean\" class=\"Models.Sneakers\" scope=\"session\"/>\n" +
                    "    <script src=\"for_sort.js\"></script>\n" +
                    "    <script src=\"./js/login_worker.js\"></script>\n" +
                    "    <script src=\"./js/cabinet_worker.js\"></script>\n" +
                    "    <script src=\"./js/cart_worker.js\"></script>\n" +
                    "    <script src='./js/comments_worker.js'></script>"+
                    "    <title>my design</title>\n" +
                    "</head>" +
                    "<body onLoad=\"changeText('" + choise + "')\">\r\n"+
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
                            //"<li><a href=\"/cabinet\">"+ userinfo  +"</a></li>\r\n" +
                            "<li><a href=\"/cart\">"+bundle.getString("cart")+"</a></li>\r\n" +
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
                            "</div>");
            sb.append( "<div class=\"for-line\">\r\n"+"<section class=\"center\">\r\n" );
            sb.append("<div class='cabinet_block' style='margin: 15px 0px; overflow: hidden;'>");
            sb.append("<div id='datetime'></div>");
            sb.append("<script>enableDateTimer()</script>");
            sb.append("<span id='cabinet_name center'>" + bundle.getString("loginas") + " <u>" + user + "</u></span>");
            sb.append("<form action='./cabinet' method='post' id='cabinet_form'>");
            sb.append("	<h2>" +  bundle.getString("def") + ": </h2>");
            sb.append("    <span><input id='c1' class='option-input radio' name='choise' type='radio' value='1'>" +  bundle.getString("discr") + "</span><br>");
            sb.append("    <span><input id='c2' class='option-input radio' name='choise' type='radio' value='2'>" +  bundle.getString("full_discr") + "</span><br>");
            sb.append("    <span><input id='c3' class='option-input radio' name='choise' type='radio' value='3'>" +  bundle.getString("reviews") + "</span><br>");
            sb.append("    <span><button class=\"button\" type=\"submit\">OK</button></span>");
            sb.append(" </form>");
            sb.append("    <span><a href='./exit' class=\"button\">Exit</a></span>");
            sb.append("</div>");


            ArrayList<OrdersEntity> orders = OrderService.getUserAllPurchases(user);

        sb.append("<div class='cabinet_block korpus '>");
            sb.append("<input type='radio' name='odin' checked='checked' id='vkl1'/>");
            sb.append("<label for='vkl1'>" + bundle.getString("history") + "</label>");
            sb.append("<input type='radio' name='odin' id='vkl2'/>");
//            sb.append("<label for='vkl2'>" + bundle.getString("reviews") + "</label>");
            sb.append("<hr noshade color='#eee' style='margin: 0px'>");
            sb.append("<div>");
            if(orders.size() > 0){
                String curier, adr;

                //sb.append("<h1 class='c_title'>" + bundle.getString("history") + "</h1>");
                //sb.append("<hr noshade size='5' color='#D27B43'>");
                sb.append("<table id='cart_table' width='880px'>");
                sb.append("	<thead>");
                sb.append("		<td width='125px'>" + bundle.getString("date") + "</td><td>" + bundle.getString("list") + "</td><td>" + bundle.getString("curier") + "</td><td>" + bundle.getString("Addresse") + "</td>");
                sb.append("	</thead>");
                for(OrdersEntity order : orders) {
                    if(order.getWithCurier() == 0){
                        curier = bundle.getString("no");
                        adr = bundle.getString("title" + order.getShopId()) + " #" + Integer.toString(order.getShopId());
                    }else{
                        curier = bundle.getString("yes");
                        adr = order.getAddressee();
                    }

                    sb.append("<tr>");
                    sb.append("<td>" + order.getOrderDate() + "</td>" +
                            "<td>" + order.getPurchases() + "</td>" +
                            "<td>" + curier + "</td>" +
                            "<td>" + adr + "</td>");
                    sb.append("</tr>");
                }
                sb.append("</table>");
            }else{
                sb.append("<span style='display: block; margin: 10px; font-style: italic; color: #de8247'>EMPTY</span>");
            }

            sb.append("<div id='comments-area' height='500px'>");
            //sb.append("<h1 class='c_title'>" + bundle.getString("reviews") + "</h1>");
            //sb.append("<hr noshade size='5' color='#D27B43'>");
            sb.append("<div id='comments'></div>");
            sb.append("<textarea id='message' placeholder='Оставьте ваш отзыв!' maxlength=128></textarea>");
            sb.append("<button class='button' onclick='sendComment()'>Send</button>");
            sb.append("	</div>");

            sb.append("</div>");


        sb.append( "</section >" +"</div>");
            sb.append("</main>");
            //Container end
            sb.append("<footer>");
            sb.append("    <div id='about'></div>");
            sb.append("</footer>");
            sb.append("</div>");
            //Wrapper end

            sb.append("</body></html>");
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();
            out.println(sb.toString());
            out.close();
    }
}