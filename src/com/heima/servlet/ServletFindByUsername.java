package com.heima.servlet;

import com.heima.bean.User;
import com.heima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Feng on 2017/1/7.
 */
public class ServletFindByUsername extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.printf(username);
        if (username == null) {
            return;
        }
        try {
            User userbean = UserService.findUserByUsername(username);
            if (userbean == null) {
                //request.setAttribute("username", "1");
                response.getWriter().print(true);
            } else {
                //request.setAttribute("username", "0");
                response.getWriter().print(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
