<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoUsuarios" %>
<%@ page import="com.example.proyectoingweb.servlets.ServletsUsuario.ServletUsuarioInicio" %>
<%@ page import="com.example.proyectoingweb.servlets.model.beans.Usuarios" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyectoingweb.servlets.ServletSeguridadInicio" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="seguridadSession" scope="session" type="com.example.proyectoingweb.servlets.model.beans.Usuarios"
             class="com.example.proyectoingweb.servlets.model.beans.Usuarios"/>
<%
    String searchText = (String) request.getAttribute("searchText");
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Perfil / Incidencias PUCP</title>
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
                            <br><h6>Seguridad PUCP</h6></br>
                        </h1>
                    </div>
                </div>
                <nav id="navbar" class="navbar">
                    <ul>
                        <li>
                            <div class="container-fluid">
                                <form method="post" class="d-flex input-group w-auto"
                                      action="<%=request.getContextPath()%>/Seguridad?action=buscar">
                                    <input type="text"
                                           name="searchText"
                                           class="form-control rounded"
                                           placeholder="Buscar incidencia"
                                           id="floatingInput" aria-label="Search" aria-describedby="search-addon"
                                           value="<%=searchText != null? searchText:""%>"/>
                                    <span class="input-group-text border-0" id="search-addon"><i
                                            class="fas fa-search"></i></span>
                                </form>
                            </div>
                        </li>
                        <li><a class="nav-link"
                               href="<%=request.getContextPath()%>/Seguridad?action=inicioSeguridad">Inicio</a>
                        </li>
                        <li><a class="nav-link active"
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

            <%if(session.getAttribute("msg")!=null){%>
            <div class="alert alert-success" role="alert"><%=session.getAttribute("msg")%>
            </div>
            <%session.removeAttribute("msg");%>
            <%}%>
            <section class="section profile">
                <div class="container">
                    <div class="container-fluid">
                        <div class="col-xl px-4 mt-4">

                            <div class="row">
                                <div class="col-xl-4">
                                    <div class="card mb-4 mb-xl-0">
                                        <div class="card-body profile-card pt-3 d-flex flex-column align-items-center">
                                            <div style="display: inline-block; position: relative; width: 200px; height: 200px; overflow: hidden; border-radius: 50%;">
                                                <% DaoUsuarios daoUsersRegistro = new DaoUsuarios();%>
                                                <%ServletSeguridadInicio serv2 = new ServletSeguridadInicio();%>
                                                <%ArrayList<Usuarios> lista_Usuarios = daoUsersRegistro.obtenerlistaFotoNull();%>
                                                <%boolean validacion = serv2.Seguridad_tiene_foto_null(lista_Usuarios,seguridadSession.getIdUsuarios());%>
                                                <%if (validacion) {%>
                                                <img src="assets/img/fotosPerfil/perfilDefault.png"
                                                     width=70% alt="Profile" style="width: auto; height: 100%; margin-left: -10px;">
                                                <%}else{%>
                                                <img alt="Profile"
                                                     src="<%=request.getContextPath()%>/Image?action=lista_imagen_perfil_sql&id=<%=seguridadSession.getIdUsuarios()%>"
                                                     width=70% alt="Profile" style="width: auto; height: 100%; margin-left: -10px;">
                                                <%}%>
                                            </div>

                                            <h2><%=seguridadSession.getNombreYApellido()%>
                                            </h2>
                                            <h5>Seguridad PUCP</h5>

                                        </div>
                                    </div>
                                </div>

                                <div class="col-xl-8">
                                    <div class="card mb-4">
                                        <div class="card-body pt-3">
                                            <!-- Bordered Tabs -->
                                            <ul class="nav nav-tabs nav-tabs-bordered">

                                                <li class="nav-item">
                                                    <button class="nav-link active" data-bs-toggle="tab"
                                                            data-bs-target="#profile-overview">Detalles
                                                    </button>
                                                </li>

                                                <li class="nav-item">
                                                    <button class="nav-link" data-bs-toggle="tab"
                                                            data-bs-target="#profile-edit">Editar Perfil
                                                    </button>
                                                </li>

                                                <li class="nav-item">
                                                    <button class="nav-link" data-bs-toggle="tab"
                                                            data-bs-target="#profile-change-password">Cambiar contraseña
                                                    </button>
                                                </li>

                                            </ul>
                                            <div class="tab-content pt-2">

                                                <div class="tab-pane fade show active profile-overview"
                                                     id="profile-overview">

                                                    <h5 class="card-title">Detalles del Perfil</h5>

                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label ">Nombre completo</div>
                                                        <div class="col-lg-9 col-md-8"><%=seguridadSession.getNombres()%> <%=seguridadSession.getApellidos()%>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label ">Rol</div>
                                                        <div class="col-lg-9 col-md-8">Seguridad PUCP</div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Dni</div>
                                                        <div class="col-lg-9 col-md-8"><%=seguridadSession.getDni()%>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Telefono</div>
                                                        <div class="col-lg-9 col-md-8"><%=seguridadSession.getCelular()%>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Código</div>
                                                        <div class="col-lg-9 col-md-8"><%=seguridadSession.getCodigoPucp()%>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Correo</div>
                                                        <div class="col-lg-9 col-md-8"><%=seguridadSession.getCorreoPucp()%>
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">

                                                    <!-- Profile Edit Form -->
                                                    <form method="post"
                                                          action="<%=request.getContextPath()%>/Seguridad?action=CambiarTelefono" enctype="multipart/form-data">
                                                        <input class="form-control " id="idUsuario" type="hidden"
                                                               name="id" value="<%=seguridadSession.getIdUsuarios()%>">

                                                        <div class="row mb-3">
                                                            <label
                                                                   class="col-md-4 col-lg-3 col-form-label">Imagen de
                                                                Perfil</label>
                                                            <div class="col-md-8 col-lg-9">

                                                                <div style="display: inline-block; position: relative; width: 200px; height: 200px; overflow: hidden; border-radius: 50%;">
                                                                    <% DaoUsuarios daoUsersRegistro4 = new DaoUsuarios();%>
                                                                    <%ServletSeguridadInicio serv4 = new ServletSeguridadInicio();%>
                                                                    <%ArrayList<Usuarios> lista_Usuarios4 = daoUsersRegistro4.obtenerlistaFotoNull();%>
                                                                    <%boolean validacion2 = serv4.Seguridad_tiene_foto_null(lista_Usuarios4,seguridadSession.getIdUsuarios());%>
                                                                    <%if (validacion2) {%>
                                                                    <img src="assets/img/fotosPerfil/perfilDefault.png"
                                                                         width=70% alt="Profile" style="width: auto; height: 100%; margin-left: -10px;">
                                                                    <%}else{%>
                                                                    <img alt="Profile"
                                                                         src="<%=request.getContextPath()%>/Image?action=lista_imagen_perfil_sql&id=<%=seguridadSession.getIdUsuarios()%>"
                                                                         width=70% alt="Profile" style="width: auto; height: 100%; margin-left: -10px;">
                                                                    <%}%>
                                                                </div>


                                                                <div class="pt-2">
                                                                    <input style="display: none;" type="file" id="foto" name="foto" accept="image/*">
                                                                    <label for="foto" class="btn btn-primary btn-sm"><i class="bi bi-upload"></i></label>
                                                                    <button type="submit" class="btn btn-danger btn-sm"
                                                                            formaction="<%=request.getContextPath()%>/Seguridad?action=Borrar_Foto_Perfil"
                                                                            title="Remove my profile image"><i
                                                                            class="bi bi-trash"></i></button>
                                                                </div>


                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <label for="fullName"
                                                                   class="col-md-4 col-lg-3 col-form-label">Nombre
                                                                Completo</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input name="fullName" type="text" class="form-control"
                                                                       id="fullName"
                                                                       value="<%=seguridadSession.getNombres()%> <%=seguridadSession.getApellidos()%>"
                                                                       disabled>
                                                            </div>



                                                        </div>


                                                        <div class="row mb-3">
                                                            <label for="Email" class="col-md-4 col-lg-3 col-form-label">Correo</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input name="email" type="email" class="form-control"
                                                                       id="Email"
                                                                       value="<%=seguridadSession.getCorreoPucp()%>"
                                                                       disabled>
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">

                                                            <label for="Phone"
                                                                   class="col-md-4 col-lg-3 col-form-label">Telefono</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input name="phone" type="text" class="form-control"
                                                                       id="Phone"
                                                                       value="<%=seguridadSession.getCelular()%>">
                                                            </div>
                                                        </div>

                                                        <div class="text-center">
                                                            <button type="submit"
                                                                    class="btn btn-primary"
                                                                    formaction="<%=request.getContextPath()%>/Seguridad?action=CambiarTelefono"
                                                            >Guardar
                                                            </button>
                                                        </div>

                                                    </form><!-- End Profile Edit Form -->

                                                </div>

                                                <div class="tab-pane fade pt-3" id="profile-settings">

                                                    <!-- Settings Form -->
                                                    <form>

                                                        <div class="row mb-3">
                                                            <label for="fullName"
                                                                   class="col-md-4 col-lg-3 col-form-label"> Permitir
                                                                Notificaciones</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="checkbox"
                                                                           id="changesMade" checked>
                                                                    <label class="form-check-label" for="changesMade">
                                                                        Incidencias urgentes
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="checkbox"
                                                                           id="newProducts" checked>
                                                                    <label class="form-check-label" for="newProducts">
                                                                        Incidencia atendida
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="checkbox"
                                                                           id="proOffers">
                                                                    <label class="form-check-label" for="proOffers">
                                                                        Reclamo de incidencias
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="checkbox"
                                                                           id="securityNotify" checked disabled>
                                                                    <label class="form-check-label"
                                                                           for="securityNotify">
                                                                        Cada nueva incidencia
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="text-center">
                                                            <button type="submit" class="btn btn-primary">Guardar
                                                            </button>
                                                        </div>
                                                    </form><!-- End settings Form -->

                                                </div>

                                                <div class="tab-pane fade pt-3" id="profile-change-password">
                                                    <!-- Change Password Form -->
                                                    <form class="needs-validation" method="post" action="<%=request.getContextPath()%>/Seguridad?action=actualizarPassword" novalidate>

                                                        <div class="row mb-3">
                                                            <label for="currentPassword"
                                                                   class="col-md-4 col-lg-3 col-form-label">Contraseña
                                                                actual</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input required name="password" type="password"
                                                                       class="form-control" id="currentPassword">
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <label for="newPassword"
                                                                   class="col-md-4 col-lg-3 col-form-label">Nueva
                                                                contraseña</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input required name="nuevaPassword1" type="password"
                                                                       class="form-control" id="newPassword">
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <label for="renewPassword"
                                                                   class="col-md-4 col-lg-3 col-form-label">Confirmar
                                                                nueva contraseña</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input required name="nuevaPassword2" type="password"
                                                                       class="form-control" id="renewPassword">
                                                            </div>
                                                        </div>

                                                        <%if(session.getAttribute("msgError") != null){%>
                                                        <p class="text-danger"><%=session.getAttribute("msgError")%></p>
                                                        <%session.removeAttribute("msgError");}%>

                                                        <div class="text-center">
                                                            <button type="submit" class="btn btn-primary">Cambiar
                                                                contraseña
                                                            </button>
                                                        </div>
                                                    </form><!-- End Change Password Form -->

                                                </div>

                                            </div><!-- End Bordered Tabs -->

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-8">


                        </div>
                    </div>
                </div>
            </section>

        </main><!-- End #main -->


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
