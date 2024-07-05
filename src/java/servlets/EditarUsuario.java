package servlets;

import controller.UsuarioJpaController;
import controller.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TipoUsuario;
import model.Usuario;

@WebServlet(name = "EditarUsuario", urlPatterns = {"/EditarUsuario"})
public class EditarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // metodo que obtengo un id usuario y redirige a la pagina editar usuario
        
        String idRecibido = request.getParameter("id");

        UsuarioJpaController usuControl = new UsuarioJpaController();

        Usuario Usuario = usuControl.findUsuario(Integer.valueOf(idRecibido));

        request.setAttribute("usuario", Usuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("EditarUsuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // metodo
        
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        String rol = request.getParameter("rol");

        UsuarioJpaController usuControl1 = new UsuarioJpaController();
        Usuario usua = usuControl1.findUsuario(Integer.valueOf(id));
        
        usua.setNombre(nombre);
        usua.setPassword(contrasena);
        usua.setUsuario(usuario);
        usua.setIdTipo(new TipoUsuario(Integer.valueOf(rol)));
        try {
            usuControl1.edit(usua);
            // Redirigir internamente al servlet usando RequestDispatcher
            response.sendRedirect(request.getContextPath() + "/SvUsuarios");

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EditarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
