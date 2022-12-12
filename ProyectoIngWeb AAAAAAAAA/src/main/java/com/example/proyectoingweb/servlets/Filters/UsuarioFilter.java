package com.example.proyectoingweb.servlets.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "UsuarioFilter",
           servletNames = {"ServletUsuarioInicio"})
public class UsuarioFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession usuarioSession = req.getSession();
        if (usuarioSession.getAttribute("usuarioSession")==null) {
            res.sendRedirect(req.getContextPath());
        } else {
            chain.doFilter(request, response);
        }
    }
}
