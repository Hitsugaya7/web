package ru.unlimit;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Catalog extends HttpServlet  {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("j_username");

        if(user == null || user == ""){
            response.sendRedirect("/login");
        }else {
            request.getSession().setAttribute("username", user);
            response.addCookie(new Cookie("user", user));

            log(new Date().toString()+": пользователь " + user + " авторизовался");

            doGet(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String filter_param = getInitParameter("filter");

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("filter".equals(c.getName())) {
                    filter_param = c.getValue();
                }
            }
        }
        response.addCookie(new Cookie("filter", filter_param));

        String lang = request.getParameter("lang");
        if(lang != null){
            response.addCookie(new Cookie("lang", lang));
        }else{
            response.addCookie(new Cookie("lang", "en"));
        }

        getServletContext().getRequestDispatcher("/catalog.jsp").forward(request, response);
    }
}
