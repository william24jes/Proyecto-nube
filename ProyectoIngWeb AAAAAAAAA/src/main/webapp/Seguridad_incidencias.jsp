<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="incidencia" scope="request" type="com.example.proyectoingweb.servlets.model.beans.Incidencias"/>
<jsp:useBean id="seguridadSession" scope="session" type="com.example.proyectoingweb.servlets.model.beans.Usuarios"
             class="com.example.proyectoingweb.servlets.model.beans.Usuarios"/>

<jsp:useBean id="comentario2" scope="request" type="com.example.proyectoingweb.servlets.model.beans.Comentarios"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Seguridad / Incidencia </title>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            if(seguridadSession == null){
                response.sendRedirect(request.getContextPath());
            }
        %>

        <!-- Favicons -->
        <link href="assets/img/telepucp.png" rel="icon">
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
        <link href="assets/css/style_seguridad.css" rel="stylesheet">

    </head>

    <body>

        <!-- ======= Top Bar ======= -->
        <section id="topbar" class="d-flex align-items-center">
            <div class="container d-flex justify-content-center justify-content-md-between">
                <div class="contact-info d-flex align-items-center">
                </div>
            </div>
        </section>

        <!-- ======= Header ======= -->
        <header id="header" class="d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">

                <div class="d-md-flex align-items-center">
                    <div>
                        <h1 class="name" style="margin-top: 25px"><%=seguridadSession.getNombreYApellido()%>
                            <br><h6>Seguridad</h6></br>
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
                        </li>
                        <li><a class="nav-link  "
                               href="<%=request.getContextPath()%>/Seguridad?action=inicioSeguridad">Inicio</a>
                        </li>
                        <li><a class="nav-link scrollto"
                               href="<%=request.getContextPath()%>/Seguridad?action=perfil">Perfil</a></li>
                        <li><a class="nav-link scrollto"
                               href="<%=request.getContextPath()%>/Seguridad?action=cerrarSesion">Cerrar
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
                            <h1 class="h3 mb-0 text-gray-800">Incidencias PUCP</h1>
                        </div>

                        <div class="container">

                            <div class="col-lg-12 mx-auto">
                                <!-- Dropdown Card Example -->
                                <div class="card shadow mb-4 ">
                                    <!-- Card Header - Dropdown -->
                                    <div class="card-header p-5 d-flex flex-row align-items-center justify-content-between">
                                        <img class="d-lg-block"
                                             src="assets/img/fotosPerfil/<%=incidencia.getUsuario().getFotoPerfil()%>"
                                             width="10%"/>
                                        <br>
                                        <div></div>
                                        <h4 class="m-0 font-weight-bold text-primary"
                                            style="width:75%"><%=incidencia.getNombre()%>
                                            <div style="color:darkgray;display: flex;flex-direction: column">
                                                <h6><%=(incidencia.getAnonimo() == 0) ? (incidencia.getUsuario().getNombres() + " " + incidencia.getUsuario().getApellidos()) : "Anónimo"%>
                                                </h6>
                                                <h6>Registrado hoy</h6></div>
                                            <div><a class="btn btn-warning btn-circle disabled">
                                                <i class="fas fa-exclamation-triangle"><%=incidencia.getDestacado()%>
                                                </i>
                                            </a></div>
                                        </h4>
                                        <button type="button"
                                                class="btn btn-primary btn-sm disabled"><%=incidencia.getEstadoIncidencia()%>
                                        </button>


                                    </div>


                                    <!-- Card Body -->

                                    <div class="card-body">
                                        <div><h4>Descripción</h4></div>
                                        <%=incidencia.getDescripcion()%>
                                        <div><br><h4>Zona PUCP:</h4></div>
                                        <%=incidencia.getZonaPucp().getNombreZona()%>
                                        <div><br>
                                            <h4>Ubicación
                                                <div class="col-lg-8">
                                                        <div class="card card-body">
                                                            <div class="wrapper" style="position: relative; overflow: hidden;padding-top:56.25%">
                                                                <iframe style="position: absolute;top:0;left:0;width:100%;height:100%" loading="lazy" allowfullscreen
                                                                        src="https://www.google.com/maps/embed/v1/place?key=AIzaSyDT71SdHy5wcg6PK_JVCcuON5AqLpCcqA4&q=<%=incidencia.getLatitud()%>,<%=incidencia.getLongitud()%>&zoom=19"></iframe>
                                                            </div>
                                                        </div>
                                                </div>
                                            </h4>
                                        </div>

                                        <div><br><h4>Fotos</h4></div>
                                        <div class="col-12">
                                            <br>
                                            <div class="col-auto">
                                                <img class="crop"
                                                     src="<%=request.getContextPath()%>/Image?action=lista_imagen_sql&id=<%=incidencia.getIdIncidencia()%>"
                                                     style="max-width: 100%;max-height: 100%; border-radius: 1em"/>
                                            </div>
                                            <br>


                                        </div>

                                        <div class="row mb-3">
                                            <label class="col-sm-2 col-form-label"><h5>Tipo de incidencia </h5></label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control"
                                                       value="<%=incidencia.getTipo()%>" disabled>
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label class="col-sm-2 col-form-label"><h5>Nivel de urgencia </h5></label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control"
                                                       value="<%=incidencia.getUrgencia()%>" disabled>
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label class="col-sm-2 col-form-label"><h5>Estado </h5></label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control"
                                                       value="<%=incidencia.getEstadoIncidencia()%>" disabled>
                                            </div>
                                        </div>
                                        <%int contador = 0;%>
                                        <%if (comentario2.getContenido1() != null) {%>
                                        <%contador = 1;%>
                                        <div><br><h5>Comentario
                                            de <%=incidencia.getSeguridad().getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido1()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido2() != null) {%>
                                        <div style="text-align: right">
                                            <%contador = 2;%>
                                            <br><h5>Comentario de  <%=incidencia.getUsuario().getNombreYApellido()%>
                                            : </h5>
                                            <%=comentario2.getContenido2()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido3() != null) {%>
                                        <%contador = 3;%>
                                        <div><br><h5>Comentario
                                            de <%=incidencia.getSeguridad().getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido3()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido4() != null) {%>
                                        <div style="text-align: right">
                                            <%contador = 4;%>
                                            <br><h5>Comentario de  <%=incidencia.getUsuario().getNombreYApellido()%>
                                            : </h5>
                                            <%=comentario2.getContenido4()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido5() != null) {%>
                                        <%contador = 5;%>
                                        <div><br><h5>Comentario
                                            de <%=incidencia.getSeguridad().getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido5()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido6() != null) {%>
                                        <div style="text-align: right">
                                            <%contador = 6;%>
                                            <br><h5>Comentario de  <%=incidencia.getUsuario().getNombreYApellido()%>
                                            : </h5>
                                            <%=comentario2.getContenido6()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido7() != null) {%>
                                        <%contador = 7;%>
                                        <div><br><h5>Comentario
                                            de <%=incidencia.getSeguridad().getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido7()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido8() != null) {%>
                                        <div style="text-align: right">
                                            <%contador = 8;%>
                                            <br><h5>Comentario de  <%=incidencia.getUsuario().getNombreYApellido()%>
                                            : </h5>
                                            <%=comentario2.getContenido8()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido9() != null) {%>
                                        <%contador = 9;%>
                                        <div><br><h5>Comentario
                                            de <%=incidencia.getSeguridad().getNombreYApellido()%>: </h5>
                                            <%=comentario2.getContenido9()%>
                                        </div>
                                        <%}%>

                                        <%if (comentario2.getContenido10() != null) {%>
                                        <div style="text-align: right">
                                            <%contador = 10;%>
                                            <br><h5>Comentario de  <%=incidencia.getUsuario().getNombreYApellido()%>
                                            : </h5>
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
                                                   name="id_usuario"
                                                   value="<%=incidencia.getUsuario().getIdUsuarios()%>">
                                            <input class="form-control " type="hidden"
                                                   name="id_security" value="<%=seguridadSession.getIdUsuarios()%>">
                                            <input class="form-control" type="hidden"
                                                   name="usuario_quiere_comentar"
                                                   value="<%=comentario2.getValidacion_usuario_comentar()%>">
                                            <input class="form-control " type="hidden"
                                                   name="id_incidencia"
                                                   value="<%=incidencia.getIdIncidencia()%>">
                                            <input class="form-control " type="hidden"
                                                   name="ultima_columna_llena" value="<%=value_enviar%>">


                                            <%if (variable == 0) { %>
                                            <br>

                                            <div class="form-group mb-3">
                                                                    <textarea class="form-control"
                                                                              rows="3"
                                                                              name="mensaje_aceptar"></textarea>
                                            </div>

                                            <div class="col-auto">
                                                <br>
                                                <div>
                                                    <button type="submit"
                                                            formaction="<%=request.getContextPath()%>/Seguridad?action=Atender_Incidencia"
                                                            class="btn btn-primary btn-lg">
                                                        Atender Incidencia
                                                    </button>
                                                </div>
                                            </div>

                                            <%}%>
                                            <%if(incidencia.getSeguridad() != null){%>
                                            <%if (incidencia.getSeguridad().getIdUsuarios() == seguridadSession.getIdUsuarios() && variable != 0) {%>
                                            <%if (variable % 2 == 1) {%>

                                            <%if (!(incidencia.getEstadoIncidencia().equalsIgnoreCase("Resuelto"))) {%>


                                            <%if (incidencia.getSeguridad().getIdUsuarios() == seguridadSession.getIdUsuarios()) {%>
                                            <%
                                                if (incidencia.getEstadoIncidencia().equalsIgnoreCase("En proceso") ||
                                                        incidencia.getEstadoIncidencia().equalsIgnoreCase("Atendido")) {
                                            %>
                                            <div class="row">
                                                <%if (n != 0) {%>

                                                <div class="col-auto">
                                                    <br>
                                                    <div>
                                                        <button type="button"
                                                                class="btn btn-primary btn-lg disabled "
                                                                style="background-color: yellow; border: yellow; color: black">
                                                            Esperando Respuesta de Usuario . . .
                                                        </button>
                                                    </div>
                                                </div>
                                                <%}%>
                                            </div>
                                            <%}%>
                                            <%}%>

                                            <%} else {%>
                                            <br>
                                            <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                                                <symbol id="check-circle-fill" viewBox="0 0 16 16">
                                                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                </symbol>
                                                <symbol id="info-fill" viewBox="0 0 16 16">
                                                    <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
                                                </symbol>
                                                <symbol id="exclamation-triangle-fill" viewBox="0 0 16 16">
                                                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                                </symbol>
                                            </svg>
                                            <div class="alert alert-success d-flex align-items-center" role="alert">
                                                <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Success:">
                                                    <use xlink:href="#check-circle-fill"/>
                                                </svg>
                                                <div>
                                                    La incidencia fue resuelta con éxito!
                                                </div>
                                            </div>
                                            <%}%>

                                            <%} else {%>
                                            <%if (incidencia.getSeguridad().getIdUsuarios() == seguridadSession.getIdUsuarios()) {%>
                                            <br>
                                            <%if (!(incidencia.getEstadoIncidencia().equalsIgnoreCase("Resuelto"))) {%>
                                            <%if ((n - 1) != 0) {%>
                                            <div class="row">
                                                <div><br><h5>Comentario
                                                    de <%=seguridadSession.getNombreYApellido()%>: </h5>
                                                </div>
                                                <div class="form-group mb-3">
                                                                    <textarea class="form-control"
                                                                              rows="3"
                                                                              name="mensaje_responder"></textarea>
                                                </div>
                                                <div class="col-auto">
                                                    <br>
                                                    <div>
                                                        <button type="submit"
                                                                formaction="<%=request.getContextPath()%>/Seguridad?action=Seguridad_Responder_Incidencia"
                                                                class="btn btn-primary btn-lg">
                                                            Responder (<%=n - 1%>)
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <%} else {%>

                                            <%if (!(incidencia.getEstadoIncidencia().equalsIgnoreCase("Resuelto"))) {%>
                                            <div class="row">
                                                <br>
                                                <div class="col-auto">
                                                    <br>
                                                    <div>
                                                        <button type="submit"
                                                                formaction="<%=request.getContextPath()%>/Seguridad?action=AceptarResultado "
                                                                class="btn btn-primary btn-lg">
                                                            Dar como resuelta la incidencia.
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <%} else {%>
                                            <br>
                                            <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                                                <symbol id="check-circle-fill" viewBox="0 0 16 16">
                                                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                </symbol>
                                                <symbol id="info-fill" viewBox="0 0 16 16">
                                                    <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
                                                </symbol>
                                                <symbol id="exclamation-triangle-fill" viewBox="0 0 16 16">
                                                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                                </symbol>
                                            </svg>
                                            <div class="alert alert-success d-flex align-items-center" role="alert">
                                                <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Success:">
                                                    <use xlink:href="#check-circle-fill"/>
                                                </svg>
                                                <div>
                                                    La incidencia fue resuelta con éxito!
                                                </div>
                                            </div>
                                            <%}%>

                                            <%}%>
                                            <%} else {%>
                                            <br>
                                            <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                                                <symbol id="check-circle-fill" viewBox="0 0 16 16">
                                                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                </symbol>
                                                <symbol id="info-fill" viewBox="0 0 16 16">
                                                    <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
                                                </symbol>
                                                <symbol id="exclamation-triangle-fill" viewBox="0 0 16 16">
                                                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                                </symbol>
                                            </svg>
                                            <div class="alert alert-success d-flex align-items-center" role="alert">
                                                <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Success:">
                                                    <use xlink:href="#check-circle-fill"/>
                                                </svg>
                                                <div>
                                                    La incidencia fue resuelta con éxito!
                                                </div>
                                            </div>
                                            <%}%>
                                            <%}%>
                                            <%}%>
                                            <%} else if (incidencia.getSeguridad().getIdUsuarios() != seguridadSession.getIdUsuarios() && variable != 0) {%>
                                            <div class="row">
                                                <div class="col-auto">
                                                    <br>
                                                    <div>
                                                        <button type="button"
                                                                class="btn btn-primary btn-lg disabled"
                                                                style="background-color: yellow; border: yellow; color: black">
                                                            Esta incidencia fue atendida por el
                                                            Seguridad: <%=incidencia.getSeguridad().getNombreYApellido()%>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <%}%>
                                            <%}%>

                                        </form>


                                        <div class="row">

                                            <div class="col-sm-1">
                                                <br>
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-danger">
                                                        <i class="bi bi-filetype-pdf"></i>
                                                    </button>
                                                    <button type="button" class="btn btn-success">
                                                        <i class="bi bi-filetype-xls"></i>
                                                    </button>
                                                    <button type="button" class="btn btn-primary">
                                                        <i class="bi bi-filetype-txt"></i>
                                                    </button>
                                                </div>
                                            </div>

                                        </div>



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
