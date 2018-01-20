package ru.unlimit;

import Models.Purchase;
import Models.PurchaseList;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Cart extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss = request.getSession();
        String lang = request.getParameter("lang");
        Cookie[] cookies = request.getCookies();

        int one = 0, two = 0, three = 0 , four = 0;
        double cost1 = 0, cost2 = 0, cost3 = 0, cost4 = 0;

        try {
            for (Cookie c : cookies) {
                if ("one".equals(c.getName()))
                    one = Integer.parseInt(c.getValue());
                if ("two".equals(c.getName()))
                    two = Integer.parseInt(c.getValue());
                if ("three".equals(c.getName()))
                    three = Integer.parseInt(c.getValue());
                if ("four".equals(c.getName()))
                    four = Integer.parseInt(c.getValue());

                if ("lang".equals(c.getName()) && lang == null )
                    lang = c.getValue();

                if ("user".equals(c.getName()))
                    ss.setAttribute("username", c.getValue());
            }
        }catch (Exception ex){};

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
        cost1 = Double.parseDouble(bundle.getString("cost1"));
        cost2 = Double.parseDouble(bundle.getString("cost2"));
        cost3 = Double.parseDouble(bundle.getString("cost3"));
        cost4 = Double.parseDouble(bundle.getString("cost4"));

        PurchaseList purchases = new PurchaseList();


        purchases.addPurchase(new Purchase(1, bundle.getString("nameOfDiscr"), bundle.getString("typeOfDiscr1"), "./image/items/1.jpg", one, cost1));
        purchases.addPurchase(new Purchase(2, bundle.getString("nameOfDiscr"), bundle.getString("typeOfDiscr2"), "./image/items/2.jpg",  two, cost2));
        purchases.addPurchase(new Purchase(3, bundle.getString("nameOfDiscr"), bundle.getString("typeOfDiscr3"), "./image/items/3.jpg",  three, cost3));
        purchases.addPurchase(new Purchase(4, bundle.getString("nameOfDiscr"), bundle.getString("typeOfDiscr4"), "./image/items/4.jpg",  four, cost4));
        

        response.addCookie(new Cookie("lang",lang));
        ss.setAttribute("locale", lang);
        ss.setAttribute("purchases", purchases);

        getServletContext().getRequestDispatcher("/cart.jsp").forward(request,response);
    }
}
