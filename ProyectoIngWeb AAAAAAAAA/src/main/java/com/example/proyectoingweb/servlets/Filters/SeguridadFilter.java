package com.example.proyectoingweb.servlets.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "SessionFilter",
         servletNames = {"ServletSeguridadInicio"})

public class SeguridadFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession seguridadSession = req.getSession();

        if (seguridadSession.getAttribute("seguridadSession")==null) {
            res.sendRedirect(req.getContextPath());
        } else {
            chain.doFilter(request, response);
        }
    }
}
