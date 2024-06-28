package servlets;

import controller.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TipoUsuario;
import model.Usuario;

@WebServlet(name = "SvUsuarios", urlPatterns = {"/SvUsuarios"})
public class SvUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario>listaUsuarios=new ArrayList<Usuario>();
        UsuarioJpaController u1=new UsuarioJpaController();
        listaUsuarios=u1.findUsuarioEntities();
        HttpSession misession = request.getSession();
        misession.setAttribute("listaUsuarios", listaUsuarios);
        response.sendRedirect("verUsuarios.jsp");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario=request.getParameter("nombreusu");
        String nombre=request.getParameter("nombre");
        int rol=  Integer.parseInt(request.getParameter("rol"));
        String contrasena=request.getParameter("contrasena");
        
        Usuario usuarioNuevo = new Usuario(Integer.SIZE, nombre, nombreUsuario, contrasena);
        usuarioNuevo.setIdTipo(new TipoUsuario(rol));
        
        UsuarioJpaController usuControl = new UsuarioJpaController();
        
        usuControl.create(usuarioNuevo);
        
        
        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}