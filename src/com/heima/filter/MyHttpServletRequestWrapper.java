package com.heima.filter;

import org.apache.catalina.servlet4preview.http.HttpServletRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by Feng on 2017/1/7.
 * 完整解决乱码方式
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String method = request.getMethod();
        System.out.println(method);
        if (method.equalsIgnoreCase("get")) {
            String value = null;
            try {
                value = new String(request.getParameter(name).getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return value;
        } else if (method.equalsIgnoreCase("post")) {
            try {
                request.setCharacterEncoding("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return super.getParameter(name);
    }
}
