<%--
  Created by IntelliJ IDEA.
  User: noqe2
  Date: 12/12/2022
  Time: 01:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="token" scope="request" type="java.lang.String"></jsp:useBean>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Reestablecer Contraseña</title>

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
                      <h5 class="card-title text-center pb-0 fs-4">Cambia tu contraseña</h5>
                      <p class="text-center small">Rellena los campos cuidadosamente</p>
                    </div>

                    <form method="post"
                          action="<%=request.getContextPath()%>/IniciarSesion?post=reestablecerPassword"
                          class="row g-3 needs-validation" novalidate>

                      <input name="token" value="<%=token%>" type="hidden">

                      <div class="col-12">
                        <label for="yourPassword" class="form-label">Nueva contraseña:</label>
                        <input type="password" name="nuevaPassword1" class="form-control"
                               id="yourPassword" required>
                        <div class="invalid-feedback">Confirma tu contraseña</div>
                      </div>

                      <div class="col-12">
                        <label for="yourPassword2" class="form-label">Confirmar tu nueva contraseña:</label>
                        <input type="password" name="nuevaPassword2" class="form-control"
                               id="yourPassword2" required>
                        <div class="invalid-feedback">Confirma tu contraseña</div>
                      </div>
                      <br>
                      <% if (session.getAttribute("msgError") != null) { %>
                      <p class="text-danger small mb-0"><%=session.getAttribute("msgError")%>
                      </p>
                      <% session.removeAttribute("msgError");
                      } %>
                      <div class="col-12">
                        <button class="btn btn-primary w-100" type="submit">Guardar contraseña
                        </button>
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
