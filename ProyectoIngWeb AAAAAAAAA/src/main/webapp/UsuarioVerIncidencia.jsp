<%@ page import="com.example.proyectoingweb.servlets.model.beans.Incidencias" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoIncidencias" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoUsuarios" %>
<%@ page import="com.example.proyectoingweb.servlets.Usuario.ServletUsuarioInicio" %>
<%@ page import="com.example.proyectoingweb.servlets.model.beans.Usuarios" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoComentarios" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="incidencia_send_jsp" scope="request" type="com.example.proyectoingweb.servlets.model.beans.Incidencias"/>
<jsp:useBean id="usuarioSession" scope="session" type="com.example.proyectoingweb.servlets.model.beans.Usuarios"
             class="com.example.proyectoingweb.servlets.model.beans.Usuarios"/>
<jsp:useBean id="comentario2" scope="request" type="com.example.proyectoingweb.servlets.model.beans.Comentarios"/>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Incidencia / Incidencias PUCP</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

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

        <!-- =======================================================
        * Template Name: BizLand - v3.9.0
        * Template URL: https://bootstrapmade.com/bizland-bootstrap-business-template/
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
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
                <!-- Uncomment below if you prefer to use an image logo -->
                <!-- <a href="index.html" class="logo"><img src="assets/img/logo.png" alt=""></a>-->

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
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=listar">Inicio</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=perfil">Perfil</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=misIncidencias">Mis Incidencias</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=registrarIncidencia">Registrar Incidencia</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=cerrarSesion">Cerrar sesión</a></li>
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
                                        <h4 class="m-0 font-weight-bold text-primary" style="width:75%"><%=incidencia_send_jsp.getNombre()%>
                                            <div style="color:darkgray;display: flex;flex-direction: column"><h6><%=incidencia_send_jsp.getNombreUsuarioQueDestaco()%></h6><h6>Registrado <%=incidencia_send_jsp.getDatetime()%></h6></div>
                                            <div>
                                                <form method="post"
                                                                 action="<%=request.getContextPath()%>/Inicio?action=DestacarIncidencia_verIncidencias"
                                            >
                                                <input class="form-control " id="idUsuario" type="hidden"
                                                       name="id" value="<%=usuarioSession.getIdUsuarios()%>">
                                                <input class="form-control" id="Cantidad_de_Destacados" type="hidden"
                                                       name="Cantidad_destacados" value="<%=incidencia_send_jsp.getDestacado()%>">
                                                <input class="form-control" id="idIncidencia" type="hidden"
                                                       name="idIncidencia" value="<%=incidencia_send_jsp.getIdIncidencia()%>">
                                                <% DaoIncidencias daoincidenciasjsp = new DaoIncidencias();%>
                                                <% DaoUsuarios daousersjsp = new DaoUsuarios();%>
                                                <%ServletUsuarioInicio serv = new ServletUsuarioInicio();%>
                                                <%ArrayList<Usuarios> lista_Usuarios =daoincidenciasjsp.IdDeUsuariosQueDestacaron(String.valueOf(incidencia_send_jsp.getIdIncidencia()));%>
                                                <%Usuarios user2 = daousersjsp.buscarPorId(String.valueOf(usuarioSession.getIdUsuarios()));%>
                                                <%boolean validacion = serv.Usuario_destaco_o_no(lista_Usuarios,user2);%>
                                                <%if(validacion) {%>
                                                <button type="submit" class="btn btn-warning btn-circle">
                                                    <i class="fas fa-exclamation-triangle"> <%=incidencia_send_jsp.getDestacado()%>
                                                    </i> Destacada!
                                                </button>
                                                <%} else {%>
                                                <button type="submit" class="btn btn-warning btn-circle" style="background-color: grey;border-color:grey" >
                                                    <i class="fas fa-exclamation-triangle"> <%=incidencia_send_jsp.getDestacado()%>
                                                    </i> (:
                                                </button>
                                                <%}%>
                                            </form>
                                            </div>




                                        </h4>
                                        <button type="button" class="btn btn-primary btn-sm disabled"> <%=incidencia_send_jsp.getEstadoIncidencia()%></button>


                                    </div>


                                    <!-- Card Body -->

                                    <div class="card-body">
                                        <div><h4>Descripción:</h4></div>
                                        <%=incidencia_send_jsp.getDescripcion()%>
                                        <div><br><h4>Zona PUCP:</h4></div>
                                        <%=incidencia_send_jsp.getZonaPucp().getNombreZona()%>
                                        <div><br><h4>Ubicación: </h4></div>
                                        <button type="button" class="btn btn-primary btn-lg"
                                                style="background-color: red;border:red">Ver en el mapa
                                        </button>
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
                                               aria-disabled="true"><%=incidencia_send_jsp.getTipo()%></a>
                                        </div>



                                        <div><br><h4>Nivel de urgencia: </h4></div>
                                        <div>
                                            <a href="#" class="btn btn-primary btn-lg disabled" role="button" style="background: red;border: red"
                                               aria-disabled="true"><%=incidencia_send_jsp.getUrgencia()%></a>
                                        </div>

                                        <div><br><h5>Comentario de Seguridad: </h5>
                                            <%comentario2.getContenido1();%>xdxd
                                            <%comentario2.getIdComentario();%>
                                        </div>

                                        <div style="text-align: right"><br><h5>Comentario de Frank Einstein: </h5>Lorem ipsum dolor
                                            sit amet, consectetur adipisicing elit. Ab accusantium alias amet
                                            dolore doloremque ea, et exercitationem impedit itaque iure maxime neque nobis odit
                                            praesentium ratione rem sit vitae voluptates! Lorem ipsum dolor sit amet,
                                            consectetur
                                            adipisicing elit. Impedit iste nesciunt nulla perspiciatis, quaerat rem
                                            reprehenderit
                                            saepe! Accusantium consectetur, delectus deserunt eum hic nihil provident quos sunt
                                            tempore tenetur veniam?
                                        </div>


                                        <div><br><h5>Comentario de Seguridad: </h5>
                                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusantium alias amet
                                            dolore doloremque ea, et exercitationem impedit itaque iure maxime neque nobis odit
                                            praesentium ratione rem sit vitae voluptates! Lorem ipsum dolor sit amet,
                                            consectetur
                                            adipisicing elit.
                                        </div>
                                        <%if(incidencia_send_jsp.getIdUsuarioQueCreoIncidencia() == usuarioSession.getIdUsuarios()){%>
                                        <div class="row">
                                            <div class="col-auto">
                                                <br>
                                                <div><button type="button" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/Inicio?action=misIncidencias" >Aceptar</button></div>

                                            </div>
                                            <div class="col-auto">
                                                <br>
                                                <div><button type="button" class="btn btn-primary btn-lg"  href="<%=request.getContextPath()%>/Inicio">Reabrir (4)</button></div>
                                            </div>
                                        </div>
                                        <%}%>

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

