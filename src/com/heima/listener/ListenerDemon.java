package com.heima.listener; /**
 * Created by Feng on 2017/1/7.
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class ListenerDemon implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ListenerDemon() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("ServletContext对象被创建了...");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象被销毁了...");
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("HttpSession被创建了...");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("HttpSession被销毁了...");
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
