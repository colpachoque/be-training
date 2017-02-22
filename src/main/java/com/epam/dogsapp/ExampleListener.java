package com.epam.dogsapp;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class ExampleListener implements ServletRequestListener {
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("request sent to " + servletRequestEvent.getServletRequest().getRemoteAddr());
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("initialize " + servletRequestEvent.getServletRequest().getRemoteAddr());
    }
}
