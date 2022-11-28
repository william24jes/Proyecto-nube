package com.example.proyectoingweb.servlets.ServletsUsuario;

import com.example.proyectoingweb.servlets.model.daos.DaoImagen;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "ImagenServlet", value = "/Image")
public class ImagenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        DaoImagen imageDao = new DaoImagen();

        switch (action){
            case "lista_imagen_sql"->{
                int id = Integer.parseInt(request.getParameter("id"));
                byte[] content = null;
                content = imageDao.obtenerimagenes(id);
                if (content.length == 1 && content[0] == 0) {
                    System.out.println("Algo falló al nivel de SQL/DB");
                } else if (content.length == 1 && content[0] == 1) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    response.setContentType("image/gif");
                    response.setContentLength(content.length);
                    response.getOutputStream().write(content);
                }
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
