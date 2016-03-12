package ru.javawebinar.topjava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Сергей on 12.03.2016.
 */
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(request.getParameter("enter") != null) {
            request.getRequestDispatcher("enter.jsp").forward(request, response);
        }else{

        }


    }
}
