package servlets;

import controller.UsuarioJpaController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author USER
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Puedes dejar esto vacío o agregar lógica común para GET y POST si lo deseas
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implementa si necesitas manejar solicitudes GET
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String nombreUsuario = request.getParameter("Correo");
        String contrasena = request.getParameter("password");
        
        UsuarioJpaController usuControl = new UsuarioJpaController();
        
        boolean validacion = false;
        
        for (Usuario usuario : usuControl.findUsuarioEntities()) {
            if (usuario.getUsuario().equals(nombreUsuario) && usuario.getPassword().equals(contrasena)) {
                validacion = true;
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("usuario", nombreUsuario);
                response.sendRedirect("index.jsp");
                break;
            }
        }
        
        if (!validacion) {
            response.sendRedirect("loginerror.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
