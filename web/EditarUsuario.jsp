<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Modificar Usuario</title>
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Custom CSS for form container -->
    <style>
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            background-color: rgba(255, 255, 255, 0.9);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .titulo {
            font-family: 'Nunito', sans-serif;
            font-size: 36px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
            text-align: center;
        }
        .subtitulo {
            font-family: 'Nunito', sans-serif;
            font-size: 24px;
            color: #666;
            text-align: center;
        }
    </style>
</head>
<body class="bg-gradient-primary">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-xl-10 col-lg-12 col-md-9">
                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5 form-container">
                                    <div class="text-center">
                                        <h1 class="titulo">Modificar Usuario</h1>
                                        <h2 class="subtitulo">Actualiza la información del usuario</h2>
                                    </div>
                                    <% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
                                    <% if (usuario != null) { %>
                                    <form class="user" action="EditarUsuario" method="post">
                                        <div class="form-group">
                                            <label for="id">ID</label>
                                            <input type="text" class="form-control form-control-user" id="id" name="id" value="<%= usuario.getIdUser() %>" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombre">Nombre</label>
                                            <input type="text" class="form-control form-control-user" id="nombre" name="nombre" value="<%= usuario.getNombre() %>">
                                        </div>
                                        <div class="form-group">
                                            <label for="usuario">Usuario</label>
                                            <input type="text" class="form-control form-control-user" id="usuario" name="usuario" value="<%= usuario.getUsuario() %>">
                                        </div>
                                        <div class="form-group">
                                            <label for="contrasena">Contraseña</label>
                                            <input type="password" class="form-control form-control-user" id="contrasena" name="contrasena" value="<%= usuario.getPassword() %>">
                                        </div>
                                        <div class="form-group">
                                            <label for="rol">Rol</label>
                                            <input type="text" class="form-control form-control-user" id="rol" name="rol" value="<%= usuario.getIdTipo().getIdTipo() %>">
                                        </div>
                                        <button type="submit" class="btn btn-primary btn-user btn-block">Actualizar</button>
                                    </form>
                                    <% } else { %>
                                    <p>Usuario no encontrado.</p>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
</body>
</html>
