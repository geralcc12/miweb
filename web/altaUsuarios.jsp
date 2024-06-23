<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file ="componentes/header.jsp"%>
<%@include file ="componentes/bodyprimer.jsp"%>

<h1>Usuarios</h1>
<p>levantar <p>

<form class="user"action="SvUsuarios"method="POST">
    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombreusu"name="nombreusu"
                   placeholder="nombreUsuario">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="contrasena"name="contrasena"
                   placeholder="Contraseña">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id=" rol"name="rol"
                   placeholder=" Rol ">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id=" direccion"name="direccion"
                   placeholder="Direccion">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id=" fechanac"name="fechanac"
                   placeholder="FechaNac">
        </div>
        <!-- aca ira todo lo de horario y usuario -->
    </div>
    <button class ="btn btn-primary btn-user btn-block" type="submit">
        Crear Usuario
    </button>
    
</form>
</html>
<%@include file ="componentes/bodyfinal.jsp"%>
