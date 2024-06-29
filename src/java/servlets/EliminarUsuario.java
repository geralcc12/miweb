/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import controller.PropiedadJpaController;
import controller.UsuarioJpaController;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Propiedad;
import model.Usuario;

/**
 *
 * @author User
 */
@WebServlet(name = "EliminarUsuario", urlPatterns = {"/EliminarUsuario"})
public class EliminarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EliminarUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EliminarUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String idRecibido = request.getParameter("id");
    UsuarioJpaController usuControl = new UsuarioJpaController();
    Usuario usu = usuControl.findUsuario(Integer.valueOf(idRecibido));

    if (usu != null) {
        // Se verifica si el usuario tiene propiedades asociadas
        if (usu.getPropiedadList() != null && !usu.getPropiedadList().isEmpty()) {
            for (Propiedad propiedad : usu.getPropiedadList()) {
                propiedad.setIdPropiedad(null);
            }
        }

        try {
            usuControl.destroy(usu.getIdUser());
            response.sendRedirect(request.getContextPath() + "/SvUsuarios");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EliminarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        // Aquí puedes manejar la situación en la que el usuario no se encontró
        // Por ejemplo, redirigiendo a una página de error o mostrando un mensaje adecuado.
        response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
    }
}

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
