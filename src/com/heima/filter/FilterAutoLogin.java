package com.heima.filter;

import com.heima.bean.User;
import com.heima.service.UserService;
import com.heima.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Feng on 2017/1/7.
 *
 * @author Feng
 *         自动登录(Filter)
 */
public class FilterAutoLogin implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //把ServletRequest转化为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) req;
        //获取Session
        HttpSession session = request.getSession();
        //防止在没关闭浏览器的情况下直接访问index页面,首先查询userBean
        User userBean = (User) session.getAttribute("userBean");
        if (userBean != null) {
            chain.doFilter(req, resp);
        } else {
            //获取所有Cookie
            Cookie[] cookies = request.getCookies();
            //获取存入用户名和密码的Cookie
            Cookie cookie = CookieUtils.findCookie(cookies, "autoLogin");
            if (cookie == null) {
                chain.doFilter(req, resp);
            } else {
                // 获取存入用户名和密码的值
                String cookieValue = cookie.getValue();
                String username = cookieValue.split("#")[0];
                String password = cookieValue.split("#")[1];
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                try {
                    //防止数据被篡改,直接向数据库查询用户信息
                    User userBeans = UserService.login(user);
                    if (userBeans == null) {
                        chain.doFilter(req, resp);
                    } else {
                        session.setAttribute("userBean", userBeans);
                        chain.doFilter(request, resp);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
