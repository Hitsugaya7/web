package ru.unlimit;

import dbTools.CommentService;
import dbTools.CommentEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Comments extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        String del = request.getParameter("del");

        String userName = (String) request.getSession().getAttribute("username");
        if(text != null && text != "") {
            CommentEntity comment = new CommentEntity(text, userName);
            CommentService.saveComment(comment);

            return;
        }

        if(del != null && del != ""){
            CommentService.deleteComment(Integer.parseInt(del), userName);

            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder allCommentsHtml = new StringBuilder();

        String userName = (String)request.getSession().getAttribute("username");
        ArrayList<CommentEntity> comments = CommentService.getAllComments();

        for (CommentEntity comment: comments) {
            allCommentsHtml.append("<div class='comment'>");
            allCommentsHtml.append("	<span class='c_info'>");
            if(comment.getUserName().equals(userName))
            allCommentsHtml.append("<a onclick='delcomment("+ comment.getId() +")'><img style='width: 20px; vertical-align: middle;' src='./image/x.png'></a>");
            allCommentsHtml.append("<b>" + comment.getUserName() + "</b> <i>" + comment.getCommentDate() + "</i></span>");
            allCommentsHtml.append("	<hr noshade>");
            allCommentsHtml.append("	<p>" + comment.getText() + "</p>");
            allCommentsHtml.append("</div>");
        }

        if(allCommentsHtml.length() == 0)
            allCommentsHtml.append("<span style='display: block; margin: 10px; font-style: italic; color: #de8247'>EMPTY</span>");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(allCommentsHtml.toString());
    }
}
