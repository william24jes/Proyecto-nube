<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyectoingweb.servlets.model.daos.DaoUsuarios" %>
<%@ page import="com.example.proyectoingweb.servlets.ServletsUsuario.ServletUsuarioInicio" %>
<%@ page import="com.example.proyectoingweb.servlets.model.beans.Usuarios" %>
<%@ page import="com.example.proyectoingweb.servlets.AdminServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userAdmin" scope="session" type="com.example.proyectoingweb.servlets.model.beans.Usuarios"
             class="com.example.proyectoingweb.servlets.model.beans.Usuarios"/>

<%
    ArrayList<String> categorias = (ArrayList<String>) request.getAttribute("categorias");
    String searchText = (String) request.getAttribute("searchText");
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
              rel="stylesheet">
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/600382161a.js" crossorigin="anonymous"></script>
        <link href="assets/css/style_administrador.css" rel="stylesheet">

        <title>Perfil / Incidencias PUCP</title>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            if (userAdmin == null) {
                response.sendRedirect(request.getContextPath());
            }
        %>

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">

    </head>

    <body>

        <!-- ======= Top Bar ======= -->
        <section style="background-color: crimson" id="topbar" class="d-flex align-items-center">
            <div class="container d-flex justify-content-center justify-content-md-between">
                <div class="contact-info d-flex align-items-center">
                </div>
            </div>
        </section>

        <!-- ======= Header ======= -->
        <header style="background-color: white" id="header" class="d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">

                <div class="d-md-flex align-items-center">
                    <div>
                        <h1 class="name" style="margin-top: 25px"><%=userAdmin.getNombreYApellido()%>
                            <br><h6>Administrador</h6></br>
                        </h1>
                    </div>
                </div>
                <nav id="navbar" class="navbar">
                    <ul>
                        <li><a class="nav-link scrollto " href="<%=request.getContextPath()%>/Admin?action=listar">Lista
                            de usuarios</a></li>
                        <li><a class="nav-link scrollto active"
                               href="<%=request.getContextPath()%>/Admin?action=perfil">Perfil</a></li>
                        <li>
                            <div class="container-fluid">
                                <form method="post" class="d-flex input-group w-auto"
                                      action="<%=request.getContextPath()%>/Admin?action=buscar">
                                    <input type="text"
                                           name="searchText"
                                           class="form-control rounded"
                                           placeholder="Buscar usuario"
                                           id="floatingInput" aria-label="Search" aria-describedby="search-addon"
                                           value="<%=searchText != null? searchText:""%>"/>
                                    <span class="input-group-text border-0" id="search-addon"><i
                                            class="fas fa-search"></i></span>
                                </form>
                            </div>
                        </li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Admin?action=cerrarSesion">Cerrar
                            sesión</a></li>
                    </ul>

                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav><!-- .navbar -->

            </div>
        </header><!-- End Header -->

        <main id="main" data-aos="fade-up">

            <section class="section profile">
                <div class="container">
                    <div class="container-fluid">
                        <div class="col-xl px-4 mt-4">

                            <div class="row">
                                <div class="col-xl-4">
                                    <div class="card mb-4 mb-xl-0">
                                        <div class="card-body profile-card pt-3 d-flex flex-column align-items-center">


                                            <div style="display: inline-block; position: relative; width: 150px; height: 150px; overflow: hidden; border-radius: 50%;">
                                                <% DaoUsuarios daoUsersRegistro1 = new DaoUsuarios();%>
                                                <%AdminServlet serv1 = new AdminServlet();%>
                                                <%ArrayList<Usuarios> lista_Usuarios1 = daoUsersRegistro1.obtenerlistaFotoNull();%>
                                                <%boolean validacion1 = serv1.Admin_tiene_foto_null(lista_Usuarios1, userAdmin.getIdUsuarios());%>
                                                <%if (validacion1) {%>
                                                <img src="assets/img/fotosPerfil/perfilDefault.png"
                                                     height="150" width="150" alt="Profile"
                                                     style="width: auto; height: 100%; margin-left: -10px;">
                                                <%} else {%>
                                                <img height="150" width="150" alt="Profile"
                                                     id="profileImage"
                                                     src="<%=request.getContextPath()%>/Image?action=lista_imagen_perfil_sql&id=<%=userAdmin.getIdUsuarios()%>"
                                                     style="width: auto; height: 100%; margin-left: -10px;"/>
                                                <%}%>
                                            </div>
                                            <h2><%=userAdmin.getNombreYApellido()%>
                                            </h2>
                                            <h5>Administrador</h5>

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
                                                        <div class="col-lg-3 col-md-4 label ">Código</div>
                                                        <div class="col-lg-9 col-md-8"><%=userAdmin.getCodigoPucp()%>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label ">Nombres</div>
                                                        <div class="col-lg-9 col-md-8"><%=userAdmin.getNombres()%>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label ">Apellidos</div>
                                                        <div class="col-lg-9 col-md-8"><%=userAdmin.getApellidos()%>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Correo</div>
                                                        <div class="col-lg-9 col-md-8"><%=userAdmin.getCorreoPucp()%>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">DNI</div>
                                                        <div class="col-lg-9 col-md-8"><%=userAdmin.getDni()%>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Celular</div>
                                                        <div class="col-lg-9 col-md-8"><%=userAdmin.getCelular()%>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Categoría</div>
                                                        <div class="col-lg-9 col-md-8"><%=userAdmin.getCategorias()%>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Rol</div>
                                                        <div class="col-lg-9 col-md-8"><%=userAdmin.getRol()%>
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">

                                                    <!-- Profile Edit Form -->
                                                    <form class="needs-validation" method="post"
                                                          action="<%=request.getContextPath()%>/Admin?action=CambiarDatos"
                                                          novalidate enctype="multipart/form-data">
                                                        <input class="form-control" id="idUsuario" type="hidden"
                                                               name="id" value="<%=userAdmin.getIdUsuarios()%>">

                                                        <div class="row mb-3">
                                                            <label for="profileImage"
                                                                   class="col-md-4 col-lg-3 col-form-label">Imagen de
                                                                Perfil</label>
                                                            <div class="col-md-8 col-lg-9">

                                                                <div style="display: inline-block; position: relative; width: 150px; height: 150px; overflow: hidden; border-radius: 50%;">
                                                                    <% DaoUsuarios daoUsersRegistro2 = new DaoUsuarios();%>
                                                                    <%AdminServlet serv2 = new AdminServlet();%>
                                                                    <%ArrayList<Usuarios> lista_Usuarios2 = daoUsersRegistro2.obtenerlistaFotoNull();%>
                                                                    <%boolean validacion2 = serv2.Admin_tiene_foto_null(lista_Usuarios2, userAdmin.getIdUsuarios());%>
                                                                    <%if (validacion2) {%>
                                                                    <img src="assets/img/fotosPerfil/perfilDefault.png"
                                                                         height="150" width="150" alt="Profile"
                                                                         style="width: auto; height: 100%; margin-left: -10px;">
                                                                    <%} else {%>
                                                                    <img height="150" width="150" alt="Profile"
                                                                         id="profileImage"
                                                                         src="<%=request.getContextPath()%>/Image?action=lista_imagen_perfil_sql&id=<%=userAdmin.getIdUsuarios()%>"
                                                                         style="width: auto; height: 100%; margin-left: -10px;"/>
                                                                    <%}%>
                                                                </div>
                                                                <div class="pt-2">
                                                                    <input style="display: none;" type="file"
                                                                           id="foto_subida" name="foto_subida"
                                                                           accept="image/*">
                                                                    <label for="foto_subida"
                                                                           class="btn btn-primary btn-sm"><i
                                                                            class="bi bi-upload"></i></label>
                                                                    <button type="submit" class="btn btn-danger btn-sm"
                                                                            formaction="<%=request.getContextPath()%>/Admin?action=Borrar_Foto_Perfil"
                                                                            title="Remove my profile image"><i
                                                                            class="bi bi-trash"></i></button>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <input name="ID Usuario" type="hidden" class="form-control"
                                                               id="ID Usuario" value="<%=userAdmin.getIdUsuarios()%>">

                                                        <div class="row mb-3">
                                                            <label for="Codigo"
                                                                   class="col-md-4 col-lg-3 col-form-label">Código</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input minlength="8" maxlength="8" required
                                                                       name="Codigo"
                                                                       type="number" class="form-control" id="Codigo"
                                                                       value="<%=userAdmin.getCodigoPucp()%>">
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <label for="Nombres"
                                                                   class="col-md-4 col-lg-3 col-form-label">Nombres</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input required name="Nombres" type="text"
                                                                       class="form-control" id="Nombres"
                                                                       value="<%=userAdmin.getNombres()%>">
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <label for="Apellidos"
                                                                   class="col-md-4 col-lg-3 col-form-label">Apellidos</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input required name="Apellidos" type="text"
                                                                       class="form-control" id="Apellidos"
                                                                       value="<%=userAdmin.getApellidos()%>">
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <label for="Correo"
                                                                   class="col-md-4 col-lg-3 col-form-label">Correo</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input required name="Correo PUCP" type="text"
                                                                       class="form-control" id="Correo"
                                                                       value="<%=userAdmin.getCorreoPucp()%>">
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <label for="DNI"
                                                                   class="col-md-4 col-lg-3 col-form-label">DNI</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input required name="DNI" type="number"
                                                                       class="form-control" id="DNI"
                                                                       value="<%=userAdmin.getDni()%>">
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <label for="Celular"
                                                                   class="col-md-4 col-lg-3 col-form-label">Celular</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <input required min="1" max="999999999999"
                                                                       name="Celular"
                                                                       type="number" class="form-control" id="Celular"
                                                                       value="<%=userAdmin.getCelular()%>">
                                                            </div>
                                                        </div>

                                                        <input name="Rol" type="hidden" class="form-control" id="Rol"
                                                               value="<%=userAdmin.getRol()%>">

                                                        <div class="row mb-3">
                                                            <label for="Categoría"
                                                                   class="col-md-4 col-lg-3 col-form-label">Categoría</label>
                                                            <div class="col-md-8 col-lg-9">
                                                                <select disabled class="form-select"
                                                                        name="Categoría">
                                                                    <%for (String categoria : categorias) {%>
                                                                    <option value="<%=categoria%>" <%=categoria.equals(userAdmin.getCategorias()) ? "selected" : ""%>><%=categoria%>
                                                                    </option>
                                                                    <%}%>
                                                                </select>
                                                            </div>

                                                            <%//NO BORRAR, NO ES DUPLICADO%>
                                                            <div class="col-md-8 col-lg-9">
                                                                <select hidden class="form-select" id="Categoría"
                                                                        name="Categoría">
                                                                    <%for (String categoria : categorias) {%>
                                                                    <option value="<%=categoria%>" <%=categoria.equals(userAdmin.getCategorias()) ? "selected" : ""%>><%=categoria%>
                                                                    </option>
                                                                    <%}%>
                                                                </select>
                                                            </div>

                                                        </div>

                                                        <div class="text-center">
                                                            <button type="submit"
                                                                    class="btn btn-primary"
                                                                    formaction="<%=request.getContextPath()%>/Admin?action=CambiarDatos"
                                                            >Guardar
                                                            </button>
                                                        </div>
                                                    </form><!-- End Profile Edit Form -->
                                                </div>

                                                <div class="tab-pane fade pt-3" id="profile-change-password">
                                                    <!-- Change Password Form -->

                                                    <form class="needs-validation" method="post"
                                                          action="<%=request.getContextPath()%>/Admin?action=actualizarPassword"
                                                          novalidate>

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

                                                        <%if (session.getAttribute("msgError") != null) {%>
                                                        <p class="text-danger"><%=session.getAttribute("msgError")%>
                                                        </p>
                                                        <%
                                                                session.removeAttribute("msgError");
                                                            }
                                                        %>

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

        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict'

                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.querySelectorAll('.needs-validation')

                // Loop over them and prevent submission
                Array.prototype.slice.call(forms)
                    .forEach(function (form) {
                        form.addEventListener('submit', function (event) {
                            if (!form.checkValidity()) {
                                event.preventDefault()
                                event.stopPropagation()
                            }

                            form.classList.add('was-validated')
                        }, false)
                    })
            })()
        </script>

    </body>

</html>