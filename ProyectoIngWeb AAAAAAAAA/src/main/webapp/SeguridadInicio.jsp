<%@ page import="com.example.proyectoingweb.servlets.model.beans.Incidencias" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoUsuarios" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="seguridadSession" scope="session" type="com.example.proyectoingweb.servlets.model.beans.Usuarios"
             class="com.example.proyectoingweb.servlets.model.beans.Usuarios"/>
<%
    ArrayList<Incidencias> listaPermanente = (ArrayList<Incidencias>) request.getAttribute("listaIncidenciasPermanente");
    ArrayList<Incidencias> listaPaginada = (ArrayList<Incidencias>) request.getAttribute("listaIncidenciasPaginada");

%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Inicio / Incidencias PUCP</title>
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
                        <li><a class="nav-link active"
                               href="<%=request.getContextPath()%>/Seguridad?action=inicioSeguridad">Inicio</a>
                        </li>
                        <li><a class="nav-link"
                               href="<%=request.getContextPath()%>/Seguridad?action=perfil">Perfil</a></li>
                        <li><a class="nav-link"
                               href="<%=request.getContextPath()%>/Seguridad?action=cerrarSesion">Cerrar
                            sesión</a></li>
                    </ul>

                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav><!-- .navbar -->

            </div>
        </header><!-- End Header -->

        <main id="main" data-aos="fade-up">

            <%if(session.getAttribute("msg")=="Contraseña cambiada correctamente"){%>
            <div class="alert alert-success" role="alert"><%=session.getAttribute("msg")%>
            </div>
            <%session.removeAttribute("msg");%>
            <%}%>
            <!-- ======= Portfolio Details Section ======= -->
            <section id="portfolio-details" class="portfolio-details">
                <div class="container">
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Incidencias PUCP</h1>
                            <a class="nav-link"
                               href="<%=request.getContextPath()%>/Seguridad?action=incidenciasPDF">Generar Reporte</a>
                        </div>


                        <div class="container">
                            <% for (Incidencias i : listaPaginada) {%>

                            <div class="col-lg-10 mx-auto">

                                <!-- Dropdown Card Example -->

                                <a class="nav-link  "
                                   href="<%=request.getContextPath()%>/Seguridad?action=detalles&id=<%=i.getIdIncidencia()%>">
                                    <div class="card shadow mb-4">
                                        <!-- Card Header - Dropdown -->
                                        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                            <img src="<%=i.getAnonimo()==1?"assets/img/fotosPerfil/perfilDefault.png":"assets/img/fotosPerfil/"+i.getUsuario().getFotoPerfil()%>"
                                                 height="70px"/>
                                            <br>
                                            <div></div>
                                            <h4 class="m-0 font-weight-bold text-primary"
                                                style="width:75%"><%=i.getNombre()%>
                                                <div style="color:darkgray;display: flex;flex-direction: column">
                                                    <h6><%=(i.getAnonimo() == 1) ? "Anónimo" : i.getUsuario().getNombres() + " " + i.getUsuario().getApellidos()%>
                                                    </h6>
                                                    <h6>Registrado </h6></div>
                                                <div><a class="btn btn-warning btn-circle disabled">
                                                    <i class="fas fa-exclamation-triangle"><%=i.getDestacado()%>
                                                    </i>
                                                </a></div>
                                            </h4>
                                            <button type="button"
                                                    class="btn btn-primary btn-sm"><%=i.getEstadoIncidencia()%>
                                            </button>
                                        </div>
                                    </div>
                                </a>

                            </div>
                            <% }%>


                        </div>
                        <nav aria-label="Page navigation example" style="margin-top: 20px;">
                            <ul class="pagination justify-content-center">
                                <%for (double j = 1; j <= Math.ceil((double) (listaPermanente.size()) / 16.0); j++) {%>
                                <li class="page-item"><a class="page-link"
                                                         href="<%=request.getContextPath()%>/Seguridad?action=page&id=<%=(int)j%>"><%=(int) j%>
                                </a></li>
                                <%}%>
                            </ul>
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