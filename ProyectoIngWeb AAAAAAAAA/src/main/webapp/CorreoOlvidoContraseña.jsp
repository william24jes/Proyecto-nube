<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Olvidaste tu contraseña</title>

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
              rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style_inicio_sesion.css" rel="stylesheet">

    </head>

    <body>

        <main>
            <div class="container-login100" style="background-image:url(assets/img/fondo_inicio_sesion.png);">
                <section
                        class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
                    <div class="container">
                        <div class="row justify-content-end">
                            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

                                <div class="d-none d-lg-block justify-content-center py-4">
                                    <div class="logo d-flex align-items-center w-auto">
                                        <img src="assets/img/telepucp.png" alt="Logo" style="height: 200px;">
                                        <span class="d-none d-lg-block">INCIDENCIAS PUCP</span>
                                    </div>
                                </div><!-- End Logo -->

                                <div class="card mb-3">

                                    <div class="card-body">

                                        <div class="pt-4 pb-2">
                                            <h5 class="card-title text-center pb-0 fs-4">¿Olvidaste tu contraseña?</h5>
                                            <p class="text-center small">Se ha enviado un correo a su dirección de
                                                correo electrónico en el cual encontrarás un link
                                                para restablecer tu contraseña. Si no encuentras este correo en tu
                                                bandeja de entrada, revisa las carpetas de SPAM o correos no deseado
                                                antes de enviar una nueva solicitud.</p>
                                        </div>

                                        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                            <!--<a class="nav-link  "
                                               href="<%=request.getContextPath()%>/IniciarSesion?action=reenviarCorreo">
                                                <button type="button" style="margin-right: 10px;"
                                                        class="btn btn-primary">Reenviar correo
                                                </button>
                                            </a>-->
                                            <a class="nav-link  " href="<%=request.getContextPath()%>/IniciarSesion">
                                                <button style="margin-left: 10px;" class="btn btn-primary">Volver a
                                                    inicio
                                                </button>
                                            </a>
                                        </div>

                                    </div>
                                </div>

                            </div>
                        </div>

                </section>

            </div>
        </main><!-- End #main -->

        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
                class="bi bi-arrow-up-short"></i></a>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/chart.js/chart.min.js"></script>
        <script src="assets/vendor/echarts/echarts.min.js"></script>
        <script src="assets/vendor/quill/quill.min.js"></script>
        <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>

    </body>

</html>
