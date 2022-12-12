<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Registrarse</title>

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

        <link href="assets/css/style_registro.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    </head>

    <body>

        <main>
            <div class="container-login100" style="background-image:url(assets/img/fondo_registro.png);">
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
                                            <h5 class="card-title text-center pb-0 fs-4">Regístrate</h5>
                                            <p class="text-center small">Ingresa tu correo y código PUCP</p>
                                        </div>

                                        <form class="row g-3 needs-validation" method="post"
                                              action="<%=request.getContextPath()%>/IniciarSesion?post=registrarse"
                                              novalidate>

                                            <div class="col-12">
                                                <label for="yourUsername" class="form-label">Correo</label>
                                                <div class="input-group has-validation">
                                                    <span class="input-group-text" id="inputGroupPrepend">@</span>
                                                    <input type="text" name="correoPucp" class="form-control"
                                                           id="yourUsername" required>
                                                    <div class="invalid-feedback">Ingresa tu correo!</div>
                                                </div>
                                            </div>

                                            <div class="col-12">
                                                <label for="yourCode" class="form-label">Código PUCP</label>
                                                <input type="number" name="codigoPucp" class="form-control" id="yourCode"
                                                       required>
                                                <div class="invalid-feedback">Ingresa tu código!</div>
                                            </div>

                                            <% if (session.getAttribute("msg") != null) { %>
                                            <p class="text-danger small mb-0"><%=session.getAttribute("msg")%>
                                            </p>
                                            <% session.removeAttribute("msg");
                                            } %>

                                            <div class="col-12">
                                                <a class="nav-link">
                                                    <button class="btn btn-primary w-100" type="submit">
                                                        Registrarse
                                                    </button>
                                                </a>
                                            </div>
                                            <div class="col-12">
                                                <p class="small mb-0">¿Ya tienes una cuenta? <a
                                                        href="<%=request.getContextPath()%>/IniciarSesion">Inicia
                                                    sesión aquí</a></p>
                                            </div>
                                        </form>

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

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
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
