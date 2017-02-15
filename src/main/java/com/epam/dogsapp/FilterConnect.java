package com.epam.dogsapp;

import javax.servlet.*;
import java.io.IOException;

public class FilterConnect implements Filter {
    private FilterConfig config = null;
    private boolean active = false;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        String act = config.getInitParameter("active");
        if (act != null) {
            this.active = (act.toUpperCase().equals("TRUE"));
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (this.active) {
            System.out.println("pikpikpik");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        this.config = null;
    }
}
