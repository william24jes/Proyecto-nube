package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.servlets.model.beans.ZonaPucp;
import com.example.proyectoingweb.servlets.model.daos.DaoZonaPucp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ZonaPucpServlet", value = "/ZonaPucpServlet")
public class ZonaPucpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();
        ArrayList<ZonaPucp> listaZonasPucp = daoZonaPucp.obtenerListaZonaPucp();
        request.setAttribute("lista", listaZonasPucp);

        RequestDispatcher view = request.getRequestDispatcher("listaZonaPucp.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
