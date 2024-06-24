package com.example.webbasestarttask.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestedEncoding = servletRequest.getCharacterEncoding();
        if (encoding != null && encoding.equalsIgnoreCase(requestedEncoding)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}