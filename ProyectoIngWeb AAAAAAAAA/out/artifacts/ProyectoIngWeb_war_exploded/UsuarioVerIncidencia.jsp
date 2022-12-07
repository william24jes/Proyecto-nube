<%@ page import="com.example.proyectoingweb.servlets.model.beans.Incidencias" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoIncidencias" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoUsuarios" %>
<%@ page import="com.example.proyectoingweb.servlets.ServletsUsuario.ServletUsuarioInicio" %>
<%@ page import="com.example.proyectoingweb.servlets.model.beans.Usuarios" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoComentarios" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="incidencia_send_jsp" scope="request"
             type="com.example.proyectoingweb.servlets.model.beans.Incidencias"/>
<jsp:useBean id="usuarioSession" scope="session" type="com.example.proyectoingweb.servlets.model.beans.Usuarios"
             class="com.example.proyectoingweb.servlets.model.beans.Usuarios"/>
<jsp:useBean id="comentario2" scope="request" type="com.example.proyectoingweb.servlets.model.beans.Comentarios"/>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Incidencia / Incidencias PUCP</title>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            if(usuarioSession == null){
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
                                <form class="d-flex input-group w-auto">
                                    <input
                                            type="search"
                                            class="form-control rounded"
                                            placeholder="Buscar Incidencia"
                                            aria-label="Search"
                                            aria-describedby="search-addon"
                                    />
                                    <span class="input-group-text border-0" id="search-addon">
        <i class="fas fa-search"></i>
      </span>
                                </form>
                            </div>
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
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Detalle de Incidencias</h1>
                        </div>

                        <div class="container">

                            <div class="col-lg-12 mx-auto">
                                <!-- Dropdown Card Example -->
                                <div class="card shadow mb-4 ">
                                    <!-- Card Header - Dropdown -->
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <img src="assets/img/profile_upload.png" height="70px"/>
                                        <br>
                                        <div></div>
                                        <h4 class="m-0 font-weight-bold text-primary"
                                            style="width:75%"><%=incidencia_send_jsp.getNombre()%>
                                            <div style="color:darkgray;display: flex;flex-direction: column">
                                                <h6><%=incidencia_send_jsp.getNombreUsuarioQueDestaco()%>
                                                </h6><h6>Registrado <%=incidencia_send_jsp.getDatetime()%>
                                            </h6></div>
                                            <div>
                                                <form method="post"
                                                      action="<%=request.getContextPath()%>/Inicio?action=DestacarIncidencia_verIncidencias"
                                                >
                                                    <input class="form-control " id="idUsuario" type="hidden"
                                                           name="id" value="<%=usuarioSession.getIdUsuarios()%>">
                                                    <input class="form-control" id="Cantidad_de_Destacados"
                                                           type="hidden"
                                                           name="Cantidad_destacados"
                                                           value="<%=incidencia_send_jsp.getDestacado()%>">
                                                    <input class="form-control" id="idIncidencia" type="hidden"
                                                           name="idIncidencia"
                                                           value="<%=incidencia_send_jsp.getIdIncidencia()%>">
                                                    <% DaoIncidencias daoincidenciasjsp = new DaoIncidencias();%>
                                                    <% DaoUsuarios daousersjsp = new DaoUsuarios();%>
                                                    <%ServletUsuarioInicio serv = new ServletUsuarioInicio();%>
                                                    <%ArrayList<Usuarios> lista_Usuarios = daoincidenciasjsp.IdDeUsuariosQueDestacaron(String.valueOf(incidencia_send_jsp.getIdIncidencia()));%>
                                                    <%Usuarios user2 = daousersjsp.buscarPorId(String.valueOf(usuarioSession.getIdUsuarios()));%>
                                                    <%boolean validacion = serv.Usuario_destaco_o_no(lista_Usuarios, user2);%>
                                                    <%if (validacion) {%>
                                                    <button type="submit" class="btn btn-warning btn-circle">
                                                        <i class="fas fa-exclamation-triangle"><%=incidencia_send_jsp.getDestacado()%>
                                                        </i>
                                                    </button>
                                                    <%} else {%>
                                                    <button type="submit" class="btn btn-warning btn-circle"
                                                            style="background-color: grey;border-color:grey">
                                                        <i class="fas fa-exclamation-triangle"><%=incidencia_send_jsp.getDestacado()%>
                                                        </i>
                                                    </button>
                                                    <%}%>
                                                </form>
                                            </div>


                                        </h4>
                                        <button type="button"
                                                class="btn btn-primary btn-sm disabled"><%=incidencia_send_jsp.getEstadoIncidencia()%>
                                        </button>


                                    </div>


                                    <!-- Card Body -->
                                    <div class="card-body">
                                        <div><h4>Descripción:</h4></div>
                                        <%=incidencia_send_jsp.getDescripcion()%>
                                        <div><br><h4>Zona PUCP:</h4></div>
                                        <%=incidencia_send_jsp.getZonaPucp().getNombreZona()%>
                                        <div><br><h4>Ubicación: </h4></div>
                                        <p>
                                            <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"
                                                    style="background-color: red;border:red">Ver en el mapa
                                            </button>
                                        </p>
                                        <div class="col-lg-8">
                                        <div class="collapse" id="collapseExample">
                                            <div class="card card-body">
                                                <div class="wrapper" style="position: relative; overflow: hidden;padding-top:56.25%">
                                                    <iframe style="position: absolute;top:0;left:0;width:100%;height:100%" loading="lazy" allowfullscreen
                                                            src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAIaTIoLtWkd_HzedbV4wzDF596WAbcPBo&q=<%=incidencia_send_jsp.getLatitud()%>,<%=incidencia_send_jsp.getLongitud()%>&zoom=19"></iframe>
                                                </div>
                                            </div>
                                        </div>
                                        </div>

                                        <div><br><h4>Fotos:</h4></div>
                                        <div class="col-12">
                                            <br>
                                            <div class="col-auto">
                                                <img class="crop"
                                                     src="<%=request.getContextPath()%>/Image?action=lista_imagen_sql&id=<%=incidencia_send_jsp.getIdIncidencia()%>"
                                                     style="max-width: 100%;max-height: 100%; border-radius: 1em"/>
                                            </div>
                                            <br>


                                        </div>
                                        <div><br><h4>Tipo de incidencia: </h4></div>
                                        <div>
                                            <a href="#" class="btn btn-primary btn-lg disabled" role="button"
                                               aria-disabled="true"><%=incidencia_send_jsp.getTipo()%>
                                            </a>
                                        </div>


                                        <div><br><h4>Nivel de urgencia: </h4></div>
                                        <div>
                                            <a href="#" class="btn btn-primary btn-lg disabled" role="button"
                                               style="background: red;border: red"
                                               aria-disabled="true"><%=incidencia_send_jsp.getUrgencia()%>
                                            </a>
                                        </div>
                                        <%if(incidencia_send_jsp.getSeguridad() != null){%>
                                        <%if(incidencia_send_jsp.getSeguridad().getIdUsuarios() == 2 ){%>
                                        <%="hola, tienes que borrar esto"%>
                                        <%}%>
                                        <%}%>


                                        <%int contador = 0;%>
                                        <%if (comentario2.getContenido1() != null) {%>
                                        <div>
                                            <%contador = 1;%>
                                            <br><h5>Comentario de Seguridad: </h5>
                                            <%=comentario2.getContenido1()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido2() != null) {%>
                                        <%contador = 2;%>
                                        <div style="text-align: right"><br><h5>Comentario
                                            de <%=usuarioSession.getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido2()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido3() != null) {%>
                                        <div>
                                            <%contador = 3;%>
                                            <br><h5>Comentario de Seguridad: </h5>
                                            <%=comentario2.getContenido3()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido4() != null) {%>
                                        <%contador = 4;%>
                                        <div style="text-align: right"><br><h5>Comentario
                                            de <%=usuarioSession.getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido4()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido5() != null) {%>
                                        <div>
                                            <%contador = 5;%>
                                            <br><h5>Comentario de Seguridad: </h5>
                                            <%=comentario2.getContenido5()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido6() != null) {%>
                                        <%contador = 6;%>
                                        <div style="text-align: right"><br><h5>Comentario
                                            de <%=usuarioSession.getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido6()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido7() != null) {%>
                                        <div>
                                            <%contador = 7;%>
                                            <br><h5>Comentario de Seguridad: </h5>
                                            <%=comentario2.getContenido7()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido8() != null) {%>
                                        <%contador = 8;%>
                                        <div style="text-align: right"><br><h5>Comentario
                                            de <%=usuarioSession.getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido8()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido9() != null) {%>
                                        <div>
                                            <%contador = 9;%>
                                            <br><h5>Comentario de Seguridad: </h5>
                                            <%=comentario2.getContenido9()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido10() != null) {%>
                                        <%contador = 10;%>
                                        <div style="text-align: right"><br><h5>Comentario
                                            de <%=usuarioSession.getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido10()%>
                                        </div>
                                        <%}%>

                                        <%int value_enviar = 0;%>
                                        <%int i;%>
                                        <%int variable = 0;%>
                                        <%int n = 0;%>
                                        <%for (i = 1; i <= 10; i++) {%>
                                        <%if (i == contador) {%>
                                        <%value_enviar = i;%>
                                        <%variable = contador;%>
                                        <%if (variable == 1 || variable == 2) {%>
                                        <%n = 5;%>
                                        <%}%>
                                        <%if (variable == 3 || variable == 4) {%>
                                        <%n = 4;%>
                                        <%}%>
                                        <%if (variable == 5 || variable == 6) {%>
                                        <%n = 3;%>
                                        <%}%>
                                        <%if (variable == 7 || variable == 8) {%>
                                        <%n = 2;%>
                                        <%}%>
                                        <%if (variable == 9 || variable == 10) {%>
                                        <%n = 1;%>
                                        <%}%>

                                        <%}%>
                                        <%}%>

                                        <form method="post">
                                            <input class="form-control " type="hidden"
                                                   name="id" value="<%=usuarioSession.getIdUsuarios()%>">
                                            <input class="form-control" type="hidden"
                                                   name="usuario_quiere_comentar"
                                                   value="<%=comentario2.getValidacion_usuario_comentar()%>">
                                            <input class="form-control " type="hidden"
                                                   name="id_incidencia"
                                                   value="<%=incidencia_send_jsp.getIdIncidencia()%>">
                                            <input class="form-control " type="hidden"
                                                   name="ultima_columna_llena" value="<%=value_enviar%>">
                                            <%if (variable % 2 == 0) {%>
                                            <%if (incidencia_send_jsp.getIdUsuarioQueCreoIncidencia() == usuarioSession.getIdUsuarios()) {%>

                                            <%
                                                if (incidencia_send_jsp.getEstadoIncidencia().equalsIgnoreCase("Registrado")
                                                        || incidencia_send_jsp.getEstadoIncidencia().equalsIgnoreCase("En proceso")) {
                                            %>

                                            <div class="row">
                                                <%if (n != 0) {%>
                                                <div class="col-auto">
                                                    <br>
                                                    <div>
                                                        <button type="submit"
                                                                formaction="<%=request.getContextPath()%>/Inicio?action=AceptarResultado"
                                                                class="btn btn-primary btn-lg">
                                                            Aceptar Respuesta
                                                        </button>
                                                    </div>


                                                </div>

                                                <%if( n-1 != 0 ){%>

                                                <div class="col-auto">
                                                    <br>
                                                    <div>
                                                        <button type="button"
                                                                class="btn btn-primary btn-lg disabled ">
                                                            Reabrir(<%=n - 1%>)
                                                        </button>
                                                    </div>
                                                </div>
                                                <%}%>

                                                <%} else {%>
                                                <div class="col-auto">
                                                    <br>
                                                    <div>
                                                        <button type="button"
                                                                class="btn btn-primary btn-lg disabled"
                                                                style="background-color: yellow; border: yellow; color: black">
                                                            Esperando Respuesta del Seguridad . . .
                                                        </button>
                                                    </div>
                                                </div>
                                                <%}%>
                                            </div>
                                            <%} else if (incidencia_send_jsp.getEstadoIncidencia().equalsIgnoreCase("Atendido")) {%>
                                                <%}%>
                                            <%}%>

                                            <%} else {%>

                                            <%if (incidencia_send_jsp.getIdUsuarioQueCreoIncidencia() == usuarioSession.getIdUsuarios()) {%>

                                            <%if (incidencia_send_jsp.getEstadoIncidencia().equalsIgnoreCase("Registrado") ||
                                                    incidencia_send_jsp.getEstadoIncidencia().equalsIgnoreCase("En proceso") ||
                                                    incidencia_send_jsp.getEstadoIncidencia().equalsIgnoreCase("Atendido")) {%>

                                            <%if (comentario2.getValidacion_usuario_comentar() == 0) {%>

                                            <div class="row">
                                                <div class="col-auto">
                                                    <br>
                                                    <div>
                                                        <button type="submit"
                                                                formaction="<%=request.getContextPath()%>/Inicio?action=AceptarResultado"
                                                                class="btn btn-primary btn-lg">
                                                            Aceptar Respuesta
                                                        </button>
                                                    </div>

                                                </div>
                                                <div class="col-auto">

                                                    <br>
                                                    <div>
                                                        <button type="submit"
                                                                formaction="<%=request.getContextPath()%>/Inicio?action=Usuario_reabre_incidencia"
                                                                class="btn btn-primary btn-lg">Reabrir
                                                            (<%=n%>)
                                                        </button>
                                                    </div>

                                                </div>
                                            </div>
                                            <%} else if (comentario2.getValidacion_usuario_comentar() == 1) {%>
                                            <br>
                                            <div class="row">
                                                <div style="text-align: right"><br><h5>Comentario
                                                    de <%=usuarioSession.getNombreYApellido()%>: </h5>
                                                </div>

                                                <div class="form-group mb-3">
                                                                    <textarea class="form-control"
                                                                              id="exampleFormControlTextarea1"
                                                                              rows="3" name="mensaje"></textarea>
                                                </div>
                                                <div class="col-auto">
                                                    <br>
                                                    <div>
                                                        <button type="submit"
                                                                formaction="<%=request.getContextPath()%>/Inicio?action=Usuario_reabre_incidencia"
                                                                class="btn btn-primary btn-lg">
                                                            Enviar
                                                        </button>
                                                    </div>
                                                </div>

                                            </div>
                                            <%}%>
                                            <%}else if (incidencia_send_jsp.getEstadoIncidencia().equalsIgnoreCase("Resuelto")) {%>

                                            <%}%>


                                            <%}%>
                                            <%}%>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>

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

