package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.servlets.model.beans.*;
import com.example.proyectoingweb.servlets.model.daos.*;
import com.example.proyectoingweb.servlets.model.dtos.ReporteIncidenciaTXT;
import com.example.proyectoingweb.servlets.model.dtos.ReporteIncidenciasPDF;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name = "ServletSeguridadInicio", value = "/Seguridad")
@MultipartConfig(maxFileSize = 16177215)
public class ServletSeguridadInicio extends HttpServlet {

    private ArrayList<Incidencias> listaPermanente;
    private ArrayList<Incidencias> listaPaginada;
    private int centinelaSearch;
    private String opcion;
    private String search;
    private String orden;

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "inicioSeguridad" : action;
        RequestDispatcher requestDispatcher;
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        Incidencias incidencia;
        Comentarios comentario;
        Usuarios usuario = new Usuarios();
        DaoComentarios daoComentarios = new DaoComentarios();
        HttpSession session = request.getSession();
        ReporteIncidenciasPDF reporte = new ReporteIncidenciasPDF();
        ReporteIncidenciaTXT reporteTXT = new ReporteIncidenciaTXT();
        int idPage;
        switch (action) {
            case "inicioSeguridad":
                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.obtenerlistaIncidencias());
                request.setAttribute("listaIncidenciasPermanente", daoIncidencias.obtenerlistaIncidenciasCompleta());
                setListaPermanente(daoIncidencias.obtenerlistaIncidenciasCompleta());
                requestDispatcher = request.getRequestDispatcher("SeguridadInicio.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "perfil":
                requestDispatcher = request.getRequestDispatcher("SeguridadPerfil.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "misIncidencias":
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "detalles":

                Usuarios seguridad = (Usuarios) session.getAttribute("seguridadSession");
                String idIncidencia = request.getParameter("id");
                incidencia = daoIncidencias.buscarPorId(idIncidencia);
                request.setAttribute("incidencia", incidencia);
                request.setAttribute("seguridadSession", seguridad);
                comentario = daoComentarios.buscarporIdIncidencia_y_idUsuarioQueCreo(idIncidencia, String.valueOf(incidencia.getUsuario().getIdUsuarios()));
                request.setAttribute("comentario2", comentario);
                requestDispatcher = request.getRequestDispatcher("Seguridad_incidencias.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "incidenciasPDF":
                try {
                    reporte.crearDocumento();
                    reporte.abrirDOcumento();
                    reporte.agregarTitulo("REPORTE DE INCIDENCIAS PUCP");
                    reporte.agregarSaltoLiena();
                    reporte.agregarParrafo("Reporte actual de las incidencias PUCP 2022");
                    reporte.agregarSaltoLiena();
                    reporte.agregarTablaIncidencias();
                    reporte.cerrarDocumento();
                    session.setAttribute("msg","El documento se guardo exitosamente en su escritorio");
                    response.sendRedirect(request.getContextPath() + "/Seguridad?action=inicioSeguridad");
                    System.out.printf("reporte completado");

                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "incidenciastxt":
                reporteTXT.crearDocumento();
                session.setAttribute("msg","El documento se guardo exitosamente en su escritorio");
                response.sendRedirect(request.getContextPath() + "/Seguridad?action=inicioSeguridad");
                System.out.printf("reporte txt completado");

                break;

            case "cerrarSesion":
                //session.invalidate();
                session.removeAttribute("seguridadSession");
                response.sendRedirect(request.getContextPath());
                /*
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request,response);
                */
                break;




            case "page":
                idPage = Integer.parseInt(request.getParameter("id"));
                int centinela = getCentinelaSearch();
                String busqueda=getSearch();

                if(centinela == 0) {
                    setListaPaginada(daoIncidencias.paginarIncidencias(idPage));
                    request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                    request.setAttribute("listaIncidenciasPaginada", getListaPaginada());
                } else if (centinela == 1) {
                    setListaPaginada(daoIncidencias.paginarIncidenciasBuscadas(idPage, busqueda));
                    request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                    request.setAttribute("listaIncidenciasPaginada", getListaPaginada());
                } else if (centinela == 2) {
                    setListaPaginada(daoIncidencias.paginarIncidenciasOrdenadas(idPage, getOpcion(),getOrden()));
                    request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                    request.setAttribute("listaIncidenciasPaginada", getListaPaginada());
                }
                requestDispatcher = request.getRequestDispatcher("SeguridadInicio.jsp");
                requestDispatcher.forward(request, response);

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        RequestDispatcher requestDispatcher;
        Incidencias incidencias = new Incidencias();
        Usuarios usuario;
        ZonaPucp zonaPucp;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();
        DaoComentarios daoComentarios = new DaoComentarios();
        ArrayList<Usuarios> lista_usuarios;
        switch (action) {
            case "Atender_Incidencia": {
                String id_incidencia = request.getParameter("id_incidencia");
                String id_security = request.getParameter("id_security");
                String mensaje = request.getParameter("mensaje_aceptar");
                String id_usuario = request.getParameter("id_usuario");
                String ultima_columna_llena = request.getParameter("ultima_columna_llena");
                usuario = daoUsuarios.buscarPorId(id_security);
                Comentarios comment_user = daoComentarios.buscarporIdIncidencia_y_idUsuarioQueCreo(id_incidencia, id_usuario);
                int columna_para_llenar = Integer.parseInt(ultima_columna_llena) + 1;
                daoComentarios.actualizar_tabla_comentarios(comment_user, String.valueOf(columna_para_llenar), mensaje);
                daoComentarios.actualizarComentario_idSeguridad(Integer.parseInt(id_security),comment_user.getIdComentario());
                daoIncidencias.actualizar_estado("Atendido", id_incidencia);
                daoIncidencias.actualizar_idSecurity_from_incidencias(id_security,id_incidencia);
                response.sendRedirect(request.getContextPath() + "/Seguridad?action=detalles&id=" + id_incidencia);
                break;
            }
            case "Seguridad_Responder_Incidencia": {
                //               String user_validacion = request.getParameter("usuario_quiere_comentar");
                String id_incidencia = request.getParameter("id_incidencia");
                String id_security = request.getParameter("id_security");
                String id_usuario = request.getParameter("id_usuario");
                String ultima_columna_llena = request.getParameter("ultima_columna_llena");
                String mensaje = request.getParameter("mensaje_responder");

                Comentarios comment_user = daoComentarios.buscarporIdIncidencia_y_idUsuarioQueCreo(id_incidencia, id_usuario);
                int columna_para_llenar = Integer.parseInt(ultima_columna_llena) + 1;
                daoComentarios.actualizar_tabla_comentarios(comment_user, String.valueOf(columna_para_llenar), mensaje);
                response.sendRedirect(request.getContextPath() + "/Seguridad?action=detalles&id=" + id_incidencia);
                break;
            }
            case "AceptarResultado": {
                String id_incidencia = request.getParameter("id_incidencia");
                daoIncidencias.actualizar_estado("Resuelto", id_incidencia);
                response.sendRedirect(request.getContextPath() + "/Seguridad?action=detalles&id=" + id_incidencia);
                break;
            }
            case "actualizarPassword":
                HttpSession session = request.getSession();
                Usuarios usuarios = (Usuarios) session.getAttribute("seguridadSession");
                String password = request.getParameter("password");

                Credenciales credenciales = daoUsuarios.validarCambioPassword(usuarios.getIdUsuarios(), password);

                if (credenciales != null){
                    if (request.getParameter("nuevaPassword1").equals(request.getParameter("nuevaPassword2"))){

                        daoUsuarios.actualizarPassword(Integer.parseInt(credenciales.getIdUsuario()), request.getParameter("nuevaPassword1"));

                        session.setAttribute("msg","Contraseña cambiada correctamente");
                        response.sendRedirect(request.getContextPath() + "/Seguridad");
                    }
                    else {
                        session.setAttribute("msgError", "Las contraseñas deben ser iguales");
                        response.sendRedirect(request.getContextPath()+"/Seguridad?action=perfil");
                    }
                }
                else {
                    session.setAttribute("msgError", "La contraseña actual es incorrecta");
                    response.sendRedirect(request.getContextPath()+"/Seguridad?action=perfil");
                }

                break;
            case "CambiarTelefono": {
                InputStream inputStream2; // input stream of the upload file
                Part filePart2 = request.getPart("foto");
                String idUsuario3 = request.getParameter("id");
                String nuevo_celular = request.getParameter("phone");
                Usuarios user_a_cambiar = daoUsuarios.buscarPorId(idUsuario3);
                user_a_cambiar.setCelular(nuevo_celular);
                // obtains input stream of the upload file
                System.out.println(filePart2);
                inputStream2 = filePart2.getInputStream();
                if(filePart2.getSize() != 0){
                    daoUsuarios.actualizar_usuario_telefono_fotoPerfil(user_a_cambiar, inputStream2);
                }else if(filePart2.getSize() == 0){
                    daoUsuarios.actualizar_usuario_telefono(user_a_cambiar);
                }
                HttpSession session3 = request.getSession();
                session3.setAttribute("seguridadSession", user_a_cambiar);
                session3.setAttribute("msg", "Usuario editado correctamente");
                response.sendRedirect(request.getContextPath() + "/Seguridad?action=perfil");
                break;
            }
            case "Borrar_Foto_Perfil": {
                String idUsuario3 = request.getParameter("id");
                /*String filePath = getServletContext();
                 */
                ServletContext context = getServletContext();
                String fullPath = context.getRealPath("/assets/img/fotosPerfil/perfilDefault.png");
                System.out.println(fullPath);

                InputStream inputStream3;
                byte[] bytes = Files.readAllBytes(Paths.get(fullPath));
                inputStream3 = new ByteArrayInputStream(bytes);
                Usuarios user_a_cambiar = daoUsuarios.buscarPorId(idUsuario3);
                daoUsuarios.eliminar_fotoPerfil(user_a_cambiar, inputStream3);
                HttpSession session2 = request.getSession();
                session2.setAttribute("seguridadSession", user_a_cambiar);

                response.sendRedirect(request.getContextPath() + "/Seguridad?action=perfil");
                break;
            }
            case "order":
                setCentinelaSearch(2);
                String opcionjsp = request.getParameter("tipo");
                String ordenamiento= request.getParameter("orden");
                setOpcion(opcionjsp);
                setOrden(ordenamiento);

                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.paginarIncidenciasOrdenadas(1,opcionjsp,ordenamiento));
                request.setAttribute("listaIncidenciasPermanente", daoIncidencias.obtenerlistaIncidenciasCompleta());

                setListaPermanente(daoIncidencias.obtenerlistaIncidenciasCompletaOrdenada(opcionjsp,ordenamiento));

                requestDispatcher = request.getRequestDispatcher("SeguridadInicio.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "buscar":
                setCentinelaSearch(1);
                String searchText = request.getParameter("searchText");
                setSearch(searchText);

                ArrayList<Incidencias> lista = daoIncidencias.buscarIncidenciasCompleto(searchText);
                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.buscarIncidencias(searchText)); //editar
                request.setAttribute("listaIncidenciasPermanente", lista);

                setListaPermanente(lista);
                request.setAttribute("searchText", searchText);

                requestDispatcher = request.getRequestDispatcher("SeguridadInicio.jsp");
                requestDispatcher.forward(request, response);


                break;
        }
    }

    public boolean Seguridad_tiene_foto_null(ArrayList<Usuarios> lista_users_que_destacan, int seguridad_id) {
        for (Usuarios lista_foto_null : lista_users_que_destacan) {
            if (lista_foto_null.getIdUsuarios() == seguridad_id) {
                //significa que el usuario ha destacado, entonces a volver a casa
                return true;
            }
        }
        return false;
    }
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public ArrayList<Incidencias> getListaPermanente() {
        return listaPermanente;
    }

    public void setListaPermanente(ArrayList<Incidencias> listaPermanente) {
        this.listaPermanente = listaPermanente;
    }

    public ArrayList<Incidencias> getListaPaginada() {
        return listaPaginada;
    }

    public void setListaPaginada(ArrayList<Incidencias> listaPaginada) {
        this.listaPaginada = listaPaginada;
    }

    public int getCentinelaSearch() {
        return centinelaSearch;
    }

    public void setCentinelaSearch(int centinelaSearch) {
        this.centinelaSearch = centinelaSearch;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }
}
