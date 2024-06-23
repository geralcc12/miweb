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
        HttpSession session = request.getSession();
        session.setAttribute("listaUsuarios", listaUsuarios);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombreUsuario =request.getParameter("nombreusu");
        String contra=request.getParameter("contrasena");
        String rol = request.getParameter("rol");
        Usuario usu = new Usuario( Integer.SIZE,nombreUsuario,"geral" , contra);
        UsuarioJpaController usujpa=new UsuarioJpaController();
        usujpa.create(usu);
 
        response.sendRedirect("index.jsp");
        
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}