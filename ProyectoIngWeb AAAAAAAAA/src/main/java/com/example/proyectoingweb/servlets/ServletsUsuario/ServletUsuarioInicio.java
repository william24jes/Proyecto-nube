package com.example.proyectoingweb.servlets.ServletsUsuario;

import com.example.proyectoingweb.servlets.model.beans.*;
import com.example.proyectoingweb.servlets.model.daos.DaoComentarios;
import com.example.proyectoingweb.servlets.model.daos.DaoIncidencias;
import com.example.proyectoingweb.servlets.model.daos.DaoUsuarios;
import com.example.proyectoingweb.servlets.model.daos.DaoZonaPucp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import javax.xml.stream.events.Comment;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ServletUsuarioInicio", value = "/Inicio")
@MultipartConfig(maxFileSize = 16177215)

public class ServletUsuarioInicio extends HttpServlet {

    private ArrayList<Incidencias> listaPermanente;
    private ArrayList<Incidencias> listaPaginada;
    private String search;
    private int centinelaSearch;
    private String opcion;
    private String orden;
    private String usuarioSesion;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "listar" : action;
        RequestDispatcher requestDispatcher;
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        DaoComentarios daoComentarios = new DaoComentarios();
        Comentarios comentario = new Comentarios();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();
        String idIncidencia;
        Incidencias incidencia;
        int centinela;
        String busqueda;
        int idPage;
        HttpSession session = request.getSession();
        switch (action) {
            case "listar":
                setCentinelaSearch(0);
                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.obtenerlistaIncidencias());
                request.setAttribute("listaIncidenciasPermanente", daoIncidencias.obtenerlistaIncidenciasCompleta());
                setListaPermanente(daoIncidencias.obtenerlistaIncidenciasCompleta());
                requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                requestDispatcher.forward(request, response);

                break;
            case "perfil":
                requestDispatcher = request.getRequestDispatcher("UsuarioEditarPerfil.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "misIncidencias":
                setCentinelaSearch(0);
                HttpSession sessionUsuario = request.getSession();
                Usuarios user = (Usuarios) sessionUsuario.getAttribute("usuarioSession");
                setUsuarioSesion(String.valueOf(user.getIdUsuarios()));
                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.incidenciasDestXUser("" + user.getIdUsuarios() + ""));
                request.setAttribute("listaIncidenciasPermanente", daoIncidencias.incidenciasDestXUserCompleta("" + user.getIdUsuarios() + ""));
                setListaPermanente(daoIncidencias.incidenciasDestXUserCompleta("" + user.getIdUsuarios() + ""));
                //request.setAttribute("listaIncidenciasDestacadas", daoIncidencias.incidenciasDestXUser("" + user.getIdUsuarios() + ""));
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "registrarIncidencia":
                request.setAttribute("listaIncidencias", daoIncidencias.obtenerlistaIncidenciasCompleta());
                request.setAttribute("listaZonasPUCP", daoZonaPucp.obtenerListaZonaPucp());
                requestDispatcher = request.getRequestDispatcher("UsuarioRegistrarIncidencia.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "cerrarSesion":
                //session.invalidate();
                session.removeAttribute("usuarioSession");
                response.sendRedirect(request.getContextPath());
                /*
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
                */
                break;
            case "verIncidencia":

                idIncidencia = request.getParameter("id");
                incidencia = daoIncidencias.buscarPorId(idIncidencia);
                HttpSession sessionUsuario2 = request.getSession();
                Usuarios user2 = (Usuarios) sessionUsuario2.getAttribute("usuarioSession");
                comentario = daoComentarios.buscarporIdIncidencia_y_idUsuarioQueCreo(idIncidencia, String.valueOf(user2.getIdUsuarios()));


                if (incidencia != null) { //abro el form para editar
                    request.setAttribute("comentario2", comentario);
                    request.setAttribute("incidencia_send_jsp", incidencia);
                    requestDispatcher = request.getRequestDispatcher("UsuarioVerIncidencia.jsp");
                    requestDispatcher.forward(request, response);
                } else { //id no encontrado
                    response.sendRedirect(request.getContextPath() + "/Inicio?action=listar");
                }
                break;

            case "page":
                idPage = Integer.parseInt(request.getParameter("id"));
                centinela = getCentinelaSearch();
                busqueda = getSearch();

                if (centinela == 0) {
                    setListaPaginada(daoIncidencias.paginarIncidencias(idPage));
                    request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                    request.setAttribute("listaIncidenciasPaginada", getListaPaginada());
                } else if (centinela == 1) {
                    setListaPaginada(daoIncidencias.paginarMisIncidenciasBuscadas(idPage, busqueda));
                    request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                    request.setAttribute("listaIncidenciasPaginada", getListaPaginada());
                } else if (centinela == 2) {
                    setListaPaginada(daoIncidencias.paginarIncidenciasOrdenadas(idPage, getOpcion(), getOrden()));
                    request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                    request.setAttribute("listaIncidenciasPaginada", getListaPaginada());
                }
                requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                requestDispatcher.forward(request, response);

                break;

            case "pageMisIncidencias":
                idPage = Integer.parseInt(request.getParameter("id"));
                String usuarios = getUsuarioSesion();
                centinela = getCentinelaSearch();
                busqueda = getSearch();

                if (centinela == 0) {
                    setListaPaginada(daoIncidencias.paginarMisIncidencias("" + usuarios + "", idPage));
                    request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                    request.setAttribute("listaIncidenciasPaginada", getListaPaginada());
                } else if (centinela == 1) {
                    setListaPaginada(daoIncidencias.paginarMisIncidenciasBuscadasidIncidencia(idPage, busqueda, "" + usuarios + ""));
                    request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                    request.setAttribute("listaIncidenciasPaginada", getListaPaginada());
                } else if (centinela == 2) {
                    setListaPaginada(daoIncidencias.paginarIncidenciasOrdenadas(idPage, getOpcion(), getOrden()));
                    request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                    request.setAttribute("listaIncidenciasPaginada", getListaPaginada());
                }
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request, response);

                break;

        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        Incidencias incidencias = new Incidencias();
        Usuarios usuario;
        ZonaPucp zonaPucp;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();
        DaoComentarios daoComentarios = new DaoComentarios();
        ArrayList<Usuarios> lista_usuarios;
        ArrayList<Comentarios> lista_comentarios;
        RequestDispatcher requestDispatcher;

        switch (action) {
            case "guardar":
                InputStream inputStream; // input stream of the upload file
                String idusuario = request.getParameter("id");
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String tipo = request.getParameter("tipo");
                String nivel = request.getParameter("nivel");
                String zona = request.getParameter("zona");
                Part filePart = request.getPart("foto1");
                String latitud = request.getParameter("latitud");
                String longitud = request.getParameter("longitud");
                String value_incidencia = request.getParameter("idIncidencia");
                String anonimo = request.getParameter("anonimo");
                if (anonimo == null) {
                    anonimo = "0";
                    incidencias.setAnonimo(Integer.parseInt(anonimo));
                }
                if (anonimo != null) {
                    incidencias.setAnonimo(Integer.parseInt(anonimo));
                }

                inputStream = filePart.getInputStream();
                if (filePart != null) {
                    // prints out some information for debugging
                    System.out.println(filePart.getContentType());

                    // obtains input stream of the upload file
                    inputStream = filePart.getInputStream();
                }
                usuario = daoUsuarios.buscarPorId(idusuario);
                incidencias.setIdIncidencia(Integer.parseInt(value_incidencia) + 1);
                incidencias.setUsuario(usuario);
                incidencias.setLatitud(latitud);
                incidencias.setLongitud(longitud);
                incidencias.setNombre(nombre);
                incidencias.setDescripcion(descripcion);
                incidencias.setDestacado(1);
                incidencias.setTipo(tipo);
                incidencias.setUrgencia(nivel);
                zonaPucp = daoZonaPucp.obtenerXId(zona);
                incidencias.setZonaPucp(zonaPucp);
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                incidencias.setDatetime(formattedDate);
                incidencias.setEstadoIncidencia("Registrado");
                daoIncidencias.guardarIncidencias(incidencias, inputStream);
                daoIncidencias.destacarIncidencia_para_idUsuario(idusuario, String.valueOf(Integer.parseInt(value_incidencia) + 1));


                lista_comentarios = daoComentarios.obtenerListaComentarios();
                int value_id_Incidencia = 0;
                for (Comentarios lista : lista_comentarios) {
                    value_id_Incidencia = lista.getIdIncidencia();
                }
                String id_incidencia_nuevo = String.valueOf(value_id_Incidencia + 1);
                daoComentarios.guardarComentario(id_incidencia_nuevo, idusuario);

                response.sendRedirect(request.getContextPath() + "/Inicio?action=misIncidencias");
                break;
            case "DestacarIncidencia": {
                String idUsuario2 = request.getParameter("id");
                String Cantidad_destacados = request.getParameter("Cantidad_destacados");
                String idIncidencia = request.getParameter("idIncidencia");
                Usuarios usuario2 = daoUsuarios.buscarPorId(idUsuario2);//indica el usuario de la sesion
                lista_usuarios = daoIncidencias.IdDeUsuariosQueDestacaron(idIncidencia);
                boolean validacion = Usuario_destaco_o_no(lista_usuarios, usuario2);
                if (validacion) {
                    daoIncidencias.destacarIncidencia_para_idUsuario_negativo(idUsuario2, idIncidencia);
                    daoIncidencias.destacar_en_tabla_incidencias_negativo(idIncidencia, Cantidad_destacados);
                    response.sendRedirect(request.getContextPath() + "/Inicio");
                    break;
                } else {
                    daoIncidencias.destacarIncidencia_para_idUsuario(idUsuario2, idIncidencia);
                    daoIncidencias.destacar_en_tabla_incidencias(idIncidencia, Cantidad_destacados);
                    response.sendRedirect(request.getContextPath() + "/Inicio");
                    break;
                }
            }
            case "CambiarTelefono": {
                InputStream inputStream2; // input stream of the upload file
                Part filePart2 = request.getPart("foto_subida");
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
                HttpSession session = request.getSession();
                session.setAttribute("usuarioSession", user_a_cambiar);
                response.sendRedirect(request.getContextPath() + "/Inicio?action=perfil");
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
                HttpSession session = request.getSession();
                session.setAttribute("usuarioSession", user_a_cambiar);

                response.sendRedirect(request.getContextPath() + "/Inicio?action=perfil");
                break;
            }

            case "DestacarIncidencia_verIncidencias": {
                String idUsuario2 = request.getParameter("id");
                String Cantidad_destacados = request.getParameter("Cantidad_destacados");
                String idIncidencia = request.getParameter("idIncidencia");
                Usuarios usuario2 = daoUsuarios.buscarPorId(idUsuario2);//indica el usuario de la sesion
                lista_usuarios = daoIncidencias.IdDeUsuariosQueDestacaron(idIncidencia);
                boolean validacion = Usuario_destaco_o_no(lista_usuarios, usuario2);
                if (validacion) {
                    daoIncidencias.destacarIncidencia_para_idUsuario_negativo(idUsuario2, idIncidencia);
                    daoIncidencias.destacar_en_tabla_incidencias_negativo(idIncidencia, Cantidad_destacados);
                    response.sendRedirect(request.getContextPath() + "/Inicio?action=verIncidencia&id=" + idIncidencia);
                    break;
                } else {
                    daoIncidencias.destacarIncidencia_para_idUsuario(idUsuario2, idIncidencia);
                    daoIncidencias.destacar_en_tabla_incidencias(idIncidencia, Cantidad_destacados);
                    response.sendRedirect(request.getContextPath() + "/Inicio?action=verIncidencia&id=" + idIncidencia);
                    break;
                }
            }
            case "DestacarIncidencia_MisIncidencias": {
                String idUsuario2 = request.getParameter("id");
                String Cantidad_destacados = request.getParameter("Cantidad_destacados");
                String idIncidencia = request.getParameter("idIncidencia");
                Usuarios usuario2 = daoUsuarios.buscarPorId(idUsuario2);//indica el usuario de la sesion
                lista_usuarios = daoIncidencias.IdDeUsuariosQueDestacaron(idIncidencia);
                boolean validacion = Usuario_destaco_o_no(lista_usuarios, usuario2);
                if (validacion) {
                    daoIncidencias.destacarIncidencia_para_idUsuario_negativo(idUsuario2, idIncidencia);
                    daoIncidencias.destacar_en_tabla_incidencias_negativo(idIncidencia, Cantidad_destacados);
                    response.sendRedirect(request.getContextPath() + "/Inicio?action=misIncidencias");
                    break;
                } else {
                    daoIncidencias.destacarIncidencia_para_idUsuario(idUsuario2, idIncidencia);
                    daoIncidencias.destacar_en_tabla_incidencias(idIncidencia, Cantidad_destacados);
                    response.sendRedirect(request.getContextPath() + "/Inicio?action=misIncidencias");
                    break;
                }
            }
            case "Usuario_reabre_incidencia": {
                String user_validacion = request.getParameter("usuario_quiere_comentar");
                String id_incidencia = request.getParameter("id_incidencia");
                String id_usuario = request.getParameter("id");
                String ultima_columna_llena = request.getParameter("ultima_columna_llena");
                String mensaje = request.getParameter("mensaje");
                Comentarios comment_user = daoComentarios.buscarporIdIncidencia_y_idUsuarioQueCreo(id_incidencia, id_usuario);
                if (Integer.parseInt(user_validacion) == 0) {
                    daoComentarios.actualizarComentario_subida(comment_user);
                    daoIncidencias.actualizar_estado("En proceso", id_incidencia);
                    response.sendRedirect(request.getContextPath() + "/Inicio?action=verIncidencia&id=" + id_incidencia);
                    break;

                } else if (Integer.parseInt(user_validacion) == 1) {
                    daoComentarios.actualizarComentario_bajada(comment_user);
                    int columna_para_llenar = Integer.parseInt(ultima_columna_llena) + 1;
                    daoComentarios.actualizar_tabla_comentarios(comment_user, String.valueOf(columna_para_llenar), mensaje);
                    response.sendRedirect(request.getContextPath() + "/Inicio?action=verIncidencia&id=" + id_incidencia);
                    break;
                }

            }
            case "AceptarResultado": {
                String id_incidencia = request.getParameter("id_incidencia");
                daoIncidencias.actualizar_estado("Resuelto", id_incidencia);
                response.sendRedirect(request.getContextPath() + "/Inicio?action=verIncidencia&id=" + id_incidencia);
                break;
            }
            case "actualizarPassword":
                HttpSession session = request.getSession();
                Usuarios usuarios = (Usuarios) session.getAttribute("usuarioSession");
                String password = request.getParameter("password");

                Credenciales credenciales = daoUsuarios.validarCambioPassword(usuarios.getIdUsuarios(), password);

                if (credenciales != null) {
                    if (request.getParameter("nuevaPassword1").equals(request.getParameter("nuevaPassword2"))) {

                        daoUsuarios.actualizarPassword(Integer.parseInt(credenciales.getIdUsuario()), request.getParameter("nuevaPassword1"));

                        session.setAttribute("msg", "Contraseña cambiada correctamente");
                        response.sendRedirect(request.getContextPath() + "/Inicio");
                    } else {
                        session.setAttribute("msgError", "Las contraseñas deben ser iguales");
                        response.sendRedirect(request.getContextPath() + "/Inicio?action=perfil");
                    }
                } else {
                    session.setAttribute("msgError", "La contraseña actual es incorrecta");
                    response.sendRedirect(request.getContextPath() + "/Inicio?action=perfil");
                }

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


                requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "buscarMisIncidencias":
                setCentinelaSearch(1);
                HttpSession sessionUsuario = request.getSession();
                Usuarios user = (Usuarios) sessionUsuario.getAttribute("usuarioSession");
                setUsuarioSesion(String.valueOf(user.getIdUsuarios()));
                String searchText2 = request.getParameter("searchText");
                setSearch(searchText2);
                ArrayList<Incidencias> lista2 = daoIncidencias.buscarMisIncidenciasCompleto(searchText2, "" + user.getIdUsuarios() + "");
                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.buscarMisIncidencias(searchText2, "" + user.getIdUsuarios() + "")); //editar
                request.setAttribute("listaIncidenciasPermanente", lista2);

                setListaPermanente(lista2);
                request.setAttribute("searchText", searchText2);


                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "order":
                setCentinelaSearch(2);
                String opcionjsp = request.getParameter("tipo");
                String ordenamiento = request.getParameter("orden");
                setOpcion(opcionjsp);
                setOrden(ordenamiento);

                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.paginarIncidenciasOrdenadas(1, opcionjsp, ordenamiento));
                request.setAttribute("listaIncidenciasPermanente", daoIncidencias.obtenerlistaIncidenciasCompleta());

                setListaPermanente(daoIncidencias.obtenerlistaIncidenciasCompletaOrdenada(opcionjsp, ordenamiento));

                requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                requestDispatcher.forward(request, response);
                break;

            /*case "orderMisIncicencias":
                setCentinelaSearch(3);
                HttpSession sessionUsuario2 = request.getSession();
                Usuarios user2 = (Usuarios) sessionUsuario2.getAttribute("usuarioSession");
                setUsuarioSesion(String.valueOf(user2.getIdUsuarios()));
                String opcionjsp2 = request.getParameter("tipo");
                String ordenamiento2= request.getParameter("orden");
                setOpcion(opcionjsp2);
                setOrden(ordenamiento2);


                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.paginarMisIncidenciasOrdenadas(1,opcionjsp2,ordenamiento2,"" + user2.getIdUsuarios() + ""));

                request.setAttribute("listaIncidenciasPermanente", daoIncidencias.incidenciasDestXUserCompleta("" + user2.getIdUsuarios() + ""));

                setListaPermanente(daoIncidencias.incidenciasDestXUserCompleta("" + user2.getIdUsuarios() + ""));

                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request, response);
                break;*/
        }
    }


    public boolean Usuario_destaco_o_no(ArrayList<Usuarios> lista_users_que_destacan, Usuarios usuario_sesion) {
        for (Usuarios lista_destacaron : lista_users_que_destacan) {
            if (lista_destacaron.getIdUsuarios() == usuario_sesion.getIdUsuarios()) {
                //significa que el usuario ha destacado, entonces a volver a casa
                return true;
            }
        }
        return false;
    }
    public boolean Usuario_tiene_foto_null(ArrayList<Usuarios> lista_users_que_destacan, int usuario_sesion_id) {
        for (Usuarios lista_foto_null : lista_users_que_destacan) {
            if (lista_foto_null.getIdUsuarios() == usuario_sesion_id) {
                //significa que el usuario ha destacado, entonces a volver a casa
                return true;
            }
        }
        return false;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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

    public String getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(String usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }
}

