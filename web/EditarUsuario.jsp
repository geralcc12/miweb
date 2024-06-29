<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <h1>Modificar Usuario</h1>
    
    <%-- Obtener el objeto usuario del atributo de solicitud --%>
    <% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
    
    <%-- Verificar si el usuario no es nulo --%>
    <% if (usuario != null) { %>
    
    <form action="EditarUsuario" method="post">
        id: <input type="text" name="id" value="<%= usuario.getIdUser() %>" readonly>
        Nombre: <input type="text" name="nombre" value="<%= usuario.getNombre() %>">
        Usuario: <input type="text" name="usuario" value="<%= usuario.getUsuario() %>">
        Contrasena: <input type="text" name="contrasena" value="<%= usuario.getPassword()%>">
        rol: <input type="text" name="rol" value="<%= usuario.getIdTipo().getIdTipo()%>">
        <!-- Otros campos según sea necesario -->
        <input type="submit" value="Actualizar">
    </form>
    
    <% } else { %>
        <p>Usuario no encontrado.</p>
    <% } %>
    
</body>
</html>
