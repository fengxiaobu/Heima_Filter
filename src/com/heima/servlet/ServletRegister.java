package com.heima.servlet;

import com.heima.bean.User;
import com.heima.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Feng on 2017/1/7.
 * 用户注册
 */
public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code1 = request.getParameter("code_img");
        String code2 = (String) request.getSession().getAttribute("code_img");
        request.getSession().removeAttribute("code_img");
        if (!code1.equalsIgnoreCase(code2)) {
            request.setAttribute("msg", "验证码输入错误!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
            int dir = UserService.register(user);
            if (dir > 0) {
                request.setAttribute("msg","注册成功!请登录.");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            } else {
                request.setAttribute("msg","注册失败!请重新输入.");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
