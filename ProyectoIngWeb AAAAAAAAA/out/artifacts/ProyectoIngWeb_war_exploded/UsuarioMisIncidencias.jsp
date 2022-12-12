<%@ page import="com.example.proyectoingweb.servlets.model.beans.Incidencias" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoIncidencias" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoUsuarios" %>
<%@ page import="com.example.proyectoingweb.servlets.ServletsUsuario.ServletUsuarioInicio" %>
<%@ page import="com.example.proyectoingweb.servlets.model.beans.Usuarios" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaIncidenciasPaginada" scope="request"
             type="java.util.ArrayList<com.example.proyectoingweb.servlets.model.beans.Incidencias>"/>
<jsp:useBean id="listaIncidenciasPermanente" scope="request"
             type="java.util.ArrayList<com.example.proyectoingweb.servlets.model.beans.Incidencias>"/>
<jsp:useBean id="usuarioSession" scope="session" type="com.example.proyectoingweb.servlets.model.beans.Usuarios"
             class="com.example.proyectoingweb.servlets.model.beans.Usuarios"/>
<%
    String searchText = (String) request.getAttribute("searchText");
%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Mis incidencias / Incidencias PUCP</title>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            if (usuarioSession == null) {
                response.sendRedirect(request.getContextPath());
            }
        %>

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
              rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/600382161a.js" crossorigin="anonymous"></script>

        <!-- Template Main CSS File -->
        <link href="assets/css/style_usuario.css" rel="stylesheet">

    </head>

    <body>

        <!-- ======= Top Bar ======= -->
        <section id="topbar" class="d-flex align-items-center">
            <div class="container d-flex justify-content-center justify-content-md-between">
            </div>
        </section>

        <!-- ======= Header ======= -->
        <header id="header" class="d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">

                <div class="d-md-flex align-items-center">
                    <div>
                        <h1 class="name" style="margin-top: 25px"><%=usuarioSession.getNombreYApellido()%>
                            <br><h6>Usuario PUCP</h6></br>
                        </h1>
                    </div>
                </div>

                <nav id="navbar" class="navbar">


                    <ul>
                        <li>
                            <div class="container-fluid">
                                <form method="post" class="d-flex input-group w-auto"
                                      action="<%=request.getContextPath()%>/Inicio?action=buscarMisIncidencias">
                                    <input type="text"
                                           name="searchText"
                                           class="form-control rounded"
                                           placeholder="Buscar mis incidencias"
                                           id="floatingInput" aria-label="Search" aria-describedby="search-addon"
                                           value="<%=searchText != null? searchText:""%>"/>
                                    <span class="input-group-text border-0" id="search-addon"><i
                                            class="fas fa-search"></i></span>
                                </form>
                            </div>
                        </li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=listar">Inicio</a>
                        </li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=perfil">Perfil</a>
                        </li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=misIncidencias">Mis
                            Incidencias</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=registrarIncidencia">Registrar
                            Incidencia</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=cerrarSesion">Cerrar
                            sesión</a></li>
                    </ul>

                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav><!-- .navbar -->

            </div>
        </header><!-- End Header -->

        <main id="main" data-aos="fade-up">


            <!-- ======= Portfolio Details Section ======= -->
            <section id="portfolio-details" class="portfolio-details">
                <div class="container">
                    <!--<div class="container-fluid">
                        <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                            <form method="post" action="<%=request.getContextPath()%>/Inicio?action=orderMisIncicencias" class="row g-3 needs-validation" >
                                <div class="col-lg-4">
                                    <select required class="form-select" id="tipo" placeholder="tipo" name="tipo">
                                        <option value="fechaHora">Fecha</option>
                                        <option value="urgencia">Urgencia</option>
                                        <option value="tipo">Tipo de accidente</option>
                                        <option value="destacado">Destacado</option>
                                    </select>
                                </div>

                                <div class="col-lg-4">
                                    <select required style="width: fit-content" class="form-select" id="orden" placeholder="orden" name="orden">
                                        <option value="asc">Ascendente</option>
                                        <option value="desc">Descendente</option>
                                    </select>
                                </div>

                                <div class="d-grid gap-2 col-6 col-lg-4 col-xl-3 mx-auto">
                                    <button class="btn btn-danger" type="submit">Ordenar</button>
                                </div>
                            </form>
                            <a class="btn btn-primary" href="<%=request.getContextPath()%>/Inicio?action=misIncidencias"
                               role="button">Limpiar filtros</a>
                        </div>
                        <br>-->

                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Mis Incidencias</h1>
                        </div>

                        <div class="container">

                            <div class="row">
                                <!-- Dropdown Card Example -->
                                <% for (Incidencias incidenciasDestacadas : listaIncidenciasPaginada) { %>
                                <div class="card shadow mb-4 ">
                                    <!-- Card Header - Dropdown -->
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <img src="assets/img/profile_upload.png" height="70px"/>
                                        <br>
                                        <a href="<%=request.getContextPath()%>/Inicio?action=verIncidencia&id=<%=incidenciasDestacadas.getIdIncidencia()%>">
                                            <h4 class="m-0 font-weight-bold text-primary"
                                                style="width:75%"><%=incidenciasDestacadas.getNombre()%>
                                        </a>
                                        <div style="color:darkgray;display: flex;flex-direction: column">
                                            <%if (incidenciasDestacadas.getAnonimo() == 0) {%>
                                            <h6><%=incidenciasDestacadas.getUsuario().getNombres()%> <%=incidenciasDestacadas.getUsuario().getApellidos()%>
                                            </h6>
                                            <%} else if (incidenciasDestacadas.getAnonimo() == 1 && incidenciasDestacadas.getIdUsuarioQueCreoIncidencia() != usuarioSession.getIdUsuarios()) {%>
                                            <h6>Anónimo</h6>
                                            <%} else if (incidenciasDestacadas.getAnonimo() == 1 && incidenciasDestacadas.getIdUsuarioQueCreoIncidencia() == usuarioSession.getIdUsuarios()) {%>
                                            <h6><%=incidenciasDestacadas.getUsuario().getNombres()%> <%=incidenciasDestacadas.getUsuario().getApellidos()%> (Anónimo)
                                            </h6>
                                            <%}%>
                                            <h6>Registrado <%=incidenciasDestacadas.getDatetime()%>
                                            </h6>
                                            <%if (incidenciasDestacadas.getUrgencia().equals("Bajo")) {%>
                                            <h6 style="-webkit-text-stroke: 0.4px black;color:dodgerblue"><%=incidenciasDestacadas.getUrgencia()%>
                                            </h6>
                                            <%} else if (incidenciasDestacadas.getUrgencia().equals("Medio")) {%>
                                            <h6 style="-webkit-text-stroke: 0.4px black;color:purple"><%=incidenciasDestacadas.getUrgencia()%>
                                            </h6>
                                            <%} else if (incidenciasDestacadas.getUrgencia().equals("Urgente")) {%>
                                            <h6 style="-webkit-text-stroke: 0.4px black;color:red"><%=incidenciasDestacadas.getUrgencia()%>
                                            </h6>
                                            <%}%>
                                        </div>
                                        </h4>


                                        <div>
                                            <form method="post"
                                                  action="<%=request.getContextPath()%>/Inicio?action=DestacarIncidencia_MisIncidencias"
                                            >
                                                <input class="form-control " id="idUsuario" type="hidden"
                                                       name="id" value="<%=usuarioSession.getIdUsuarios()%>">
                                                <input class="form-control" id="Cantidad_de_Destacados" type="hidden"
                                                       name="Cantidad_destacados"
                                                       value="<%=incidenciasDestacadas.getDestacado()%>">
                                                <input class="form-control" id="idIncidencia" type="hidden"
                                                       name="idIncidencia"
                                                       value="<%=incidenciasDestacadas.getIdIncidencia()%>">
                                                <% DaoIncidencias daoincidenciasjsp = new DaoIncidencias();%>
                                                <% DaoUsuarios daousersjsp = new DaoUsuarios();%>
                                                <%ServletUsuarioInicio serv = new ServletUsuarioInicio();%>
                                                <%
                                                    ArrayList<Usuarios> lista_Usuarios = daoincidenciasjsp.IdDeUsuariosQueDestacaron(String.valueOf(incidenciasDestacadas.getIdIncidencia()));%>
                                                <%
                                                    Usuarios user2 = daousersjsp.buscarPorId(String.valueOf(usuarioSession.getIdUsuarios()));%>
                                                <%
                                                    boolean validacion = serv.Usuario_destaco_o_no(lista_Usuarios, user2);%>
                                                <%if (validacion) {%>
                                                <button type="submit" class="btn btn-warning btn-circle">
                                                    <i class="fas fa-exclamation-triangle"><%=incidenciasDestacadas.getDestacado()%>
                                                    </i>
                                                </button>
                                                <%} else {%>
                                                <button type="submit" class="btn btn-warning btn-circle"
                                                        style="background-color: grey;border-color:grey">
                                                    <i class="fas fa-exclamation-triangle"><%=incidenciasDestacadas.getDestacado()%>
                                                    </i>
                                                </button>
                                                <%}%>
                                            </form>
                                        </div>

                                    </div>


                                    <!-- Card Body -->
                                    <div class="card-body">
                                        <%=incidenciasDestacadas.getDescripcion()%>
                                        <div class="col-12">
                                            <br>
                                            <div class="row">
                                                <div class="col-auto">
                                                    <img class="crop"
                                                         src="<%=request.getContextPath()%>/Image?action=lista_imagen_sql&id=<%=incidenciasDestacadas.getIdIncidencia()%>"
                                                         style="max-width: 100%;max-height: 100%;border-radius: 1em"/>
                                                </div>

                                                <div><br>
                                                    <button type="button"
                                                            class="btn btn-primary btn-lg disabled"><%=incidenciasDestacadas.getEstadoIncidencia()%>
                                                    </button>
                                                </div>
                                            </div>
                                            <br>
                                        </div>
                                    </div>
                                </div>
                                <% } %>

                            </div>

                        </div>
                        <nav aria-label="Page navigation example" style="margin-top: 20px;">
                            <ul class="pagination justify-content-center">
                                <%for (double i = 1; i <= Math.ceil((double) (listaIncidenciasPermanente.size()) / 10.0); i++) {%>
                                <li class="page-item"><a class="page-link"
                                                         href="<%=request.getContextPath()%>/Inicio?action=pageMisIncidencias&id=<%=(int)i%>"><%=(int) i%>
                                </a></li>
                                <%}%>
                            </ul>
                        </nav>
                        </nav>

                    </div>


                </div>
            </section><!-- End Portfolio Details Section -->
        </main>

        <div id="preloader"></div>
        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
                class="bi bi-arrow-up-short"></i></a>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>

    </body>

</html>