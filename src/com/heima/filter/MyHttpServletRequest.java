package com.heima.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Feng on 2017/1/7.
 */
public class MyHttpServletRequest extends HttpServletRequestWrapper {
    private final HttpServletRequest request;
    private boolean hasEncode;

    public MyHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        //获取请求方式
        String method = request.getMethod();
        //POST请求方式
        if (method.equalsIgnoreCase("post")) {
            try {
                request.setCharacterEncoding("UTF-8");
                return request.getParameterMap();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }//GET请求方式
        } else if (method.equalsIgnoreCase("get")) {
            //获取表单所有元素
            Map<String, String[]> map = request.getParameterMap();
            if (!hasEncode) {//确保GET手动编码只运行一次
                for (String parmeter : map.keySet()) {
                    //获取元素Value值
                    String values[] = map.get(parmeter);
                    if (values != null) {
                        for (int i = 0; i < values.length; i++) {
                            try {
                                //手动编码位UTF-8
                                values[i] = new String(values[i].getBytes("ISO-8859-1"), "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                hasEncode = true;
            }
            return map;
        }
        return super.getParameterMap();
    }

    @Override
    public String getParameter(String name) {
        Map<String, String[]> map = getParameterMap();
        String[] values = map.get(name);
        if (values == null) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        Map<String, String[]> map = getParameterMap();
        String[] values = map.get(name);
        return values;
    }
}
