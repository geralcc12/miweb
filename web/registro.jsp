<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file ="componentes/header.jsp"%>
<%@include file ="componentes/bodyprimer.jsp"%>

<h1>holiiii</h1>
<p>ESO CINNNN <p>

<form class="user">
    <div class="form-group col">
        
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="Nombre"
                   placeholder="Nombre">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id=" Apellidos"
                   placeholder=" Apellidos ">
        </div>
        <!-- aca ira todo lo de horario y usuario -->
    </div>
     <a href="SvUsuarios" class="btn btn-primary btn-user btn-block">
        Crear Usuario
    </a>
    
</form>
</html>
<%@include file ="componentes/bodyfinal.jsp"%>