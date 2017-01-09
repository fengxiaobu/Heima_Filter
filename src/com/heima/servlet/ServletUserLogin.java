package com.heima.servlet;

import com.heima.bean.User;
import com.heima.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Feng on 2017/1/7.
 * 登录Servlet
 */
public class ServletUserLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String  code1=request.getParameter("code_img");
            String code2= (String) request.getSession().getAttribute("code_img");
            request.getSession().removeAttribute("code_img");
            if(!code1.equalsIgnoreCase(code2)){
                request.setAttribute("msg","验证码输入错误!");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
            //获取所有表单元素
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            //封装数据
            BeanUtils.populate(user, map);
            //查询
            User userBean = UserService.login(user);
            System.out.println(userBean);
            if (userBean == null) {
                request.setAttribute("msg", "用户名或密码不正确.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                //判断是否选中自动登录
                String autoLogin = request.getParameter("autoLogin");
                if ("true".equals(autoLogin)) {
                    System.out.printf("自动登录");
                    //把用户名和密码存入Cookie,并持久化一周
                    Cookie cookie = new Cookie("autoLogin", userBean.getUsername() + "#" + userBean.getPassword());
                    cookie.setPath(request.getContextPath());
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    //把Cookie存入浏览器
                    response.addCookie(cookie);
                }
                //把用户信息存入Session
                request.getSession().setAttribute("userBean", userBean);
                //重定向到首页
                response.sendRedirect(request.getContextPath() + "/index.jsp");
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
        doGet(request, response);
    }
}
