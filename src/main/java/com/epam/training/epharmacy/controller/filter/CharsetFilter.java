package com.epam.training.epharmacy.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements javax.servlet.Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding==null){
            encoding="utf-8";
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain next) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        next.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }

}
