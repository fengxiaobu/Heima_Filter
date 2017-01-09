package com.heima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Feng on 2017/1/7.
 */
public class FilterEncoding implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        MyHttpServletRequest requestWrapper = new MyHttpServletRequest(request);
        chain.doFilter(requestWrapper, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
